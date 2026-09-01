import re

with open("app/src/main/java/com/example/model/CityModels.kt", "r") as f:
    city_content = f.read()

# Add illusion properties to CityConfig
if "illusionPreference: Float = 0f" not in city_content:
    city_content = city_content.replace(
        "val longSweepPreference: Float = 0f  // 0.0 to 1.0\n)",
        "val longSweepPreference: Float = 0f,  // 0.0 to 1.0\n    val illusionPreference: Float = 0f, // 0.0 to 1.0 for visual illusions\n    val densityPreference: Float = 0f // 0.0 to 1.0\n)"
    )

# Update City 1 (Zendai) and City 2 (Sandara) to have high illusion preference
city_content = city_content.replace(
    "symmetryPreference = 0.2f\n        ),",
    "symmetryPreference = 0.2f,\n            illusionPreference = 0.5f,\n            densityPreference = 0.4f\n        ),"
)

city_content = city_content.replace(
    "longSweepPreference = 0.7f\n        ),",
    "longSweepPreference = 0.7f,\n            illusionPreference = 0.8f,\n            densityPreference = 0.6f\n        ),"
)

with open("app/src/main/java/com/example/model/CityModels.kt", "w") as f:
    f.write(city_content)

