import java.util.HashMap;
import java.util.Map;

public class TrieNode {
    private boolean isWord;
    private Map<Character, TrieNode> children = new HashMap<>();

    public TrieNode() {
        this.isWord = false;
    }

    public boolean isWord() {
        return isWord;
    }

    public void setWord(boolean word) {
        isWord = word;
    }

    public Map<Character, TrieNode> getChildren() {
        return children;
    }
}
