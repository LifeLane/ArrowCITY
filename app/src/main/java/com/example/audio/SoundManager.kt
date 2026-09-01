package com.example.audio

import android.content.Context
import android.media.AudioAttributes
import android.media.AudioFormat
import android.media.AudioTrack
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.Random
import kotlin.math.PI
import kotlin.math.exp
import kotlin.math.sin

/**
 * Procedural audio synthesizer for calming ASMR sound feedback and haptic vibration.
 * Generates relaxing organic wooden clicks, soft whooshes, pure chimes, and harmonic chords
 * without requiring large external audio assets.
 */
class SoundManager(private val context: Context) {
    private val scope = CoroutineScope(Dispatchers.Default)
    private val random = Random()

    val haptics = HapticFeedbackService(context)

    var isMovementSoundEnabled: Boolean = true
    var isAmbientNatureEnabled: Boolean = true
    var isHapticEnabled: Boolean
        get() = haptics.isEnabled
        set(value) {
            haptics.isEnabled = value
        }

    // Legacy property compatibility
    var isSoundEnabled: Boolean
        get() = isMovementSoundEnabled
        set(value) {
            isMovementSoundEnabled = value
        }

    private var ambientJob: Job? = null
    private var ambientAudioTrack: AudioTrack? = null

    /**
     * Starts the serene ambient nature background sound loop (gentle breeze, harmonic zen drone, soft stream rustle).
     */
    fun startAmbientNature() {
        if (!isAmbientNatureEnabled) return
        if (ambientJob?.isActive == true) return

        ambientJob = scope.launch {
            try {
                val sampleRate = 22050
                val loopDurationSec = 4.0
                val numSamples = (loopDurationSec * sampleRate).toInt()
                val audioData = ShortArray(numSamples)

                // Synthesize seamless loop of gentle forest wind, filtered water ripples, and warm singing bowl drone (108Hz & 216Hz)
                for (i in 0 until numSamples) {
                    val t = i.toDouble() / sampleRate
                    val cyclePhase = (i.toDouble() / numSamples) * 2.0 * PI

                    // 1. Deep 108Hz / 216Hz warm meditative drone
                    val drone1 = sin(2.0 * PI * 108.0 * t) * 0.08
                    val drone2 = sin(2.0 * PI * 216.0 * t + 0.5) * 0.04
                    val drone3 = sin(2.0 * PI * 324.0 * t + 1.0) * 0.02

                    // 2. Slow breathing wind swell (modulating with cyclePhase for seamless loop)
                    val windLfo = (sin(cyclePhase) + 1.0) * 0.5
                    val windCarrier = sin(2.0 * PI * 180.0 * t + sin(2.0 * PI * 0.5 * t) * 12.0) * 0.035 * (0.6 + 0.4 * windLfo)

                    // 3. Gentle pink-noise stream rustle
                    val noise = (random.nextDouble() * 2.0 - 1.0) * 0.015

                    val mix = (drone1 + drone2 + drone3 + windCarrier + noise).coerceIn(-1.0, 1.0)
                    audioData[i] = (mix * Short.MAX_VALUE).toInt().toShort()
                }

                val bufferSize = numSamples * 2
                val track = AudioTrack.Builder()
                    .setAudioAttributes(
                        AudioAttributes.Builder()
                            .setUsage(AudioAttributes.USAGE_GAME)
                            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                            .build()
                    )
                    .setAudioFormat(
                        AudioFormat.Builder()
                            .setEncoding(AudioFormat.ENCODING_PCM_16BIT)
                            .setSampleRate(sampleRate)
                            .setChannelMask(AudioFormat.CHANNEL_OUT_MONO)
                            .build()
                    )
                    .setBufferSizeInBytes(bufferSize)
                    .setTransferMode(AudioTrack.MODE_STATIC)
                    .build()

                track.write(audioData, 0, numSamples)
                track.setLoopPoints(0, numSamples, -1) // Infinite seamless loop
                track.play()
                ambientAudioTrack = track
            } catch (e: Exception) {
                // Audio track fallback
            }
        }
    }

