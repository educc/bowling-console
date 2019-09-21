package com.ecacho.challenge.bowling.game.impl;

import com.ecacho.challenge.bowling.board.IBowlingBoard;
import com.ecacho.challenge.bowling.board.IBowlingBoardFactory;
import com.ecacho.challenge.bowling.board.impl.TenPinBowlingBoardFactoryImpl;
import com.ecacho.challenge.bowling.exception.BowlingException;
import com.ecacho.challenge.bowling.game.IBowlingGame;
import com.ecacho.challenge.bowling.player.IPlayer;

import java.util.HashMap;
import java.util.Map;

public class TenPinBowlingGame  implements IBowlingGame {

    private Map<IPlayer, IBowlingBoard> mapBoardsByPlayer;
    private IBowlingBoardFactory bowlingBoardFactory;

    public TenPinBowlingGame() {
        this.mapBoardsByPlayer = new HashMap<>();
        this.bowlingBoardFactory = new TenPinBowlingBoardFactoryImpl();
    }

    @Override
    public void addPlayerRoll(IPlayer player, Integer roll) throws BowlingException {
        if (!mapBoardsByPlayer.containsKey(player)){
            mapBoardsByPlayer.put(player, bowlingBoardFactory.createBoard());
        }

        IBowlingBoard board = mapBoardsByPlayer.get(player);
        board.addRoll(roll);
    }
}
