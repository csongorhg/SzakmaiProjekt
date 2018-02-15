package hu.pejedlik.game.Loading;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import hu.pejedlik.game.Menu.MenuScreen;
import hu.pejedlik.game.Music.MusicClass;
import hu.pejedlik.game.MyBaseClasses.MyScreen;
import hu.pejedlik.game.GlobalClasses.*;
import hu.pejedlik.game.MyBaseClasses.OneSpriteStaticActor;
import hu.pejedlik.game.MyGdxGame;


public class LoadingScreen extends MyScreen {

	public static MusicClass music;
	private Stage stage;
	private OneSpriteStaticActor loadingImage, loadingLine;
	private float elapsedTime = 0;


    public LoadingScreen(MyGdxGame game) {
		super(game);

		stage = new Stage();

		loadingImage = new OneSpriteStaticActor("Loading/jedlik.png");
		loadingImage.setSize(stage.getViewport().getWorldWidth() * 0.95f, (stage.getViewport().getWorldHeight() - 10) * 0.95f);
		loadingImage.setPosition(stage.getViewport().getWorldWidth() / 2 - loadingImage.getWidth() / 2,
				stage.getViewport().getWorldHeight() / 2  - loadingImage.getHeight() / 2 + 10);
		stage.addActor(loadingImage);
		loadingImage.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
		loadingLine = new OneSpriteStaticActor("Loading/blackLine.png");
		loadingLine.setSize(0, loadingLine.getHeight() / 2);
		loadingLine.setPosition(0,0);
		stage.addActor(loadingLine);

		// Iskola képek felolvasása
		new ImageReader(EventType.SCHOOL);


    }
	BitmapFont bitmapFont = new BitmapFont();

    @Override
	public void show() {
	    Assets.manager.finishLoading();
		Assets.load();
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		stage.act(delta);
		stage.draw();

		loadingLine.setWidth(Assets.manager.getProgress() * 100f * stage.getViewport().getWorldWidth() / 100);

		if (Assets.manager.update()) {
			Assets.afterLoaded();

			//zene
			music = new MusicClass();
			music.playMusic();
			//zene vége

			game.setScreen(new MenuScreen(game));
		}

		elapsedTime+=delta;
	}

	@Override
	public void hide() {

	}

	@Override
	public void init() {
		setBackGroundColor(1f, 1f, 1f);
	}


}
