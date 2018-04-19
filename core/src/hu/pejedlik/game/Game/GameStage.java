package hu.pejedlik.game.Game;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AssetLoader;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;
import java.util.Random;

import hu.pejedlik.game.GlobalClasses.Assets;
import hu.pejedlik.game.Loading.EventType;
import hu.pejedlik.game.Math.RandomNumber;
import hu.pejedlik.game.MyBaseClasses.ImgButton;
import hu.pejedlik.game.MyBaseClasses.MyLabel;
import hu.pejedlik.game.MyBaseClasses.MyStage;
import hu.pejedlik.game.MyBaseClasses.OneSpriteStaticActor;
import hu.pejedlik.game.MyBaseClasses.SituationsActor;
import hu.pejedlik.game.MyGdxGame;

/**
 * Created by Hegedüs Csongor on 2/14/2018.
 */

public class GameStage extends MyStage {
    public static boolean Newactor = false;
    public static boolean END = false;
    private Array<String> imgs;
    private Table table;
    private MyLabel courrent;
    private Table text;
    public GameStage(Viewport viewport, Batch batch, MyGdxGame game) {
        super(viewport, batch, game);
    }

    @Override
    public void init() {
        super.init();
        setCameraResetToLeftBottomOfScreen();
        addBackEventStackListener();
        table = new Table();
        table.setFillParent(true);
        text = new Table();
        text.setFillParent(true);
        Newactor = true;
        END = false;
        this.addActor(table);
        EventType.last.clear();
        courrent = new MyLabel("a",game.getSkin()){

            Pixmap a = new Pixmap(1,1, Pixmap.Format.RGB888);
            Sprite text = new Sprite(new Texture(a));
            @Override
            public void draw(Batch batch, float parentAlpha) {
                text.setAlpha(0.75f);
                text.setBounds(this.getX(),this.getY(),this.getWidth(),this.getHeight());
                text.draw(batch,parentAlpha);
                super.draw(batch, parentAlpha);
            }
        };
        courrent.setText("Jelenleg: "+Assets.getImg(EventType.currentId).getSubtitle());
        text.bottom();
        text.add(courrent);
        this.addActor(text);

    }
    private void actors()
    {
        imgs = Assets.getChilderns(EventType.currentId);
        if(imgs != null) {
            for (String a : imgs) {
                int i = 0;
                while (!a.equals(Assets.readImages.get(i).getId())) {
                    i++;
                }

                table.add(new SituationsActor(a,false)).size(300, 300).center().spaceLeft(50f);
                /*MyLabel myLabel = new MyLabel(Assets.readImages.get(i).getSubtitle(), game.getSkin());
                //myLabel.setPosition(?,?);
                addActor(myLabel);*/

            }
        }
        else
        {
            table.add(new SituationsActor("END", false)).size(100, 100).center().spaceLeft(50f);
        }

    }
    private void button()
    {
        imgs = Assets.getChilderns(EventType.currentId);
        if(imgs != null) {
            String endid = "";
            Array<String> randoms = new Array<String>();
            Array<String> normal = new Array<String>();
            for (String id : imgs) {
                if (Assets.getImg(id).getType().equals("r")) {
                    randoms.add(id);
                } else {
                    normal.add(id);
                }
            }
            Random random = new Random();
            if (normal.size == 0 && randoms.size != 0) {
                int r = random.nextInt(randoms.size);
                endid = randoms.get(r);
            } else if (normal.size != 0 && randoms.size == 0) {
                endid = normal.get(0);
            } else if (normal.size != 0 && randoms.size != 0) {
                int chance = random.nextInt(100);
                if (chance < 30) {
                    int c = random.nextInt(randoms.size);
                    endid = randoms.get(c);
                } else {
                    endid = normal.get(0);
                }
            }
            table.add(new SituationsActor(endid, true)).size(100, 100).center().spaceLeft(50f);
        }
        else
        {
            table.add(new SituationsActor("END", true)).size(100, 100).center().spaceLeft(50f);
        }
    }
    @Override
    public void act(float delta) {
        super.act(delta);
        if(Newactor)
        {
            if(imgs != null) {
                imgs.clear();
                table.clear();
            }
            else
            {
                table.clear();
            }
            if(Assets.getImg(EventType.currentId).getType().equals("?")) {
                actors();
            }
            else
            {
                button();
            }
            if(EventType.currentId.equals(EventType.nextId))
            courrent.setText("Jelenleg: "+Assets.getImg(EventType.currentId).getSubtitle());
            Newactor = false;
            for(ReadImages dat : Assets.readImages)
            {
                Assets.pref.putBoolean(dat.getId()+ EventType.currentEventType,dat.isPlayed());
            }
            Assets.pref.flush();
        }
        if(SituationsActor.clicked) {
            for (Cell a : table.getCells()) {
                SituationsActor actor = (SituationsActor) a.getActor();
                if(!actor.id.equals(SituationsActor.first)) {
                    actor.firstclick = true;
                }




            }
            SituationsActor.clicked = false;
        }
        if(END)
        {
            game.setScreenBackByStackPop();
        }
    }

    @Override
    public void dispose() {
        super.dispose();

    }
}
