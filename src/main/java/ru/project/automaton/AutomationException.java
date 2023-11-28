package ru.project.automaton;

public class AutomationException extends Exception{
    public AutomationException(String message){
        super(message);
    }

    public AutomationException(String message, Throwable cause) {
        super(message, cause);
    }

    public AutomationException(Throwable cause) {
        super(cause);
    }
}
