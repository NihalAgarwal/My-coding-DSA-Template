import turtle
import pyperclip as pc


class TreeNode:
    def __init__(self, val, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

    def __repr__(self):
        return "TreeNode({})".format(self.val)


class TreeBuild:
    """ Display how our tree looks like. """

    def __init__(self):
        repeat = "y"
        while repeat == "y" or repeat == "Y":
            ar = input("Enter the tree  info in form '[...]': \n").strip()
            print(ar)
            self.drawtree(self.deserialize(ar))
            repeat = input("Display another tree, if true 'y' else 'n' : ").strip()

    def deserialize(self, string):
        if string == "{}":
            return None
        nodes = [
            None if val == "null" else TreeNode(int(val))
            for val in string.strip("[]{}").split(",")
        ]
        kids = nodes[::-1]
        root = kids.pop()
        for node in nodes:
            if node:
                if kids:
                    node.left = kids.pop()
                if kids:
                    node.right = kids.pop()
        return root

    def drawtree(self, root):
        def height(root):
            return 1 + max(height(root.left), height(root.right)) if root else -1

        def jumpto(x, y):
            t.penup()
            t.goto(x, y)
            t.pendown()

        def draw(node, x, y, dx):
            if node:
                t.goto(x, y)
                jumpto(x, y - 20)
                t.write(node.val, align="center", font=("Arial", 12, "normal"))
                draw(node.left, x - dx, y - 60, dx / 2)
                jumpto(x, y - 20)
                draw(node.right, x + dx, y - 60, dx / 2)

        t = turtle.Turtle()
        t.speed(0)
        turtle.delay(0)
        h = height(root)
        jumpto(0, 30 * h)
        draw(root, 0, 30 * h, 40 * h)
        t.hideturtle()
        turtle.mainloop()


class SquareBracketToCurlyBracket:
    """ [[ ]] -> {{}} or [] -> {} or [[...[]...]] -> {{..{}..}} """

    def __init__(self):
        flag = True
        while flag:
            s = input(
                "Enter another input OR press any key (except '[') to exit: \n"
            ).strip()
            if not s.startswith("["):
                break
            pc.copy(self.braceToCurly(s))
            pc.paste()

    def braceToCurly(self, s):
        return s.replace("[", "{").replace("]", "}")


class ToCharArray:
    def stringToCharArray(self, s):
        if s.startswith('"'):
            s = s[1:]
        if s.endswith('"'):
            s = s[:-1]
        str = "{"
        for i in s:
            str += "'" + i + "'" + ", "
        str = str[:-2] + "}"
        print(str)
        return str

    def __init__(self):
        a = "y"
        while a == "y":
            s = input("print string to char array: \n").strip()
            pc.copy(self.stringToCharArray(s))
            pc.paste()
            a = input("Coninue, if true 'y' else 'n': ")


if __name__ == "__main__":
    rep = "y"
    while rep == "y" or rep == "Y":
        print("*" * 10 + " ~~HELPER MENU~~ " + "*" * 10 + "\n")
        print("1. Draw Tree (press 1) ")
        print("2. Convert [] to {} (press 2) ")
        print("3. String to CharArray e.g., \"add\" -> \"{'a','d','d'}\" (press 3)")
        inp = int(input("\nPress enter your choice : "))
        print()
        if inp == 1:
            t = TreeBuild()
        elif inp == 2:
            t = SquareBracketToCurlyBracket()
        elif inp == 3:
            t = ToCharArray()
        else:
            exit()
        rep = input(
            "\nDo you want to see 'HELPER MENU', if true 'y' else 'n' : "
        ).strip()

