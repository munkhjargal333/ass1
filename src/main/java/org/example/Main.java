package org.example;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.example.card.FlashCard;
import org.example.data.CardLoader;
import org.example.data.CardStore;
import org.example.order.CardDeck;
import org.example.order.CardOrganizer;
import org.example.order.prioritization.RecentMistakesFirstSorter;

public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length == 0 || Arrays.asList(args).contains("--help")) {
            System.out.println("""
            Usage: flashcard <cards-file> [options]
            Options:
              --help                     Show help message
              --order <order>           [random | worst-first | recent-mistakes-first] (default: random)
              --repetitions <num>       Number of correct answers required (default: 1)
              --invertCards             Flip question and answer
            """);
            return;
        }

        String file =  "flashcards.txt";  //args[0];
        int repetitions = 1;
        boolean invert = false;
        String order = "random";

        for (int i = 1; i < args.length; i++) {
            switch (args[i]) {
                case "--invertCards" -> invert = true;
                case "--order" -> order = args[++i];
                case "--repetitions" -> repetitions = Integer.parseInt(args[++i]);
            }
        }

        CardStore cards = CardLoader.loadCards(file, invert);

        //CardOrganizer organizer = (CardOrganizer) new RecentMistakesFirstSorter();

        CardOrganizer organizer = switch (order) {
            case "recent-mistakes-first" -> (CardOrganizer) new RecentMistakesFirstSorter();
            //case "worst-first" -> (CardOrganizer) new 
            default -> (CardOrganizer) new RecentMistakesFirstSorter();
        };
        
        
        List<FlashCard> flashCards = cards.getAllCards();  

        System.out.println("Loaded cards: " + flashCards.size());

        CardDeck cardDeck = new CardDeck(flashCards, organizer);

        ConsoleUI consoleUI = new ConsoleUI();
        consoleUI.studyCards(cardDeck, repetitions);
    }
}
