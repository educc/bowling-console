package com.ecacho.challenge.bowling.roll;

public interface IRoll {

    Integer getPins();
    Integer getMaxPins();
    boolean isFoul();
    boolean isAllPinsKnockedDown();
}
