package view;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Console {
    public static final String CYAN = "\u001B[36m";
    public static final String RED = "\u001B[31m";
    public static final String BLACK = "\u001B[30m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String WHITE = "\u001B[37m";

    public static final String BLACK_BACKGROUND = "\u001B[40m";
    public static final String RED_BACKGROUND = "\u001B[41m";
    public static final String GREEN_BACKGROUND = "\u001B[42m";
    public static final String YELLOW_BACKGROUND = "\u001B[43m";
    public static final String BLUE_BACKGROUND = "\u001B[44m";
    public static final String PURPLE_BACKGROUND = "\u001B[45m";
    public static final String CYAN_BACKGROUND = "\u001B[46m";
    public static final String WHITE_BACKGROUND = "\u001B[46m";
    public static final String RESET = "\u001B[0m";

    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static String getString() {
        String string = "";
        try {
            string = reader.readLine();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return string;
    }

    public static String getString(String prompt) {
        System.out.print(prompt);
        return getString();
    }

    public static String getString(String prompt, String color) {
        print(prompt, color);
        return getString();
    }

    public static String getMultipleLineString(String prompt, String end) {
        System.out.println(prompt);
        String string = "";
        while (true) {
            String line = getString();
            if (line.equalsIgnoreCase(end)) break;
            string += line + '\n';
        }

        return string;
    }

    public static String getStringNonEmpty(String prompt) {
        while (true) {
            String string = getString(prompt);
            if (!string.isEmpty()) {
                return string;
            }
        }
    }

    public static int getInteger() {
        while (true) {
            try {
                int i = Integer.parseInt(getString());
                return i;
            } catch (Exception ex) {
                System.out.println("invalid integer");
            }
        }
    }

    public static int getInteger(String prompt) {
        while (true) {
            try {
                int i = Integer.parseInt(getString(prompt));
                return i;
            } catch (Exception ex) {
                System.out.println("invalid integer");
            }
        }
    }

    public static int getInteger(String prompt, int min, int max) {
        while (true) {
            try {
                int i = Integer.parseInt(getString(prompt));
                if (i < min || i > max) throw new Exception();
                return i;
            } catch (Exception ex) {
                System.out.println("invalid value");
            }
        }
    }

    public static int getInteger(String prompt, String color) {
        while (true) {
            try {
                int i = Integer.parseInt(getString(prompt, color));
                return i;
            } catch (Exception ex) {
                System.out.println("invalid integer");
            }
        }
    }

    public static float getFloat(String prompt, float min, float max) {
        while (true) {
            try {
                float i = Float.parseFloat(getString(prompt));
                if (i < min || i > max) throw new Exception();
                return i;
            } catch (Exception ex) {
                System.out.println("invalid value");
            }
        }
    }

    public static boolean getBoolean(String prompt, String trueStr, String falseStr) {
        while (true) {
            String string = getString(prompt);
            if (string.equalsIgnoreCase(trueStr)) return true;
            else if (string.equalsIgnoreCase(falseStr)) return false;
        }
    }

    public static void displayStrings(String[] strings) {
        for (int i = 0; i < strings.length; i++) {
            Console.println((i + 1) + ") " + strings[i], Console.GREEN);
        }
    }

    public static void print(String string, String color) {
        System.out.print(color + string + RESET);
    }

    public static void println(String string, String color) {
        System.out.println(color + string + RESET);
    }
}