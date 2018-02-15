package hu.pejedlik.game.MyBaseClasses;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Created by Felhasznalo on 2017. 10. 28..
 */

public class ImgButton extends Actor implements InitableInterface{
    private Sprite img;
    public ImgButton(final Sprite sprite) {
        img = sprite;
        img.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        img.setBounds(this.getX(),this.getY(),this.getWidth(),this.getHeight());
        this.addListener(new ClickListener() {

            @Override
            public boolean handle(Event e) {
                return super.handle(e);
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                img.setAlpha(0.4f);
                return super.touchDown(event, x, y, pointer, button);

            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                img.setAlpha(1f);
                super.touchUp(event, x, y, pointer, button);
            }
        });
    }

    @Override
    public void init() {

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        img.setBounds(this.getX(),this.getY(),this.getWidth(),this.getHeight());
        img.draw(batch,parentAlpha);
    }
}
