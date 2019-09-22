package com.ecacho.challenge.client.console.game_manager.impl;

import com.ecacho.challenge.bowling.board.IBowlingBoard;
import com.ecacho.challenge.bowling.frame.IFrame;
import com.ecacho.challenge.bowling.game.IBowlingGame;
import com.ecacho.challenge.bowling.player.IPlayer;
import com.ecacho.challenge.bowling.roll.IRoll;
import com.ecacho.challenge.client.console.exception.ConsoleBowlingException;
import com.ecacho.challenge.client.console.game_manager.IGameManager;
import com.ecacho.challenge.client.console.reader.IReaderBowlingPlayerRoll;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.lang.System.out;

@Service
public class TenPinGameManagerImpl implements IGameManager {


    IBowlingGame bowlingGame;

    public TenPinGameManagerImpl(IBowlingGame bowlingGame) {
        this.bowlingGame = bowlingGame;
    }

    @Override
    public void printAllPlayersScore(IReaderBowlingPlayerRoll reader) throws ConsoleBowlingException {

        reader.addAllPlayersRollToBowlingGame(bowlingGame);
        //TODO: print all result here
        Map<IPlayer, IBowlingBoard> mapScoreboards = bowlingGame.getAllPlayersScoreboard();
        for (IPlayer player : mapScoreboards.keySet()){
            IBowlingBoard scoreboard = mapScoreboards.get(player);

            printFrameNumbers(scoreboard);
            printPlayer(player);
            printPinfall(scoreboard);
            printScore(scoreboard);
        }
    }

    private void printScore(IBowlingBoard board) {
        out.print("Score\t\t");

        out.println();
    }

    private void printPinfall(IBowlingBoard board) {
        out.print("Pinfalls\t");
        for(IFrame frame: board.getAllFrames()) {
            List<IRoll> allRollsPlayed = frame.getAllRollsPlayed();
            int size = allRollsPlayed.size();
            if (size == 1) {
                out.print("\t");
            }

            boolean first = true;
            for(IRoll roll: allRollsPlayed){
                if (roll.isFoul()) {
                    out.print("F");
                } else {
                    if (roll.isAllPinsKnockedDown()) {
                        out.print("X");
                    } else {
                        if (!first && frame.isSpare()) {
                            out.print("/");
                        } else {
                            out.print(roll.getPins());
                        }
                    }
                }
                out.print("\t");
                first = false;
            }
        }
        out.println();
    }

    private void printPlayer(IPlayer player){
        out.println(player.getName());
    }

    private void printFrameNumbers(IBowlingBoard board) {
        out.print("Frame\t\t");
        for(IFrame frame: board.getAllFrames()) {
            out.print(frame.getFrameNumber());
            out.print("\t\t");
        }
        out.println();
    }
}
