This program has a simple command line syntax:

```
$ python src/main.py FRACTAL_NAME PALETTE_NAME
```

`FRACTAL_NAME` is the name of a fractal image this program is capable of
producing.  The default fractal is Mandelbrot, but other valid fractals are available in
 the data folder. You may modify the .frac files to change the fractal drawn.  Output images
 are saved as .png files in the data folder. Some .frac files may not yet be functional.
Specify the relative filepath in the command argument.

`PALETTE_NAME` is the color palette name of your choice.  Currently, this is
 either `Daydream` or `Nightmare`.  Default is Daydream.

You will most likely need to run 'pip install colour' to run this program.

Starter code and assignment goals were written by Professor Erik Falor at USU for CS 1440, 
refactoring an re-architecting were done by me as part of that course.  If you are currently 
enrolled in the same class, stop trying to rip someone else's work and do it yourself.

