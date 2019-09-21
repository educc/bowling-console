package com.ecacho.challenge.client.console.reader.impl;

import com.ecacho.challenge.client.console.exception.ConsoleBowlingException;
import com.ecacho.challenge.client.console.model.PlayerAndRoll;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class FileReaderBowlingPlayerRollTest {


    @Test
    public void fileOkTest() throws ConsoleBowlingException {
        FileReaderBowlingPlayerRoll reader = createReader("input_reader/input_ok.txt");

        List<PlayerAndRoll> playerAndRolls = reader.readAll();
        assertEquals(3, playerAndRolls.size());
    }

    @Test(expected = ConsoleBowlingException.class)
    public void fileErrorTest() throws ConsoleBowlingException {
        FileReaderBowlingPlayerRoll reader = createReader("input_reader/input_fail.txt");
        reader.readAll();
    }

    @Test
    public void fileEmptyTest() throws ConsoleBowlingException {
        FileReaderBowlingPlayerRoll reader = createReader("input_reader/input_empty.txt");

        List<PlayerAndRoll> playerAndRolls = reader.readAll();
        assertEquals(0, playerAndRolls.size());

    }

    private FileReaderBowlingPlayerRoll createReader(String filename){
        ClassLoader classLoader = getClass().getClassLoader();
        String path = classLoader.getResource(filename).getPath();
        return new FileReaderBowlingPlayerRoll(path);
    }
}