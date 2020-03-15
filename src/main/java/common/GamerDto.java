package common;

import domain.card.Card;
import domain.gamer.Gamer;

import java.util.List;

public class GamerDto {
    private String name;
    private List<Card> cards;

    private GamerDto(String name, List<Card> cards) {
        this.name = name;
        this.cards = cards;
    }

    public static GamerDto of(Gamer gamer) {
        List<Card> cards = gamer.getPlayingCards();
        return new GamerDto(gamer.getName(), cards);
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards;
    }
}
