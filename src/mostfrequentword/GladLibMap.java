/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mostfrequentword;

import edu.duke.*;
import java.util.*;

public class GladLibMap {
    
    private HashMap<String, ArrayList<String>> myMap;
    private ArrayList<String> usedWords;
    private ArrayList<String> usedCategory;
    
    private Random myRandom;
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";
    
    public GladLibMap() {
        this.myMap = new HashMap<>();
        this.usedWords = new ArrayList<>();
        this.usedCategory = new ArrayList<>();
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
    }
    
    public GladLibMap(String source) {
        initializeFromSource(source);
        myRandom = new Random();
    }
    
    private void initializeFromSource(String source) {
        ArrayList<String> arrayList = new ArrayList<>();
        ArrayList<String> categoryList = new ArrayList<>();
        categoryList.add("adjective");
        categoryList.add("noun");
        categoryList.add("color");
        categoryList.add("country");
        categoryList.add("name");
        categoryList.add("animal");
        categoryList.add("timeframe");
        categoryList.add("verb");
        categoryList.add("fruit");
        for (int i = 0; i < categoryList.size(); i++) {
            String category = categoryList.get(i);
            arrayList = readIt(source + "/" + category + ".txt");
            myMap.put(category, arrayList);
        }
    }
    
    private String randomFrom(ArrayList<String> source) {
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute(String label) {
        for (Map.Entry<String, ArrayList<String>> entry : myMap.entrySet()) {
            String category = entry.getKey();
            if (label.equals(category)) {
                if (!usedCategory.contains(category)) {
                    usedCategory.add(category);
                }
                return randomFrom(entry.getValue());
            }
        }
        return "**UNKNOWN**";
    }
    
    private String processWord(String w) {
        int first = w.indexOf("<");
        int last = w.indexOf(">", first);
        if (first == -1 || last == -1) {
            return w;
        }
        String prefix = w.substring(0, first);
        String suffix = w.substring(last + 1);
        String sub = getSubstitute(w.substring(first + 1, last));
        if (usedWords.indexOf(sub) == -1) {
            usedWords.add(sub);
        } else if (usedWords.indexOf(sub) != -1) {
            while (usedWords.contains(sub)) {
                sub = getSubstitute(w.substring(first + 1, last));
                if (usedWords.indexOf(sub) == -1) {
                    usedWords.add(sub);
                    break;
                }
            }
        }
        return prefix + sub + suffix;
    }
    
    private void printOut(String s, int lineWidth) {
        int charsWritten = 0;
        for (String w : s.split("\\s+")) {
            if (charsWritten + w.length() > lineWidth) {
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w + " ");
            charsWritten += w.length() + 1;
        }
    }
    
    private String fromTemplate(String source) {
        usedWords.clear();
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for (String word : resource.words()) {
                story = story + processWord(word) + " ";
            }
        } else {
            FileResource resource = new FileResource(source);
            for (String word : resource.words()) {
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    private ArrayList<String> readIt(String source) {
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for (String line : resource.lines()) {
                list.add(line);
            }
        } else {
            FileResource resource = new FileResource(source);
            for (String line : resource.lines()) {
                list.add(line);
            }
        }
        return list;
    }
    
    public void makeStory() {
        System.out.println("\n");
        String story = fromTemplate("data/madtemplate3.txt");
        printOut(story, 60);
        System.out.println("\n Total number of words that were replaced: " + usedWords.size());
        System.out.println(totalWordsInMap());
        System.out.println(totalWordsConsidered());
    }
    
    private int totalWordsInMap() {
        int totalWords = 0;
        for (Map.Entry<String, ArrayList<String>> entry : myMap.entrySet()) {
            for (String value : entry.getValue()) {
                totalWords++;
            }
        }
        return totalWords;
    }
    
    private int totalWordsConsidered() {
        int totalWords = 0;
        for (Map.Entry<String, ArrayList<String>> entry : myMap.entrySet()) {
            for (int i = 0; i < usedCategory.size(); i++) {
                if (entry.getKey().equals(usedCategory.get(i))) {
                    totalWords = totalWords + entry.getValue().size();
                }
            }
        }
        return totalWords;
    }
    
}
