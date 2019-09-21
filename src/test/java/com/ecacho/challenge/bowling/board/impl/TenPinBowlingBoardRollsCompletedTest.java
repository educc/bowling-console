package com.ecacho.challenge.bowling.board.impl;

import com.ecacho.challenge.bowling.board.IBowlingBoard;
import com.ecacho.challenge.bowling.board.IBowlingBoardFactory;
import com.ecacho.challenge.bowling.exception.BowlingException;
import com.ecacho.challenge.utils.BowlingBoardUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.stream.IntStream;


public class TenPinBowlingBoardRollsCompletedTest {


    IBowlingBoardFactory factory = new TenPinBowlingBoardFactoryImpl();
    IBowlingBoard board;

    @Test
    public void twentyRollsTest() throws BowlingException {
        board = factory.createBoard();

        int[] ints = IntStream.range(0, 20)
                .map(it -> 4)
                .toArray();

        BowlingBoardUtils.addRollsToBoard(board, ints);

        Assert.assertEquals("Rolls remaining should be 0", Integer.valueOf(0), board.getRollsRemaining());
    }

    @Test
    public void twentyOneRollsWithStrikeTest() throws BowlingException {
        board = factory.createBoard();

        int[] rolls = new int[]{
                5,5, 5,5,
                5,5, 5,5,
                5,5, 5,5,
                5,5, 5,5,
                5,5, 10,5,
                5
        };

        BowlingBoardUtils.addRollsToBoard(board, rolls);

        Assert.assertEquals("Rolls remaining should be 0", Integer.valueOf(0), board.getRollsRemaining());
    }

    @Test
    public void twentyOneRollsWithSpareTest() throws BowlingException {
        board = factory.createBoard();

        int[] rolls = new int[]{
                5,5, 5,5,
                5,5, 5,5,
                5,5, 5,5,
                5,5, 5,5,
                5,5, 5,5,
                5
        };

        BowlingBoardUtils.addRollsToBoard(board, rolls);

        Assert.assertEquals("Rolls remaining should be 0", Integer.valueOf(0), board.getRollsRemaining());
    }


    @Test
    public void twentyOneRollsWithFirstStrikesTest() throws BowlingException {
        board = factory.createBoard();

        int[] rolls = new int[]{
                10, 10,
                10, 10,
                10, 5,5,
                5,5, 5,5,
                5,5, 5,5,
                5
        };

        BowlingBoardUtils.addRollsToBoard(board, rolls);

        Assert.assertEquals("Rolls remaining should be 0", Integer.valueOf(0), board.getRollsRemaining());
    }

    @Test
    public void allRollsWereStrikeTest() throws BowlingException {
        board = factory.createBoard();

        int[] ints = IntStream.range(0, 12)
                .map(it -> 10)
                .toArray();

        BowlingBoardUtils.addRollsToBoard(board, ints);

        Assert.assertEquals("Rolls remaining should be 0", Integer.valueOf(0), board.getRollsRemaining());

    }

    @Test
    public void fiveStrikesTest() throws BowlingException {
        board = factory.createBoard();

        IntStream strikesStream = IntStream.range(0, 5)
                .map(it -> 10);


        IntStream rollsStream = IntStream.range(0, 10)
                .map(it -> 4);

        BowlingBoardUtils.addRollsToBoard(board, IntStream.concat(strikesStream, rollsStream).toArray());

        Assert.assertEquals("Rolls remaining should be 0", Integer.valueOf(0), board.getRollsRemaining());

    }


}