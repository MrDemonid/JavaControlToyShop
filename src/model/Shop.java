package model;


import ex.BadWriteLineException;
import ex.BarReadLineException;
import ex.NeverFileException;
import model.fileio.FileText;
import model.fileio.FileToy;

import java.io.*;
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
            if (toys.containsKey(id))
                toys.get(id).add(toy.getCount());  // добавляем количество
            else
                toys.put(id,toy);                  // добавляем новую игрушку
        }
    }

    /**
     * Из списка строк вида: "id":1,"name":"Машинка","count":5,"probability":12
     * создаёт экземпляры класса Toy и добавляет в список toys
     */
    public void load(String fileName) throws NeverFileException, BarReadLineException
    {
        FileText file = new FileToy(fileName);
        List<String> list = file.load();
        for (String s : list)
        {
            Toy toy = new Toy();
            if (toy.load(s))
                add(toy);
        }
    }

    public void save(String fileName) throws NeverFileException, BadWriteLineException
    {
        List<String> list = new ArrayList<>();

        for (Toy toy : toys.values())
        {
            list.add(toy.serialize());
        }

        FileToy file = new FileToy(fileName);
        file.save(list);

//        list.add("[");
//
//        Iterator<Map.Entry<Integer, Toy>> elem = toys.entrySet().iterator();
//        while (elem.hasNext())
//        {
//            Map.Entry<Integer, Toy> entry = elem.next();
//            Toy toy = entry.getValue();
//            if (elem.hasNext())
//                list.add(toy.serialize() + ",");
//            else
//                list.add(toy.serialize());
//        }
//        list.add("]");
//
//        // скидываем в файл
//        FileText file = new FileText(fileName);
//        file.save(list);
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

}
