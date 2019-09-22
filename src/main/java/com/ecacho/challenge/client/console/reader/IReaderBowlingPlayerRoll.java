package com.ecacho.challenge.client.console.reader;

import com.ecacho.challenge.bowling.game.IBowlingGame;
import com.ecacho.challenge.client.console.exception.ConsoleBowlingException;

public interface IReaderBowlingPlayerRoll {

    void addAllPlayersRollToBowlingGame(IBowlingGame game) throws ConsoleBowlingException;
}
