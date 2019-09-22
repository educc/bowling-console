package com.ecacho.challenge.bowling.frame.impl;

import com.ecacho.challenge.bowling.exception.BowlingException;
import com.ecacho.challenge.bowling.frame.IFrame;
import com.ecacho.challenge.bowling.frame.calculate_score.IScoreCalculate;
import com.ecacho.challenge.bowling.roll.IRoll;
import com.ecacho.challenge.bowling.roll.IRollFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Default frame with only two Rolls for using as Frame 1 to Frame 9
 */
public class FrameImpl extends AbstractFrame implements IFrame {

    protected List<Optional<IRoll>> rolls;
    private int numberOfFrame;
    private IRollFactory rollFactory;

    public FrameImpl(IRollFactory factory, IScoreCalculate scoreCalculate) {
        super(scoreCalculate);
        this.numberOfFrame = -1;
        this.rollFactory = factory;
        this.initRolls();
    }

    public boolean addRoll(Integer pins) throws BowlingException {
        return addRoll(rollFactory.createRoll(pins));
    }

    public boolean addFoulRoll() throws BowlingException {
        return addRoll(rollFactory.createFoul());
    }

    @Override
    public int getFrameNumber() {
        return numberOfFrame;
    }

    @Override
    public List<IRoll> getAllRollsPlayed() {
        return rolls
                .stream()
                .filter(it -> it.isPresent())
                .map(it -> it.get())
                .collect(Collectors.toList());
    }

    public void setNumberOfFrame(int numberOfFrame) {
        this.numberOfFrame = numberOfFrame;
    }

    private void initRolls(){
        int defaultMaxRolls = 2;
        this.rolls = new ArrayList<>();
        for(int i = 0; i < defaultMaxRolls; i++){
            this.rolls.add(Optional.empty());
        }
    }

    protected void validateSumOfTwoFirstRolls() throws BowlingException {
        if (this.rolls.get(0).isPresent() && this.rolls.get(1).isPresent()) {
            IRoll first = rolls.get(0).get();
            IRoll second = rolls.get(1).get();
            if ( (first.getPins() + second.getPins()) > first.getMaxPins()) {
                throw new BowlingException("The two first rolls for this frame sum more then 10");
            }
        }
    }

    /**
     * Add roll to this frame
     * @return false when this frame cannot add another roll
     */
    protected boolean addRoll(IRoll roll) throws BowlingException {
        if (isRollsCompleted()) {
            return false;
        }

        int size = this.rolls.size();
        for (int i = 0; i < size; i++) {
            Optional<IRoll> item = this.rolls.get(i);
            if (!item.isPresent()) {
                this.rolls.set(i, Optional.of(roll));
                break;
            }
        }
        this.validateSumOfTwoFirstRolls();
        this.calculateAnsSetScore();
        return true;
    }

    @Override
    public Optional<IRoll> getPinsFromRoll(int numberOfRoll) throws BowlingException {
        if (numberOfRoll < 1 || numberOfRoll > this.rolls.size()) {
            throw new BowlingException("Invalid number of roll for this frame");
        }
        return this.rolls.get(numberOfRoll-1);
    }

    public boolean isRollsCompleted(){
        return isStrike() ||
                rolls.size() == rolls.stream().filter(Optional::isPresent).count();
    }

    public boolean isSpare(){
        if (!rolls.get(1).isPresent()){
            return false;
        }

        IRoll first = this.rolls.get(0).get();
        IRoll second = this.rolls.get(1).get();
        return (first.getPins() + second.getPins()) == first.getMaxPins();
    }

    public boolean isStrike(){
        if (!rolls.get(0).isPresent()){
            return false;
        }
        IRoll first = this.rolls.get(0).get();
        return first.isAllPinsKnockedDown();
    }

    @Override
    public int getRollsRemaining() {
        if (isStrike()) {
            return 0;
        }

        if (isSpare()) {
            return 0;
        }

        return (int)rolls.stream().filter(it -> !it.isPresent()).count();
    }

    @Override
    public void calculateAnsSetScore() {
        if (!getScore().isPresent()) {
            this.scoreCalculate.calculateAndSetScore(this);
        }
    }
}
