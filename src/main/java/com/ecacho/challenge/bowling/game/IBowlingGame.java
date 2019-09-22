package com.ecacho.challenge.bowling.game;

import com.ecacho.challenge.bowling.board.IBowlingBoard;
import com.ecacho.challenge.bowling.exception.BowlingException;
import com.ecacho.challenge.bowling.player.IPlayer;

import java.util.Map;

public interface IBowlingGame {

    void addPlayerRoll(IPlayer player, Integer pins) throws BowlingException;
    void addPlayerFoulRoll(IPlayer player) throws BowlingException;
    Map<IPlayer, IBowlingBoard> getAllPlayersScoreboard();
}
