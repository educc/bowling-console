package com.ecacho.challenge.bowling.frame;

import com.ecacho.challenge.bowling.frame.impl.AbstractFrame;

public interface IFrameFactory {

    AbstractFrame createFirstFrame();
    AbstractFrame createNextFrame(AbstractFrame frame);
}
