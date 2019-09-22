package com.ecacho.challenge.bowling.frame.impl;

import com.ecacho.challenge.bowling.exception.BowlingException;
import com.ecacho.challenge.bowling.frame.IFrame;
import com.ecacho.challenge.bowling.frame.calculate_score.IScoreCalculate;
import com.ecacho.challenge.bowling.roll.IRoll;

import java.util.Optional;

public abstract class AbstractFrame implements IFrame {

    protected Optional<Integer> score;
    protected Optional<AbstractFrame> previousFrame;
    protected Optional<AbstractFrame> nextFrame;
    protected IScoreCalculate scoreCalculate;

    /**
     * Create Frame with default behavior
     */
    public AbstractFrame(IScoreCalculate scoreCalculate) {
        this.scoreCalculate = scoreCalculate;
        score = Optional.empty();
        previousFrame = Optional.empty();
        nextFrame = Optional.empty();
    }

    public Optional<Integer> getScore(){
        return score;
    }

    public Optional<AbstractFrame> getPreviousFrame() {
        return previousFrame;
    }

    public void setPreviousFrame(AbstractFrame previousFrame) {
        this.previousFrame = Optional.of(previousFrame);
    }

    public Optional<AbstractFrame> getNextFrame() {
        return nextFrame;
    }

    public void setNextFrame(AbstractFrame nextFrame) {
        this.nextFrame = Optional.of(nextFrame);
    }

    public boolean isFrameCompleted(){
        return isRollsCompleted()
                && getScore().isPresent();
    }

    public void setScore(Integer score) {
        this.score = Optional.of(score);
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
    public abstract void calculateAnsSetScore();
}
