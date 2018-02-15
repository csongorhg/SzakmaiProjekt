package hu.pejedlik.game.Game;

import com.badlogic.gdx.utils.Array;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * Created by Hegedüs Csongor on 2/14/2018.
 */

public class ReadParameter {

    private Array<EventFrame> readParameter;

    public ReadParameter(String filename) {
        readParameter = new Array<EventFrame>();



        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("Parameters/" + filename)));
            while(br.ready()) {
                br.readLine();
            }br.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
