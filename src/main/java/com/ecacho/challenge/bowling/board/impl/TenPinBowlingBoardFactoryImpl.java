package com.ecacho.challenge.bowling.board.impl;

import com.ecacho.challenge.bowling.board.IBowlingBoard;
import com.ecacho.challenge.bowling.board.IBowlingBoardFactory;
import com.ecacho.challenge.bowling.frame.impl.TenPinFrameFactoryImpl;

public class TenPinBowlingBoardFactoryImpl implements IBowlingBoardFactory {

    @Override
    public IBowlingBoard createBoard() {
        return new TenPinBowlingBoardImpl(new TenPinFrameFactoryImpl(), 10);
    }
}
