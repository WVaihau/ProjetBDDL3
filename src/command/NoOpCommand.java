package command;

public class NoOpCommand implements Command {

	@Override
	public void execute() {}

	@Override
	public void unexecute() {}

}
