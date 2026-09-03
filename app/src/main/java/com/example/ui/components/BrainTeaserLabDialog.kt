package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.Casino
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material.icons.outlined.Psychology
import androidx.compose.material.icons.outlined.Tune
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.model.BrainTeaserDifficulty
import com.example.model.GameTheme
import kotlin.random.Random

@Composable
fun BrainTeaserLabDialog(
    theme: GameTheme,
    onGenerateAndPlay: (BrainTeaserDifficulty, Long) -> Unit,
    onDismiss: () -> Unit
) {
    var selectedTier by remember { mutableStateOf(BrainTeaserDifficulty.STRATEGIST) }
    var currentSeed by remember { mutableLongStateOf(System.currentTimeMillis() % 100000L) }
    val scrollState = rememberScrollState()

    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .testTag("brain_teaser_lab_dialog"),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = theme.cardBg),
            elevation = CardDefaults.cardElevation(defaultElevation = 14.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.5.dp, theme.bannerBorder, RoundedCornerShape(24.dp))
                    .padding(20.dp)
                    .verticalScroll(scrollState),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Psychology,
                            contentDescription = null,
                            tint = theme.headerGold,
                            modifier = Modifier.size(28.dp)
                        )
                        Column {
                            Text(
                                text = "Brain Teaser Lab",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = theme.textPrimary
                            )
                            Text(
                                text = "Infinite Procedural Deduction Engine",
                                fontSize = 11.sp,
                                color = theme.textSecondary
                            )
                        }
                    }

                    IconButton(
                        onClick = onDismiss,
                        modifier = Modifier.size(32.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close",
                            tint = theme.textSecondary
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    text = "Select your target cognitive complexity tier. Every puzzle generated is mathematically analyzed and guaranteed 100% solvable.",
                    fontSize = 12.sp,
                    color = theme.textSecondary,
                    lineHeight = 16.sp
                )

                Spacer(modifier = Modifier.height(14.dp))

                // Difficulty Selector Cards
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    BrainTeaserDifficulty.entries.forEach { diff ->
                        val isSelected = selectedTier == diff
                        Surface(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(14.dp))
                                .clickable { selectedTier = diff }
                                .testTag("difficulty_tier_${diff.name.lowercase()}"),
                            shape = RoundedCornerShape(14.dp),
                            color = if (isSelected) theme.headerGold.copy(alpha = 0.18f) else theme.boardBackground.copy(alpha = 0.5f),
                            border = androidx.compose.foundation.BorderStroke(
                                width = if (isSelected) 1.5.dp else 1.dp,
                                color = if (isSelected) theme.headerGold else theme.bannerBorder.copy(alpha = 0.3f)
                            )
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 12.dp, vertical = 10.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                                ) {
                                    Text(
                                        text = diff.icon,
                                        fontSize = 22.sp
                                    )
                                    Column {
                                        Text(
                                            text = diff.title,
                                            fontSize = 14.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = if (isSelected) theme.headerGold else theme.textPrimary
                                        )
                                        Text(
                                            text = diff.subtitle,
                                            fontSize = 11.sp,
                                            color = theme.textSecondary
                                        )
                                    }
                                }

                                Surface(
                                    shape = RoundedCornerShape(8.dp),
                                    color = if (isSelected) theme.headerGold else theme.cardBg
                                ) {
                                    Text(
                                        text = "${diff.targetArrows} Arrows",
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = if (isSelected) Color.Black else theme.textSecondary,
                                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                                    )
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Seed Controller
                Surface(
                    shape = RoundedCornerShape(14.dp),
                    color = theme.boardBackground.copy(alpha = 0.6f),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 14.dp, vertical = 10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "Puzzle Seed",
                                fontSize = 11.sp,
                                color = theme.textSecondary
                            )
                            Text(
                                text = "#$currentSeed",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = theme.textPrimary
                            )
                        }

                        OutlinedButton(
                            onClick = {
                                currentSeed = (Random.nextLong(10000, 999999))
                            },
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier.testTag("roll_seed_button")
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Casino,
                                contentDescription = "Roll Seed",
                                modifier = Modifier.size(16.dp),
                                tint = theme.headerGold
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Randomize",
                                fontSize = 12.sp,
                                color = theme.headerGold,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Launch CTA Button
                Button(
                    onClick = {
                        onGenerateAndPlay(selectedTier, currentSeed)
                        onDismiss()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .testTag("launch_brain_teaser_button"),
                    colors = ButtonDefaults.buttonColors(containerColor = theme.headerGold),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.PlayArrow,
                        contentDescription = null,
                        tint = Color.Black,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Generate & Launch Puzzle",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.Black
                    )
                }
            }
        }
    }
}
