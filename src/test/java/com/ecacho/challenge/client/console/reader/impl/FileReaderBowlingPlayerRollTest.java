package com.ecacho.challenge.client.console.reader.impl;

import com.ecacho.challenge.bowling.roll.impl.TenPinRollFactoryImpl;
import com.ecacho.challenge.client.console.exception.ConsoleBowlingException;
import com.ecacho.challenge.client.console.model.PlayerAndRoll;
import org.junit.Test;

import java.io.File;
import java.net.URISyntaxException;
import java.util.List;

import static org.junit.Assert.*;

public class FileReaderBowlingPlayerRollTest {

    @Test
    public void fileOkTest() throws ConsoleBowlingException {
        FileReaderBowlingPlayerRoll reader = createReader("input_reader/input_ok.txt");
        assertTrue(reader != null);

        List<PlayerAndRoll> playerAndRolls = reader.readAll();
        assertEquals(3, playerAndRolls.size());
    }

    @Test(expected = ConsoleBowlingException.class)
    public void fileErrorTest() throws ConsoleBowlingException {
        FileReaderBowlingPlayerRoll reader = createReader("input_reader/input_fail.txt");
        assertTrue(reader != null);
        reader.readAll();
    }

    @Test
    public void fileEmptyTest() throws ConsoleBowlingException {
        FileReaderBowlingPlayerRoll reader = createReader("input_reader/input_empty.txt");

        assertTrue(reader != null);

        List<PlayerAndRoll> playerAndRolls = reader.readAll();
        assertEquals(0, playerAndRolls.size());
    }

    private FileReaderBowlingPlayerRoll createReader(String filename){
        ClassLoader classLoader = getClass().getClassLoader();
        String path = null;
        FileReaderBowlingPlayerRoll reader = null;
        try {
            path = new File(classLoader.getResource(filename).toURI())
                    .getPath();
            reader = new FileReaderBowlingPlayerRoll(path);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return reader;
    }
}