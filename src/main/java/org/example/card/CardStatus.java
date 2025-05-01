package org.example.card;
import java.util.ArrayList;
import java.util.List;


public class CardStatus {
    private final FlashCard card;
    private final List<Boolean> results;

    public CardStatus(FlashCard card) {
        this.card = card;
        this.results = new ArrayList<>();
    }

    public FlashCard getCard() {
        return card;
    }

    public List<Boolean> getResults() {
        return new ArrayList<>(results); // Return a copy to maintain immutability
    }

    public int countCorrect() {
        int correct = 0;
        for (Boolean r : results) {
            if (r) {
                correct++;
            }
        }
        return correct;
    }
    
    public int countAnswer() {
        return results.size();
    }
    

    public void recordResult(boolean success) {
        results.add(success);
    }

    public void clearResults() {
        results.clear();
    }
}
