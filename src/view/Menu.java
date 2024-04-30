package view;

import java.util.List;


public class Menu {

    protected String text;
    protected List<MenuItem> head;
    protected Menu active;
    protected Menu prev;

    public Menu(String text, List<MenuItem> head)
    {
        this.text = text;
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
        for (MenuItem i : active.head)
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
                if (select(key-1) != MenuCmd.NONE)      // NONE - это что-то неправильное ввели, игнорируем
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

    public String toString()
    {
        return text;
    }

}



