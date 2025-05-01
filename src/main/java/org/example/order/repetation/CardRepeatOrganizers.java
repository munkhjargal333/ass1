package org.example.order.repetation;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CardRepeatOrganizers {

    public static CardOrganizer newCardRepeater(Predicate<CardStatus> isComplete) {
        return new CardOrganizer() {
            @Override
            public List<CardStatus> reorganize(List<CardStatus> cards) {
                return cards.stream()
                        .filter(card -> !isComplete.test(card)) // isNotComplete
                        .collect(Collectors.toList());
            }
        };
    }

    public static CardOrganizer newNonRepeatingCardOrganizer() {
        return newCardRepeater(card -> card.getResults().size() > 0);
    }

    public static CardOrganizer newRepeatingCardOrganizer(int repetitions) {
        if (repetitions < 1) {
            throw new IllegalArgumentException("repetitions must be positive");
        }

        return newCardRepeater(card -> {
            long correctCount = card.getResults().stream().filter(result -> result).count();
            return correctCount >= repetitions;
        });
    }

    // Interfaces (та тусад нь байршуулж болно)
    public interface CardOrganizer {
        List<CardStatus> reorganize(List<CardStatus> cards);
    }

    public interface CardStatus {
        List<Boolean> getResults();
    }
}
