package com.phenix.pattern.command;

/**
 * @author Merlin
 */
public abstract class Command {
    Receiver receiver;

    public Command(Receiver pReceiver) {
        this.receiver = pReceiver;
    }

    /**
     * to implement the method.
     */
    public abstract void execute();
}
