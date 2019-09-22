package com.ecacho.challenge.client.console.reader.impl;

import com.ecacho.challenge.bowling.exception.BowlingException;
import com.ecacho.challenge.bowling.game.IBowlingGame;
import com.ecacho.challenge.bowling.roll.IRoll;
import com.ecacho.challenge.bowling.roll.IRollFactory;
import com.ecacho.challenge.client.console.exception.ConsoleBowlingException;
import com.ecacho.challenge.client.console.model.PlayerAndRoll;
import com.ecacho.challenge.client.console.player.SimplePlayer;
import com.ecacho.challenge.client.console.reader.IReaderBowlingPlayerRoll;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileReaderBowlingPlayerRoll implements IReaderBowlingPlayerRoll {

    String filename;
    Pattern patternLine;
    Pattern patternPlayerName;
    Pattern patternPlayerRoll;
    Pattern patternFoulRoll;


    public FileReaderBowlingPlayerRoll(String filename) {
        this.filename = filename;
        this.patternLine = Pattern.compile("^[a-zA-Z]+\\t(\\d+|F)$");
        this.patternPlayerName = Pattern.compile("[a-zA-Z]+");
        this.patternPlayerRoll = Pattern.compile("\\d+");
        this.patternFoulRoll = Pattern.compile("F$");
    }

    private void validateLine(String line) throws ConsoleBowlingException {
        Matcher matcher = patternLine.matcher(line);
        if (!matcher.find()) {
            throw new ConsoleBowlingException("Cannot parse this line: " + line);
        }
    }

    private PlayerAndRoll parseLine(String line) throws ConsoleBowlingException {
        validateLine(line);
        String playerName = findByPattern(patternPlayerName, line);

        String rollStr = findByPattern(patternPlayerRoll, line);
        String foulStr = findByPattern(patternFoulRoll, line);

        PlayerAndRoll playerAndRoll = null;

        if (rollStr != null) {
            playerAndRoll = new PlayerAndRoll(
                    new SimplePlayer(playerName),
                    Integer.valueOf(rollStr),
                    false
            );
        }

        if (foulStr != null) {
            playerAndRoll = new PlayerAndRoll(
                    new SimplePlayer(playerName),
                    0,
                    true
            );
        }

        if (playerAndRoll == null) {
            throw new ConsoleBowlingException("Doesn't find a roll valid in this line: " + line);
        }

        return playerAndRoll;
    }

    private String findByPattern(Pattern pattern, String str){
        Matcher matcher = pattern.matcher(str);

        while (matcher.find()) {
            return matcher.group();
        }
        return null;
    }

    public List<PlayerAndRoll> readAll() throws ConsoleBowlingException {
        List<PlayerAndRoll> result = new LinkedList<>();


        try (BufferedReader br = Files.newBufferedReader(Paths.get(filename))) {

            String line;
            while ((line = br.readLine()) != null) {
                if (line.isEmpty()) {
                    continue;
                }
                result.add(parseLine(line));
            }

        } catch (IOException e) {
            e.printStackTrace();
            throw new ConsoleBowlingException("Error at read the file: " + filename);
        }
        return result;
    }

    @Override
    public void addAllPlayersRollToBowlingGame(IBowlingGame game) throws ConsoleBowlingException {

        try {
            for(PlayerAndRoll item: readAll()) {
                if (item.isFoul()) {
                        game.addPlayerFoulRoll(item.getPlayer());
                } else {
                    game.addPlayerRoll(item.getPlayer(), item.getRoll());
                }
            }

        } catch (BowlingException e) {
            e.printStackTrace();
            throw new ConsoleBowlingException(e.getMessage());
        }
    }
}
