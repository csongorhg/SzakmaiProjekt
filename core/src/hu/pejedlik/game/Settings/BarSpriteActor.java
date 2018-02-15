package hu.pejedlik.game.Settings;

import com.badlogic.gdx.graphics.Texture;
import hu.pejedlik.game.MyBaseClasses.OneSpriteStaticActor;

/**
 * Created by Vince on 2017. 11. 04..
 */

public class BarSpriteActor extends OneSpriteStaticActor {

    public BarSpriteActor(Texture texture, float x, float y, float size) {
        super(texture);
        this.setSize(size,size);
        this.setPosition(x,y);
    }

}
