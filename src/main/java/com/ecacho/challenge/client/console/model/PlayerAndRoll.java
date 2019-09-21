package com.ecacho.challenge.client.console.model;

import com.ecacho.challenge.bowling.player.IPlayer;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PlayerAndRoll {

    IPlayer player;
    Integer roll;
}
