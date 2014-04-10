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

import com.formatic.boxes.Command;
import com.formatic.boxes.widgets.Box;

// Batch, actualiza sus comandos uno tras otro
// Termina cuando termina el más largo
public class BatchCommand extends Command {
	Vector<Command> commands;
	int actualCommand;

	public BatchCommand() {
		this(1);
	}

	BatchCommand(int times) {
		super(times);
		this.commands = new Vector<Command>();
		actualCommand = 0;
	}

	public void setBox(Box box) {
		super.setBox(box);
		for (Command c : commands) {
			c.setBox(box);
		}
	}

	public void addCommand(Command command) {
		command.setBox(box);
		commands.add(command);
	}

	public void update() {
		Command command;
		if (actualCommand < commands.size()) {
			command = commands.get(actualCommand);
			if (!command.isFinished()) {
				command.update();
			} else {
				command.reset();
				actualCommand++;
			}
		} else {
			actualCommand = 0;
			super.update();
		}
	}

	public void reset() {
		for (Command c : commands) {
			c.reset();
		}
		actualCommand = 0;
		super.reset();
	}
}
