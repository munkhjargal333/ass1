package org.example.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.example.card.FlashCard;

public class CardLoader {
    /**
     * Loads flashcards from a file.
     * Each line must be in the format: answer--question
     *
     * @param filename The path to the input file.
     * @param invert Whether to flip question and answer.
     * @return A CardStore instance populated with parsed flashcards.
     * @throws IOException if file can't be read.
     */
    public static CardStore loadCards(String filename, boolean invert) throws IOException {
        List<FlashCard> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                int idx = line.indexOf("--");
                if (idx > 0) {
                    String left = line.substring(0, idx).trim();
                    String right = line.substring(idx + 2).trim();
                    if (invert) {
                        result.add(new FlashCard(left, right));  // left is question, right is answer
                    } else {
                        result.add(new FlashCard(right, left));  // right is question, left is answer
                    }
                }
            }
        }
        return new InMemoryCardStore(result);
    }
}
