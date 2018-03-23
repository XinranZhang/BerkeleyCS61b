public class OffByN implements CharacterComparator {

    int n;
    // The OffBYN constructor should return an object whose equalChars method
    // returns true for characters that are off by N. For example the call
    // to equal chars below should return true, since a and f are off by 5 letters,
    // but the second call would return false since f and h are off by 4 letters.
    public OffByN(int N) {
        n = N;
    }

    @Override
    public boolean equalChars(char x, char y) {
        if (x - y == n || x - y == -n) {
            return true;
        }
        return false;
    }
}
