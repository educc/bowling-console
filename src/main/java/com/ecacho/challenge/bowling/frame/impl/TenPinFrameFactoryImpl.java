package com.ecacho.challenge.bowling.frame.impl;

import com.ecacho.challenge.bowling.frame.AbstractFrame;
import com.ecacho.challenge.bowling.frame.IFrameFactory;
import org.springframework.stereotype.Component;

@Component
public class TenPinFrameFactoryImpl implements IFrameFactory {

    @Override
    public AbstractFrame createFirstFrame() {
        return new TenPinFrameImpl(1);
    }

    @Override
    public AbstractFrame createNextFrame(AbstractFrame lastFrame) {
        int numberOfFrame = lastFrame.getFrameNumber();
        if (numberOfFrame >= 9) {
            return new TenPinTenthFrameImpl();
        }
        return new TenPinFrameImpl(numberOfFrame+1);
    }
}
