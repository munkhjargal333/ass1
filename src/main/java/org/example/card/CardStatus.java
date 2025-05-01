package org.example.card;
import java.util.ArrayList;
import java.util.List;


public class CardStatus {
    private final FlashCard card;
    private final List<Boolean> results;

    /**
     * Creates a new CardStatus instance.
     *
     * @param card The FlashCard to track answer correctness for.
     */
    public CardStatus(FlashCard card) {
        this.card = card;
        this.results = new ArrayList<>();
    }

    /**
     * Retrieves the FlashCard associated with this CardStatus.
     *
     * @return The associated FlashCard.
     */
    public FlashCard getCard() {
        return card;
    }

    /**
     * Retrieves the record of past successes at answering this card.
     *
     * @return A list of booleans indicating the recorded outcome of previous attempts.
     */
    public List<Boolean> getResults() {
        return new ArrayList<>(results); // Return a copy to maintain immutability
    }

    /**
     * Updates the internal success tracker with a new answering outcome.
     *
     * @param success true if this card was answered correctly.
     */
    public void recordResult(boolean success) {
        results.add(success);
    }

    /**
     * Resets the record of past answering outcomes.
     */
    public void clearResults() {
        results.clear();
    }
}
