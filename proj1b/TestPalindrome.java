public class TestPalindrome {
    public static void main(String[] args) {
        Palindrome t1 = new Palindrome();
        System.out.println(t1.isPalindrome("grace"));
        System.out.println(t1.isPalindrome("noon"));
        t1.wordToDeque("grace").printDeque();
        CharacterComparator cc = new OffByOne();
        System.out.println(cc.equalChars('f', 'e'));
        System.out.println(t1.isPalindrome("flake", cc));
    }
}
