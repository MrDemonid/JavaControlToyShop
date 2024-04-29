import ex.BadWriteLineException;
import ex.BarReadLineException;
import ex.NeverFileException;
import model.Shop;
import model.Toy;
import view.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

        Menu menu = new Menu("Главное меню", Arrays.asList(
                new MenuItem(MenuCmd.VIEW, "Посмотреть",
                        new Menu("Просмотр", Arrays.asList(
                                new MenuItem(MenuCmd.VIEW_ALL, "Все записи", null),
                                new MenuItem(MenuCmd.VIEW_ONE, "Выбрать", null),
                                new MenuItem(MenuCmd.VIEW_PRIZE, "Очередь на выдачу", null)
                        ))),
                new MenuItem(MenuCmd.ADD_TOY, "Добавить", null),
                new MenuItem(MenuCmd.EDIT, "Изменить",
                        new Menu("Редактирование", Arrays.asList(
                                new MenuItem(MenuCmd.EDIT_COUNT, "Количество", null),
                                new MenuItem(MenuCmd.EDIT_PROBABILITY, "Вес (вероятность)", null)
                        ))),
                new MenuItem(MenuCmd.RAFFLE, "Розыгрыш", null),
                new MenuItem(MenuCmd.ISSUANCE, "Выдать",
                        new Menu("Выдача", Arrays.asList(
                                new MenuItem(MenuCmd.ISSUANCE_ONE, "Выдать очередную", null),
                                new MenuItem(MenuCmd.ISSUANCE_ALL, "Выдать все", null)
                        ))))
        );


//        System.setOut(new java.io.PrintStream(System.out, true, "UTF-8"));

        menu.show();


    }

}