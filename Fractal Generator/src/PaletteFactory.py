from Daydream import Daydream
from Nightmare import Nightmare

def makePalette(paletteName, maxIterations):
    if paletteName.lower() == 'daydream':
        return Daydream(maxIterations)
    if paletteName.lower() == 'nightmare':
        return Nightmare(maxIterations)
    raise NotImplementedError("Requested palette, " + paletteName + ", has not been implemented.")

def makeDefaultPalette(maxIterations):
    return Daydream(maxIterations)