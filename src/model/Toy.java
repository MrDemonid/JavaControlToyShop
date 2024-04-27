package model;

import java.io.*;

public class Toy {
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

    /**
     * Возвращает данные объекта в текстовом виде, пригодном для записи
     */
    public String serialize()
    {
        return String.format("{\n    'id' : %d,\n    'name' : '%s',\n    'count' : %d,\n    'probability' : %d\n}", id, name, count, probability);
    }

}
