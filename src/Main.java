import ex.BadWriteLineException;
import ex.BarReadLineException;
import ex.NeverFileException;
import model.Shop;
import model.Toy;
import view.AnsiView;
import view.View;

public class Main {
    public static void main(String[] args) {
        View view = new AnsiView();
        Shop shop = new Shop();

//        shop.add(new Toy(1, "Машинка", 5, 12));
//        shop.add(new Toy(4, "Самолет", 2, 2));
//        shop.add(new Toy(12, "Экскаватор", 7, 15));
//        shop.add(new Toy(4, "Самолет", 8, 2));
//        view.output(shop);
//        try
//        {
//            shop.save("assets/toys.txt");
//        } catch (NeverFileException | BadWriteLineException e) {
//            throw new RuntimeException(e);
//        }


        try
        {
            shop.load("assets/toys.txt");
            view.output(shop);
        } catch (NeverFileException | BarReadLineException e) {
            throw new RuntimeException(e);
        }


    }

}