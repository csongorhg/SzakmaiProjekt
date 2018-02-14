package com.mygdx.game.Game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.MyBaseClasses.ImgButton;
import com.mygdx.game.MyBaseClasses.OneSpriteStaticActor;

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
