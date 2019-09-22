package com.ecacho.challenge.bowling.frame;

import com.ecacho.challenge.bowling.roll.IRoll;

import java.util.List;
import java.util.Optional;

public interface IFrame {

    Optional<Integer> getScore();
    int getFrameNumber();
    boolean isStrike();
    boolean isSpare();
    List<IRoll> getAllRollsPlayed();
}
