package com.ecacho.challenge.client.console.reader;

import com.ecacho.challenge.client.console.exception.ConsoleBowlingException;
import com.ecacho.challenge.client.console.model.PlayerAndRoll;

import java.util.List;

public interface IReaderBowlingPlayerRoll {

    List<PlayerAndRoll> readAll() throws ConsoleBowlingException;
}
