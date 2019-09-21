package com.ecacho.challenge.bowling.frame.impl;

import com.ecacho.challenge.bowling.exception.BowlingException;
import org.junit.Assert;
import org.junit.Test;
import java.util.Optional;

public class FrameTest {

    @Test
    public void pinsFromFirstRollTest() throws BowlingException {
        Frame f = new Frame(1);

        f.addRoll(5);

        Assert.assertEquals("The first roll must have 5 pins",
                f.getPinsFromRoll(1),
                Optional.of(5));
    }


    @Test
    public void pinsFromFirstAndSecondRollTest() throws BowlingException {
        Frame f = new Frame(1);

        int firstRoll = 4;
        int secondRoll = 6;

        f.addRoll(firstRoll);
        f.addRoll(secondRoll);

        Assert.assertEquals("The first roll must have " + firstRoll + " pins",
                f.getPinsFromRoll(1),
                Optional.of(firstRoll));


        Assert.assertEquals("The second roll must have " + secondRoll + " pins",
                f.getPinsFromRoll(2),
                Optional.of(secondRoll));
    }

    @Test(expected = BowlingException.class)
    public void addInvalidRoll() throws BowlingException {
        Frame f = new Frame(1);
        f.addRoll(20);
    }

    @Test
    public void strikeTest() throws BowlingException {
        Frame f = new Frame(1);

        f.addRoll(10);
        Assert.assertTrue("The frame must contains a strike", f.isStrike());
    }


    @Test
    public void spareTest() throws BowlingException {
        Frame f = new Frame(1);

        f.addRoll(5);
        f.addRoll(5);

        Assert.assertTrue("The frame doesn't contains a strike", !f.isStrike());
        Assert.assertTrue("The frame must contains an spare", f.isSpare());
    }

    @Test
    public void noSpareTest() throws BowlingException {
        Frame f = new Frame(1);

        f.addRoll(5);
        f.addRoll(3);

        Assert.assertTrue("The frame doesn't contains a strike", !f.isStrike());
        Assert.assertTrue("The frame doesn't contains an spare", !f.isSpare());
    }

    @Test
    public void addRollAfterStrikeTest() throws BowlingException {
        Frame f = new Frame(1);

        boolean firstRollAdded = f.addRoll(10);
        boolean secondRollAdded = f.addRoll(1);

        Assert.assertTrue("The frame doesn't avoid add roll after strike",
                firstRollAdded && !secondRollAdded);

    }

    @Test
    public void addRollAfterSpareTest() throws BowlingException {
        Frame f = new Frame(1);

        boolean firstRollAdded = f.addRoll(1);
        boolean secondRollAdded = f.addRoll(9);
        boolean thirdRollAdded = f.addRoll(1);

        Assert.assertTrue("The frame doesn't avoid add roll after spare",
                firstRollAdded && secondRollAdded && !thirdRollAdded);

        Assert.assertTrue("The frame doesn't contains a strike", !f.isStrike());
        Assert.assertTrue("The frame doesn't contains an spare", f.isSpare());
    }
}