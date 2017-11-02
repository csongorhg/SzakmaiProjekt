package com.mygdx.game.Settings;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.DemoMenu.MenuStage;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.MyBaseClasses.MyButton;
import com.mygdx.game.MyBaseClasses.MyStage;
import com.mygdx.game.MyBaseClasses.OneSpriteStaticActor;
import com.mygdx.game.MyGdxGame;

/**
 * Created by Vince on 2017. 11. 02..
 */

public class SettingsStage extends MyStage {



    public SettingsStage(Viewport viewport, Batch batch, MyGdxGame game) {
        super(viewport, batch, game);
    }


    public void init() {
        super.init();
        addBackEventStackListener();

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


    //a zenének a hangosítása / halkítása --- rajzzal együtt
    private void musicVolume(){
        OneSpriteStaticActor plusSpriteActor, minusSpriteActor;
        final float pozXandSize = ((ExtendViewport)getViewport()).getMinWorldWidth()/13; // 3. - és 14 +  (0-tól van az index)
        final float pozY = ((ExtendViewport)getViewport()).getMinWorldHeight()/2;
        minusSpriteActor = new OneSpriteStaticActor(Assets.manager.get(Assets.MINUS_TEXTURE));
        minusSpriteActor.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if(MenuStage.music.getVolume() >= 0f)
                    MenuStage.music.musicVolume(MenuStage.music.getVolume()-0.1f);
                volumeBarDraw(pozXandSize, pozY);
            }
        });
        addActor(minusSpriteActor);
        minusSpriteActor.setSize(pozXandSize,pozXandSize);
        minusSpriteActor.setPosition(pozXandSize*1,pozY-pozXandSize/2);

        plusSpriteActor = new OneSpriteStaticActor(Assets.manager.get(Assets.PLUS_TEXTURE));
        plusSpriteActor.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if(MenuStage.music.getVolume() <= 0.9f)
                    MenuStage.music.musicVolume(MenuStage.music.getVolume()+0.1f);
                volumeBarDraw(pozXandSize, pozY);
            }
        });
        addActor(plusSpriteActor);
        plusSpriteActor.setSize(pozXandSize,pozXandSize);
        plusSpriteActor.setPosition(pozXandSize*11,pozY-pozXandSize/2);
        volumeBarDraw(pozXandSize, pozY);
    }

    private void volumeBarDraw(float pozXandSize, float pozY){ //a - és a + közötti kis rublikákat rajzolja ki
        OneSpriteStaticActor barSpriteActor = null;
        for (Actor actor:getActors()) {
            if(actor == barSpriteActor)
                actor.remove();
        }
        int volume = (int)(MenuStage.music.getVolume()*10);
        for(int i = 1; i <= 9; i++){
            if(volume != 0 && i <= volume){
                barSpriteActor = new OneSpriteStaticActor(Assets.manager.get(Assets.FULLBAR_TEXTURE));
                addActor(barSpriteActor);
                barSpriteActor.setSize(pozXandSize, pozXandSize);
                barSpriteActor.setPosition(pozXandSize*(i+1),pozY-pozXandSize/2);
            }
            else{
                barSpriteActor = new OneSpriteStaticActor(Assets.manager.get(Assets.EMPTYBAR_TEXTURE));
                addActor(barSpriteActor);
                barSpriteActor.setSize(pozXandSize, pozXandSize);
                barSpriteActor.setPosition(pozXandSize*(i+1),pozY-pozXandSize/2);
            }
        }
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
