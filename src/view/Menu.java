package view;

import java.util.List;


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
            View.colput(active.text, View.ANSI_BLUE_BACKGROUND + View.ANSI_BLACK);
            View.print("\n---------------------\n");
        }
        int idx = 1;
        for (MenuBase i : active.head)
        {
            View.printf("%d. %s\n", idx, i);
            idx++;
        }
        if (active.prev == null)
        {
            View.print("0. Выход\n");
        } else {
            View.print("0. Назад\n");
        }
    }

    private MenuCmd select(int index)
    {
        if (index < active.head.size())
        {
            MenuItem sm = active.head.get(index);
            if (sm.submenu != null)
            {
                sm.submenu.prev = active;
                active = sm.submenu;
            } else {
                return sm.getId();
            }
        }
        return MenuCmd.NONE;
    }

    private void back()
    {
        if (active.prev != null)
            active = active.prev;
    }

    public MenuCmd run()
    {
        while (true)
        {
            show();
            int key = View.getInt(">", -1);
            if (key > 0)
            {
                if (select(key-1) != MenuCmd.NONE)
                {
                    return active.head.get(key-1).getId();
                }
            } else if (key == 0)
            {
                if (active.prev == null)
                    return MenuCmd.NONE;
                back();
            }

        }
    }


//    #
//            # Процесс меню, на выходе: код выбранного меню, или 0, если пользователь захотел выйти из программы
//    #
//    def run(self) -> int:
//            while True:
//            self.show()
//            try:
//    print(">", end = "")
//    key = int(input())
//            if key == 0:
//            if self.active.prev is None:
//            return 0
//            self.back()
//            else:
//    key -= 1
//            if self.select(key) is not None:
//            return self.active.head[key].id
//
}



