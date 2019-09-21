package com.ecacho.challenge.bowling.board;

import com.ecacho.challenge.bowling.exception.BowlingException;

public interface IBowlingBoard {

    void addRoll(int pin) throws BowlingException;
    Integer getRollsRemaining();

}
