package controller;

import models.Dictionary;
import models.Word;

import java.io.*;
import java.util.Map;
import java.util.Scanner;

public class DictionaryManagement {

    public static void insertFromCommandLlne(Dictionary dict) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the number of words adding to dictionary");
        int numsOfWord = Integer.parseInt(scan.nextLine());
        while (numsOfWord > 0) {
            System.out.println("Enter the word adding to dictionary: ");
            String word_target = scan.nextLine();
            System.out.println("Enter the meaning: ");
            String word_explain = scan.nextLine();
            Word newWord = new Word(word_target, word_explain);
            dict.insert(newWord);
            numsOfWord--;
        }
    }


public static void insertFromFile(Dictionary dict) {
    String path = new File("").getAbsolutePath() + "\\src\\data\\dictionaries.txt";
    try {
        BufferedReader bufferedReader =
                new BufferedReader(new InputStreamReader(new FileInputStream(path)));
        String word_target = bufferedReader.readLine();
        String word_explain = bufferedReader.readLine();
        while (word_target != null && word_explain != null) {
            Word newWord = new Word(word_target, word_explain);
            dict.insert(newWord);
            word_target = bufferedReader.readLine();
            word_explain = bufferedReader.readLine();
        }
        bufferedReader.close();
    } catch (FileNotFoundException er) {
        System.out.println("The file not found" + er);
    } catch (IOException ioe) {
        System.out.println("I/O Exception: " + ioe);
    }
}

public static void dictionaryLookup(Dictionary dict) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the word to transtale: ");
        String findWord = scan.nextLine().trim().toLowerCase();
        if(dict.getWords().containsKey(findWord)) {
            System.out.println("The meaning is: " + dict.translate(findWord));
        } else {
            System.out.println("Couldn't find the word in dictionary!");
        }
}

public static void dictionaryEdit(Dictionary dict) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the word you want to translate: ");
        String editWord = scan.nextLine().trim().toLowerCase();
        if(dict.getWords().containsKey(editWord)) {
            System.out.println("Enter the new meaning: ");
            String newWordMeaning = scan.nextLine().trim().toLowerCase();
            dict.getWords().replace(editWord, newWordMeaning);
        } else {
            System.out.println("Couldn't find the word in dictionary!");
        }
}

public static void dictionaryRemove (Dictionary dict) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the word to remove: ");
        String removeWord  = scan.nextLine().trim().toLowerCase();
        if(dict.getWords().containsKey(removeWord)) {
            dict.remove(removeWord);
            System.out.println("Remove word succesfull");
    } else {
        System.out.println("Couldn't find the word in dictionary!");
    }
}

public static void dictionryExportToFile(Dictionary dict) {
        String path = new File("").getAbsolutePath() + "\\src\\main\\data\\dictionaryModified.txt";
        try{
            File file = new File(path);
            if(!file.exists()) {
                file.createNewFile();
            } else {
                file.delete();
                file.createNewFile();
            }
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            for (Map.Entry<String, String> e : dict.getWords().entrySet()) {
                bw.write(e.getKey());
                bw.newLine();
                bw.write(e.getValue());
                bw.newLine();
            }
            bw.close();
        } catch (IOException ioe) {
            System.out.println("Exception occurred: ");
            ioe.printStackTrace();
        }

}
}
