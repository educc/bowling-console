package com.ecacho.challenge.client.console.player;

import com.ecacho.challenge.bowling.player.IPlayer;

import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimplePlayer that = (SimplePlayer) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
