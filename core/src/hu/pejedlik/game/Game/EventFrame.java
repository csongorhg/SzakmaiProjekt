package hu.pejedlik.game.Game;

import com.badlogic.gdx.graphics.Texture;
import hu.pejedlik.game.MyBaseClasses.OneSpriteStaticActor;

/**
 * Created by Hegedüs Csongor on 2/14/2018.
 */

public class EventFrame extends OneSpriteStaticActor{

    private String id;

    public EventFrame(Texture texture, String id) {
        super(texture);
        this.id = id;
    }
}
