from Fractal import Fractal


class Mandelbrot3(Fractal):
    info = {}

    def __init__(self, info):
        self.info = info

    def count(self, x, y):
        """Return the color of the current pixel within the Mandelbrot set"""
        z = 0
        c = complex(x, y)
        for i in range(self.info['iterations']):
            z = z * z * z + c  # Get z1, z2, ...
            if abs(z) > 2:
                return i  # The sequence is unbounded
        return self.info['iterations'] - 1   # Indicate a bounded sequence