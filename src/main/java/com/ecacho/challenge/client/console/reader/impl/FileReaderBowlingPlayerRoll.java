package com.ecacho.challenge.client.console.reader.impl;

import com.ecacho.challenge.client.console.exception.ConsoleBowlingException;
import com.ecacho.challenge.client.console.model.PlayerAndRoll;
import com.ecacho.challenge.client.console.player.SimplePlayer;
import com.ecacho.challenge.client.console.reader.IReaderBowlingPlayerRoll;

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

    public FileReaderBowlingPlayerRoll(String filename) {
        this.filename = filename;
        this.patternLine = Pattern.compile("[a-zA-Z]+\\t\\d+$");
        this.patternPlayerName = Pattern.compile("[a-zA-Z]+");
        this.patternPlayerRoll = Pattern.compile("\\d+");
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
        Integer roll = Integer.valueOf(findByPattern(patternPlayerRoll, line));
        return new PlayerAndRoll(new SimplePlayer(playerName), roll);
    }

    private String findByPattern(Pattern pattern, String str){
        Matcher matcher = pattern.matcher(str);

        while (matcher.find()) {
            return matcher.group();
        }
        return null;
    }

    @Override
    public List<PlayerAndRoll> readAll() throws ConsoleBowlingException {
        List<PlayerAndRoll> result = new LinkedList<>();


        try (BufferedReader br = Files.newBufferedReader(Paths.get(filename))) {

            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
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
}
