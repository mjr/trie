import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class TrieTree {
    private TrieNode root;
    private List<String> wordList = new ArrayList<>();

    public TrieTree() {
        this.root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode node = this.root;

        for (char character: word.toCharArray()) {
            if (!node.getChildren().containsKey(character)) {
                node.getChildren().put(character, new TrieNode());
            }

            node = node.getChildren().get(character);
        }

        node.setWord(true);
    }

    public void insert(String[] wordList) {
        for (String word : wordList) {
            this.insert(word);
        }
    }

    public boolean check(String word) {
        TrieNode node = this.root;
        boolean found = true;

        for (char character: word.toCharArray()) {
            if (!node.getChildren().containsKey(character)) {
                found = false;
                break;
            }

            node = node.getChildren().get(character);
        }

        return node != null && node.isWord() && found;
    }

    public void remove(String word) {
        TrieNode node = this.root;
        if (this.check(word)) {
            for (char character: word.toCharArray()) {
                node = node.getChildren().get(character);
            }

            node.setWord(false);
        }
    }

    public void updateWordList(TrieNode node, String word) {
        if (node.isWord()) {
            this.wordList.add(word);
        }

        for (Map.Entry<Character, TrieNode> entry: node.getChildren().entrySet()) {
            this.updateWordList(entry.getValue(), word + entry.getKey());
        }
    }

    public int checkSuggestions(String word) {
        TrieNode node = this.root;
        boolean notFound = false;
        String temporaryWord = "";
        this.wordList = new ArrayList<>();

        for (char character: word.toCharArray()) {
            if (!node.getChildren().containsKey(character)) {
                notFound = true;
                break;
            }

            temporaryWord += character;
            node = node.getChildren().get(character);
        }

        if (notFound) {
            return 0;
        } else if (node.isWord() && node.getChildren() == null) {
            return -1;
        }

        this.updateWordList(node, temporaryWord);

        return 1;
    }

    public boolean existSuggestions(int result) {
        if (result == -1 || result == 0) {
            return false;
        }

        return true;
    }

    public void printFeedbackForNoSuggestions(int result) {
        if (result == -1) {
            System.out.println("No other strings found with this prefix.");
        } else if (result == 0) {
            System.out.println("No string found with this prefix.");
        }
    }

    public void suggestions(String word) {
        int result = this.checkSuggestions(word);
        if (this.existSuggestions(result)) {
            Collections.sort(this.wordList, Comparator.comparing(String::length));
            this.wordList.forEach(System.out::println);
        } else {
            this.printFeedbackForNoSuggestions(result);
        }
    }

    public void suggestions(String word, int maxSuggestions) {
        int result = this.checkSuggestions(word);
        if (this.existSuggestions(result)) {
            for (int i = 0; i < maxSuggestions; i++) {
                System.out.println(this.wordList.get(i));
            }
        } else {
            this.printFeedbackForNoSuggestions(result);
        }
    }

    public List<String> getSuggestions(String word, int maxSuggestions) {
        List<String> list = new ArrayList<>();
        int result = this.checkSuggestions(word);
        if (this.existSuggestions(result)) {
            for (int i = 0; i < maxSuggestions; i++) {
                list.add(this.wordList.get(i));
            }
        }

        return list;
    }

    public List<String> getSuggestions(String word) {
        List<String> list = new ArrayList<>();
        int result = this.checkSuggestions(word);
        if (this.existSuggestions(result)) {
            Collections.sort(this.wordList, Comparator.comparing(String::length));
            this.wordList.forEach(w -> list.add(w));
        }

        return list;
    }
}
