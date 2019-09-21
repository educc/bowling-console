package com.ecacho.challenge.client.console.player;

import com.ecacho.challenge.bowling.player.IPlayer;

public class SimplePlayer implements IPlayer {

    private String name;

    public SimplePlayer(String name){
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
