package com.ecacho.challenge.bowling.frame.impl;

import com.ecacho.challenge.bowling.exception.BowlingException;
import com.ecacho.challenge.bowling.frame.IFrameFactory;
import com.ecacho.challenge.bowling.frame.calculate_score.impl.TenPinFrameScoreCalculateImpl;
import com.ecacho.challenge.bowling.frame.calculate_score.impl.TenPinTenthFrameScoreCalculateImpl;
import com.ecacho.challenge.bowling.roll.impl.TenPinRollFactoryImpl;
import org.junit.Assert;
import org.junit.Test;

public class FrameImplTest {

    IFrameFactory frameFactory;

    public FrameImplTest() {
        frameFactory = new TenPinFrameFactoryImpl(
                new TenPinRollFactoryImpl(),
                new TenPinFrameScoreCalculateImpl(),
                new TenPinTenthFrameScoreCalculateImpl()
        );
    }

    @Test
    public void verifyIsCompletedRollsTest() throws BowlingException {
        AbstractFrame f = frameFactory.createFirstFrame();

        Assert.assertTrue("rolls must be incomplete", !f.isRollsCompleted());

        f.addRoll(4);
        Assert.assertTrue("rolls must be incomplete", !f.isRollsCompleted());

        f.addRoll(4);
        Assert.assertTrue("rolls must be completed", f.isRollsCompleted());
    }

    @Test
    public void pinsFromFirstRollTest() throws BowlingException {
        AbstractFrame f = frameFactory.createFirstFrame();

        f.addRoll(5);

        Assert.assertSame("The first roll must have 5 pins",
                f.getPinsFromRoll(1).get().getPins(),
                5);
    }


    @Test
    public void pinsFromFirstAndSecondRollTest() throws BowlingException {
        AbstractFrame f = frameFactory.createFirstFrame();


        Integer first = 4;
        Integer second = 6;

        f.addRoll(first);
        f.addRoll(second);

        Assert.assertSame("The first roll must have 4pins",
                f.getPinsFromRoll(1).get().getPins(),
                first);


        Assert.assertSame("The second roll must have 6 pins",
                f.getPinsFromRoll(2).get().getPins(),
                second);
    }

    @Test(expected = BowlingException.class)
    public void addInvalidAtFirstRoll() throws BowlingException {
        AbstractFrame f = frameFactory.createFirstFrame();
        f.addRoll(20);
    }

    @Test(expected = BowlingException.class)
    public void addInvalidAtSecondRoll() throws BowlingException {
        AbstractFrame f = frameFactory.createFirstFrame();
        f.addRoll(4);
        f.addRoll(7);
    }

    @Test
    public void rollsCompletedWhenStrikeTest() throws BowlingException {
        AbstractFrame f = frameFactory.createFirstFrame();
        f.addRoll(10);

        Assert.assertTrue("Rolls should be completed", f.isRollsCompleted());
    }

    @Test
    public void rollsCompletedWhenSpareTest() throws BowlingException {
        AbstractFrame f = frameFactory.createFirstFrame();
        f.addRoll(0);
        f.addRoll(10);

        Assert.assertTrue("Rolls should be completed", f.isRollsCompleted());
    }

    @Test
    public void rollsNotCompletedTest() throws BowlingException {
        AbstractFrame f = frameFactory.createFirstFrame();
        f.addRoll(0);

        Assert.assertTrue("Rolls should be incomplete", !f.isRollsCompleted());
    }

    @Test
    public void strikeTest() throws BowlingException {
        AbstractFrame f = frameFactory.createFirstFrame();

        f.addRoll(10);
        Assert.assertTrue("The frame must contains a strike", f.isStrike());
    }


    @Test
    public void spareTest() throws BowlingException {
        AbstractFrame f = frameFactory.createFirstFrame();

        f.addRoll(5);
        f.addRoll(5);

        Assert.assertTrue("The frame doesn't contains a strike", !f.isStrike());
        Assert.assertTrue("The frame must contains an spare", f.isSpare());
    }

    @Test
    public void noSpareTest() throws BowlingException {
        AbstractFrame f = frameFactory.createFirstFrame();

        f.addRoll(5);
        f.addRoll(3);

        Assert.assertTrue("The frame doesn't contains a strike", !f.isStrike());
        Assert.assertTrue("The frame doesn't contains an spare", !f.isSpare());
    }

    @Test
    public void addRollAfterStrikeTest() throws BowlingException {
        AbstractFrame f = frameFactory.createFirstFrame();

        boolean firstRollAdded = f.addRoll(10);
        boolean secondRollAdded = f.addRoll(1);

        Assert.assertTrue("The frame doesn't avoid add roll after strike",
                firstRollAdded && !secondRollAdded);

    }

    @Test
    public void addRollAfterSpareTest() throws BowlingException {
        AbstractFrame f = frameFactory.createFirstFrame();

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
        AbstractFrame f = frameFactory.createFirstFrame();

        Assert.assertEquals("Rolls remaining should be 2", 2, f.getRollsRemaining());
    }


    @Test
    public void rollsRemainingOnStrikeTest() throws BowlingException {
        AbstractFrame f = frameFactory.createFirstFrame();

        f.addRoll(10);

        Assert.assertEquals("Rolls remaining should be 0", 0, f.getRollsRemaining());
    }

    @Test
    public void rollsRemainingOnSpareTest() throws BowlingException {
        AbstractFrame f = frameFactory.createFirstFrame();

        f.addRoll(5);
        f.addRoll(5);

        Assert.assertEquals("Rolls remaining should be 0", 0, f.getRollsRemaining());
    }

    @Test
    public void rollsRemainingAfterFirstRollTest() throws BowlingException {
        AbstractFrame f = frameFactory.createFirstFrame();

        f.addRoll(5);

        Assert.assertEquals("Rolls remaining should be 1", 1, f.getRollsRemaining());
    }
}