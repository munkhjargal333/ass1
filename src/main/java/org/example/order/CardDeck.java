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
            
        if(!st.getResults().get(rep-2)){
            flag = false;
           }
        }
        return flag;
    }

    public int countMaxCorrect() {
        int maxCorrect = 0;
        for (CardStatus st : status) {
            if(st.countCorrect()> maxCorrect){
                maxCorrect = st.countAnswer();
            }
        }
        return maxCorrect;
    }

    public int maxAnswer(){
        int maxAnswer = 0;
        for (CardStatus st : status) {
            if(st.countAnswer()> maxAnswer){
                maxAnswer = st.countAnswer();
            }
        }
        return maxAnswer;
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


    public boolean isComplete(int repetitions) {
        Boolean flag = true;
        for (CardStatus st : status) {
            if(st.countCorrect() < repetitions){
               flag = false;
            }
        }
        return flag;
    }


    public int countCards() {
        return status.size();
    }
}
