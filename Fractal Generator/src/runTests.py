import unittest

from Testing import TestMandelbrot, TestJulia, TestFactories


suite = unittest.TestSuite()
tests = (TestMandelbrot.TestMandelbrot, TestJulia.TestJulia, TestFactories.TestFactories)
for test in tests:
    suite.addTest(unittest.makeSuite(test))

runner = unittest.TextTestRunner(verbosity=2)
runner.run(suite)
