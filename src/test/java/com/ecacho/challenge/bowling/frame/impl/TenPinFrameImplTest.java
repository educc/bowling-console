package com.ecacho.challenge.bowling.frame.impl;

import com.ecacho.challenge.bowling.exception.BowlingException;
import org.junit.Assert;
import org.junit.Test;
import java.util.Optional;

public class TenPinFrameImplTest {

    @Test
    public void verifyIsCompletedRollsTest() throws BowlingException {
        TenPinFrameImpl f = new TenPinFrameImpl(1);

        Assert.assertTrue("rolls must be incomplete", !f.isRollsCompleted());

        f.addRoll(4);
        Assert.assertTrue("rolls must be incomplete", !f.isRollsCompleted());

        f.addRoll(4);
        Assert.assertTrue("rolls must be completed", f.isRollsCompleted());
    }

    @Test
    public void pinsFromFirstRollTest() throws BowlingException {
        TenPinFrameImpl f = new TenPinFrameImpl(1);

        f.addRoll(5);

        Assert.assertEquals("The first roll must have 5 pins",
                f.getPinsFromRoll(1),
                Optional.of(5));
    }


    @Test
    public void pinsFromFirstAndSecondRollTest() throws BowlingException {
        TenPinFrameImpl f = new TenPinFrameImpl(1);

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
    public void addInvalidAtFirstRoll() throws BowlingException {
        TenPinFrameImpl f = new TenPinFrameImpl(1);
        f.addRoll(20);
    }

    @Test(expected = BowlingException.class)
    public void addInvalidAtSecondRoll() throws BowlingException {
        TenPinFrameImpl f = new TenPinFrameImpl(1);
        f.addRoll(4);
        f.addRoll(7);
    }

    @Test
    public void rollsCompletedWhenStrikeTest() throws BowlingException {
        TenPinFrameImpl f = new TenPinFrameImpl(1);
        f.addRoll(10);

        Assert.assertTrue("Rolls should be completed", f.isRollsCompleted());
    }

    @Test
    public void rollsCompletedWhenSpareTest() throws BowlingException {
        TenPinFrameImpl f = new TenPinFrameImpl(1);
        f.addRoll(0);
        f.addRoll(10);

        Assert.assertTrue("Rolls should be completed", f.isRollsCompleted());
    }

    @Test
    public void rollsNotCompletedTest() throws BowlingException {
        TenPinFrameImpl f = new TenPinFrameImpl(1);
        f.addRoll(0);

        Assert.assertTrue("Rolls should be incomplete", !f.isRollsCompleted());
    }

    @Test
    public void strikeTest() throws BowlingException {
        TenPinFrameImpl f = new TenPinFrameImpl(1);

        f.addRoll(10);
        Assert.assertTrue("The frame must contains a strike", f.isStrike());
    }


    @Test
    public void spareTest() throws BowlingException {
        TenPinFrameImpl f = new TenPinFrameImpl(1);

        f.addRoll(5);
        f.addRoll(5);

        Assert.assertTrue("The frame doesn't contains a strike", !f.isStrike());
        Assert.assertTrue("The frame must contains an spare", f.isSpare());
    }

    @Test
    public void noSpareTest() throws BowlingException {
        TenPinFrameImpl f = new TenPinFrameImpl(1);

        f.addRoll(5);
        f.addRoll(3);

        Assert.assertTrue("The frame doesn't contains a strike", !f.isStrike());
        Assert.assertTrue("The frame doesn't contains an spare", !f.isSpare());
    }

    @Test
    public void addRollAfterStrikeTest() throws BowlingException {
        TenPinFrameImpl f = new TenPinFrameImpl(1);

        boolean firstRollAdded = f.addRoll(10);
        boolean secondRollAdded = f.addRoll(1);

        Assert.assertTrue("The frame doesn't avoid add roll after strike",
                firstRollAdded && !secondRollAdded);

    }

    @Test
    public void addRollAfterSpareTest() throws BowlingException {
        TenPinFrameImpl f = new TenPinFrameImpl(1);

        boolean firstRollAdded = f.addRoll(1);
        boolean secondRollAdded = f.addRoll(9);
        boolean thirdRollAdded = f.addRoll(1);

        Assert.assertTrue("The frame doesn't avoid add roll after spare",
                firstRollAdded && secondRollAdded && !thirdRollAdded);

        Assert.assertTrue("The frame doesn't contains a strike", !f.isStrike());
        Assert.assertTrue("The frame doesn't contains an spare", f.isSpare());
    }

    @Test
    public void rollsRemainingTest() {
        TenPinFrameImpl f = new TenPinFrameImpl(1);

        Assert.assertEquals("Rolls remaining should be 2", 2, f.getRollsRemaining());
    }


    @Test
    public void rollsRemainingOnStrikeTest() throws BowlingException {
        TenPinFrameImpl f = new TenPinFrameImpl(1);

        f.addRoll(10);

        Assert.assertEquals("Rolls remaining should be 0", 0, f.getRollsRemaining());
    }

    @Test
    public void rollsRemainingOnSpareTest() throws BowlingException {
        TenPinFrameImpl f = new TenPinFrameImpl(1);

        f.addRoll(5);
        f.addRoll(5);

        Assert.assertEquals("Rolls remaining should be 0", 0, f.getRollsRemaining());
    }

    @Test
    public void rollsRemainingAfterFirstRollTest() throws BowlingException {
        TenPinFrameImpl f = new TenPinFrameImpl(1);

        f.addRoll(5);

        Assert.assertEquals("Rolls remaining should be 1", 1, f.getRollsRemaining());
    }
}