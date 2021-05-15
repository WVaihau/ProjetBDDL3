package command;

public class CommandManager {
	Command[] commands;
	
	int slots = 2;
	
	public CommandManager() {
		commands = new Command[slots];
		for(int i = 0; i < slots; i++) {
			commands[i] = new NoOpCommand();
		}
	}
	
	public void setCommand(int slot, Command command) {
		if (command == null) {
			throw new IllegalArgumentException("Commands cannot be null");
		}else {
			commands[slot] = command;
		} 
	}
	
	public void activateSlot(int slot) {
		commands[slot].execute();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("-----Command Manager-----\n");
		for(int i = 0; i < slots; i++) {
			sb.append("Slot #" + i + " - " + commands[i].getClass().getSimpleName() + " \n");
		}
		return sb.toString();
	}
}
