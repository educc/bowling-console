package com.ecacho.challenge.client.console.gamemanager.impl;

import com.ecacho.challenge.bowling.board.IBowlingBoard;
import com.ecacho.challenge.bowling.frame.IFrame;
import com.ecacho.challenge.bowling.game.IBowlingGame;
import com.ecacho.challenge.bowling.player.IPlayer;
import com.ecacho.challenge.bowling.roll.IRoll;
import com.ecacho.challenge.client.console.exception.ConsoleBowlingException;
import com.ecacho.challenge.client.console.gamemanager.IGameManager;
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
        StringBuilder sb = new StringBuilder("Score\t\t");

        for (IFrame frame : board.getAllFrames()) {
            Optional<Integer> score = frame.getScore();
            if (score.isPresent()) {
                sb.append(score.get());
            }
            sb.append("\t\t");
        }

        out.println(sb.toString().trim());
    }

    private void printPinfall(IBowlingBoard board) {
        StringBuilder sb = new StringBuilder("Pinfalls\t");

        for(IFrame frame: board.getAllFrames()) {
            List<IRoll> allRollsPlayed = frame.getAllRollsPlayed();
            int size = allRollsPlayed.size();
            if (size == 1) {
                sb.append("\t");
            }

            int count = 1;
            for(IRoll roll: allRollsPlayed){
                if (roll.isFoul()) {
                    sb.append("F");
                } else {
                    if (roll.isAllPinsKnockedDown()) {
                        sb.append("X");
                    } else {
                        if (count == 2 && frame.isSpare()) {
                            sb.append("/");
                        } else {
                            sb.append(roll.getPins());
                        }
                    }
                }
                sb.append("\t");
                count++;
            }
        }
        out.println(sb.toString().trim());
    }

    private void printPlayer(IPlayer player){
        out.println(player.getName());
    }

    private void printFrameNumbers(IBowlingBoard board) {
        StringBuilder sb = new StringBuilder("Frame\t\t");

        for(IFrame frame: board.getAllFrames()) {
            sb.append(frame.getFrameNumber());
            sb.append("\t\t");
        }
        out.println(sb.toString().trim());
    }
}
