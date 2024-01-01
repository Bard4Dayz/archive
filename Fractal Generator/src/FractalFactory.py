from Mandelbrot import Mandelbrot
from Julia import Julia
from Mandelbrot3 import Mandelbrot3

def makeFractal(filename):
    fractalFile = open(filename)
    info = {}
    info.update({'name': filename})
    for line in fractalFile:
        if line[0] != '#':
            line = line.lower()
            splitLine = line.split(':')
            for i in range(len(splitLine)):
                splitLine[i] = splitLine[i].replace(' ', '')
                splitLine[i] = splitLine[i].replace('\n', '')
            if len(splitLine) == 2:
                info.update({splitLine[0] : splitLine[1]})
    fractalFile.close()
    info = __checkInfo(info)
    if info['type'] == 'mandelbrot':
        return Mandelbrot(info)
    if info['type'] == 'julia':
        return Julia(info)
    if info['type'] == 'mandelbrot3':
        return Mandelbrot3(info)
    raise NotImplementedError("That fractal type has not been implemented yet.")


def makeDefaultFractal():
    info = {
        'centerx': 0.0,
        'centery': 0.0,
        'axislength': 4.0,
        'type': 'mandelbrot',
        'pixels': 640,
        'iterations' : 100,
        'name' : "mandelbrot"
    }
    return Mandelbrot(info)


def __checkInfo(info):
    if 'type' in info:
        if not isinstance(info['type'], str):
            raise RuntimeError("The fractal type was not a string.")
    else:
        raise RuntimeError("The fractal type was not found.")
    if 'centerx' in info:
        testString = info['centerx'].replace('.', '', 1)
        testString = testString.replace('-', '', 1)
        testString = testString.replace('e', '', 1)
        if testString.isdecimal():
            info['centerx'] = float(info['centerx'])
        if not isinstance(info['centerx'], float):
            raise RuntimeError("The center X coordinate was not a float value.")
    else:
        raise RuntimeError("The center X coordinate was not found.")
    if 'centery' in info:
        testString = info['centery'].replace('.', '', 1)
        testString = testString.replace('-', '', 1)
        testString = testString.replace('e', '', 1)
        if testString.isdecimal():
            info['centery'] = float(info['centery'])
        if not isinstance(info['centery'], float):
            raise RuntimeError("The center Y coordinate was not a float value.")
    else:
        raise RuntimeError("The center Y coordinate was not found.")
    if 'axislength' in info:
        testString = info['axislength'].replace('.', '', 1)
        testString = testString.replace('-', '', 1)
        testString = testString.replace('e', '', 1)
        if testString.isdecimal():
            info['axislength'] = float(info['axislength'])
        if not isinstance(info['axislength'], float):
            raise RuntimeError("The axisLength was not a float value.")
    else:
        raise RuntimeError("The axisLength value was not found.")
    if 'pixels' in info:
        if info['pixels'].isdecimal():
            info['pixels'] = int(info['pixels'])
        if not isinstance(info['pixels'], int):
            raise RuntimeError("The pixel count was not an int value.")
    else:
        raise RuntimeError("The pixel value was not found.")
    if 'iterations' in info:
        if info['iterations'].isdecimal():
            info['iterations'] = int(info['iterations'])
        if not isinstance(info['iterations'], int):
            raise RuntimeError("The iteration count was not an int value.")
    else:
        raise RuntimeError("The iteration value was not found.")
    if info['type'] == 'julia':
        if 'creal' in info:
            testString = info['creal'].replace('.', '', 1)
            testString = testString.replace('-', '', 1)
            if testString.isdecimal():
                info['creal'] = float(info['creal'])
            if not isinstance(info['creal'], float):
                raise RuntimeError("The creal data was not a float value.")
        else:
            raise RuntimeError("The creal value was not found, and is needed for this Julia type fractal.")
        if 'cimag' in info:
            testString = info['cimag'].replace('.', '', 1)
            testString = testString.replace('-', '', 1)
            if testString.isdecimal():
                info['cimag'] = float(info['cimag'])
            if not isinstance(info['cimag'], float):
                raise RuntimeError("The cimag data was not a float value.")
        else:
            raise RuntimeError("The cimag value was not found, and is needed for this Julia type fractal.")
    return info