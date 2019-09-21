package com.ecacho.challenge.bowling.frame;

import com.ecacho.challenge.bowling.exception.BowlingException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AbstractFrame {

    private int number;
    protected List<Optional<Integer>> rolls;
    protected int maxRolls;
    private Optional<Integer> score;

    protected int MAX_PINS = 10;
    protected int FIRST_ROLL = 0;
    protected int SECOND_ROLL = 1;

    /**
     * Create Frame with default behavior
     * @param number the number of the frame
     * @param numberExtraRolls must be positive or zero
     */
    public AbstractFrame(int number, int numberExtraRolls) {
        this.number = number;;
        this.maxRolls = 2 + numberExtraRolls;
        this.score = Optional.empty();
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
        if (pins < 0 || pins > MAX_PINS) {
            String msg = String.format("Each roll must have between 0 to %d pins",
                pins);
            throw new BowlingException(msg);
        }

        if (isRollsComplete()) {
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

    public Optional<Integer> getScore(){
        return score;
    }

    public int getFrameNumber(){
        return this.number;
    }

    public boolean isFrameComplete(){
        return isRollsComplete()
                && getScore().isPresent();
    }

    public boolean isRollsComplete(){
        return isStrike() || rolls.size() == rolls.stream().filter(it -> it.isPresent()).count();
    }

    public boolean isSpare(){
        if (!rolls.get(SECOND_ROLL).isPresent()){
            return false;
        }

        Integer firstRoll = this.rolls.get(FIRST_ROLL).get();
        Integer secondRoll = this.rolls.get(SECOND_ROLL).get();
        return (firstRoll + secondRoll) == MAX_PINS;
    }

    public boolean isStrike(){
        if (!rolls.get(FIRST_ROLL).isPresent()){
            return false;
        }
        return this.rolls.get(FIRST_ROLL).get() == MAX_PINS;
    }
}
