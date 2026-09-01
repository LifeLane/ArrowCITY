import os

workflows_dir = ".github/workflows"

keystore_step = """      - name: Generate debug keystore
        run: |
          if [ ! -f "debug.keystore" ]; then
            keytool -genkey -v -keystore debug.keystore -storepass android -alias androiddebugkey -keypass android -keyalg RSA -keysize 2048 -validity 10000 -dname "CN=Android Debug,O=Android,C=US"
          fi
"""

for filename in os.listdir(workflows_dir):
    if filename.endswith(".yml") or filename.endswith(".yaml"):
        filepath = os.path.join(workflows_dir, filename)
        with open(filepath, "r") as f:
            content = f.read()
        
        if "keytool -genkey" not in content:
            # Insert after "chmod +x gradlew"
            content = content.replace(
                "      - name: Make gradlew executable\n        run: chmod +x gradlew",
                "      - name: Make gradlew executable\n        run: chmod +x gradlew\n" + keystore_step
            )
            with open(filepath, "w") as f:
                f.write(content)
                print(f"Updated {filename}")
