import re

with open("app/src/main/java/com/example/engine/ProceduralGenerator.kt", "r") as f:
    content = f.read()

bad_level_data = """        return LevelData(
            levelNumber = levelNumber,
            title = levelTitle,
            subtitle = bannerText,
            gridWidth = width,
            gridHeight = height,
            arrows = arrows,
            maxDrops = city.dropLimitRange.last,
            backgroundId = city.backgroundId,
            gridStyleId = city.gridStyleId,
            ambientSoundId = city.ambientSoundId
        )"""

good_level_data = """        return LevelData(
            levelNumber = levelNumber,
            title = levelTitle,
            bannerText = bannerText,
            gridWidth = width,
            gridHeight = height,
            arrows = arrows,
            maxDrops = 3
        )"""

content = content.replace(bad_level_data, good_level_data)

with open("app/src/main/java/com/example/engine/ProceduralGenerator.kt", "w") as f:
    f.write(content)

