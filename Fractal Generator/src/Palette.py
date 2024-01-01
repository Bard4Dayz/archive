class Palette():

    def __init__(self):
        raise NotImplementedError("Concrete subclass of Palette must implement __init__")


    def getColor(self):
        raise NotImplementedError("Concrete subclass of Palette must implement getColor() method")

