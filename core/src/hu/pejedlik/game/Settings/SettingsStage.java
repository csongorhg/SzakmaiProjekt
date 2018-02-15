package hu.pejedlik.game.Settings;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
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



    public SettingsStage(Viewport viewport, Batch batch, MyGdxGame game) {
        super(viewport, batch, game);
    }


    public void init() {
        super.init();

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


    //a zenének a hangosítása / halkítása, + és - rajz
    private void musicVolume(){
        OneSpriteStaticActor plusSpriteActor, minusSpriteActor;
        final float pozXandSize = ((ExtendViewport)getViewport()).getMinWorldWidth()/13; // 3. - és 14 +  (0-tól van az index)
        final float pozY = ((ExtendViewport)getViewport()).getMinWorldHeight()/2;
        minusSpriteActor = new OneSpriteStaticActor(Assets.manager.get(Assets.MINUS_TEXTURE));
        minusSpriteActor.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if(LoadingScreen.music.getVolume() >= 0f)
                    LoadingScreen.music.musicVolume(LoadingScreen.music.getVolume()-0.1f);
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
                if(LoadingScreen.music.getVolume() <= 0.9f)
                    LoadingScreen.music.musicVolume(LoadingScreen.music.getVolume()+0.1f);
                volumeBarDraw(pozXandSize, pozY);
            }
        });
        addActor(plusSpriteActor);
        plusSpriteActor.setSize(pozXandSize,pozXandSize);
        plusSpriteActor.setPosition(pozXandSize*11,pozY-pozXandSize/2);
        volumeBarDraw(pozXandSize, pozY);
    }

    //+ és a - közötti állapotsáv kirajzolása
    private void volumeBarDraw(float pozXandSize, float pozY){ //a - és a + közötti kis rublikákat rajzolja ki
        BarSpriteActor barSpriteActor = null;
        for (Actor actor:getActors()) {
            if(actor instanceof BarSpriteActor){
                actor.remove();
            }
        }
        int volume = (int)(LoadingScreen.music.getVolume()*10);
        for(int i = 1; i <= 9; i++){
            if(volume != 0 && i <= volume){
                barSpriteActor = new BarSpriteActor(Assets.manager.get(Assets.FULLBAR_TEXTURE), pozXandSize*(i+1),pozY-pozXandSize/2, pozXandSize);
                addActor(barSpriteActor);
            }
            else{
                barSpriteActor = new BarSpriteActor(Assets.manager.get(Assets.EMPTYBAR_TEXTURE), pozXandSize*(i+1),pozY-pozXandSize/2, pozXandSize);
                addActor(barSpriteActor);
            }
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        System.out.println("SettingsStage disposed!");
    }
}
