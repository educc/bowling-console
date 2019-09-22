package com.ecacho.challenge.bowling.board.impl;

import com.ecacho.challenge.bowling.board.IBowlingBoard;
import com.ecacho.challenge.bowling.board.IBowlingBoardFactory;
import com.ecacho.challenge.bowling.frame.IFrameFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("TenPinBowlingBoardFactory")
public class TenPinBowlingBoardFactoryImpl implements IBowlingBoardFactory {

    IFrameFactory tenpinFrameFactory;

    public TenPinBowlingBoardFactoryImpl(
            @Qualifier("TenPinFrameFactory") IFrameFactory tenpinFrameFactory) {
        this.tenpinFrameFactory = tenpinFrameFactory;
    }

    @Override
    public IBowlingBoard createBoard() {
        return new TenPinBowlingBoardImpl(this.tenpinFrameFactory, 10);
    }
}
