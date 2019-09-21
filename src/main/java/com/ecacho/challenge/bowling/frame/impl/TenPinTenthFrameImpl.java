package com.ecacho.challenge.bowling.frame.impl;

import com.ecacho.challenge.bowling.exception.BowlingException;

import java.util.Optional;

public class TenPinTenthFrameImpl extends TenPinFrameImpl {

    public TenPinTenthFrameImpl() {
        super(10);
    }

    @Override
    public boolean addRoll(int pins) throws BowlingException {
        boolean canAdd = super.addRoll(pins);

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
