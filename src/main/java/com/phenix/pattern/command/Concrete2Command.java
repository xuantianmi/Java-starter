package com.phenix.pattern.command;

/**
 * @author Merlin
 */
public class Concrete2Command extends Command {

    public Concrete2Command(Receiver pReceiver) {
        super(pReceiver);
    }

    @Override
    public void execute() {
        System.out.println("Concrete2Command.execute.");
        this.receiver.action2();
    }
}
