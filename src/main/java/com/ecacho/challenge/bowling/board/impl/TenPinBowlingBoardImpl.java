package com.ecacho.challenge.bowling.board.impl;

import com.ecacho.challenge.bowling.board.IBowlingBoard;
import com.ecacho.challenge.bowling.exception.BowlingException;
import com.ecacho.challenge.bowling.frame.IFrame;
import com.ecacho.challenge.bowling.frame.impl.AbstractFrame;
import com.ecacho.challenge.bowling.frame.IFrameFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TenPinBowlingBoardImpl implements IBowlingBoard {


    private IFrameFactory frameFactory;
    protected int numberOfFrames;
    protected List<AbstractFrame> frames;

    public TenPinBowlingBoardImpl(IFrameFactory frameFactory, int numberOfFrames){
        this.frameFactory = frameFactory;
        this.numberOfFrames = numberOfFrames;
        this.initBoard();
    }

    private void initBoard(){
        this.frames = new ArrayList<>();
        if(this.numberOfFrames <= 0){
            return;
        }

        AbstractFrame currentFrame = this.frameFactory.createFirstFrame();
        this.frames.add(currentFrame);
        for(int i = 1; i < this.numberOfFrames; i++){
            this.frames.add(this.frameFactory.createNextFrame(currentFrame));
            currentFrame = this.frames.get(i);
        }
    }

    protected Optional<AbstractFrame> getLastFrameForAddRoll(){
        return this.frames
                .stream()
                .filter(it -> !it.isRollsCompleted())
                .findFirst();
    }

    @Override
    public void addRoll(Integer pins) throws BowlingException {
        Optional<AbstractFrame> last = getLastFrameForAddRoll();
        if (!last.isPresent()) {
            throw new BowlingException("All rolls were completed");
        }
        last.get().addRoll(pins);
    }

    @Override
    public void addFoulRoll() throws BowlingException {
        Optional<AbstractFrame> last = getLastFrameForAddRoll();
        if (!last.isPresent()) {
            throw new BowlingException("All rolls were completed");
        }
        last.get().addFoulRoll();
    }

    @Override
    public List<IFrame> getAllFrames() {
        return this.frames
                .stream()
                .map(it -> (IFrame) it)
                .collect(Collectors.toList());
    }

    @Override
    public Integer getRollsRemaining() {
        return this.frames
                .stream()
                .map(AbstractFrame::getRollsRemaining)
                .reduce(0, (subtotal, element) -> subtotal + element);
    }
}
