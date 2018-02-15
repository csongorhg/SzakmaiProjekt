package hu.pejedlik.game.Loading;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.graphics.Texture;
import hu.pejedlik.game.GlobalClasses.Assets;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * Created by Hegedüs Csongor on 2/14/2018.
 */

public class ImageReader {

    public ImageReader(EventType eventType) {
        try{
            BufferedReader br = new BufferedReader(new FileReader(new File("Parameters/" + eventType.toString() + ".txt")));
            while (br.ready()) {
                String read = br.readLine();
                // Színes képek
                Assets.manager.load(new AssetDescriptor("Events/" + eventType.toString() + "/" + read + ".png", Texture.class));
                // Nem színes képek
                Assets.manager.load(new AssetDescriptor("Events/" + eventType.toString() + "/" + read + "2.png", Texture.class));
            }
            br.close();
        }catch(Exception e) {e.printStackTrace();}

    }
}
