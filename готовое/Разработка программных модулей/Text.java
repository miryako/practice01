package tasks1_10;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


class Word {
    String word;

    public Word(String word) {
        this.word = word;
    }
    public String getWord() {
        return word;
    }
    public void setWord(String word) {
        this.word = word;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Word word1)) return false;
        return getWord().equals(word1.getWord());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getWord());
    }

    @Override
    public String toString() {
        return word;
    }
}

class Sentence {
    List<Word> sentence;

    public Sentence(List<Word> sentence) {
        this.sentence = sentence;
    }
    public List<Word> getSentence() {
        return sentence;
    }
    public void setSentence(List<Word> sentence) {
        this.sentence = sentence;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sentence sentence1)) return false;
        return getSentence().equals(sentence1.getSentence());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSentence());
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Word word: sentence) {
            builder.append(word.getWord()).append(" ");
        }
        builder.append(".");
        return builder.toString().trim();
    }
}

public class Text {
    List<Sentence> text;
    String title;

    public Text(List<Sentence> text, String title) {
        this.text = text;
        this.title = title;
    }

    public List<Sentence> getText() {
        return text;
    }
    public void setText(List<Sentence> text) {
        this.text = text;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Text text1)) return false;
        return getText().equals(text1.getText()) && getTitle().equals(text1.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getText(), getTitle());
    }

    public void addSentence(Sentence newSentence) {
        text.add(newSentence);
    }

    public void printText() {
        for (Sentence sentence: text) {
            System.out.println(sentence);
        }
    }

    public void printTitle() {
        System.out.println(title);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(title).append("\n");
        for (Sentence sentence: text) {
            builder.append(sentence).append("");
        }
        return builder.toString().trim();
    }

    public static void main(String[] args) {
        List<Word> words = new ArrayList<>();
        List<Word> words2 = new ArrayList<>();
        List<Sentence> sentences = new ArrayList<>();

        String[] manyWords = {"Цель", "СУБД", "заключается", "в", "том,", "чтобы"};
        for (int i = 0; i < manyWords.length; i++) {
            Word word = new Word(manyWords[i]);
            words.add(word);
        }

        String[] manyWords2 = {"представить", "абстрактное", "представление", "БД"};
        for (int i = 0; i < manyWords2.length; i++) {
            Word word = new Word(manyWords2[i]);
            words2.add(word);
        }

        Sentence sentence = new Sentence(words);
        sentences.add(sentence);

        Text text = new Text(sentences, "OMG!");
        text.addSentence(new Sentence(words2));

        System.out.println(text);
    }
}
