from Palette import Palette
from colour import Color

class Daydream(Palette):
    palette = []

    def __init__(self, maxIterations):
        white = Color('white')
        peach = Color('#ff9363')
        lilac = Color('#d885ff')
        meadow = Color('#62ff3b')
        sky = Color('#339ae8')
        dandelion = Color('#d9f551')
        seafoam = Color('#69ffb6')

        counter = 0
        colors = [peach, lilac, meadow, sky, dandelion, seafoam]
        self.palette = list(white.range_to(colors[counter], 8))
        self.palette += list(colors[counter].range_to(white, 8))[1:]

        while len(self.palette) < maxIterations:
            counter += 1
            if counter >= len(colors):
                counter = 0
            self.palette += list(white.range_to(colors[counter], 8))[1:]
            self.palette += list(colors[counter].range_to(white, 8))[1:]

    def getColor(self, number):
        return self.palette[number]