package com.ecacho.challenge.bowling.frame.calculate_score.impl;

import com.ecacho.challenge.bowling.frame.calculate_score.IScoreCalculate;
import com.ecacho.challenge.bowling.frame.impl.AbstractFrame;
import com.ecacho.challenge.bowling.roll.IRoll;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component("TenPinFrameScoreCalculate")
public class TenPinFrameScoreCalculateImpl implements IScoreCalculate {

    @Override
    public void calculateAndSetScore(AbstractFrame frame) {
        Optional<Integer> scoreBaseOpt = Optional.empty();
        Optional<AbstractFrame> previousFrame = frame.getPreviousFrame();

        if (previousFrame.isPresent()) {
            previousFrame.get().calculateAnsSetScore();
            scoreBaseOpt = previousFrame.get().getScore();
        } else {
            scoreBaseOpt = Optional.of(0);
        }

        if (!frame.isRollsCompleted() || !scoreBaseOpt.isPresent()) {
            return;
        }

        Integer scoreBase = scoreBaseOpt.get();
        Integer pins = getPinsFromRollPlayed(frame);

        if (frame.isStrike()) {
            Optional<Integer> pinsNextFrame = getPinsNextFrame(frame,2);

            if (pinsNextFrame.isPresent()) {
                frame.setScore(scoreBase + pins + pinsNextFrame.get());
            }

        } else if (frame.isSpare()) {
            Optional<Integer> pinsNextFrame = getPinsNextFrame(frame,1);

            if (pinsNextFrame.isPresent()) {
                frame.setScore(scoreBase + pins + pinsNextFrame.get());
            }

        } else {
            frame.setScore(scoreBase + pins);
        }
    }

    private Optional<Integer> getPinsNextFrame(AbstractFrame frame, int numberOfNextRolls) {
        List<IRoll> nextRolls = new ArrayList<>();

        Optional<AbstractFrame> currentFrame = Optional.of(frame);
        while (currentFrame.isPresent() && nextRolls.size() < numberOfNextRolls) {

            currentFrame = currentFrame.get().getNextFrame();
            if (currentFrame.isPresent()) {
                currentFrame.get().getAllRollsPlayed()
                        .forEach(it -> {
                            nextRolls.add(it);
                        });
            }
        }

        if (nextRolls.size() < numberOfNextRolls) {
            return Optional.empty();
        }

        return Optional.of(nextRolls.stream()
                .limit(numberOfNextRolls)
                .map(IRoll::getPins)
                .reduce(0 , (subtotal, item) -> subtotal + item));
    }

    protected Integer getPinsFromRollPlayed(AbstractFrame frame) {
        return frame.getAllRollsPlayed()
                .stream()
                .map(IRoll::getPins)
                .reduce(0, (subtotal, item) -> subtotal + item);
    }
}
