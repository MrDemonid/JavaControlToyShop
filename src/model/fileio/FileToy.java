package model.fileio;

import ex.BadWriteLineException;
import ex.BarReadLineException;
import ex.NeverFileException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileToy extends FileText {

    public FileToy(String fileName, boolean append) {
        super(fileName, append);
    }

    /**
     * Считывает текстовый файл и возвращает список объектов вида: "id":1,"name":"Машинка","count":5,"probability":12
     * @return Список
     */
    @Override
    public List<String> load() throws NeverFileException, BarReadLineException
    {
        List<String> out = new ArrayList<>();
        List<String> source = super.load();
        String src = String.join(" ", source);              // String src = source.stream().collect(Collectors.joining(" "));
        src = src.replaceAll("\\s+", "").trim();            // удаляем все пробелы

        String[] s = src.split("(\\{)|(\\})");              // разбиваем по {}
        if (s[0].equals("[") && s[s.length-1].equals("]"))  // первым и последним должны быть квадратные скобки
        {
            for (int i = 1; i < s.length-1; i++)            // порядок, сканируем строку дальше
            {
                if ((i & 0x01) == 0) {
                    if (!s[i].equals(","))                  // четный индекс - должна быть запятая
                        break;                              // прерываем и возвращаем что успели распарсить
                } else
                    out.add(s[i]);                          // строка вида: "id":1,"name":"Машинка","count":5,"probability":12
            }
        }
        return out;
    }

    @Override
    public void save(List<String> buffer) throws NeverFileException, BadWriteLineException
    {
        // оформляем список в формат похожий на json
        List<String> res = new ArrayList<>();
        res.add("[");
        Iterator<String> iter = buffer.iterator();
        while (iter.hasNext())
        {
            String str = iter.next();
            if (iter.hasNext())
                str = str + ",";
            res.add(str);
        }
        res.add("]");
        // сохраняем в файл
        super.save(res);
    }

}
