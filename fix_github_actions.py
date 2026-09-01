import os

workflows_dir = ".github/workflows"

for filename in os.listdir(workflows_dir):
    if filename.endswith(".yml") or filename.endswith(".yaml"):
        filepath = os.path.join(workflows_dir, filename)
        with open(filepath, "r") as f:
            content = f.read()
        
        # We need to insert a step to make gradlew executable if it's not already there
        if "chmod +x gradlew" not in content and "./gradlew" in content:
            # We can insert it right before the first occurrence of "./gradlew"
            # But the safer way is to just do it right after setup-gradle or setup-java.
            # Let's just find the first step that runs ./gradlew and add the chmod step before it.
            
            # Simple string replacement for common tasks
            content = content.replace(
                "      - name: Build Debug APK\n        run: ./gradlew assembleDebug",
                "      - name: Make gradlew executable\n        run: chmod +x gradlew\n      - name: Build Debug APK\n        run: ./gradlew assembleDebug"
            )
            
            content = content.replace(
                "      - name: Run Unit & JVM Tests\n        run: ./gradlew testDebugUnitTest --continue",
                "      - name: Make gradlew executable\n        run: chmod +x gradlew\n      - name: Run Unit & JVM Tests\n        run: ./gradlew testDebugUnitTest --continue"
            )
            
            content = content.replace(
                "      - name: Build Release Bundle & APK\n        run: ./gradlew bundleRelease assembleRelease",
                "      - name: Make gradlew executable\n        run: chmod +x gradlew\n      - name: Build Release Bundle & APK\n        run: ./gradlew bundleRelease assembleRelease"
            )
            
            with open(filepath, "w") as f:
                f.write(content)
                print(f"Updated {filename}")
