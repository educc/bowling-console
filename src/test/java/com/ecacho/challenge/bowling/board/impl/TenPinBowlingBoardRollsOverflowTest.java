package com.ecacho.challenge.bowling.board.impl;

import com.ecacho.challenge.bowling.exception.BowlingException;
import com.ecacho.challenge.bowling.roll.IRollFactory;
import com.ecacho.challenge.bowling.roll.impl.TenPinRollFactoryImpl;
import org.junit.Before;
import org.junit.Test;

public class TenPinBowlingBoardRollsOverflowTest extends  TenPinBowlingBoardRollsCompletedTest {


    @Test(expected = BowlingException.class)
    @Override
    public void twentyRollsTest() throws BowlingException {
        super.twentyRollsTest();
        this.board.addRoll(5);
    }

    @Test(expected = BowlingException.class)
    @Override
    public void twentyOneRollsWithStrikeTest() throws BowlingException {
        super.twentyOneRollsWithStrikeTest();
        this.board.addRoll(5);
    }

    @Test(expected = BowlingException.class)
    @Override
    public void twentyOneRollsWithSpareTest() throws BowlingException {
        super.twentyOneRollsWithSpareTest();
        this.board.addRoll(5);
    }

    @Test(expected = BowlingException.class)
    @Override
    public void twentyOneRollsWithFirstStrikesTest() throws BowlingException {
        super.twentyOneRollsWithFirstStrikesTest();
        this.board.addRoll(5);
    }

    @Test(expected = BowlingException.class)
    @Override
    public void allRollsWereStrikeTest() throws BowlingException {
        super.allRollsWereStrikeTest();
        this.board.addRoll(5);
    }

    @Test(expected = BowlingException.class)
    @Override
    public void fiveStrikesTest() throws BowlingException {
        super.fiveStrikesTest();
        this.board.addRoll(5);
    }
}
