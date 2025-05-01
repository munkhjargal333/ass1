package org.example.data;

import java.util.ArrayList;
import java.util.List;

import org.example.card.FlashCard;

public class InMemoryCardStore implements CardStore {
    private final List<FlashCard> cards;

    public InMemoryCardStore(List<FlashCard> initialCards) {
        this.cards = new ArrayList<>(initialCards);
    }

    @Override
    public List<FlashCard> getAllCards() {
        return new ArrayList<>(cards); // Return a copy
    }

    @Override
    public boolean addCard(FlashCard card) {
        for (FlashCard c : cards) {
            if (c.equals(card)) return false;
        }
        cards.add(card);
        return true;
    }

    @Override
    public boolean removeCard(FlashCard card) {
        return cards.remove(card);
    }

    @Override
    public CardStore invertCards() {
        List<FlashCard> inverted = new ArrayList<>();
        for (FlashCard card : cards) {
            inverted.add(new FlashCard(card.getAnswer(), card.getQuestion()));
        }
        return new InMemoryCardStore(inverted);
    }

    // @Override
    // public boolean addCard(FlashCard card) {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'addCard'");
    // }

    // @Override
    // public boolean removeCard(FlashCard card) {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'removeCard'");
    // }
}
