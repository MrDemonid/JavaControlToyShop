package model;


import ex.BadWriteLineException;
import ex.BarReadLineException;
import ex.NeverFileException;
import model.fileio.FileText;
import model.fileio.FileToy;

import java.util.*;
import java.util.stream.Collectors;

public class Shop {
    Map<Integer, Toy> toys;

    public Shop() {
        toys = new HashMap<>();
    }

    public void clear()
    {
        toys.clear();
    }

    /**
     * Добавляет в словарь новую игрушку, или прибавляет у существующей количество
     * @param toy Игрушка (существующая или нет)
     */
    public void add(Toy toy)
    {
        if (toy != null)
        {
            int id = toy.getId();
            if (!toys.containsKey(id))
                toys.put(id,toy);                  // добавляем новую игрушку
        }
    }

    public Toy get(int id)
    {
        if (toys.containsKey(id))
            return toys.get(id);
        return null;
    }
    
    /**
     * Подргужает данные из текстового файла
     * @param fileName Имя текстового файла
     */
    public void load(String fileName) throws NeverFileException, BarReadLineException
    {
        FileText file = new FileToy(fileName, false);
        List<String> list = file.load();
        for (String s : list)
        {
            Toy toy = new Toy();
            if (toy.deserialize(s))
                add(toy);
        }
    }

    /**
     * Сохраняет данные в текстовый файл
     * @param fileName Имя файла
     */
    public void save(String fileName) throws NeverFileException, BadWriteLineException
    {
        List<String> list = new ArrayList<>();

        for (Toy toy : toys.values())
        {
            list.add(toy.serialize());
        }
        FileToy file = new FileToy(fileName, false);
        file.save(list);
    }


    @Override
    public String toString()
    {
//        StringBuilder sb = new StringBuilder();
//        for (Toy toy : toys.values())
//            sb.append(toy).append("\n");
//        return sb.toString();
//
        return toys.values().stream().map(toy -> toy + "\n").collect(Collectors.joining("", "В наличии:\n", ""));
    }

    /**
     * Возвращает список ключей
     */
    public List<Integer> getToysId()
    {
        List<Integer> lst = new ArrayList<>();

        lst = toys.keySet().stream().toList();
        return lst;
    }

}
