package com.ecacho.challenge.bowling.game;

import com.ecacho.challenge.bowling.exception.BowlingException;
import com.ecacho.challenge.bowling.player.IPlayer;

public interface IBowlingGame {

    void addPlayerRoll(IPlayer player, Integer roll) throws BowlingException;
}
