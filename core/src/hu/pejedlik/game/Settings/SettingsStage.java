package hu.pejedlik.game.Settings;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import hu.pejedlik.game.Loading.LoadingScreen;
import hu.pejedlik.game.GlobalClasses.Assets;
import hu.pejedlik.game.MyBaseClasses.MyButton;
import hu.pejedlik.game.MyBaseClasses.MyStage;
import hu.pejedlik.game.MyBaseClasses.OneSpriteStaticActor;
import hu.pejedlik.game.MyGdxGame;

/**
 * Created by Vince on 2017. 11. 02..
 */

public class SettingsStage extends MyStage {

    private Slider slider;

    public SettingsStage(Viewport viewport, Batch batch, MyGdxGame game) {
        super(viewport, batch, game);
    }

    public void init() {
        super.init();

        setCameraResetToLeftBottomOfScreen();

        //vissza gomb
        MyButton back = new MyButton("Vissza",game.getTextButtonStyle());
        back.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreenBackByStackPop();
            }
        });
        addActor(back);
        back.setPosition(((ExtendViewport)getViewport()).getMinWorldWidth() /2 - back.getWidth()/2, 0);

        musicVolume();
    }

    void musicVolume(){
        slider = new Slider(0, 100, 1, false, game.getSliderStyle(((ExtendViewport)getViewport()).getMinWorldWidth()/2, ((ExtendViewport)getViewport()).getMinWorldHeight()/20));
        addActor(slider);
        slider.setValue(LoadingScreen.music.getVolume()*100);
        slider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                LoadingScreen.music.musicVolume((float)(slider.getValue()/100.0));
            }
        });
        slider.setSize(((ExtendViewport)getViewport()).getMinWorldWidth()/2, ((ExtendViewport)getViewport()).getMinWorldHeight()/20);
        slider.setPosition(((ExtendViewport)getViewport()).getMinWorldWidth()/2-slider.getWidth()/2,
                ((ExtendViewport)getViewport()).getMinWorldHeight()/2-slider.getHeight()/2);
    }

    @Override
    public void dispose() {
        super.dispose();
        System.out.println("SettingsStage disposed!");
    }
}
