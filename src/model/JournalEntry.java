package model;

import java.time.LocalDate;

public class JournalEntry {
    private LocalDate date;
    private String content;

    //region getter/setter
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    //endregion

    public JournalEntry() {
    }

    public JournalEntry(LocalDate date, String content) {
        this.date = date;
        this.content = content;
    }

    public String getContentPreview(int maxLength) {
        String preview = content;

        if(preview.length() > maxLength){
            preview = preview.substring(0, maxLength);
            int lastSpaceIndex = preview.lastIndexOf(" ");
            preview = preview.substring(0, lastSpaceIndex);
            preview += "...";
        }

        return preview;
    }
}
