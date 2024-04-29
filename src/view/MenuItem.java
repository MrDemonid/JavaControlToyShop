package view;

public class MenuItem extends MenuBase {

    private MenuCmd id;
    private MenuBase submenu;

    public MenuItem(MenuCmd id, String text, MenuBase submenu)
    {
        super(text);
        this.id = id;
        this.submenu = submenu;
    }

    public MenuCmd getId() {
        return id;
    }

    public MenuBase getSubmenu() {
        return submenu;
    }

    @Override
    public String toString()
    {
        String res = String.format("%-30s", text);
        if (submenu != null)
            res += "-->";
        return res;
    }

}
