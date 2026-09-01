package com.example.audio

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager

/**
 * High-precision haptic feedback service providing tactile micro-sensations
 * for arrow tapping, sliding, combo streaks, and level completions.
 */
class HapticFeedbackService(private val context: Context) {

    var isEnabled: Boolean = true

    private val vibrator: Vibrator? by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val vibratorManager = context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as? VibratorManager
            vibratorManager?.defaultVibrator
        } else {
            @Suppress("DEPRECATION")
            context.getSystemService(Context.VIBRATOR_SERVICE) as? Vibrator
        }
    }

    /**
     * Subtle micro-tick when the user touches an arrow or UI element
     */
    fun onArrowTap() {
        if (!isEnabled) return
        vibrateSingle(durationMs = 12, amplitude = 70)
    }

    /**
     * Gentle slide sensation when an arrow begins gliding along its path
     */
    fun onArrowSlideStart() {
        if (!isEnabled) return
        vibrateSingle(durationMs = 22, amplitude = 120)
    }

    /**
     * Distinct, satisfying tactile vibration when an arrow escapes the grid.
     * Intensifies rhythmically with higher combos.
     */
    fun onArrowCleared(combo: Int = 1) {
        if (!isEnabled) return
        val amplitude = (120 + (combo * 16)).coerceIn(120, 255)
        val duration = (25 + (combo * 4)).coerceIn(25, 55).toLong()

        if (combo >= 3) {
            // Dual ripple for combo flow
            vibrateWaveform(
                timings = longArrayOf(0, duration, 30, (duration * 0.8).toLong()),
                amplitudes = intArrayOf(0, amplitude, 0, (amplitude * 0.75).toInt())
            )
        } else {
            vibrateSingle(durationMs = duration, amplitude = amplitude)
        }
    }

    /**
     * Double soft error bump when hitting a blocked obstacle
     */
    fun onObstacleBlocked() {
        if (!isEnabled) return
        vibrateWaveform(
            timings = longArrayOf(0, 35, 40, 45),
            amplitudes = intArrayOf(0, 180, 0, 220)
        )
    }

    /**
     * Harmonic multi-stage vibration on level completion
     */
    fun onLevelComplete() {
        if (!isEnabled) return
        vibrateWaveform(
            timings = longArrayOf(0, 30, 50, 40, 50, 60),
            amplitudes = intArrayOf(0, 120, 0, 180, 0, 255)
        )
    }

    /**
     * Power-up execution tactile feedback
     */
    fun onPowerUpUsed() {
        if (!isEnabled) return
        vibrateWaveform(
            timings = longArrayOf(0, 20, 30, 35),
            amplitudes = intArrayOf(0, 100, 0, 200)
        )
    }

    /**
     * Gentle mindful pulse for Zen 4-4-4-4 breathing phase change
     */
    fun onZenBreathPhase() {
        if (!isEnabled) return
        vibrateSingle(durationMs = 28, amplitude = 90)
    }

    private fun vibrateSingle(durationMs: Long, amplitude: Int) {
        try {
            val vib = vibrator ?: return
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val safeAmp = amplitude.coerceIn(1, 255)
                val effect = VibrationEffect.createOneShot(durationMs, safeAmp)
                vib.vibrate(effect)
            } else {
                @Suppress("DEPRECATION")
                vib.vibrate(durationMs)
            }
        } catch (e: Exception) {
            // Graceful fallback on devices without vibrator hardware
        }
    }

    private fun vibrateWaveform(timings: LongArray, amplitudes: IntArray) {
        try {
            val vib = vibrator ?: return
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val safeAmps = amplitudes.map { it.coerceIn(0, 255) }.toIntArray()
                val effect = VibrationEffect.createWaveform(timings, safeAmps, -1)
                vib.vibrate(effect)
            } else {
                @Suppress("DEPRECATION")
                vib.vibrate(timings, -1)
            }
        } catch (e: Exception) {
            // Graceful fallback
        }
    }
}
