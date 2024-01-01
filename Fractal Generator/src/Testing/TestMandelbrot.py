import unittest
from Mandelbrot import Mandelbrot


# autocmd BufWritePost <buffer> !python3 runTests.py

class TestMandelbrot(unittest.TestCase):
    testInfo = {
        'name': 'mandelbrot',
        'type': 'mandelbrot',
        'pixels': 640,
        'centerx': 0.0,
        'centery': 0.0,
        'axislength': 4.0,
        'iterations': 111
        }

    def test_count(self):
        testFractal = Mandelbrot(self.testInfo)
        self.assertEqual(testFractal.count(0, 0), 110)
        self.assertEqual(testFractal.count(-0.751, 1.1075), 2)
        self.assertEqual(testFractal.count(-0.2, 1.1075), 9)
        self.assertEqual(testFractal.count(-0.75, 0.1075), 30)
        self.assertEqual(testFractal.count(-0.748, 0.1075), 56)
        self.assertEqual(testFractal.count(-0.6781250000000001, -0.46875), 29)

    def test_getterMethods(self):
        testFractal = Mandelbrot(self.testInfo)
        self.assertEqual(testFractal.getPixels(), 640)
        self.assertEqual(testFractal.getIterations(), 111)
        self.assertEqual(testFractal.getCenterX(), 0.0)
        self.assertEqual(testFractal.getCenterY(), 0.0)
        self.assertEqual(testFractal.getAxisLen(), 4.0)
        self.assertEqual(testFractal.getName(), 'mandelbrot')



if __name__ == '__main__':
    unittest.main()
