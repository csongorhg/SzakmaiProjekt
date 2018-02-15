package hu.pejedlik.game.Menu;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import hu.pejedlik.game.GlobalClasses.Assets;
import hu.pejedlik.game.MyBaseClasses.OneSpriteStaticActor;

/**
 * Created by Hegedüs Csongor on 2/11/2018.
 */

public class Heart extends OneSpriteStaticActor{

    private static int heartCount = 0;
    private float heartDissapearDelay = 10;

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

    @Override
    public void act(float delta) {
        super.act(delta);
        if (elapsedTime >= heartDissapearDelay) {
            sprite.setAlpha(sprite.getColor().a - 0.01f); // Sprite ős nem rendelkezik getAlpha getterrel, mert a colorból kiolvasható
            if (sprite.getColor().a == 0) remove();
        }

    }
}
