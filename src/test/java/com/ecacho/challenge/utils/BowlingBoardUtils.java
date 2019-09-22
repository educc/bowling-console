package com.ecacho.challenge.utils;

import com.ecacho.challenge.bowling.board.IBowlingBoard;
import com.ecacho.challenge.bowling.exception.BowlingException;

public class BowlingBoardUtils {


    public static void addRollsToBoard(IBowlingBoard board, int[] rolls) throws BowlingException {
        for(int i = 0; i < rolls.length; i++){
            board.addRoll(rolls[i]);
        }
    }
}
