package com.mygdx.game.Menu;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.MyBaseClasses.OneSpriteStaticActor;

/**
 * Created by Hegedüs Csongor on 2/11/2018.
 */

public class Heart extends OneSpriteStaticActor{

    private static int heartCount = 0;

    public Heart(EmojiStream emojiStream) {
        super(Assets.manager.get(Assets.HEART_TEXTURE));
        heartCount++;
        setSize(50,50);
        setPosition(emojiStream.getX() + emojiStream.getWidth() / 2 - getWidth() / 2
                ,emojiStream.getY() + emojiStream.getHeight() / 2 - getWidth() / 2);
        setRotation(emojiStream.getRotation());
    }

    public int getHeartCount() {
        return heartCount;
    }

    public static void setHeartCount(int heartCount) {
        Heart.heartCount = heartCount;
    }
}