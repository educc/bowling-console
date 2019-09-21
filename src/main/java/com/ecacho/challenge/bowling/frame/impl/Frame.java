package com.ecacho.challenge.bowling.frame.impl;

import com.ecacho.challenge.bowling.exception.BowlingException;
import com.ecacho.challenge.bowling.frame.AbstractFrame;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Default frame with only two Rolls for using as Frame 1 to Frame 9
 */
public class Frame extends AbstractFrame {

    protected List<Optional<Integer>> rolls;

    public Frame(int number) {
        super(number, 2);
        this.initRolls();
    }

    private void initRolls(){
        this.rolls = new ArrayList<>();
        for(int i = 0; i < this.maxRolls; i++){
            this.rolls.add(Optional.empty());
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
}
