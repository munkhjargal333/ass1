package org.example.order.prioritization;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.example.card.CardStatus;
import org.example.order.CardOrganizer;

public class RandomSorter implements CardOrganizer {

    private final Random random = new Random();

    @Override
    public List<CardStatus> reorganize(List<CardStatus> cards) {
        Collections.shuffle(cards, random);
        return cards;
    }
}
