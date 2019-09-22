package com.ecacho.challenge.bowling.roll;

import com.ecacho.challenge.bowling.exception.BowlingException;

public interface IRollFactory {

    IRoll createRoll(Integer pins) throws BowlingException;
    IRoll createFoul();
}
