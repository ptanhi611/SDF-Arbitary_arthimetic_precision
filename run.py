import os
import sys

class MyInfArith:
    def __init__(self, mode):
        self.mode = mode
        self.project_dir = os.path.dirname(os.path.abspath(__file__))
        self.jar_file = f"{self.project_dir}/target/aarithmetic.jar"
        self.java_file = f"{self.project_dir}/MyInfArith/MyInfArith.java"
        self.class_name = "MyInfArith.MyInfArith"

    def compile(self):
        classpath = f".:{self.jar_file}"
        cmd = f"javac -cp \"{classpath}\" \"{self.java_file}\""
        os.system(cmd)

    def run(self, operation, x, y):
        classpath = f".:{self.jar_file}"
        cmd = f"java -cp \"{classpath}\" {self.class_name} {self.mode} {operation} {x} {y}"
        os.system(cmd)

if __name__ == "__main__":
    if len(sys.argv) != 5:
        print("Usage: python run.py <int/float> <add/sub/mul/div> <num1> <num2>")
        sys.exit(1)

    mode = sys.argv[1]
    op = sys.argv[2]
    num1 = sys.argv[3]
    num2 = sys.argv[4]

    runner = MyInfArith(mode)
    runner.compile()
    runner.run(op, num1, num2)
