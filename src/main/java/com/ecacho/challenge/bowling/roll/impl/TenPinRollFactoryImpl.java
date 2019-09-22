package com.ecacho.challenge.bowling.roll.impl;

import com.ecacho.challenge.bowling.exception.BowlingException;
import com.ecacho.challenge.bowling.roll.IRoll;
import com.ecacho.challenge.bowling.roll.IRollFactory;
import org.springframework.stereotype.Component;


@Component("TenPinRollFactory")
public class TenPinRollFactoryImpl implements IRollFactory {

    @Override
    public IRoll createRoll(Integer pins) throws BowlingException {
        return new TenPinRollImpl(pins);
    }

    @Override
    public IRoll createFoul() {
        return new TenPinRollImpl(true);
    }
}
