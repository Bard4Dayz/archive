from Fractal import Fractal


class Julia(Fractal):
    info = {}

    def __init__(self, info):
        self.info = info

    def count(self, x, y):
        """Return the color of the current pixel within the Mandelbrot set"""
        c = complex(self.info['creal'], self.info['cimag'])
        z = complex(x, y)
        for i in range(self.info['iterations']):
            z = z * z + c # Get z1, z2, ...
            if abs(z) > 2:
                return i  # The sequence is unbounded
        return self.info['iterations'] - 1   # Indicate a bounded sequence
