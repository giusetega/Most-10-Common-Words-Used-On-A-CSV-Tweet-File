package lesson15;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Tweets {
    private HashMap<String, Integer> listWordsCounter;
    private TreeMap<Integer, String> list10MostCommonWords;

    public Tweets(){
        this.listWordsCounter = new HashMap<>();
        this.list10MostCommonWords = new TreeMap<>(Collections.reverseOrder());
    }

    public void readFile(String str){
        String path = "C:/Users/giuseppe/Desktop/" + str;
        File file = new File(path);
        String[] lineArr;
        String[] content;
        if (file.exists()) {
            System.out.println("The file exists");
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                // Skip the first line
                while ((line = br.readLine()) != null) {
                    // Split every single line into an array
                    lineArr = line.split(",");
                    // Split the interested array's field (content) in other pieces, in individuals Strings
                    content = lineArr[2].toLowerCase().split(" ");
                    count10Word(content);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Ehy dude, the file's name you entered does not exist");
        }
        copyValueIntoTreeMap();
    }

    private void count10Word(String[] arr){
        for (int i = 0; i < arr.length; i++){
            // Match only string
            if (arr[i].matches("[a-z]+")) {
                // If that word does not exist
                if (!listWordsCounter.containsKey(arr[i])) {
                    // Create new Key, Value
                    listWordsCounter.putIfAbsent(arr[i], 1);
                } else {
                    // Update the old value given the key increasing it by one
                    listWordsCounter.put(arr[i], listWordsCounter.get(arr[i]) + 1);
                }
            }
        }
    }

    private void copyValueIntoTreeMap(){
        for (Map.Entry<String, Integer> entry : listWordsCounter.entrySet()) {
            // Copy HashMap k,v to SetMap v,k (I need to put the Integer value first)
            list10MostCommonWords.put(entry.getValue(), entry.getKey());
        }
    }

    public void printTreeMap() {
        int count = 1;
        for (Map.Entry<Integer, String> entry : list10MostCommonWords.entrySet()) {
            System.out.println(count + " place: " + entry.getKey() + " times -> " + entry.getValue());
            if (count++ >= 10)
                break;
        }
    }
    
}
