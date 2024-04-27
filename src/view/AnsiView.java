package view;

public class AnsiView extends ConsoleView {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
    public static final String ANSI_CLEAR_SCREEN = "\033[H";

    public static final String ANSI_OUTPUT = "\u001b[38;5;255m";
    public static final String ANSI_INPUT = "\u001b[36m";
    public static final String ANSI_ERROR = "\u001b[31;1m";
    public static final String ANSI_ERROR_TEXT = "\u001b[33m";
    public static final String ANSI_STRING = ANSI_YELLOW;
    public static final String ANSI_NUMBER = ANSI_CYAN;


    @Override
    public void output(Object msg) {
        super.output(highlight(msg.toString()));
    }

    public void printf(String format, Object ... args)
    {
        super.output(highlight(String.format(format, args)));
    }


    @Override
    public String input(String comment) {
        String s = super.input(ANSI_INPUT + comment);
        super.output(ANSI_RESET);
        return s;
    }

    @Override
    public void error(Object msg) {
        super.error(String.format("%sError: %s%s%s%s", ANSI_ERROR, ANSI_RESET, ANSI_ERROR_TEXT, msg, ANSI_RESET));
    }

    /*
        Небольшая раскраска строки (в кавычках '' и числа)
     */
    private String highlight(String source)
    {
        StringBuilder sb = new StringBuilder();
        sb.append(ANSI_OUTPUT);
        int i = 0;
        while (i < source.length())
        {
            int ch = source.codePointAt(i);
            if (Character.isDigit(ch))
            {

                int pos = i+1;
                while (pos < source.length() && Character.isDigit(source.codePointAt(pos)))
                    pos++;
                sb.append(ANSI_NUMBER).append(source, i, pos).append(ANSI_OUTPUT);
                i = pos-1;

            } else if (ch == '"')
            {
                // ищем конец строки
                int pos = source.indexOf(ch, i+1);
                if (pos > i)
                {
                    sb.append(ANSI_STRING).append(source, i, pos+1).append(ANSI_OUTPUT);
                    i = pos;
                }  else {
                    sb.appendCodePoint(ch);
                }
            } else {
                sb.appendCodePoint(ch);
            }
            i++;
        }
        sb.append(ANSI_RESET);
        return sb.toString();
    }


}
