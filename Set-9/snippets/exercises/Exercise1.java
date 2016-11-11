/**
 * Exercise 1:
 * Create a program that takes two Strings, and returns true if those two words are anagrams, and false otherwise.
 * To do this, it should repeatedly re-arrange the letters in one word until it matches the other.
 */
public class Exercise1{
    public static void main(String[] args){
        System.out.println(testAnagram("lelHo", "Hello"));
        System.out.println(testAnagram("no", "Hello"));
    }

    /**
     * Tests if word1 and word2 are anagrams using the checkoff method.
     */
    public static boolean testAnagram(String word1, String word2){
        char[] chars1 = word1.toCharArray();
        char[] chars2 = word1.toCharArray();

        if(chars1.length != chars2.length){
            return false;
        }

        for(int i = 0; i < chars1.length; i++){
            word2 = word2.replaceFirst(String.valueOf(chars2[i]), "");
        }

        return word2.equals("");
    }
}
