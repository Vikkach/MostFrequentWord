/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mostfrequentword;

import java.util.ArrayList;
import edu.duke.*;

/**
 *
 * @author Виктория
 */
public class MostFrequentWord {
    private static CharactersInPlay test;
    private static GladLib test2;
    private static CodonCount test3;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       //test = new CharactersInPlay();
       //test.tester(); 
       // tester();
        
       //test2 = new GladLib();
       //test2.makeStory();
        test3 = new CodonCount();
        test3.test();
       
    }
    private static ArrayList<String> myWords = new ArrayList<>();
    private static ArrayList<Integer> myFreqs = new ArrayList<>();
    
    public MostFrequentWord(){
        MostFrequentWord.myWords = new ArrayList<>();
        MostFrequentWord.myFreqs = new ArrayList<>();
    }
    
    public static void findUnique(){
        myWords.clear();
        myFreqs.clear();
        FileResource fr = new FileResource();
        int index = 0;
        for (String message : fr.words()){
            message = message.toLowerCase();
            if (myWords.indexOf(message) == -1){
                myWords.add(index, message);
                myFreqs.add(index, 1);
                index++;
            } else{
                int indexOfMessage = myWords.indexOf(message);
                myFreqs.set(indexOfMessage, myFreqs.get(indexOfMessage)+1);
            }            
        }
        System.out.println("Number of unique words: " + myWords.size());
        for (int i = 0; i < myWords.size(); i++){
            System.out.println(myFreqs.get(i) + " " + myWords.get(i));
        }
    }
    
    public static int findIndexOfMax(){
        int maxFreq = 0;
        for (int i = 0; i < myFreqs.size(); i++){
            if (maxFreq < myFreqs.get(i))
                maxFreq = myFreqs.get(i);
        }
        return maxFreq;
    }
    
    public static void tester(){
        findUnique();
        System.out.println("The word that occurs most often and its count are: " + myWords.get(myFreqs.indexOf(findIndexOfMax())) + " " + findIndexOfMax());
    }
    
}
