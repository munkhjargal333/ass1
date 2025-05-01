package org.example.order;

import java.util.ArrayList;
import java.util.List;

import org.example.card.CardStatus;


public class CombinedCardOrganizer implements CardOrganizer {
    private final List<CardOrganizer> organizers;

    public CombinedCardOrganizer(List<CardOrganizer> organizers) {
        this.organizers = organizers;
    }

    @Override
    public List<CardStatus> reorganize(List<CardStatus> cards) {
        List<CardStatus> status = new ArrayList<>(cards); // Copy to avoid mutating input
        for (CardOrganizer organizer : organizers) {
            status = organizer.reorganize(status);
        }
        return status;
    }
}
