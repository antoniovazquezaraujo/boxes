interface ColorToneListener {
  void colorChanged(ColorToneChooser chooser, int colorTone);
}
class ColorToneChooser extends Box {
  int colorTone;
  int value;
  int increment;
  boolean incrementing; 
  ColorToneListener colorToneListener;
  ColorToneChooser(int colorTone) {
    this(colorTone, 0);
  }
  ColorToneChooser(int colorTone, int value) {
    super(0, 0, 1, 1);
    this.colorTone = colorTone;
    setValue(value);
    incrementing = true;
    increment = 1;
  }
  void setColor(color rgbColor) {
    switch(colorTone) {
    case RED:
      setValue((int)red(rgbColor));
      break;
    case GREEN:
      setValue((int)green(rgbColor));
      break;
    case BLUE:
      setValue((int)blue(rgbColor));
      break;
    }
  }
  void setValue(int value) {
    this.value = value;
    super.setColor(getColorTone(colorTone, value));
  }
  void setColorToneListener(ColorToneListener colorToneListener) {
    this.colorToneListener = colorToneListener;
  }
  boolean onMousePressed(Box box, int x, int y) {
    if (incrementing) {
      value+=increment;
    } 
    else {
      value-=increment;
    }
    if (value > 255) {
      value = 255;
      incrementing = false;
      increment = 1;
    }
    if (value < 0) {
      value = 0;
      incrementing = true;
      increment = 1;
    }
    setValue(value);
    if (colorToneListener != null) {
      colorToneListener.colorChanged(this, value);
    }
    return true;//super.onMousePressed(x,y);
  }
  boolean onMouseRepeated(Box box, int x, int y) {
    increment ++;
    return onMousePressed(box, x, y);
  }
  boolean onMouseReleased(Box box, int x, int y) {
    incrementing= !incrementing;
    increment = 1;
    return true;
  }
}

