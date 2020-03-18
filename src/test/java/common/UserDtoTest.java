package common;

import domain.card.Card;
import domain.card.PlayingCards;
import domain.card.Symbol;
import domain.card.Type;
import domain.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class UserDtoTest {
    private GamerDto gamerDto;

    @BeforeEach
    void setUp() {
        PlayingCards playingCards = new PlayingCards(Collections.singletonList(new Card(Symbol.QUEEN, Type.CLOVER)));
        gamerDto = GamerDto.of(new User(playingCards, "testName"));
    }

    @Test
    void of() {
        assertThat(GamerDto.of(new User(new PlayingCards(Collections.emptyList()), "testName"))).isNotNull();
    }

    @Test
    void getName() {
        assertThat(gamerDto.getName()).isEqualTo("testName");
    }

    @Test
    void getCards() {
        List<Card> cards = Collections.singletonList(new Card(Symbol.QUEEN, Type.CLOVER));
        assertThat(gamerDto.getCards().get(0)).isEqualTo(cards.get(0));
    }
}