import re

with open("app/src/main/java/com/example/ui/screens/GameScreen.kt", "r") as f:
    content = f.read()

import_beta_dialog = "import com.example.ui.components.BetaCompleteDialog"
if import_beta_dialog not in content:
    content = content.replace("import com.example.ui.components.LevelSelectDialog", "import com.example.ui.components.LevelSelectDialog\nimport com.example.ui.components.BetaCompleteDialog")

beta_dialog_call = """
    if (uiState.isBetaCompletedOpen) {
        BetaCompleteDialog(
            uiState = uiState,
            onDismiss = { viewModel.closeBetaCompleted() }
        )
    }
"""

if "uiState.isBetaCompletedOpen" not in content:
    content = content.replace("if (uiState.isLevelSelectOpen) {", beta_dialog_call + "\n    if (uiState.isLevelSelectOpen) {")

with open("app/src/main/java/com/example/ui/screens/GameScreen.kt", "w") as f:
    f.write(content)

