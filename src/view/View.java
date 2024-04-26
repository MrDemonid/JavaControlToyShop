package view;

public abstract class View {
    public abstract void output(Object msg);
    public abstract void printf(String format, Object ... args);
    public abstract String input(String comment);
    public abstract void error(Object msg);
}
