package hu.pejedlik.game.MyBaseClasses;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by tuskeb on 2016. 09. 30..
 */
public class OneSpriteStaticActor extends OneSpriteActor {

    public OneSpriteStaticActor(String file) {
        super(new Sprite(new Texture(file)));
    }

    public OneSpriteStaticActor(Texture texture) {
        super(new Sprite(texture));
    }

    public Texture getTexture()
    {
        return sprite.getTexture();
    }

    public void setAlpha(float a) {
        super.sprite.setColor(getColor().r, getColor().g, getColor().b, a);
    }

    public float getAlpha() {
        return super.sprite.getColor().a;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
}
