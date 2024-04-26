package view;

import java.util.Scanner;

public class ConsoleView extends View {

    @Override
    public void output(Object msg)
    {
        System.out.print(msg);
        System.out.flush();
    }

    @Override
    public void printf(String format, Object... args)
    {
        output(String.format(format,args));
    }

    @Override
    public String input(String comment)
    {
        output(comment);
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    @Override
    public void error(Object msg)
    {
        System.out.print(msg);
    }
}
