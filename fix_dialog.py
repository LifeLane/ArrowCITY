with open("app/src/main/java/com/example/ui/components/GameDialogs.kt", "r") as f:
    content = f.read()

# Replace theme.surface with theme.boardBackground
content = content.replace("theme.surface", "theme.boardBackground")
# Replace theme.primary with theme.arrowStroke or theme.headerGold
content = content.replace("theme.primary", "theme.headerGold")

with open("app/src/main/java/com/example/ui/components/GameDialogs.kt", "w") as f:
    f.write(content)
