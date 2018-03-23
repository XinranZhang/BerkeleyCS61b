public class TestOffByN {
    public static void main(String[] args) {
        OffByN offby5 = new OffByN(5);
        System.out.println(offby5.equalChars('a', 'f'));
        System.out.println(offby5.equalChars('f', 'h'));
    }
}
