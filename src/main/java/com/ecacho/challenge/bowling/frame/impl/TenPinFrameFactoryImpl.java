package com.ecacho.challenge.bowling.frame.impl;

import com.ecacho.challenge.bowling.frame.IFrameFactory;
import com.ecacho.challenge.bowling.frame.calculatescore.IScoreCalculate;
import com.ecacho.challenge.bowling.roll.IRollFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("TenPinFrameFactory")
public class TenPinFrameFactoryImpl implements IFrameFactory {

    IRollFactory tenPinRollFactory;
    IScoreCalculate scoreCalcForNormalFrame;
    IScoreCalculate scoreCalcForLastFrame;

    public TenPinFrameFactoryImpl(
            @Qualifier("TenPinRollFactory") IRollFactory tenPinRollFactory,
            @Qualifier("TenPinFrameScoreCalculate") IScoreCalculate scoreCalcForNormalFrame,
            @Qualifier("TenPinTenthFrameScoreCalculate") IScoreCalculate scoreCalcForLastFrame) {
        this.tenPinRollFactory = tenPinRollFactory;
        this.scoreCalcForNormalFrame = scoreCalcForNormalFrame;
        this.scoreCalcForLastFrame = scoreCalcForLastFrame;
    }

    @Override
    public AbstractFrame createFirstFrame() {
        FrameImpl frame = new FrameImpl(tenPinRollFactory, scoreCalcForNormalFrame);
        frame.setNumberOfFrame(1);
        return frame;
    }

    @Override
    public AbstractFrame createNextFrame(AbstractFrame lastFrame) {
        int numberOfFrame = lastFrame.getFrameNumber();
        if (numberOfFrame >= 9) {
            //create new frame
            TenthFrameImpl tenthFrame = new TenthFrameImpl(tenPinRollFactory, scoreCalcForLastFrame);
            tenthFrame.setNumberOfFrame(numberOfFrame+1);
            tenthFrame.setPreviousFrame(lastFrame);

            //set next frame
            lastFrame.setNextFrame(tenthFrame);

            return tenthFrame;
        }

        //create new frame
        FrameImpl frame = new FrameImpl(tenPinRollFactory, scoreCalcForNormalFrame);
        frame.setNumberOfFrame(numberOfFrame+1);
        frame.setPreviousFrame(lastFrame);

        //set next frame
        lastFrame.setNextFrame(frame);

        return frame;
    }

}
