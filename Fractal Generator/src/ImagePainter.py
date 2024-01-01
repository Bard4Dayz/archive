import time
import sys
from tkinter import Tk, Canvas, PhotoImage, mainloop
from Fractal import Fractal
from Palette import Palette

class ImagePainter():
    fractal = 0
    palette = 0

    def __init__(self, fractal, palette):
        self.fractal = fractal
        self.palette = palette
        before = time.time()
        image = self.__paint()

        # Save the image as a PNG
        after = time.time()
        print(f"Done in {after - before:.3f} seconds!", file=sys.stderr)
        image.write(f"{self.fractal.getName()}.png")
        print(f"Wrote image {self.fractal.getName()}.png")

        # Call tkinter.mainloop so the GUI remains open
        print("Close the image window to exit the program")
        mainloop()

    def __paint(self):
        """Paint a Fractal image into the TKinter PhotoImage canvas."""
        window = Tk()
        image = PhotoImage(width=self.fractal.getPixels(), height=self.fractal.getPixels())

        # Figure out how the boundaries of the PhotoImage relate to coordinates on
        # the imaginary plane.
        centerX = self.fractal.getCenterX()
        centerY = self.fractal.getCenterY()
        axisLen = self.fractal.getAxisLen()
        minX = centerX - (axisLen / 2.0)
        maxX = centerX + (axisLen / 2.0)
        minY = centerY - (axisLen / 2.0)
        maxY = centerY + (axisLen / 2.0)

        # Display the image on the screen
        canvas = Canvas(window, width=self.fractal.getPixels(), height=self.fractal.getPixels(), bg='#000000')
        canvas.pack()
        canvas.create_image((self.fractal.getPixels()/2, self.fractal.getPixels()/2), image=image, state="normal")

        # At this scale, how much length and height on the imaginary plane does one
        # pixel take?
        pixelsize = abs(maxX - minX) / self.fractal.getPixels()

        for row in range(self.fractal.getPixels(), 0, -1):
            for col in range(self.fractal.getPixels()):
                x = minX + col * pixelsize
                y = minY + row * pixelsize
                iteration = self.fractal.count(x, y)
                color = self.palette.getColor(iteration)
                image.put(color, (col, self.fractal.getPixels() - row))
            window.update()  # display a row of pixels
        return image