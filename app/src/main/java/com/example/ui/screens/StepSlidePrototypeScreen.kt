package com.example.ui.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.engine.experimental.StepBoardState
import com.example.model.ArrowItem
import com.example.model.Direction
import com.example.model.GameTheme
import com.example.viewmodel.StepSlidePrototypeViewModel
import kotlinx.coroutines.launch
import kotlin.math.min

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StepSlidePrototypeScreen(
    viewModel: StepSlidePrototypeViewModel,
    theme: GameTheme,
    onBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = theme.background,
        topBar = {
            TopAppBar(
                title = { Text("EXPERIMENTAL", color = theme.textPrimary, fontWeight = FontWeight.Black) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = theme.textPrimary)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = theme.background)
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text("Moves: ${uiState.movesCount}", color = theme.textPrimary, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Text("Blocked: ${uiState.blockedTapCount}", color = Color(0xFFEF4444), fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            Box(
                modifier = Modifier
                    .aspectRatio(1f)
                    .fillMaxWidth()
                    .background(theme.boardBackground, RoundedCornerShape(16.dp))
                    .padding(16.dp)
            ) {
                StepPrototypeBoard(
                    state = uiState.boardState,
                    theme = theme,
                    shakeArrowId = uiState.showShakeArrowId,
                    onArrowTapped = { id -> viewModel.onArrowTapped(id) },
                    onShakeComplete = { viewModel.clearShake() }
                )
                
                if (uiState.isComplete) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Black.copy(alpha = 0.5f), RoundedCornerShape(16.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text("Prototype Complete", color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Black)
                            Spacer(modifier = Modifier.height(16.dp))
                            Button(onClick = { viewModel.restart() }) {
                                Text("Restart")
                            }
                        }
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(32.dp))
            
            Text("Select Prototype", color = theme.textSecondary, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                listOf("A", "B", "C", "D").forEach { proto ->
                    Button(
                        onClick = { viewModel.selectPrototype(proto) },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (uiState.currentPrototype == proto) theme.dropActiveColor else theme.bannerBg
                        )
                    ) {
                        Text(proto, color = if (uiState.currentPrototype == proto) Color.White else theme.textPrimary)
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { viewModel.restart() }) {
                Text("Restart Level")
            }
        }
    }
}

@Composable
fun StepPrototypeBoard(
    state: StepBoardState,
    theme: GameTheme,
    shakeArrowId: Int?,
    onArrowTapped: (Int) -> Unit,
    onShakeComplete: () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    
    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val boardSize = 5
        val cellSize = maxWidth / boardSize
        val cellPx = with(androidx.compose.ui.platform.LocalDensity.current) { cellSize.toPx() }
        
        Canvas(modifier = Modifier.fillMaxSize()) {
            for (i in 0..boardSize) {
                val pos = i * cellPx
                drawLine(theme.bannerBorder, Offset(0f, pos), Offset(size.width, pos), 2f)
                drawLine(theme.bannerBorder, Offset(pos, 0f), Offset(pos, size.height), 2f)
            }
        }
        
        state.arrows.values.forEach { arrow ->
            // Determine top-left position of the bounding box.
            val minX = min(arrow.tail.x, arrow.head.x)
            val minY = min(arrow.tail.y, arrow.head.y)
            
            val offsetX = remember { Animatable(minX.toFloat()) }
            val offsetY = remember { Animatable(minY.toFloat()) }
            val shakeAnim = remember { Animatable(0f) }
            
            LaunchedEffect(minX, minY) {
                launch {
                    offsetX.animateTo(minX.toFloat(), tween(200, easing = FastOutSlowInEasing))
                }
                launch {
                    offsetY.animateTo(minY.toFloat(), tween(200, easing = FastOutSlowInEasing))
                }
            }
            
            LaunchedEffect(shakeArrowId) {
                if (shakeArrowId == arrow.id) {
                    val isHorizontal = arrow.headDirection == Direction.RIGHT || arrow.headDirection == Direction.LEFT
                    if (isHorizontal) {
                        shakeAnim.animateTo(10f, tween(50))
                        shakeAnim.animateTo(-10f, tween(50))
                        shakeAnim.animateTo(10f, tween(50))
                        shakeAnim.animateTo(0f, tween(50))
                    } else {
                        shakeAnim.animateTo(10f, tween(50))
                        shakeAnim.animateTo(-10f, tween(50))
                        shakeAnim.animateTo(10f, tween(50))
                        shakeAnim.animateTo(0f, tween(50))
                    }
                    onShakeComplete()
                }
            }
            
            val isHorizontal = arrow.headDirection == Direction.RIGHT || arrow.headDirection == Direction.LEFT
            val width = if (isHorizontal) cellSize * 2 else cellSize
            val height = if (isHorizontal) cellSize else cellSize * 2
            
            val shakeX = if (isHorizontal) shakeAnim.value.dp else 0.dp
            val shakeY = if (!isHorizontal) shakeAnim.value.dp else 0.dp
            
            Box(
                modifier = Modifier
                    .offset(
                        x = (offsetX.value * cellSize.value).dp + shakeX,
                        y = (offsetY.value * cellSize.value).dp + shakeY
                    )
                    .size(width, height)
                    .clickable { onArrowTapped(arrow.id) }
            ) {
                Canvas(modifier = Modifier.fillMaxSize()) {
                    val rectSize = Size(size.width, size.height)
                    drawRoundRect(
                        color = Color(0xFF3B82F6),
                        size = rectSize,
                        cornerRadius = CornerRadius(16f, 16f)
                    )
                    
                    val path = Path()
                    // Draw a simple arrow head inside
                    val margin = 16f
                    when (arrow.headDirection) {
                        Direction.RIGHT -> {
                            path.moveTo(size.width - margin, size.height / 2)
                            path.lineTo(size.width / 2, margin)
                            path.lineTo(size.width / 2, size.height - margin)
                        }
                        Direction.LEFT -> {
                            path.moveTo(margin, size.height / 2)
                            path.lineTo(size.width / 2, margin)
                            path.lineTo(size.width / 2, size.height - margin)
                        }
                        Direction.DOWN -> {
                            path.moveTo(size.width / 2, size.height - margin)
                            path.lineTo(margin, size.height / 2)
                            path.lineTo(size.width - margin, size.height / 2)
                        }
                        Direction.UP -> {
                            path.moveTo(size.width / 2, margin)
                            path.lineTo(margin, size.height / 2)
                            path.lineTo(size.width - margin, size.height / 2)
                        }
                    }
                    path.close()
                    drawPath(path, Color.White)
                }
            }
        }
    }
}
