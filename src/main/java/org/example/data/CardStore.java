package org.example.data;
import java.util.List;

import org.example.card.FlashCard;

public interface CardStore {
    List<FlashCard> getAllCards();
    boolean addCard(FlashCard card);
    boolean removeCard(FlashCard card);
    CardStore invertCards();
}
