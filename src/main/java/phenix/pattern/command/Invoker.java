package phenix.pattern.command;

/**
 * @author Merlin
 */
public class Invoker {
    Command command;

    public Invoker(Command pCommand) {
        this.command = pCommand;
    }

    public void call() {
        System.out.println("Invoker calling");
        this.command.execute();
    }

    public void setCommand(Command pCommand) {
       this.command = pCommand;
    }
}
