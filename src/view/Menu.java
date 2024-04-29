package view;

import java.util.ArrayList;
import java.util.List;

import static view.AnsiView.ANSI_BLACK_BACKGROUND;
import static view.AnsiView.ANSI_BLUE_BACKGROUND;

public class Menu extends MenuBase {

    protected List<MenuItem> head;
    protected Menu active;
    protected Menu prev;

    public Menu(String text, List<MenuItem> head)
    {
        super(text);
        this.head = head;
        this.active = this;
        this.prev = null;
    }

    public void append(MenuItem item)
    {
        active.head.add(item);
    }

    public void show()
    {
        if (active.text != null)
        {
            System.out.println(ANSI_BLUE_BACKGROUND + active.text + ANSI_BLACK_BACKGROUND + "\n");
        }
        int idx = 1;
        for (MenuBase i : active.head)
        {
            System.out.printf("%d. %s\n", idx, i);
            idx++;
        }
        if (active.prev == null)
        {
            System.out.println("0. Выход");
        } else {
            System.out.println("0. Назад");
        }
    }
}






//List<MenuItem> items = Arrays.asList(new MenuItem(1, "one", null),
//        new MenuItem(2, "two", null),
//        new MenuItem(3, "three",
//                new Menu("New menu",
//                        Arrays.asList(
//                                new MenuItem(4, "four", null),
//                                new MenuItem(4, "four", null)
//                        )
//                )
//
//        )
//);
