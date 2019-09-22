package com.ecacho.challenge.bowling.game.impl;

import com.ecacho.challenge.bowling.board.IBowlingBoard;
import com.ecacho.challenge.bowling.board.IBowlingBoardFactory;
import com.ecacho.challenge.bowling.exception.BowlingException;
import com.ecacho.challenge.bowling.game.IBowlingGame;
import com.ecacho.challenge.bowling.player.IPlayer;
import com.ecacho.challenge.bowling.roll.IRoll;
import com.ecacho.challenge.bowling.roll.IRollFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component("TenPinBowlingGame")
public class TenPinBowlingGame  implements IBowlingGame {

    private Map<IPlayer, IBowlingBoard> mapBoardsByPlayer;
    private IBowlingBoardFactory bowlingBoardFactory;


    public TenPinBowlingGame(
            @Qualifier("TenPinBowlingBoardFactory") IBowlingBoardFactory bowlingBoardFactory) {
        this.mapBoardsByPlayer = new HashMap<>();
        this.bowlingBoardFactory = bowlingBoardFactory;
    }

    @Override
    public void addPlayerRoll(IPlayer player, Integer pins) throws BowlingException {
        if (!mapBoardsByPlayer.containsKey(player)){
            mapBoardsByPlayer.put(player, bowlingBoardFactory.createBoard());
        }

        IBowlingBoard board = mapBoardsByPlayer.get(player);
        board.addRoll(pins);
    }

    @Override
    public void addPlayerFoulRoll(IPlayer player) throws BowlingException {

        if (!mapBoardsByPlayer.containsKey(player)){
            mapBoardsByPlayer.put(player, bowlingBoardFactory.createBoard());
        }

        IBowlingBoard board = mapBoardsByPlayer.get(player);
        board.addFoulRoll();
    }

    @Override
    public Map<IPlayer, IBowlingBoard> getAllPlayersScoreboard() {
        return mapBoardsByPlayer;
    }
}
