package hu.pejedlik.game.MyBaseClasses;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import hu.pejedlik.game.Game.GameStage;
import hu.pejedlik.game.Game.ReadImages;
import hu.pejedlik.game.GlobalClasses.Assets;
import hu.pejedlik.game.Loading.EventType;

/**
 * Created by Felhasznalo on 2018. 03. 06..
 */

public class SituationsActor extends Actor{
    private Sprite img;
    private float f;
    public SituationsActor(final String id)
    {
        final ReadImages a = Assets.getImg(id);
        if(a.isPlayed())
        {
            img = new Sprite((Texture) Assets.manager.get(a.getPath()));
        }
        else {
            img = new Sprite((Texture) Assets.manager.get(a.getPath2()));
        }
        img.setAlpha(0f);
        this.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                EventType.currentId = id;
                a.setPlayed(true);
                GameStage.Newactor = true;
            }
        });
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        img.draw(batch);
        if(f <1)
        {
            f+= 0.01f;
            if(f > 1)
            {
                f = 1;
            }
        }

        img.setAlpha(f);

    }

    @Override
    public void setBounds(float x, float y, float width, float height) {
        super.setBounds(x, y, width, height);
        img.setBounds(x,y,width,height);
    }
}
