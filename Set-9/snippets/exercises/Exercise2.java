package sample.network.Exercises;

import sample.network.debug.Benchmark;

/**
 * Exercise 1:
 * Create a program that takes two Strings, and returns true if those two words are anagrams, and false otherwise.
 * To do this, it should repeatedly re-arrange the letters in one word until it matches the other.
 */
public class Exercise2{
    /**
     * This method is for benchmarking, completing the second exercise is much more simple than this class
     * would have you think.
     */
    public static void main(String[] args){
        int sentenceSize = 200;
        final int numberOfReiterations = 10;

        //Construct large sentence
        String input = "";
        for(int i = 0; i < sentenceSize; i++){
            input += "This sentence is an anagram of the other sentence";
        }

        final String finalInput = input;
        System.out.println("Single core method: " + Benchmark.milliTimeToRun(() ->{
            for(int i = 0; i < numberOfReiterations; i++){
                testAnagram(finalInput, finalInput);
            }
        }) + "ms");

        System.out.println("Duel core method: " + Benchmark.milliTimeToRun(() -> {
            for(int i = 0; i < numberOfReiterations; i++){
                testAnagramFast(finalInput, finalInput);
            }
        }) + "ms");
    }

    /**
     * Tests if word1 and word2 are anagrams, by splitting the work into two chunks for two threads.
     * Uses testAnagram(String, String).
     */
    public static boolean testAnagramFast(String word1, String word2){
        //Obtain the first halfs of both words
        String firstHalf1 = word1.substring(0, word1.length() / 2);
        String firstHalf2 = word1.substring(0, word2.length() / 2);

        //Obtain the second halfs of both words
        String secondHalf1 = word1.substring(word1.length() / 2);
        String secondHalf2 = word1.substring(word2.length() / 2);

        boolean equal[] = new boolean[2];
        //Have a separate thread check the first half
        new Thread(() -> {
            equal[0] = testAnagram(firstHalf1, firstHalf2);
        }).start();

        //check the second half with this thread.
        equal[1] = testAnagram(secondHalf1, secondHalf2);

        return equal[0] && equal[1];
    }
    /**
     * Tests if word1 and word2 are anagrams using the checkoff method.
     */
    public static boolean testAnagram(String word1, String word2){

        if(word1.length() != word2.length()){
            return false;
        }

        char[] chars1 = word1.toCharArray();
        char[] chars2 = word1.toCharArray();

        for(int i = 0; i < chars1.length; i++){
            word2 = word2.replaceFirst(String.valueOf(chars2[i]), "");
        }

        return word2.equals("");
    }
}
