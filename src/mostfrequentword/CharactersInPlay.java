package mostfrequentword;


import edu.duke.FileResource;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Виктория
 */
public class CharactersInPlay {
    
    private ArrayList<String> characterName;
    private ArrayList<Integer> replicasNumber;
    
    public CharactersInPlay(){
        this.characterName = new ArrayList<>();
        this.replicasNumber = new ArrayList<>();
    }
    
    public void update(String person){
        int index = 0;
        if (characterName.indexOf(person) == -1){
            characterName.add(index, person);
            replicasNumber.add(index, 1);
            index++;
        } else{
            int indexOfName = characterName.indexOf(person);
            replicasNumber.set(indexOfName, replicasNumber.get(indexOfName)+1);
        }
    }
    
    public void findAllCharacters(){
        characterName.clear();
        replicasNumber.clear();
        FileResource fr = new FileResource();
        for (String line : fr.lines()){
            if (line.indexOf('.') != -1){
                String personName = line.substring(0, line.indexOf('.'));
                update(personName);     
            }           
        }
        /*for (int i = 0; i < characterName.size(); i++){
            System.out.println(characterName.get(i) + " " + replicasNumber.get(i));
        }*/
    }
    
    public int findIndexOfMax(){
        findAllCharacters();
        int maxFreq = 0;
        for (int i = 0; i < replicasNumber.size(); i++){
            if (maxFreq < replicasNumber.get(i))
                maxFreq = replicasNumber.get(i);
        }
        return maxFreq;
    }  
    
    
    public void charactersWithNumParts(int num1, int num2){
        findAllCharacters();
        for (int i = 0; i < replicasNumber.size(); i++){
            if(replicasNumber.get(i) >= num1 && replicasNumber.get(i) <= num2)
                System.out.println(characterName.get(i) + " " + replicasNumber.get(i));
        }
           
    }
    
    public void tester(){
        //System.out.println(findIndexOfMax() + " " +characterName.get(replicasNumber.indexOf(findIndexOfMax())));
        charactersWithNumParts(10, 15);
        //findAllCharacters();
    }
    
}
