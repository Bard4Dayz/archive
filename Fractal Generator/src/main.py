#!/usr/bin/env python3

import sys
import FractalFactory
import PaletteFactory
from ImagePainter import ImagePainter

# Use both command arguments for fractal and palette.
if len(sys.argv) == 3:
    fractal = FractalFactory.makeFractal(sys.argv[1])
    palette = PaletteFactory.makePalette(sys.argv[2], fractal.getIterations())

# Make default palette if only one argument is given
elif len(sys.argv) == 2:
    fractal = FractalFactory.makeFractal(sys.argv[1])
    palette = PaletteFactory.makeDefaultPalette(fractal.getIterations())

# Make default palette and fractal if no arguments are given
else:
    fractal = FractalFactory.makeDefaultFractal()
    palette = PaletteFactory.makeDefaultPalette(fractal.getIterations())

image = ImagePainter(fractal, palette)
