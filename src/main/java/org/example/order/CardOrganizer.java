package org.example.order;

import java.util.List;

import org.example.card.CardStatus;

public interface CardOrganizer {
    /**
     * Orders, and potentially filters, the provided cards.
     *
     * @param cards The CardStatus objects to order.
     * @return The provided cards, sorted and/or filtered based on the implementation.
     */
    List<CardStatus> reorganize(List<CardStatus> cards);
}
