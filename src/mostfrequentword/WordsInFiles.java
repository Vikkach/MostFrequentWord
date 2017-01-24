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
        String str = "love birds and cats";
        ArrayList<Integer> indexOfSpace = new ArrayList<>();
        ArrayList<String> words = new ArrayList<>();
        for (int i = 0; i < str.trim().length();  i++){
            if (str.charAt(i) == ' '){
                indexOfSpace.add(i);
            }
        }
        String word = null;
        int start = 0;
        int end = 0;
        int sizeOfWordsMap = indexOfSpace.size();
        for (int i = 0; i <= sizeOfWordsMap; i++){
            if (i == 0){
                end = indexOfSpace.get(i);
                word = str.substring(0, end);
                words.add(word);
            } else if (i > 0 && i < sizeOfWordsMap){
                start = end + 1;
                int last = i + 1;
                end = indexOfSpace.get(i);
                word = str.substring(start, end);
                words.add(word);
            } else if (i == sizeOfWordsMap){
                start = end + 1;
                end = str.length();
                word = str.substring(start, end);
                words.add(word);
            }
        }
    }
    
}
