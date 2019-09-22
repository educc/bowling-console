package com.ecacho.challenge.bowling.roll.impl;

import com.ecacho.challenge.bowling.exception.BowlingException;
import com.ecacho.challenge.bowling.roll.IRoll;

import java.util.Objects;

public class TenPinRollImpl implements IRoll {

    Integer pins;
    boolean isFoul;

    static final Integer MAX_PINS = 10;
    static final Integer MIN_PINS = 0;

    public TenPinRollImpl(Integer pins) throws BowlingException {
        if (pins < MIN_PINS || pins > MAX_PINS) {
            String msg = String.format(
                    "The pins should be between %d and %d", MIN_PINS, MAX_PINS);
            throw new BowlingException(msg);
        }
        this.pins = pins;
        this.isFoul = false;
    }

    public TenPinRollImpl(boolean isFoul) {
        this.pins = MIN_PINS;
        this.isFoul = isFoul;
    }

    @Override
    public Integer getPins() {
        return pins;
    }

    @Override
    public Integer getMaxPins() {
        return MAX_PINS;
    }

    @Override
    public boolean isFoul() {
        return isFoul;
    }

    @Override
    public boolean isAllPinsKnockedDown() {
        return this.pins.equals(MAX_PINS);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TenPinRollImpl that = (TenPinRollImpl) o;
        return isFoul == that.isFoul &&
                Objects.equals(pins, that.pins);
    }
}
