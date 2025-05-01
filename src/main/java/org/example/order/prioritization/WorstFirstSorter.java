package org.example.order.prioritization;

import java.util.List;

import org.example.card.CardStatus;
import org.example.order.CardOrganizer;

public class WorstFirstSorter implements CardOrganizer {

    @Override
    public List<CardStatus> reorganize(List<CardStatus> cards) {
        cards.sort((a, b) -> {
            int aMistakeCount = countFalse(a.getResults());
            int bMistakeCount = countFalse(b.getResults());
            return Integer.compare(bMistakeCount, aMistakeCount); // Descending
        });
        return cards;
    }

    private int countFalse(List<Boolean> results) {
        int count = 0;
        for (Boolean b : results) {
            if (!b) count++;
        }
        return count;
    }
}