    fun stopAmbientNature() {
        ambientJob?.cancel()
        ambientJob = null
        try {
            ambientAudioTrack?.stop()
            ambientAudioTrack?.release()
        } catch (e: Exception) {
            // Ignore track release errors
        }
        ambientAudioTrack = null
    }

    fun setAmbientNature(enabled: Boolean) {
        isAmbientNatureEnabled = enabled
        if (enabled) {
            startAmbientNature()
        } else {
            stopAmbientNature()
        }
    }

    private fun playTone(
        sampleRate: Int = 44100,
        durationSeconds: Double = 0.15,
        generator: (Double, Double) -> Double
    ) {
        if (!isMovementSoundEnabled) return

        scope.launch {
            try {
                val numSamples = (durationSeconds * sampleRate).toInt()
                val audioData = ShortArray(numSamples)

                for (i in 0 until numSamples) {
                    val t = i.toDouble() / sampleRate
                    val progress = i.toDouble() / numSamples
                    val sample = generator(t, progress).coerceIn(-1.0, 1.0)
                    audioData[i] = (sample * Short.MAX_VALUE).toInt().toShort()
                }

                val bufferSize = numSamples * 2
                val audioTrack = AudioTrack.Builder()
                    .setAudioAttributes(
                        AudioAttributes.Builder()
                            .setUsage(AudioAttributes.USAGE_GAME)
                            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                            .build()
                    )
                    .setAudioFormat(
                        AudioFormat.Builder()
                            .setEncoding(AudioFormat.ENCODING_PCM_16BIT)
                            .setSampleRate(sampleRate)
                            .setChannelMask(AudioFormat.CHANNEL_OUT_MONO)
                            .build()
                    )
                    .setBufferSizeInBytes(bufferSize)
                    .setTransferMode(AudioTrack.MODE_STATIC)
                    .build()

                audioTrack.write(audioData, 0, numSamples)
                audioTrack.play()
                
                // Release after playback finished
                kotlinx.coroutines.delay((durationSeconds * 1000 + 100).toLong())
                audioTrack.release()
            } catch (e: Exception) {
                // Ignore audio init fallback gracefully
            }
        }
    }

    /** Soft wooden resonant click when touching an arrow */
    fun playTap() {
        haptics.onArrowTap()
        playTone(durationSeconds = 0.05) { t, progress ->
            val env = exp(-progress * 25.0)
            val freq = 750.0 - progress * 200.0
            sin(2.0 * PI * freq * t) * env * 0.4
        }
    }

    /** Satisfying gentle air whoosh with subtle rising pitch when arrow clears, scaling with combo */
    fun playWhoosh(combo: Int = 1) {
        haptics.onArrowCleared(combo)
        
        // Pentatonic scale frequencies for combo multiplier (1 to 8)
        val scaleFrequencies = doubleArrayOf(
            523.25, // C5
            587.33, // D5
            659.25, // E5
            783.99, // G5
            880.00, // A5
            1046.50, // C6
            1174.66, // D6
            1318.51  // E6
        )
        val basePitch = scaleFrequencies[(combo - 1).coerceIn(0, scaleFrequencies.size - 1)]

        playTone(durationSeconds = 0.32) { t, progress ->
            val env = sin(progress * PI) * exp(-progress * 2.2)
            val freq = basePitch + (progress * 220.0)
            val sinePart = sin(2.0 * PI * freq * t) * 0.42
            val harmonic = sin(2.0 * PI * (freq * 2.0) * t) * 0.15
            val noise = (random.nextDouble() * 2.0 - 1.0) * 0.18
            (sinePart + harmonic + noise) * env * 0.75
        }
    }

    /** Power-up: Zen Snip scissor cut */
    fun playSnip() {
        haptics.onPowerUpUsed()
        playTone(durationSeconds = 0.14) { t, progress ->
            val env = exp(-progress * 28.0)
            val noise = (random.nextDouble() * 2.0 - 1.0) * 0.6
            val highChirp = sin(2.0 * PI * (1800.0 - progress * 800.0) * t) * 0.4
            (noise + highChirp) * env * 0.8
        }
    }

