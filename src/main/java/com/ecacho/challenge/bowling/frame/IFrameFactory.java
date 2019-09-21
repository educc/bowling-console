package com.ecacho.challenge.bowling.frame;

public interface IFrameFactory {

    AbstractFrame createFirstFrame();
    AbstractFrame createNextFrame(AbstractFrame frame);
}
