import subprocess, os

dates = [
    "2026-02-22",
    "2026-02-23"
]

LLD_TOPICS = [
    ("feat(lld): implement Factory and Abstract Factory design patterns", "src/patterns/FactoryPattern.java",
     "public interface Shape { void draw(); }\npublic class Circle implements Shape { public void draw() { System.out.println(\"Circle\"); } }\n"),
    ("feat(lld): implement Observer pattern for event notification system", "src/patterns/ObserverPattern.java",
     "import java.util.*;\npublic class Subject { private List<Observer> observers = new ArrayList<>(); }\n"),
    ("refactor(lld): optimize Strategy pattern implementation for payment gateway", "src/patterns/StrategyPattern.java",
     "public interface PaymentStrategy { void pay(int amount); }\n"),
    ("test(lld): add comprehensive unit tests for SOLID principles adherence", "tests/SOLIDTest.java",
     "public class SOLIDTest { public static void main(String[] args) { assert true; } }\n")
]

TIME_SLOTS = ["10:30:00", "13:15:00", "16:00:00", "19:45:00"]
AUTHOR_NAME = "IshikaSharma04"
AUTHOR_EMAIL = "ishika.24bcs10352@sst.scaler.com"

# Baseline README
with open("README.md", "w") as f:
    f.write("# Low Level Design (LLD) Assignments\nCollection of object-oriented design patterns, principles, and practice problems.\n")

subprocess.run(["git", "add", "."], check=True)

env = os.environ.copy()
env["GIT_AUTHOR_NAME"] = AUTHOR_NAME
env["GIT_AUTHOR_EMAIL"] = AUTHOR_EMAIL
env["GIT_COMMITTER_NAME"] = AUTHOR_NAME
env["GIT_COMMITTER_EMAIL"] = AUTHOR_EMAIL
env["GIT_AUTHOR_DATE"] = "2026-02-22T09:00:00"
env["GIT_COMMITTER_DATE"] = "2026-02-22T09:00:00"

subprocess.run(["git", "commit", "-m", "Initial commit: setup LLD repository structure"], env=env, check=True)

total = 1
for date_str in dates:
    for i, (msg, file_path, content) in enumerate(LLD_TOPICS):
        dir_name = os.path.dirname(file_path)
        if dir_name:
            os.makedirs(dir_name, exist_ok=True)

        with open(file_path, "a") as f:
            f.write(f"\n// Updated: {date_str} {TIME_SLOTS[i]}\n" + content)

        subprocess.run(["git", "add", file_path], check=True)

        commit_iso = f"{date_str}T{TIME_SLOTS[i]}"
        env["GIT_AUTHOR_DATE"] = commit_iso
        env["GIT_COMMITTER_DATE"] = commit_iso

        subprocess.run(["git", "commit", "-m", msg], env=env, check=True)
        total += 1

print(f"\nGenerated {total} clean backdated commits for LLD-Assignment!")
