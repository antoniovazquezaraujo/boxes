package com.formatic.boxes.widgets;

import com.formatic.boxes.Color;
import com.formatic.boxes.widgets.events.ColorChooserListener;
import com.formatic.boxes.widgets.events.DialogListener;
// cambiar ColorChooserListener a DialogListener y ponerlo en Dialog!!

public class ColorChooserDialog extends Dialog implements ColorChooserListener{
	ColorChooser colorChooser;
	public ColorChooserDialog() {
		this(ColorChooser.Size.FULL);
	}
	public ColorChooserDialog(ColorChooser.Size size) {
		colorChooser = new ColorChooser(size);
		colorChooser.setColorChooserListener(this);
		add(colorChooser);
	}
	public Color getSelectedColor(){
		return colorChooser.getSelectedColor();
	}
	@Override
	public void accepted() {
		hide();
		dialogListener.accepted();
	}
	@Override
	public void canceled() {
		hide();
		dialogListener.canceled();
	}

}
