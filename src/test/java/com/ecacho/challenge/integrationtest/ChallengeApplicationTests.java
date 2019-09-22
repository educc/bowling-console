package com.ecacho.challenge.integrationtest;

import com.ecacho.challenge.client.console.exception.ConsoleBowlingException;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.rule.OutputCapture;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ChallengeApplicationTests {


    @Autowired
    ApplicationContext ctx;


    @Rule
    public OutputCapture outputCapture = new OutputCapture();

    @Test
    public void youtubeExampleTest() throws Exception {
        CommandLineRunner runner = ctx.getBean(CommandLineRunner.class);
        runner.run ( "./data/game_youtube_player.txt");

        Map<String, Integer> usersAndScore = BowlingAppParser.findUsersAndScore(outputCapture.toString());
        Assert.assertEquals("The user doesn't have 170 as score",
                Integer.valueOf(170), usersAndScore.get("you"));
    }

    @Test
    public void perfectGameTest() throws Exception {
        CommandLineRunner runner = ctx.getBean(CommandLineRunner.class);
        runner.run ( "./data/game_perfect.txt");

        Map<String, Integer> usersAndScore = BowlingAppParser.findUsersAndScore(outputCapture.toString());
        Assert.assertEquals("The user doesn't have perfect score (300)",
                Integer.valueOf(300), usersAndScore.get("lee"));
    }

    @Test
    public void zeroGameTest() throws Exception {
        CommandLineRunner runner = ctx.getBean(CommandLineRunner.class);
        runner.run ( "./data/game_zero.txt");

        Map<String, Integer> usersAndScore = BowlingAppParser.findUsersAndScore(outputCapture.toString());
        Assert.assertEquals("The user doesn't have 0 score",
                Integer.valueOf(0), usersAndScore.get("lee"));
    }

    @Test
    public void twoPlayerGameTest() throws Exception {
        CommandLineRunner runner = ctx.getBean(CommandLineRunner.class);
        runner.run ( "./data/game_two_player.txt");

        Map<String, Integer> usersAndScore = BowlingAppParser.findUsersAndScore(outputCapture.toString());
        Assert.assertEquals("The user doesn't have 0 score",
                Integer.valueOf(170), usersAndScore.get("you"));
        Assert.assertEquals("The user doesn't have 0 score",
                Integer.valueOf(50), usersAndScore.get("edu"));
    }

    @Test
    public void onePlayerGameTest() throws Exception {
        CommandLineRunner runner = ctx.getBean(CommandLineRunner.class);
        runner.run ( "./data/game_one_player.txt");

        Map<String, Integer> usersAndScore = BowlingAppParser.findUsersAndScore(outputCapture.toString());
        Assert.assertEquals("The user doesn't have 0 score",
                Integer.valueOf(103), usersAndScore.get("edu"));
    }

    @Test(expected = ConsoleBowlingException.class)
    public void failParseTest() throws Exception {
        CommandLineRunner runner = ctx.getBean(CommandLineRunner.class);
        runner.run ( "./data/game_fail_parse.txt");
    }

    @Test(expected = ConsoleBowlingException.class)
    public void failByRollsExceedTest() throws Exception {
        CommandLineRunner runner = ctx.getBean(CommandLineRunner.class);
        runner.run ( "./data/game_fail_rolls_exceed.txt");
    }
}
