package model;

import java.io.Serializable;

public class Toy implements Serializable {
    private int id;
    private String name;
    private int count;
    private int probability;

    public Toy(int id, String name, int count, int probability)
    {
        this.id = id;
        this.name = name;
        this.count = count;
        this.probability = probability;
    }

    public int getId() {
        return id;
    }

    public int getCount() {
        return count;
    }

    public String getName() {
        return name;
    }

    public int getProbability() {
        return probability;
    }

    /**
     * Добавить одну игрушку
     */
    public void add(int num)
    {
        count += num;
    }

    /**
     * Получить одну игрушку
     * @return true - если успешно
     */
    public boolean give()
    {
        if (count <= 0)
            return false;
        count--;
        return true;
    }


    @Override
    public String toString()
    {
        return String.format("[%d], '%s', %d шт.", id, name, count);
    }
}
