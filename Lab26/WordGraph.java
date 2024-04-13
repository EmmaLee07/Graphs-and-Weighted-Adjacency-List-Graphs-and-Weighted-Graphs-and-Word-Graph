import java.util.HashMap;
import java.util.Map;

public class WordGraph {
    //INSTANCE VARIABLES
    /**
     *    Keep track of association between consecutive words.
     *    An edge between words (A -> B) means that B came after A
     *    The weight of the edge tells you how many times B came after A
     */
    private WeightedGraph<String> graph;

    /**
     *    Keep track of the lastWord that was added to the graph.
     *    When adding a new word to the graph, add an edge between
     *    the lastWord and the new word.
     */
    private String lastWord;

    //CONSTRUCTOR
    public WordGraph() {
        //initialize instance variables
        graph = new WeightedAdjacencyListGraph<>();
        lastWord = null;
    }

    //METHODS
    /**
     *    "Sanitize" newWord by trimming extra spaces from the edges (use the trim() method)
     *    Add the specified word to the graph.
     *    Add an edge between lastWord and the newWord
     *    Increment the weight between these nodes by 1
     *    Set lastWord to point to newWord
     */
    public void addWord(String newWord) {
        // Sanitize the new word
        newWord = newWord.trim();

        // Check if the new word is empty, and do not add it to the graph
        if (newWord.isEmpty()) {
            return;
        }

        // Add the word to the graph
        graph.add(newWord);

        // If lastWord is not null, add an edge between lastWord and newWord
        if (lastWord != null) {
            // Increment the weight of the edge or set it to 1 if it's the first occurrence
            int weight = graph.hasEdge(lastWord, newWord) ? graph.getWeight(lastWord, newWord) + 1 : 1;
            graph.addEdge(lastWord, newWord);
            graph.setWeight(lastWord, newWord, weight);
        }

        // Update lastWord
        lastWord = newWord;
    }

    /**
     *    Process a string by splitting it on spaces (use the split() method)
     *    and calling addWord() on each word.
     */
    public void processString(String str) {
        // Split the string into words using space as delimiter
        String[] words = str.split("\\s+");
        
        // Process each word
        for (String word : words) {
            addWord(word);
        }
    }

    /**
     *    Process a file by reading each line from a file (using nextLine() method)
     *    and call the processString() method on it.
     */
    public void processFile(String filename) {
        // Implementation to read from file and call processString() method goes here
    }

    /**
     *    Getter method for the weighted graph instance variable
     */
    public WeightedGraph<String> getGraph() {
        return graph;
    }
}
