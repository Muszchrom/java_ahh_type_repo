from random import randint

with open(r"D:\programowanie\python\templateMatching\venv\file.txt", "w+") as f:
    for _ in range(0, 100):
        f.write(f"{randint(0, 10)} {('+', '-')[randint(0,1)]} {randint(0, 10)}\n")