package com.ecacho.challenge.bowling.frame.impl;

import com.ecacho.challenge.bowling.frame.IFrameFactory;
import com.ecacho.challenge.bowling.roll.IRollFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("TenPinFrameFactory")
public class TenPinFrameFactoryImpl implements IFrameFactory {

    IRollFactory tenPinRollFactory;

    public TenPinFrameFactoryImpl(@Qualifier("TenPinRollFactory") IRollFactory tenPinRollFactory) {
        this.tenPinRollFactory = tenPinRollFactory;
    }

    @Override
    public AbstractFrame createFirstFrame() {
        FrameImpl frame = new FrameImpl(tenPinRollFactory);
        frame.setNumberOfFrame(1);
        return frame;
    }

    @Override
    public AbstractFrame createNextFrame(AbstractFrame lastFrame) {
        int numberOfFrame = lastFrame.getFrameNumber();
        if (numberOfFrame >= 9) {
            TenthFrameImpl tenthFrame = new TenthFrameImpl(tenPinRollFactory);
            tenthFrame.setNumberOfFrame(numberOfFrame+1);
            return tenthFrame;
        }
        FrameImpl frame = new FrameImpl(tenPinRollFactory);
        frame.setNumberOfFrame(numberOfFrame+1);
        return frame;
    }
}
