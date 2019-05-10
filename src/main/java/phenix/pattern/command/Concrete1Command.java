package phenix.pattern.command;

/**
 * @author Merlin
 */
public class Concrete1Command extends Command {

    public Concrete1Command(Receiver pReceiver) {
        super(pReceiver);
    }

    @Override
    public void execute() {
        System.out.println("Concrete1Command.execute.");
        this.receiver.action1();
    }
}
