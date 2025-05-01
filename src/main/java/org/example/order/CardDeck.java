package org.example.order;

import java.util.ArrayList;
import java.util.List;

import org.example.card.CardStatus;
import org.example.card.FlashCard;

/**
 * A card deck represents a set of cards in a specific order with associated state and a mechanism to filter and reorder
 * them.
 */
public class CardDeck {
    private List<CardStatus> status;
    private final CardOrganizer cardOrganizer;

    public CardDeck(List<FlashCard> cards, CardOrganizer cardOrganizer) {
        this.status = new ArrayList<>();
        for (FlashCard card : cards) {
            this.status.add(new CardStatus(card));
        }
        this.cardOrganizer = cardOrganizer;
    }

    public  Boolean checkLastCycle(int rep){
        boolean flag = true;
        for (CardStatus st : status) {
            
        if(!st.getResults().get(rep-1)){
            flag = false;
           }
        }
        return flag;
    }

    public int countMaxCorrect() {
        int maxCorrect = 0;
        for (CardStatus st : status) {
            int correct = 0;
            for (Boolean result : st.getResults()) {
                if (result) {
                    correct++;
                }
            }
            if (correct > maxCorrect) {
                maxCorrect = correct;
            }
        }
        return maxCorrect;
    }
    

    public List<CardStatus> getCards() {
        return new ArrayList<>(status);
    }


    public CardOrganizer getOrganizer() {
        return cardOrganizer;
    }


    public void reorganize() {
        this.status = cardOrganizer.reorganize(status);
    }


    public boolean isComplete() {
        return status.isEmpty();
    }


    public int countCards() {
        return status.size();
    }
}
