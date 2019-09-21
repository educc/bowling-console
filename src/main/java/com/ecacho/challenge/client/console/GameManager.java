package com.ecacho.challenge.client.console;

import com.ecacho.challenge.bowling.exception.BowlingException;
import com.ecacho.challenge.bowling.game.impl.TenPinBowlingGame;
import com.ecacho.challenge.client.console.exception.ConsoleBowlingException;
import com.ecacho.challenge.client.console.model.PlayerAndRoll;
import com.ecacho.challenge.client.console.reader.IReaderBowlingPlayerRoll;
import com.ecacho.challenge.client.console.reader.impl.FileReaderBowlingPlayerRoll;

import java.util.List;

public class GameManager {

    IReaderBowlingPlayerRoll readerBowlingPlayerRoll;
    TenPinBowlingGame bowlingGame;

    public GameManager(String filename) {
        this.readerBowlingPlayerRoll = new FileReaderBowlingPlayerRoll(filename);
        this.bowlingGame = new TenPinBowlingGame();
    }

    public void readAll() throws BowlingException, ConsoleBowlingException {
        List<PlayerAndRoll> playerAndRolls = null;

        playerAndRolls = readerBowlingPlayerRoll.readAll();

        for(PlayerAndRoll item: playerAndRolls) {
            bowlingGame.addPlayerRoll(
                    item.getPlayer(),
                    item.getRoll());
        }
    }

    public void printAllPlayersScoreboard(){

    }
}
