package com.ecacho.challenge.client.console.model;

import com.ecacho.challenge.bowling.player.IPlayer;
import com.ecacho.challenge.bowling.roll.IRoll;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PlayerAndRoll {

    IPlayer player;
    Integer roll;
    boolean isFoul;
}
