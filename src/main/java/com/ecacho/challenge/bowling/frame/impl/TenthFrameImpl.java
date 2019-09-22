package com.ecacho.challenge.bowling.frame.impl;

import com.ecacho.challenge.bowling.exception.BowlingException;
import com.ecacho.challenge.bowling.roll.IRoll;
import com.ecacho.challenge.bowling.roll.IRollFactory;

import java.util.Optional;

public class TenthFrameImpl extends FrameImpl {

    IRollFactory rollFactory;

    public TenthFrameImpl(IRollFactory rollFactory) {
        super(rollFactory);
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
}
