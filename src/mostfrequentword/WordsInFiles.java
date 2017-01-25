/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mostfrequentword;

import edu.duke.DirectoryResource;
import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

/**
 *
 * @author New
 */
public class WordsInFiles {
    private HashMap<String, ArrayList<String>> wordsMap;
    
    public WordsInFiles(){
        this.wordsMap =  new HashMap<>();
    }
    
    private void addWordsFromFile(File f) throws IOException{
        String str = new String(Files.readAllBytes(f.toPath()));
        str = str.trim();
        ArrayList<Integer> indexOfSpace = new ArrayList<>();
        for (int i = 0; i < str.trim().length();  i++){
            if (str.charAt(i) == ' '){
                indexOfSpace.add(i);
            }
        }
        String word;
        int start;
        int end = 0;
        int sizeOfWordsMap = indexOfSpace.size();
        String filename = f.getName();
        for (int i = 0; i <= sizeOfWordsMap; i++){
            if (i == 0){
                end = indexOfSpace.get(i);
                word = str.substring(0, end);
                ArrayList<String> arrayFileName = new ArrayList<>();
                if (!wordsMap.containsKey(word)){
                    arrayFileName.clear();
                    arrayFileName.add(filename);
                    wordsMap.put(word, arrayFileName); 
                } else if (wordsMap.containsKey(word)){
                    arrayFileName = wordsMap.get(word);
                    if (!arrayFileName.contains(filename)){
                        arrayFileName.add(filename);
                    }
                    wordsMap.put(word, arrayFileName);
                }
            } else if (i > 0 && i < sizeOfWordsMap){
                start = end + 1;
                end = indexOfSpace.get(i);
                word = str.substring(start, end);
                ArrayList<String> arrayFileName = new ArrayList<>();
                if (!wordsMap.containsKey(word)){
                    arrayFileName.clear();
                    arrayFileName.add(filename);
                    wordsMap.put(word, arrayFileName); 
                } else if (wordsMap.containsKey(word)){
                    arrayFileName = wordsMap.get(word);
                    if (!arrayFileName.contains(filename)){
                        arrayFileName.add(filename);
                    }
                    wordsMap.put(word, arrayFileName);
                }
            } else if (i == sizeOfWordsMap){
                start = end + 1;
                end = str.length();
                word = str.substring(start, end);
                ArrayList<String> arrayFileName = new ArrayList<>();
                if (!wordsMap.containsKey(word)){
                    arrayFileName.clear();
                    arrayFileName.add(filename);
                    wordsMap.put(word, arrayFileName); 
                } else if (wordsMap.containsKey(word)){
                    arrayFileName = wordsMap.get(word);
                    if (!arrayFileName.contains(filename)){
                        arrayFileName.add(filename);
                    }
                    wordsMap.put(word, arrayFileName);
                }
            }
        }
    }
    
    public void buildWordFileMap() throws IOException{
        wordsMap.clear();
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
            addWordsFromFile(f);
        }
        printFilesIn("cats");
    }  
    
    private int maxNumber(){
        int maxNumber = 0;
        for (Map.Entry<String, ArrayList<String>> entry : wordsMap.entrySet()){
            if (entry.getValue().size() > maxNumber){
                maxNumber = entry.getValue().size();
            }
        }
        return maxNumber;
    }
    
    private ArrayList wordsInNumFiles(int number){
        ArrayList<String> words = new ArrayList();
        for (Map.Entry<String, ArrayList<String>> entry : wordsMap.entrySet()){
            if (entry.getValue().size() == number){
                words.add(entry.getKey());
            }
        }
        return words;
    }
    
    private void printFilesIn(String word){
        for (Map.Entry<String, ArrayList<String>> entry : wordsMap.entrySet()){
            if (word.equals(entry.getKey())){
                System.out.print(entry.getKey() + ":");
                for (int i = 0; i < entry.getValue().size(); i++){
                    System.out.print(" " + entry.getValue().get(i));
                }
            }
        }
    }
}
