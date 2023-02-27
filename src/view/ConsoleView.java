package view;

public class ConsoleView {

    public void displayMenu(String[] strings) {
        Console.displayStrings(strings);
    }

    public String getString(String prompt) {
        return Console.getString(prompt);
    }

    public String getMultipleLineString(String prompt, String end) {
        return Console.getMultipleLineString(prompt, end);
    }

    public void displayString(String string) {
        Console.println(string, Console.RESET);
    }

    public int getInteger(String prompt, int min, int max) {
        return Console.getInteger(prompt, min, max);
    }
}
