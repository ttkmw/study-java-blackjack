package controller;

import common.DealerDto;
import common.PlayerDto;
import common.PlayersDto;
import domain.UserInterface;
import domain.blackjack.BlackjackService;
import domain.card.Card;
import domain.card.Deck;
import domain.card.PlayingCards;
import domain.result.DefaultMatchRule;
import domain.result.MatchRule;
import domain.result.Result;
import domain.result.Results;
import domain.user.Dealer;
import domain.user.Money;
import domain.user.Player;
import domain.user.Players;
import infra.repository.SingleDeck;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class BlackjackGame {
    private Deck deck;
    private MatchRule matchRule;
    private UserInterface userInterface;

    public BlackjackGame(Deck deck, MatchRule matchRule, UserInterface userInterface) {
        this.deck = deck;
        this.matchRule = matchRule;
        this.userInterface = userInterface;
    }

    //todo: refac
    public void play() {
        //shuffle
        Dealer dealer = Dealer.shuffle(deck);

        //join
        PlayersDto inputPlayersDto = userInterface.inputPlayers();
        Players players = Players.join(inputPlayersDto, userInterface);
        BlackjackService blackjackService = BlackjackService.start(dealer, matchRule);

        OutputView.printInitGame(dealer.serialize(), players.serialize());

        players = blackjackService.confirmCardsOfPlayers(players);
        int countOfHit = blackjackService.confirmCardsOfDealer();

        Results results = blackjackService.match(players);

//        //summarize
//        PlayersDto playersDtoToShow = PlayersDto.of(playerDtos);
//        DealerDto dealerDto = dealer.serialize(sumOfDealerProfit);
//
//        OutputView.printDealerHit(dealerDto, countOfHit);
//        OutputView.printResult(dealerDto, playersDtoToShow);

    }
}
