class Fractal():

    def __init__(self):
        raise NotImplementedError("Concrete subclass of Fractal must implement __init__")

    def count(self):
        raise NotImplementedError("Concrete subclass of Fractal must implement count() method")

    def getIterations(self):
        return self.info['iterations']

    def getPixels(self):
        return self.info['pixels']

    def getName(self):
        return self.info['name']

    def getCenterX(self):
        return self.info['centerx']

    def getCenterY(self):
        return self.info['centery']

    def getAxisLen(self):
        return self.info['axislength']
