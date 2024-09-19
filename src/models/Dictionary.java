package models;

import java.util.TreeMap;

public class Dictionary {
    private TreeMap<String, String> words = new TreeMap<String, String>();

    public TreeMap<String, String> getWords() {
        return words;
    }

    public void insert (Word word) {
        words.put(word.getWord_target(), word.getWord_explain());
    }

    public void remove (String word_target) {
        words.remove(word_target.toLowerCase());
    }

    public String translate (String word_explain) {
        return words.get(word_explain.toLowerCase());
    }
}
