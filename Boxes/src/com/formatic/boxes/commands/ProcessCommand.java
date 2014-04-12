/*
	                          BOXES
	              A minimalist color box framework
	         Copyright (C) 2014 Antonio Vazquez Araujo
	          (antoniovazquezaraujo[at]gmail[dot]com)
	          
	This file is part of Boxes.

    Boxes is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    Boxes is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with Boxes.  If not, see <http://www.gnu.org/licenses/>.
*/
package com.formatic.boxes.commands;

import java.util.Vector;

import com.formatic.boxes.widgets.Box;

// Process, actualiza sus comandos simultáneamente.
// Termina cuando terminan todos
public class ProcessCommand extends Command {
	Vector<Command> commands;
	boolean allCommandsCompleted;

	public ProcessCommand() {
		this(1);
	}

	public ProcessCommand(int times) {
		super(times);
		this.commands = new Vector<Command>();
		allCommandsCompleted = false;
	}

	public void addCommand(Command command) {
		command.setBox(box);
		commands.add(command);
	}

	public void setBox(Box box) {
		super.setBox(box);
		for (Command c : commands) {
			c.setBox(box);
		}
	}

	public void update() {
		allCommandsCompleted = true;
		for (Command c : commands) {
			if (!c.isFinished()) {
				c.update();
				allCommandsCompleted = false;
			}
		}
		if (allCommandsCompleted) {
			for (Command c : commands) {
				c.reset();
			}
			super.update();
		}
	}

	public void reset() {
		for (Command c : commands) {
			c.reset();
		}
		super.reset();
	}
}