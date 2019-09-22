package com.ecacho.challenge.bowling.board;

import com.ecacho.challenge.bowling.exception.BowlingException;
import com.ecacho.challenge.bowling.frame.IFrame;
import com.ecacho.challenge.bowling.frame.impl.AbstractFrame;

import java.util.List;

public interface IBowlingBoard {

    void addRoll(Integer pins) throws BowlingException;
    void addFoulRoll() throws BowlingException;
    List<IFrame> getAllFrames();
    Integer getRollsRemaining();

}
