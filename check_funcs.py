with open("app/src/main/java/com/example/viewmodel/GameViewModel.kt", "r") as f:
    lines = f.readlines()

depth = 0
in_class = False
for i, line in enumerate(lines):
    if "class GameViewModel" in line:
        in_class = True
    
    if in_class:
        for char in line:
            if char == '{':
                depth += 1
            elif char == '}':
                depth -= 1
        
        if "fun " in line and depth > 2:
            print(f"Function declared inside another block at line {i+1}, depth {depth}: {line.strip()}")

