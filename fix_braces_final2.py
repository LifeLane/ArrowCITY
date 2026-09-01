with open("app/src/main/java/com/example/viewmodel/GameViewModel.kt", "r") as f:
    lines = f.readlines()

depth = 0
class_start = -1
for i, line in enumerate(lines):
    if "class GameViewModel" in line:
        class_start = i
        
    if class_start != -1 and i >= class_start:
        for char in line:
            if char == '{':
                depth += 1
            elif char == '}':
                depth -= 1
        if depth == 0 and i > class_start:
            print(f"Class ended prematurely at line {i+1}: {line.strip()}")
            # Break so we see the FIRST premature end
            break

print(f"Final depth: {depth}")
