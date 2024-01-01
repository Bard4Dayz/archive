import unittest
from Julia import Julia


# autocmd BufWritePost <buffer> !python3 runTests.py

class TestJulia(unittest.TestCase):
    testInfo = {
        'name': 'julia',
        'type': 'julia',
        'pixels': 1024,
        'centerx': 0.0,
        'centery': 0.0,
        'axislength': 4.0,
        'iterations': 78,
        'creal': -1.0,
        'cimag': 0.0
    }

    def test_count(self):
        testFractal = Julia(self.testInfo)
        self.assertEqual(testFractal.count(0, 0), 77)
        self.assertEqual(testFractal.count(-0.751, 1.1075), 0)
        self.assertEqual(testFractal.count(-0.2, 1.1075), 0)
        self.assertEqual(testFractal.count(-0.75, 0.1075), 77)
        self.assertEqual(testFractal.count(-0.748, 0.1075), 77)
        self.assertEqual(testFractal.count(-0.6781250000000001, -0.46875), 2)

    def test_getterMethods(self):
        testFractal = Julia(self.testInfo)
        self.assertEqual(testFractal.getPixels(), 1024)
        self.assertEqual(testFractal.getIterations(), 78)
        self.assertEqual(testFractal.getCenterX(), 0.0)
        self.assertEqual(testFractal.getCenterY(), 0.0)
        self.assertEqual(testFractal.getAxisLen(), 4.0)
        self.assertEqual(testFractal.getName(), 'julia')

if __name__ == '__main__':
    unittest.main()
