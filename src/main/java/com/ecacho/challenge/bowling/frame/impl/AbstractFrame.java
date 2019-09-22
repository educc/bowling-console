package com.ecacho.challenge.bowling.frame.impl;

import com.ecacho.challenge.bowling.exception.BowlingException;
import com.ecacho.challenge.bowling.frame.IFrame;
import com.ecacho.challenge.bowling.roll.IRoll;

import java.util.Optional;

public abstract class AbstractFrame implements IFrame {

    protected Optional<Integer> score;

    /**
     * Create Frame with default behavior
     */
    public AbstractFrame() {
        this.score = Optional.empty();
    }

    public Optional<Integer> getScore(){
        return score;
    }


    public boolean isFrameCompleted(){
        return isRollsCompleted()
                && getScore().isPresent();
    }

    /**
     * Add roll with pins in this Frame
     * @param pins
     * @return
     * @throws BowlingException when pins are invalid see validatePinsBeforeAddRoll method
     */
    public abstract boolean addRoll(Integer pins) throws BowlingException;
    public abstract boolean addFoulRoll() throws BowlingException;
    public abstract int getFrameNumber();
    public abstract Optional<IRoll> getPinsFromRoll(int numberOfRoll) throws BowlingException;
    public abstract boolean isRollsCompleted();
    public abstract boolean isSpare();
    public abstract boolean isStrike();
    public abstract int getRollsRemaining();
}
