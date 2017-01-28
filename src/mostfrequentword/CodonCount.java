/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mostfrequentword;

import edu.duke.FileResource;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Виктория
 */
public class CodonCount {

    private static HashMap<String, Integer> DNAMap;

    public CodonCount() {
        CodonCount.DNAMap = new HashMap<>();
    }

    private void buildCodonMap(int start, String dna) {
        for (int i = start; i < dna.length() - 2; i = i + 3) {
            String codon = dna.substring(i, i + 3);
            if (DNAMap.containsKey(codon)) {
                int count = DNAMap.get(codon);
                DNAMap.put(codon, count + 1);
            } else {
                DNAMap.put(codon, 1);
            }
        }
    }

    private String getMostCommonCodon() {
        int maxCount = 0;
        String maxCodon = "";
        for (Map.Entry<String, Integer> entry : DNAMap.entrySet()) {
            if (maxCount < entry.getValue()) {
                maxCount = entry.getValue();
                maxCodon = entry.getKey();
            }
        }
        return maxCodon;
    }

    private void printCodonCounts(int start, int end) {
        for (Map.Entry<String, Integer> entry : DNAMap.entrySet()) {
            if (entry.getValue() >= start && entry.getValue() <= end) {
                System.out.println(entry.getKey() + " " + entry.getValue());
            }
        }
    }

    public void test() {
        FileResource fr = new FileResource();
        String DNA = fr.asString().trim().toUpperCase();
        //buildCodonMap(0, DNA);
        buildCodonMap(1, DNA);
        //buildCodonMap(2, DNA);
        //System.out.println(DNAMap.size());
        printCodonCounts(6, 6);
        //System.out.println(getMostCommonCodon());
    }
}
