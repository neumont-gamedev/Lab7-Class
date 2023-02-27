package controller;

import model.JournalEntry;
import view.ConsoleView;

import java.io.File;
import java.time.LocalDate;

public class Journal {
    ConsoleView view = new ConsoleView();

    public void run() throws Exception {
        boolean quit = false;
        while (!quit) {
            // display menu
            String[] menu = {"Create", "View", "Search", "Quit"};
            view.displayMenu(menu);
            int selection = view.getInteger("Enter Selection: ", 1, menu.length);
            // do menu selection
            switch (selection) {
                case 1: // create / edit
                    createNewJournalEntry();
                    break;
                case 2: // view
                    viewJournalEntry();
                    break;
                case 3: // search
                    searchJournalEntries();
                    break;
                case 4: // quit
                    quit = true;
                    break;
            }
        }
    }

    public void createNewJournalEntry() throws Exception {
        String[] menu = {"Today's date", "User date"};
        view.displayMenu(menu);
        int selection = view.getInteger("Enter selection: ", 1, menu.length);

        // get journal entry date and content
        LocalDate date = (selection == 1) ? LocalDate.now() : getUserDate("Enter date (mm-dd-yyyy): ");

        // try to get existing journal
        JournalEntry entry = getJournalFromDate(date);
        if (entry != null) {
            // append to existing journal
            String content = entry.getContent();
            view.displayString(content);
            content += view.getMultipleLineString("Append journal entry", "...");
            entry.setContent(content);
        }
        else {
            // create and save journal entry
            String content = view.getMultipleLineString("Enter journal entry", "...");
            entry = new JournalEntry(date, content);
        }

        saveJournalEntry(entry);
    }

    public void viewJournalEntry() throws Exception {
        // display journal filenames
        String[] filenames = FileIO.getFilenames(FileIO.ROOT);
        for (String filename : filenames) {
            view.displayString(filename);
        }

        LocalDate date = getUserDate("Enter date (mm-dd-yyyy): ");

        JournalEntry entry = getJournalFromDate(date);
        if (entry != null) {
            view.displayString(entry.getContent());
        }
        else {
            view.displayString("Journal entry does not exist for " + DateUtils.getString(date) + ".");
        }
    }

    public void searchJournalEntries() throws Exception {
        LocalDate beginDate = getUserDate("Enter start date (mm-dd-yyyy): ");
        LocalDate endDate = getUserDate("Enter end date (mm-dd-yyyy): ");

        String[] filenames = FileIO.getFilenames(FileIO.ROOT);
        for (String filename : filenames) {
            LocalDate date = DateUtils.getDate(filename);
            if (DateUtils.inRange(date, beginDate, endDate)) {
                JournalEntry entry = getJournalFromDate(date);
                view.displayString(filename);
                view.displayString(entry.getContentPreview(45));
            }
        }
    }

    JournalEntry getJournalFromDate(LocalDate date) throws Exception {
        JournalEntry entry = null;
        // get file pathname from date
        String pathname = FileIO.ROOT + DateUtils.getString(date) + ".txt";
        // get file, returns null if it doesn't exist
        File file = FileIO.getFile(pathname);
        if (file != null) {
            // get file contents
            String content = FileIO.readFromFile(pathname);
            // create a journal entry
            entry = new JournalEntry(date, content);
        }

        return entry;
    }

    void saveJournalEntry(JournalEntry entry) {
        String pathname = FileIO.ROOT + DateUtils.getString(entry.getDate()) + ".txt";
        FileIO.writeToFile(pathname, entry.getContent());
    }

    LocalDate getUserDate(String prompt) {
        while (true) {
            try {
                String dateString = view.getString(prompt);
                LocalDate date = DateUtils.getDate(dateString);
                // check if date is after now, if so throw exception (hint! hint! hint!)
                return date;
            } catch (Exception ex) {
                view.displayString("Invalid date.");
            }
        }
    }
}
