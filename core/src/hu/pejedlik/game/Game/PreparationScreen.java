package hu.pejedlik.game.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

import java.util.Arrays;

import hu.pejedlik.game.GlobalClasses.Assets;
import hu.pejedlik.game.Graph.SituationParameterScreen;
import hu.pejedlik.game.Loading.EventType;
import hu.pejedlik.game.MyBaseClasses.MyLabel;
import hu.pejedlik.game.MyBaseClasses.MyScreen;
import hu.pejedlik.game.MyBaseClasses.OneSpriteStaticActor;
import hu.pejedlik.game.MyGdxGame;
import hu.pejedlik.game.String.FirstLetterUppercase;

/**
 * Created by Hegedüs Csongor on 2/20/2018.
 */

public class PreparationScreen extends MyScreen {

    private Stage stage;
    private OneSpriteStaticActor loadingImage1, loadingImage2, loadingLine;
    private float elapsedTime;
    private MyLabel eventLabel;


        public PreparationScreen(MyGdxGame game) {
            super(game);

            loadingLine = new OneSpriteStaticActor("load/blackline.png");
            loadingLine.setSize(0, loadingLine.getHeight() / 2);
            loadingLine.setPosition(0,0);
            stage.addActor(loadingLine);
        }

        @Override
        public void show() {
            Assets.manager.finishLoading();
            load();
        }

        private void load() {
            FileHandle fileHandle = Gdx.files.internal("parameters/" + EventType.currentEventType.toString() + "_parameter.txt");
            String[] read  = fileHandle.readString().split("\n");
            Assets.data = new String[read.length];
            Assets.longestLine = 0; // Getting the longest line
            int index = 0;
            for (String name : read) {
                String path = "events/" + EventType.currentEventType.toString() + "_event/" + name.trim()+".png";
                Assets.manager.load(path, Texture.class);
                path = "events/" + EventType.currentEventType.toString() + "_event/" + name.trim()+"2.png";
                Assets.manager.load(path, Texture.class);
                Assets.longestLine = name.length() > Assets.longestLine ? name.length() : Assets.longestLine;
                Assets.data[index] = name.trim();
                index++;
            }
        }


        @Override
        public void render(float delta) {
            super.render(delta);

            stage.act(delta);
            stage.draw();
            loadingLine.setWidth(Assets.manager.getProgress() * 100f * stage.getViewport().getWorldWidth() / 100);
            loadingImage1.setAlpha(Math.round(Assets.manager.getProgress() * 100f) / 100f);
            loadingImage2.setAlpha(1f - Math.round(Assets.manager.getProgress() * 100f) / 100f);

            if (Assets.manager.update()) {
                game.setScreen(new SituationParameterScreen(game), false);
            }

            elapsedTime += delta;

        }

        @Override
        public void hide() {

        }

        @Override
        public void init() {
            setBackGroundColor(1f, 1f, 1f);
            stage = new Stage();
            // színes
            loadingImage1 = new OneSpriteStaticActor("menu/" + EventType.currentEventType.toString() + ".png");
            // nem színes
            loadingImage2 = new OneSpriteStaticActor("menu/" + EventType.currentEventType.toString() + "2.png");

            loadingImage1.setSize(stage.getViewport().getWorldWidth(), stage.getViewport().getWorldHeight() - 10);
            loadingImage2.setSize(stage.getViewport().getWorldWidth(), stage.getViewport().getWorldHeight());

            loadingImage1.setPosition(stage.getViewport().getWorldWidth() / 2 - loadingImage1.getWidth() / 2,
                    stage.getViewport().getWorldHeight() / 2  - loadingImage1.getHeight() / 2 + 10);
            loadingImage2.setPosition(stage.getViewport().getWorldWidth() / 2 - loadingImage2.getWidth() / 2,
                    stage.getViewport().getWorldHeight() / 2  - loadingImage2.getHeight() / 2 + 10);

            loadingImage1.setAlpha(0f);

            eventLabel = new MyLabel(new FirstLetterUppercase(EventType.currentEventType.toString()).toString(), game.getSkin());
            eventLabel.setPosition(stage.getViewport().getWorldWidth() / 2 - eventLabel.getWidth() / 2,
                    stage.getViewport().getWorldHeight() - eventLabel.getHeight() * 2);
            eventLabel.setSize(300f, 300f);

            stage.addActor(loadingImage1);
            stage.addActor(loadingImage2);
            stage.addActor(eventLabel);

           // Assets.readImages = new Array<ReadImages>();
        }



    }
