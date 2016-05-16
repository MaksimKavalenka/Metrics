package by.training.other;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

// Just call the icon 'Jackson'
public class Moonwalker {

    private static final String TITLE = "Jackson";

    private static boolean      flag  = true;

    public static synchronized void orNot(final String title) {
        if (flag && TITLE.equals(title)) {
            flag = false;

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        File file = new File("src/main/resources/other/moonwalker.mp3");
                        FileInputStream fis = new FileInputStream(file);
                        BufferedInputStream bis = new BufferedInputStream(fis);

                        Player player = new Player(bis);
                        player.play();
                    } catch (IOException | JavaLayerException e) {
                    }
                }
            }).start();
        }
    }
}
