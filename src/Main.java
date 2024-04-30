import controller.Controller;
import ex.BarReadLineException;
import ex.NeverFileException;
import model.Shop;
import view.*;

import java.util.Arrays;

public class Main {

    public static void main(String[] args)
    {

        //        System.setOut(new java.io.PrintStream(System.out, true, "UTF-8"));
        Controller controller = new Controller();
        controller.run();
    }

}