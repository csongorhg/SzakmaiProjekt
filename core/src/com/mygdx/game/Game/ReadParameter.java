package com.mygdx.game.Game;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.glutils.FileTextureData;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.GlobalClasses.Assets;

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
