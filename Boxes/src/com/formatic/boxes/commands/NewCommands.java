package com.formatic.boxes.commands;

import java.util.Vector;


public class NewCommands {

}

class Comandos {
	public static void main(String[] args) {
		CommandRunner runner = new CommandRunner();
		Box v = new Box();
		runner.add(new BoxCommand(v, 1000, 9000){
			@Override
			public void run() {
				box.position+=2;
				System.out.println("Box:"+box.position);
			}
		});
		while (true) {
			runner.update();
//			Scanner s = new Scanner(System.in);
//			s.nextLine();
		}
	}
}
abstract class BoxCommand extends Commando{
	Box box;
	public BoxCommand(Box box, int speed, int times) {
		super(speed, times);
		this.box=box;
	}
}

interface AutomatedTask {
	public void run();
	public void setSpeed(int speed);
	public void setTimes(int time);
	public void update();
	public void pause();
	public void resume();
	public void stop();
}
abstract class Commando implements AutomatedTask{
	private static final int MAX_SPEED = 1000;
	private int turns;
	private int speed;
	private int times;
	private int actualTime;
	private boolean paused;
	public Commando(int speed, int times){
		this.speed = speed;
		if(this.speed > MAX_SPEED){
			this.speed = MAX_SPEED;
		}
		if(this.speed <= 0){
			turns = -1;
		}else{
			this.turns = MAX_SPEED/speed;
		}
		this.times = times;
		this.actualTime=times;
	}

	@Override
	public void setSpeed(int speed) {
		this.speed = speed;
	}

	@Override
	public void setTimes(int times) {
		this.times = times;
	}
	@Override
	public void update() {
		if(paused)return;
		if(turns > 0 ){
			turns --;
		}else{
			if(turns == 0){
				turns = MAX_SPEED/speed;
				if(actualTime-- > 0){
					run();
				}
			}
		}
	}
	@Override
	public void pause() {
		paused = true;
	}
	@Override
	public void resume() {
		paused= false;
	}
	@Override
	public void stop() {
		times = 0;
	}
}


class CommandRunner {
	Vector<AutomatedTask> commands;
	public CommandRunner() {
		commands = new Vector<AutomatedTask>();
	}
	public void add(AutomatedTask c) {
		commands.add(c);
	}
	public void update() {
		for (AutomatedTask c : commands) {
			c.update();
		}
	}
}
class Box {
	public int position;

	public String toString() {
		return "" + position;
	}
}