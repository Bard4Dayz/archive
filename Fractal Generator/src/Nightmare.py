from Palette import Palette
from colour import Color

class Nightmare(Palette):
    palette = []

    def __init__(self, maxIterations):
        black = Color('black')
        blood = Color('#701515')
        deepSea = Color('#18175e')
        plague = Color('#114f0d')
        abyss = Color('#1c002e')
        pumpkin = Color('#995914')
        monster = Color('#781a57')

        counter = 0
        colors = [blood, deepSea, plague, abyss, pumpkin, monster]
        self.palette = list(black.range_to(colors[counter], 16))
        self.palette += list(colors[counter].range_to(black, 16))[1:]

        while len(self.palette) < maxIterations:
            counter += 1
            if counter >= len(colors):
                counter = 0
            self.palette += list(black.range_to(colors[counter], 16))[1:]
            self.palette += list(colors[counter].range_to(black, 16))[1:]

    def getColor(self, number):
        return self.palette[number]