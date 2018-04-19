package hu.pejedlik.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import hu.pejedlik.game.Game.ReadImages;
import hu.pejedlik.game.GlobalClasses.Assets;
import hu.pejedlik.game.Loading.EventType;
import hu.pejedlik.game.Loading.LoadingScreen;
import hu.pejedlik.game.MyBaseClasses.MyScreen;

import java.lang.reflect.InvocationTargetException;
import java.util.Stack;

public class MyGdxGame extends Game {

    public final Stack<Class> backButtonStack = new Stack();
    protected Slider.SliderStyle style;

    public Label.LabelStyle getLabelStyle() {
        Label.LabelStyle style;
        style = new Label.LabelStyle();
        style.font = Assets.manager.get(Assets.ALEGREYAREGULAR_FONT);
        style.fontColor = Color.YELLOW;
        Pixmap p = new Pixmap(1, 1, Pixmap.Format.RGB888);
        p.setColor(0.4f, 0.2f, 0.8f, 0.5f);
        p.fill();
        return style;
    }

    public TextField.TextFieldStyle getTextFieldStyle() {
        /*TextField.TextFieldStyle style = new TextField.TextFieldStyle();
		style.background = new TextureRegionDrawable(new TextureRegion(Assets.manager.get(Assets.TEXTBOX_TEXTURE)));
		style.background.setLeftWidth(style.background.getLeftWidth() + 20);
		style.background.setRightWidth(style.background.getRightWidth() + 20);
		style.font = Assets.manager.get(Assets.ALEGREYAREGULAR_FONT);
		style.cursor = new TextureRegionDrawable(new TextureRegion(new TextureRegion(Assets.manager.get(Assets.CURSOR_TEXTURE))));
		style.cursor.setMinWidth(50);
		style.fontColor = Color.BLACK;
		Pixmap p = new Pixmap(1, 1, Pixmap.Format.RGB888);
		p.setColor(0.4f, 0.2f, 0.8f, 0.5f);
		p.fill();
		style.selection = new TextureRegionDrawable(new TextureRegion(new Texture(p)));
		return style;*/

		TextField.TextFieldStyle style;
		style = new TextField.TextFieldStyle();
		style.font = Assets.manager.get(Assets.ALEGREYAREGULAR_FONT);

		style.fontColor = Color.BLACK;
		Pixmap p = new Pixmap(1,1, Pixmap.Format.RGBA8888);

		p.setColor(Color.BLACK);
		p.fill();
		style.cursor = new TextureRegionDrawable(new TextureRegion(new Texture(p)));
		style.cursor.setMinWidth(10);

		p.setColor(Color.WHITE);
		p.fill();
		style.background = new TextureRegionDrawable(new TextureRegion(new Texture(p)));

		return style;
	}

	public Skin getSkin()
	{
		return Assets.manager.get(Assets.SKIN);
	}

	public Slider.SliderStyle getSliderStyle(float x, float y){
		style = new Slider.SliderStyle();
		Texture t = Assets.manager.get(Assets.BLACKLINE_TEXTURE);
		style.background = new TextureRegionDrawable(new TextureRegion(t));
		style.background.setMinWidth(x);
		style.background.setMinHeight(y);
		style.knob = new TextureRegionDrawable(new TextureRegion(Assets.manager.get(Assets.SLIDERBUTTON_TEXTURE)));
		style.knob.setMinHeight(x/7);
		style.knob.setMinWidth(x/7);
		return style;
	}

	public Slider.SliderStyle getSliderStyle() {
		return style;
	}


	public TextButton.TextButtonStyle getTextButtonStyle() {

		TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
		textButtonStyle.font = Assets.manager.get(Assets.ALEGREYAREGULAR_FONT);

		Pixmap p = new Pixmap(1,1, Pixmap.Format.RGB888);
		p.setColor(0.1f,0.2f,0.2f, 0.5f);
		p.fill();
		textButtonStyle.up = new TextureRegionDrawable(new TextureRegion(new Texture(p)));

		p.setColor(0.3f,0.5f,0.8f, 0.5f);
		p.fill();
		textButtonStyle.over = new TextureRegionDrawable(new TextureRegion(new Texture(p)));

		p.setColor(1f,0.5f,0.8f, 1f);
		p.fill();
		textButtonStyle.down = new TextureRegionDrawable(new TextureRegion(new Texture(p)));
		return textButtonStyle;
	}


	@Override
	public void create () {
		Assets.prepare();
		setScreen(new LoadingScreen(this), false); // avoid putting back to stack
		Gdx.input.setCatchBackKey(true);
		Gdx.input.setCatchMenuKey(true);
	}

	@Override
	public void resume() {
		super.resume();
		Assets.manager.update();
	}

	@Override
	public void dispose () {
		super.dispose();

	}

	@Override
	public void pause() {
		super.pause();
	}

	public void setScreenBackByStackPop(){
		if (backButtonStack.size()>=1){
			try {
				setScreen((MyScreen) backButtonStack.pop().getConstructor(MyGdxGame.class).newInstance(this));
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		else
		{
			Gdx.app.exit();
		}
	}


	public void setScreen(Screen screen, boolean pushToStack) {
		Screen prevScreen = getScreen();
		if (prevScreen!=null) {
			if (pushToStack) {backButtonStack.push(prevScreen.getClass());
			}
			prevScreen.dispose();
		}
		super.setScreen(screen);
	}

}
