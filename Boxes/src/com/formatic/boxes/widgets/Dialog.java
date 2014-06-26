package com.formatic.boxes.widgets;

import com.formatic.boxes.Boxes;
import com.formatic.boxes.widgets.events.DialogListener;

public class Dialog extends BoxContainer {
	public enum DialogChoice {
		ACCEPT, CANCEL
	}

	protected DialogListener dialogListener;
	private DialogChoice choice;

	public void show() {
		Boxes.topBox.showDialog(this);
	}

	public void hide() {
		Boxes.topBox.hideDialog();
	}

	public DialogChoice getChoice() {
		return choice;
	}

	public void setChoice(DialogChoice choice) {
		this.choice = choice;
	}

	public void setDialogListener(DialogListener dialogListener) {
		this.dialogListener = dialogListener;
	}
}
