package org.example.order.prioritization;

import java.util.List;

import org.example.card.CardStatus;
import org.example.order.CardOrganizer;

public class RecentMistakesFirstSorter implements CardOrganizer {

    @Override
    public List<CardStatus> reorganize(List<CardStatus> cards) {
        cards.sort((a, b) -> {
            int aLastMistake = lastIndexOfFalse(a.getResults());
            int bLastMistake = lastIndexOfFalse(b.getResults());
            return Integer.compare(bLastMistake, aLastMistake); // descending
        });
        return cards;
    }

    private static int lastIndexOfFalse(List<Boolean> results) {
        for (int i = results.size() - 1; i >= 0; i--) {
            if (!results.get(i)) {
                return i;
            }
        }
        return -1;
    }
}
