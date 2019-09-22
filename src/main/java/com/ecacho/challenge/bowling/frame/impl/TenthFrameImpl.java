package com.ecacho.challenge.bowling.frame.impl;

import com.ecacho.challenge.bowling.exception.BowlingException;
import com.ecacho.challenge.bowling.frame.calculatescore.IScoreCalculate;
import com.ecacho.challenge.bowling.roll.IRoll;
import com.ecacho.challenge.bowling.roll.IRollFactory;

import java.util.Optional;

public class TenthFrameImpl extends FrameImpl {

    public TenthFrameImpl(IRollFactory factory, IScoreCalculate scoreCalculate) {
        super(factory, scoreCalculate);
    }

    @Override
    public boolean addRoll(IRoll roll) throws BowlingException {
        boolean canAdd = super.addRoll(roll);

        if (isStrike() || isSpare()) {
            if (this.rolls.size() <= 2) {
                this.rolls.add(Optional.empty());
            }
        }

        return canAdd;
    }

    @Override
    public boolean isRollsCompleted() {
        return rolls.size() == rolls.stream().filter(Optional::isPresent).count();
    }

    @Override
    public int getRollsRemaining() {
        return (int)rolls.stream().filter(it -> !it.isPresent()).count();
    }

    @Override
    protected void validateSumOfTwoFirstRolls() throws BowlingException {
        if (!isStrike()) {
            super.validateSumOfTwoFirstRolls();
        }
    }

    @Override
    public void calculateAnsSetScore() {
        this.scoreCalculate.calculateAndSetScore(this);
    }
}
