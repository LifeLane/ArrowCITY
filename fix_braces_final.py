with open("app/src/main/java/com/example/viewmodel/GameViewModel.kt", "r") as f:
    lines = f.readlines()

depth = 0
for i, line in enumerate(lines):
    for char in line:
        if char == '{':
            depth += 1
        elif char == '}':
            depth -= 1
    if depth < 1 and i > 100:  # If we drop out of the class body
        print(f"Dropped out of class at line {i+1}: {line.strip()}")

print(f"Final depth: {depth}")
