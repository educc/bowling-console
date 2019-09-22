package com.ecacho.challenge.client.console.gamemanager;

import com.ecacho.challenge.client.console.exception.ConsoleBowlingException;
import com.ecacho.challenge.client.console.reader.IReaderBowlingPlayerRoll;

public interface IGameManager {

    void printAllPlayersScore(IReaderBowlingPlayerRoll reader) throws ConsoleBowlingException;
}
