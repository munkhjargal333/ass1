package org.example.card;


public class FlashCard {
    private final String question;
    private final String answer;

    public FlashCard(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    /**
     * Checks whether the provided response matches the target.
     * Ignores mismatches in capitalization and any extra leading or trailing whitespace.
     * @param response The user-provided response.
     * @return true if the definition matches the response.
     */
    public boolean checkSuccess(String response) {
        if (response == null) return false;
        return answer.trim().equalsIgnoreCase(response.trim());
    }

    @Override
    public String toString() {
        return "FlashCard[" + question + ", " + answer + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof FlashCard)) return false;
        FlashCard other = (FlashCard) obj;
        return question.equals(other.question) && answer.equals(other.answer);
    }

    @Override
    public int hashCode() {
        return question.hashCode() + 31 * answer.hashCode();
    }
}
