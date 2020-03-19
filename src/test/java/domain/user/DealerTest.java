package domain.user;

import domain.card.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DealerTest {

    Deck deck = mock(Deck.class);

    @Test
    @DisplayName("첫턴에 딜러는 2장의 카드를 받는다.")
    void construct() {
        PlayingCards playingCards = PlayingCards.of(Arrays.asList(new Card(Symbol.ACE, Type.DIAMOND),
                new Card(Symbol.TEN, Type.HEART)));
        assertThat(new Dealer(playingCards, deck, mock(Money.class))).isNotNull();
    }

    @Test
    @DisplayName("딜러의 카드를 추가한다.")
    void addCard() {
        PlayingCards playingCards = PlayingCards.of(new ArrayList<>(Arrays.asList(new Card(Symbol.QUEEN, Type.CLOVER), new Card(Symbol.QUEEN, Type.SPADE))));
        //todo : 왜 여기서 먼저 꺼내와야 하는 지 알아내기
        int defaultCardsSize = playingCards.size();
        Dealer dealer = new Dealer(playingCards, deck, mock(Money.class));
        Card card = new Card(Symbol.ACE, Type.CLOVER);
        dealer.addCard(card);

        assertThat(dealer.countCards()).isEqualTo(defaultCardsSize + 1);
    }

    @Test
    @DisplayName("#hit() : should add card without return")
    void hit() {
        //given
        PlayingCards playingCards = PlayingCards.of(new ArrayList<>(Arrays.asList(new Card(Symbol.QUEEN, Type.SPADE), new Card(Symbol.QUEEN, Type.CLOVER))));
        Dealer dealer = new Dealer(playingCards, deck, mock(Money.class));
        int defaultSizeOfCards = dealer.countCards();
        Card card = new Card(Symbol.QUEEN, Type.SPADE);

        //when
        dealer.hit(card);

        //then
        assertThat(dealer.countCards()).isEqualTo(defaultSizeOfCards + 1);
    }

    @Test
    @DisplayName("#confirmCards : should add cards for given times without return")
    void confirmCards() {
        //given
        PlayingCards playingCards = PlayingCards.of(new ArrayList<>(Arrays.asList(new Card(Symbol.QUEEN, Type.SPADE), new Card(Symbol.KING, Type.SPADE))));
        Dealer dealer = new Dealer(playingCards, deck, mock(Money.class));
        List<Card> cards = setUpDeck();
        int hitSize = cards.size();
        int defaultSizeOfCards = dealer.countCards();

        //when
        dealer.confirmCards(hitSize);

        //then
        assertThat(dealer.countCards()).isEqualTo(defaultSizeOfCards + hitSize);
    }

    private List<Card> setUpDeck() {
        List<Card> cards = Arrays.asList(new Card(Symbol.QUEEN, Type.SPADE), new Card(Symbol.QUEEN, Type.DIAMOND), new Card(Symbol.QUEEN, Type.CLOVER));
        for (Card card : cards) {
            when(deck.pop()).thenReturn(card);
        }
        return cards;
    }
}
