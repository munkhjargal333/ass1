package org.example;

import java.util.Scanner;

import org.example.card.CardStatus;
import org.example.card.FlashCard;
import org.example.order.CardDeck;

public class ConsoleUI implements UI {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void studyCards(CardDeck producer, int repetitions) {
        double totalTime = 0;
        System.out.println(producer.countCards() + " cards to go...");
        int repCount = 0;

        while(!producer.isComplete(repetitions)) {
    
            System.out.println("-------------Started repetition " + (repCount + 1));
            long start = System.currentTimeMillis();
    
            cueAllCards(producer);
    
            long end = System.currentTimeMillis();
            double elapsedSeconds = (end - start) / 1000.0;
            totalTime += elapsedSeconds;
    
            System.out.println("Reached the end of the card deck, reorganizing...");
            producer.reorganize();
    
            System.out.println("-------------Finished repetition " + (repCount + 1));
            repCount++;
        }
    
        System.out.println();
        System.out.println("===== 🧠 Performance Summary =====");
    
        double avgTimePerCard = totalTime / (producer.getCards().size() * repCount);
        if (avgTimePerCard < 5) {
            System.out.println("🏆 Achievement Unlocked: FAST (avg. " + String.format("%.2f", avgTimePerCard) + " sec/card)");

            if (producer.checkLastCycle(repCount)) {
                System.out.println("🎯 Achievement Unlocked: CORRECT (All answers correct in last cycle)");
            }
    
            if (producer.maxAnswer() >= 5) {
                System.out.println("🔁 Achievement Unlocked: REPEAT (Some card answered 5+ times)");
            }  
    
            if (producer.countMaxCorrect() >= 3) {
                System.out.println("💪 Achievement Unlocked: CONFIDENT (Some card answered correctly 3+ times)");
            }
        }
    
    
        System.out.println("=================================");
        System.out.println("✅ Study session complete!");
    }
    
    

    private void cueAllCards(CardDeck producer) {
        for (CardStatus cardStatus : producer.getCards()) {
            FlashCard card = cardStatus.getCard();
            boolean correctAnswer = cueCard(card);
            cardStatus.recordResult(correctAnswer);
        }
    }

    private boolean cueCard(FlashCard card) {
        System.out.println("\nNext cue: " + card.getQuestion());
        System.out.print("answer> ");
        String input = scanner.nextLine();
        boolean success = card.checkSuccess(input);
        if (success) {
            System.out.println("That's correct!");
        } else {
            System.out.println("That is incorrect; the correct response was: " + card.getAnswer());
        }
        return success;
    }
}


interface UI {
    void studyCards(CardDeck producer, int repetitions);
}

