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
import org.example.order.prioritization.WorstFirstSorter;

public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length == 0 || Arrays.asList(args).contains("--help")) {
            System.out.println("""
            flashcard <cards-file> [options]
            Options:
                --help Тусламжийн мэдээлэл харуулах
                --order <order> Зохион байгуулалтын төрөл, default нь "random" [сонголт: "random", "worst-first", "recent-mistakes-first"]
                --repetitions <num> Нэг картыг хэдэн удаа зөв хариулахыг шаардлага болгож тохируулна. Хэрэв тодорхойлохгүй бол зөвхөн нэг удаа асууна.
                --invertCards Тохиргоо идэвхэжсэн бол картын асуулт, хариултыг сольж харуулна.
                Default: false
                """);
            return;
        }

        String file =  args[0];
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

        CardOrganizer organizer = switch (order) {
            case "recent-mistakes-first" -> (CardOrganizer) new RecentMistakesFirstSorter();
            case "worst-first" -> (CardOrganizer) new WorstFirstSorter();
            default -> (CardOrganizer) new RecentMistakesFirstSorter();
        };
        
        
        List<FlashCard> flashCards = cards.getAllCards();  

        System.out.println("Loaded cards: " + flashCards.size());

        CardDeck cardDeck = new CardDeck(flashCards, organizer);

        ConsoleUI consoleUI = new ConsoleUI();
        consoleUI.studyCards(cardDeck, repetitions);
    }
}