    /** Power-up: Ghost Phase ethereal chime */
    fun playGhostPhase() {
        haptics.onPowerUpUsed()
        playTone(durationSeconds = 0.5) { t, progress ->
            val env = exp(-progress * 4.0)
            val f1 = sin(2.0 * PI * 880.0 * t + sin(2.0 * PI * 6.0 * t) * 2.0) * 0.4
            val f2 = sin(2.0 * PI * 1320.0 * t) * 0.3
            (f1 + f2) * env * 0.7
        }
    }

    /** Power-up: Singing bowl harmonic magnet pulse */
    fun playMagnetPulse() {
        haptics.onPowerUpUsed()
        playTone(durationSeconds = 0.7) { t, progress ->
            val env = exp(-progress * 3.0)
            val base = sin(2.0 * PI * 440.0 * t) * 0.4
            val octave = sin(2.0 * PI * 880.0 * t) * 0.25
            val shimmer = sin(2.0 * PI * 1760.0 * t) * 0.15
            (base + octave + shimmer) * env * 0.85
        }
    }

    /** Power-up: Flow Recall rewind sound */
    fun playRecall() {
        haptics.onPowerUpUsed()
        playTone(durationSeconds = 0.35) { t, progress ->
            val env = exp(-progress * 5.0)
            // Descending sweep with reverse chirp
            val freq = 1200.0 - progress * 700.0
            val s = sin(2.0 * PI * freq * t) * 0.5
            s * env * 0.75
        }
    }

    /** Soft blocked thud when player taps an obstructed arrow */
    fun playBlocked() {
        haptics.onObstacleBlocked()
        playTone(durationSeconds = 0.18) { t, progress ->
            val env = exp(-progress * 18.0)
            val lowFreq = 160.0 - progress * 40.0
            (sin(2.0 * PI * lowFreq * t) * 0.6 + sin(2.0 * PI * (lowFreq * 1.5) * t) * 0.3) * env * 0.6
        }
    }

    /** Gentle crystalline chime for Guidance / Hint */
    fun playHint() {
        haptics.onArrowTap()
        playTone(durationSeconds = 0.45) { t, progress ->
            val env = exp(-progress * 6.0)
            val f1 = sin(2.0 * PI * 1046.5 * t) * 0.4 // C6
            val f2 = sin(2.0 * PI * 1318.5 * t) * 0.3 // E6
            val f3 = sin(2.0 * PI * 1567.98 * t) * 0.2 // G6
            (f1 + f2 + f3) * env * 0.65
        }
    }

    /** Pentatonic calming fanfare chord when level is cleared */
    fun playLevelComplete() {
        haptics.onLevelComplete()
        playTone(durationSeconds = 0.85) { t, progress ->
            val env = exp(-progress * 3.5)
            // Pentatonic scale arpeggio notes (C5, E5, G5, A5, C6)
            val note1 = sin(2.0 * PI * 523.25 * t) * 0.25
            val note2 = sin(2.0 * PI * 659.25 * t) * 0.25
            val note3 = sin(2.0 * PI * 783.99 * t) * 0.25
            val note4 = sin(2.0 * PI * 880.00 * t) * 0.2
            val note5 = sin(2.0 * PI * 1046.50 * t) * 0.25
            (note1 + note2 + note3 + note4 + note5) * env * 0.8
        }
    }

    /** Soft gentle drop sound when a life / drop is lost */
    fun playDropLost() {
        haptics.onArrowTap()
        playTone(durationSeconds = 0.25) { t, progress ->
            val env = exp(-progress * 12.0)
            val freq = 450.0 - progress * 150.0
            sin(2.0 * PI * freq * t) * env * 0.45
        }
    }

    enum class HapticType { LIGHT, MEDIUM, SUCCESS, ERROR }

    fun triggerHaptic(type: HapticType) {
        if (!isHapticEnabled) return
        when (type) {
            HapticType.LIGHT -> haptics.onArrowTap()
            HapticType.MEDIUM -> haptics.onArrowSlideStart()
            HapticType.SUCCESS -> haptics.onLevelComplete()
            HapticType.ERROR -> haptics.onObstacleBlocked()
        }
    }
}
