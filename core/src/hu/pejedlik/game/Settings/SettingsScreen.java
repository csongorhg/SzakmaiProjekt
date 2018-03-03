package hu.pejedlik.game.Settings;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import hu.pejedlik.game.MyBaseClasses.MyScreen;
import hu.pejedlik.game.MyGdxGame;

/**
 * Created by Vince on 2017. 11. 02..
 */

public class SettingsScreen extends MyScreen {


    protected SettingsStage settingsStage;


    public SettingsScreen(MyGdxGame game) {
        super(game);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        settingsStage.act(delta);
        settingsStage.draw();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void dispose() {
        super.dispose();
        System.out.println("SettingsScreen disposed!");
    }

    @Override
    public void init() {
        settingsStage  = new SettingsStage(new ExtendViewport(1280,720,new OrthographicCamera(1280,720)), spriteBatch, game);
        setBackGroundColor(1f,1f,1f);
        Gdx.input.setInputProcessor(settingsStage);
    }


}