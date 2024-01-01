import unittest
import FractalFactory
from Fractal import Fractal
from Mandelbrot import Mandelbrot
from Julia import Julia
from Mandelbrot3 import Mandelbrot3
import PaletteFactory
from Palette import Palette
from Daydream import Daydream
from Nightmare import Nightmare

class TestFactories(unittest.TestCase):

    def test_PaletteFactory(self):
        testPalette = PaletteFactory.makePalette('nightmare', 90)
        self.assertIsInstance(testPalette, Palette)
        self.assertIsInstance(testPalette, Nightmare)
        testPalette = PaletteFactory.makeDefaultPalette(50)
        self.assertIsInstance(testPalette, Palette)
        self.assertIsInstance(testPalette, Daydream)

    def test_FractalFactory(self):
        testFractal = FractalFactory.makeFractal('data/mandelbrot.frac')
        self.assertIsInstance(testFractal, Fractal)
        self.assertIsInstance(testFractal, Mandelbrot)
        testFractal = FractalFactory.makeFractal('data/mandelthree.frac')
        self.assertIsInstance(testFractal, Fractal)
        self.assertIsInstance(testFractal, Mandelbrot3)
        testFractal = FractalFactory.makeFractal('data/fulljulia.frac')
        self.assertIsInstance(testFractal, Fractal)
        self.assertIsInstance(testFractal, Julia)

    def test_fractalDataConversion(self):
        testFractal = FractalFactory.makeFractal('data/mandelbrot.frac')
        self.assertIsInstance(testFractal.getIterations(), int)
        self.assertIsInstance(testFractal.getPixels(), int)
        self.assertIsInstance(testFractal.getCenterX(), float)
        self.assertIsInstance(testFractal.getCenterY(), float)
        self.assertIsInstance(testFractal.getAxisLen(), float)
        testFractal = FractalFactory.makeDefaultFractal()
        self.assertEqual(testFractal.getName(), 'mandelbrot')
        self.assertEqual(testFractal.getPixels(), 640)
        self.assertEqual(testFractal.getIterations(), 100)
        self.assertEqual(testFractal.getAxisLen(), 4.0)
        self.assertEqual(testFractal.getCenterX(), 0.0)
        self.assertEqual(testFractal.getCenterY(), 0.0)


if __name__ == '__main__':
    unittest.main()