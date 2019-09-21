package com.ecacho.challenge.bowling.frame;

import com.ecacho.challenge.bowling.exception.BowlingException;
import java.util.Optional;

public abstract class AbstractFrame {

    protected int maxRolls;
    protected Optional<Integer> score;
    private int number;

    protected int MAX_PINS = 10;
    protected int FIRST_ROLL = 1;
    protected int SECOND_ROLL = 2;

    /**
     * Create Frame with default behavior
     * @param numberOfFrame
     * @param maxRolls
     */
    public AbstractFrame(int numberOfFrame, int maxRolls) {
        this.number = numberOfFrame;
        this.maxRolls = maxRolls;
        this.score = Optional.empty();
    }

    public void validatePinsBeforeAddRoll(int pins) throws BowlingException {
        if (pins < 0 || pins > MAX_PINS) {
            String msg = String.format("Each roll must have between 0 to %d pins",
                    pins);
            throw new BowlingException(msg);
        }
    }

    /**
     * Add roll with pins in this Frame
     * @param pins
     * @return
     * @throws BowlingException when pins are invalid see validatePinsBeforeAddRoll method
     */
    public abstract boolean addRoll(int pins) throws BowlingException;

    public Optional<Integer> getScore(){
        return score;
    }

    public int getFrameNumber(){
        return this.number;
    }

    public boolean isFrameCompleted(){
        return isRollsCompleted()
                && getScore().isPresent();
    }

    public abstract Optional<Integer> getPinsFromRoll(int numberOfRoll) throws BowlingException;
    public abstract boolean isRollsCompleted();
    public abstract boolean isSpare();
    public abstract boolean isStrike();

}
