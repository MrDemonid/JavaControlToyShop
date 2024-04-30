package controller;

import ex.BadWriteLineException;
import ex.BarReadLineException;
import ex.NeverFileException;
import model.Shop;
import model.Toy;
import model.fileio.FileText;
import view.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Controller {
    Menu menu;
    Shop shop;                  // менеджер игрушек
    List<Toy> prize;            // список игрушек на выдачу
    Random random;

    public Controller() {
        menu = new Menu("Главное меню", Arrays.asList(
                new MenuItem(MenuCmd.VIEW, "Посмотреть",
                        new Menu("Просмотр", Arrays.asList(
                                new MenuItem(MenuCmd.VIEW_ALL, "В наличии", null),
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

        shop = new Shop();
        prize = new ArrayList<>();
        random = new Random();
    }

    public void run()
    {
        MenuCmd cmd;
        load();                     // подгружаем игрушки из файла (если он есть)

        while ((cmd = menu.run()) != MenuCmd.NONE)
        {
            switch (cmd)
            {
                case MenuCmd.VIEW_ALL:
                    View.print(shop);
                    break;
                case VIEW_PRIZE:
                    viewPrize();
                    break;
                case ADD_TOY:
                    addToy();
                    break;
                case EDIT_COUNT:
                    editCount();
                    break;
                case EDIT_PROBABILITY:
                    editProbability();
                    break;
                case RAFFLE:
                    raffle();
                    break;
                case ISSUANCE_ONE:
                    issuance();
                    break;
                case ISSUANCE_ALL:
                    issuanceAll();
                    break;
            }
        }

        /*
            Сохраняем текущее состояние магазина и выходим
         */
        try {
            shop.save("assets/toys.txt");

        } catch (NeverFileException | BadWriteLineException e) {
            View.error(e.getMessage());
        }

    }

    private void viewPrize()
    {
        View.println("На выдачу:");
        for (Toy toy : prize) {
            View.println(toy.toString());
        }
    }

    private void addToy()
    {
        String name = View.input("Введите название игрушки: ");
        int id = View.getInt("Введите артикул: ", -1);
        if (id >= 0 && shop.get(id) != null)
        {
            View.error("Такой товар уже есть в магазине!\n");
            return;
        }
        int count = View.getInt("Введите количество: ", 0);
        int prb = View.getInt("Введите вероятность (0 - 100): ", 0);
        if (prb > 100)
        {
            View.error("Вероятность не может быть выше 100%!\n");
            return;
        }
        Toy toy = new Toy(id, name, count, prb);
        shop.add(toy);
        View.printf("Добавлено:\n%s\n", toy.toString());
    }

    private void editCount()
    {
        int id = View.getInt("Введите артикул: ", -1);
        if (id >= 0)
        {
            Toy toy = shop.get(id);
            if (toy != null)
            {
                View.println(toy);
                int cnt = View.getInt("Введите новое количество: ", toy.getCount());
                toy.setCount(cnt);
                View.println(toy);
            }
        }
    }

    private void editProbability()
    {
        int id = View.getInt("Введите артикул: ", -1);
        if (id >= 0)
        {
            Toy toy = shop.get(id);
            if (toy != null)
            {
                View.println(toy);
                int prb = View.getInt("Введите новую вероятность (0 - 100): ", toy.getProbability());
                toy.setProbability(prb);
                View.println(toy);
            }
        }
    }

    private void raffle()
    {
        List<Integer> mas = shop.getToysId();

        // подсчитываем общую вероятность
        int len = 0;
        for (Integer m : mas) {
            Toy toy = shop.get(m);
            if (toy.getCount() > 0)
                len += toy.getProbability();
        }
        if (len > 0)
        {
            Toy toy = null;
            int t = random.nextInt(len);
            // Ищем элемент, которому принадлежит этот индекс
            for (Integer m : mas)
            {
                Toy p = shop.get(m);
                if (p.getCount() > 0)
                    t -= p.getProbability();
                if (t < 0)
                {
                    toy = new Toy(p.getId(), p.getName(), 1, p.getProbability());
                    p.give();           // взяли одну игрушку
                    break;
                }
            }
            if (toy != null)
            {
                prize.add(toy);
                View.printf("Выиграли:\n%s\n", toy);
            }
        }
    }

    private void issuance()
    {
        if (!prize.isEmpty())
        {
            Toy toy = prize.removeFirst();
            View.printf("Выдали:\n%s\n", toy.toString());
            FileText file = new FileText("assets/prizes.txt", true);
            try {
                file.save(List.of(toy.toString()));
            } catch (NeverFileException | BadWriteLineException e) {
                View.error(e.getMessage());
            }
        }
    }

    private void issuanceAll()
    {
        while (!prize.isEmpty())
        {
            issuance();
        }
    }
    

    private void load()
    {
        View.print("Загружаем игрушки...");
        try {
            shop.load("assets/toys.txt");
            View.println("ок");
        } catch (NeverFileException | BarReadLineException e) {
            View.println("");
            View.error(e.getMessage());
        }
    }
}
