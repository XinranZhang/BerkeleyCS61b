public class Palindrome {

    public Palindrome() {

    }

    // build a Deque where the characters in the deque appear in the same order as in the word.
    public static Deque<Character> wordToDeque(String word) {
        Deque<Character> newdeque = new ArrayDequeSolution<>();
        for (int i = 0; i < word.length(); i++) {
            newdeque.addLast(word.charAt(i));
        }
        return newdeque;
    }

    //return true if the given word is a palindrome, and false otherwise.
    // A palindrome is defined as a word that is the same whether it is read forwards or backwards.
    // For example "a", "racecar", and "noon" are all palindromes. "horse", "rancor", and "aaaaab" are not palindromes.
    // Any word of length 1 or 0 is a palindrome.
    public static boolean isPalindrome(String word) {
        if (word == null || word.length() == 0 || word.length() == 1) {
            return true;
        }

        if (word.charAt(0) == word.charAt(word.length() - 1)) {
            return isPalindrome(word.substring(1, word.length() - 1));
        }

        return false;

    }

    //return true if the word is a palindrome according to the character comparison test
    // provided by the CharacterComparator passed in as argument cc
    //To allow for odd length palindromes, we do not check the middle character for equality with itself.
    public static boolean isPalindrome(String word, CharacterComparator cc) {
        if (word.length() == 0 || word.length() == 1) {
            return true;
        }

        if (cc.equalChars(word.charAt(0), word.charAt(word.length() - 1))) {
                return isPalindrome(word.substring(1, word.length() - 1), cc);
        }

        return false;
    }
}
