import java.util.Vector;

//Command, comando que puede repetirse un número de veces
class Command {
	int times, actualTime;
	Box box;
	Command(int times) {
		this.times = times;
		actualTime = 0;
	}
	void setBox(Box box){
		this.box = box;
	}
	void update() {
		if (times > 0) {
			actualTime++;
		}
	}
	boolean isFinished() {
		return actualTime == times;
	}
	void reset() {
		actualTime = 0;
	}
}
//Batch, actualiza sus comandos uno tras otro
//Termina cuando termina el más largo
class BatchCommand extends Command {
	Vector<Command>commands;
	int actualCommand;
	BatchCommand() {
		this(1);
	}
	BatchCommand(int times) {
		super(times);
		this.commands=new Vector<Command>();
		actualCommand = 0;
	}
	void setBox(Box box){
		super.setBox(box);
		for(Command c: commands){
			c.setBox(box);
		}
	}
	void addCommand(Command command) {
		command.setBox(box);
		commands.add(command);
	}
	void update() {
		Command command;
		if (actualCommand < commands.size()) {
			command = commands.get(actualCommand);
			if (!command.isFinished()) {
				command.update();
			}else{
				command.reset();
				actualCommand++;         
			}
		}
		else {
			actualCommand = 0;
			super.update();
		}
	}

	void reset() {
		for (Command c: commands) {
			c.reset();
		}
		actualCommand = 0;
		super.reset();
	}
}
//Process, actualiza sus comandos simultáneamente.
//Termina cuando terminan todos
class ProccessCommand extends Command {
	Vector<Command>commands;
	boolean allCommandsCompleted;
	ProccessCommand() {
		this(1);
	}
	ProccessCommand(int times) {
		super(times);
		this.commands=new Vector<Command>();
		allCommandsCompleted=false;
	}
	void addCommand(Command command) {
		command.setBox(box);
		commands.add(command);
	}
	void setBox(Box box){
		super.setBox(box);
		for(Command c: commands){
			c.setBox(box);
		}
	}

	void update() {
		allCommandsCompleted = true;
		for (Command c: commands) {
			if (!c.isFinished()) {
				c.update();
				allCommandsCompleted = false;
			}
		}
		if (allCommandsCompleted) {
			for (Command c: commands) {
				c.reset();
			}
			super.update();
		}
	}
	void reset() {
		for (Command c: commands) {
			c.reset();
		}
		super.reset();
	}
}

