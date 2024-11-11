package tasks1_10;

import java.util.ArrayList;
import java.util.Objects;

public class Notepad {
    class Note {
        String title, text, date;

        public Note(String title, String text, String date) {
            this.title = title;
            this.text = text;
            this.date = date;
        }

        @Override
        public String toString() {
            return "\nЗапись за " + date + "\n<<" + title + ">>\n" + text;
        }
    }

    ArrayList<Note> notes;

    public Notepad() {
        this.notes = new ArrayList<>();
    }

    public void addNote(String title, String text, String date) {
        notes.add(new Note(title, text, date));
    }

    public void removeNote(int noteNum) {
        notes.remove(noteNum);
    }

    public void printNotes() {
        System.out.println(notes);
    }

    public ArrayList<Note> printNotesBydate(String date) {
        ArrayList<Note> notesByDate = new ArrayList<>();
        for (Note note: notes) {
            if (Objects.equals(note.date, date)) notesByDate.add(note);
        }
        return notesByDate;
    }

    public static void main(String[] args) {
        Notepad notepad = new Notepad();

        notepad.addNote("Утро", "Еду на велосипеде в голове ля-ля", "22.04");
        notepad.addNote("Вечер", "Утро может быть добрым, но только не сегодняшнее", "22.04");
        notepad.addNote("Вечер", "Попросили отнести брата в школу, кота на кастрацию. Перепутал кого-куда", "23.04");
        notepad.addNote("Утро", "Сегодня понедельник? блин...", "26.04");
        notepad.addNote("Вечер", "ТЫ Предал Христа", "29.04");

        System.out.println("Записи за 22 марта:");
        ArrayList<Note> notesByDate = notepad.printNotesBydate("22.04");
        for (Note note: notesByDate) System.out.println(note);
    }
}
