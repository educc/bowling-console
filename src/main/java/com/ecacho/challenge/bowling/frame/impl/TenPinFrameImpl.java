package com.ecacho.challenge.bowling.frame.impl;

import com.ecacho.challenge.bowling.exception.BowlingException;
import com.ecacho.challenge.bowling.frame.AbstractFrame;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Default frame with only two Rolls for using as Frame 1 to Frame 9
 */
public class TenPinFrameImpl extends AbstractFrame {

    protected List<Optional<Integer>> rolls;
    protected int MAX_PINS = 10;

    public TenPinFrameImpl(int number) {
        super(number);
        this.initRolls();
    }

    private void initRolls(){
        int defaultMaxRolls = 2;
        this.rolls = new ArrayList<>();
        for(int i = 0; i < defaultMaxRolls; i++){
            this.rolls.add(Optional.empty());
        }
    }

    @Override
    public void validatePinsBeforeAddRoll(int pins) throws BowlingException {
        if (pins < 0 || pins > MAX_PINS) {
            String msg = String.format("Each roll must have between 0 to %d pins",
                    pins);
            throw new BowlingException(msg);
        }
    }

    protected void validateSumOfTwoFirstRolls() throws BowlingException {
        if (this.rolls.get(0).isPresent() && this.rolls.get(1).isPresent()) {
            int first = rolls.get(0).get();
            int second = rolls.get(1).get();
            if ( (first + second) > MAX_PINS) {
                throw new BowlingException("The two first rolls for this frame sum more then 10");
            }
        }
    }

    /**
     * Add roll to this frame
     * @return false when this frame cannot add another roll
     */
    public boolean addRoll(int pins) throws BowlingException {
        this.validatePinsBeforeAddRoll(pins);

        if (isRollsCompleted()) {
            return false;
        }

        int size = this.rolls.size();
        for (int i = 0; i < size; i++) {
            Optional<Integer> item = this.rolls.get(i);
            if (!item.isPresent()) {
                this.rolls.set(i, Optional.of(pins));
                break;
            }
        }
        this.validateSumOfTwoFirstRolls();
        return true;
    }

    @Override
    public Optional<Integer> getPinsFromRoll(int numberOfRoll) throws BowlingException {
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

        Integer firstRoll = this.rolls.get(0).get();
        Integer secondRoll = this.rolls.get(1).get();
        return (firstRoll + secondRoll) == MAX_PINS;
    }

    public boolean isStrike(){
        if (!rolls.get(0).isPresent()){
            return false;
        }
        return this.rolls.get(0).get() == MAX_PINS;
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
}
