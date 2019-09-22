package com.ecacho.challenge.bowling.frame.calculatescore.impl;

import com.ecacho.challenge.bowling.frame.impl.AbstractFrame;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("TenPinTenthFrameScoreCalculate")
public class TenPinTenthFrameScoreCalculateImpl extends TenPinFrameScoreCalculateImpl {


    @Override
    public void calculateAndSetScore(AbstractFrame frame) {
        Optional<Integer> scoreBaseOpt = Optional.empty();
        Optional<AbstractFrame> previousFrame = frame.getPreviousFrame();

        if (previousFrame.isPresent()) {
            previousFrame.get().calculateAnsSetScore();
            scoreBaseOpt = previousFrame.get().getScore();
        }

        if (!frame.isRollsCompleted() || !scoreBaseOpt.isPresent()) {
            return;
        }

        Integer scoreBase = scoreBaseOpt.get();
        Integer pins = getPinsFromRollPlayed(frame);
        frame.setScore(scoreBase + pins);
    }
}
