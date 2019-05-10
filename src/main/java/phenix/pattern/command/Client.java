package phenix.pattern.command;

/**
 * @author Merlin
 */
public class Client {
    public static void main(String[] args) {
        Receiver receiver = new Receiver();
        Concrete1Command command1 = new Concrete1Command(receiver);
        Concrete2Command command2 = new Concrete2Command(receiver);
        Invoker invoker = new Invoker(command1);
        invoker.call();
        invoker.setCommand(command2);
        invoker.call();
    }

}
