/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mostfrequentword;

import edu.duke.FileResource;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import org.omg.CosNaming.IstringHelper;

/**
 *
 * @author New
 */
public class WordsInFiles {
    private HashMap<String, ArrayList<String>> wordsMap;
    
    public WordsInFiles(){
        this.wordsMap =  new HashMap<>();
    }
    
    public void addWordsFromFile(){
        String str = "love birds and cats love";
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
        String filename = "f1";
        //String filename = f.getName();
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
                    arrayFileName.add(filename);
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
                    arrayFileName.add(filename);
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
                    if (arrayFileName.contains(filename)){
                        arrayFileName.add(filename);
                        wordsMap.put(word, arrayFileName);
                    }
                }
            }
        }
        
       /* for (int i = 0; i < words.size(); i++){
            String key = words.get(i);
            ArrayList<String> nameOfFile = new ArrayList<>();
            if (wordsMap.containsKey(key)){
                nameOfFile.add(f.getName());
                wordsMap.put(key, nameOfFile);
            } else{
                nameOfFile.add(f.getName());
                wordsMap.put(key, nameOfFile);
            }
        }*/
    }
    
}
