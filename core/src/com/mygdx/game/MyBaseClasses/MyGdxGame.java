package com.mygdx.game.MyBaseClasses;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.Loading.LoadingScreen;

import java.lang.reflect.InvocationTargetException;
import java.util.Stack;

public class MyGdxGame extends Game {

	public final Stack<Class> backButtonStack = new Stack();

	public Label.LabelStyle getLabelStyle() {
		Label.LabelStyle style;
		style = new Label.LabelStyle();
		style.font = Assets.manager.get(Assets.ACMEREGULAR_FONT);
		style.fontColor = Color.YELLOW;
		Pixmap p = new Pixmap(1,1, Pixmap.Format.RGB888);
		p.setColor(0.4f,0.2f,0.8f, 0.5f);
		p.fill();
		return style;
	}

	public TextField.TextFieldStyle getTextFieldStyle() {
		TextField.TextFieldStyle style = new TextField.TextFieldStyle();
		style.background = new TextureRegionDrawable(new TextureRegion(Assets.manager.get(Assets.TEST_TEXTURE)));
		style.background.setLeftWidth(style.background.getLeftWidth()+20);
		style.background.setRightWidth(style.background.getRightWidth()+20);
		style.font = Assets.manager.get(Assets.ACMEREGULAR_FONT);
		style.cursor = new TextureRegionDrawable(new TextureRegion(new TextureRegion(Assets.manager.get(Assets.TEST_TEXTURE))));
		style.cursor.setMinWidth(50);
		style.fontColor = Color.BLACK;
		Pixmap p = new Pixmap(1,1, Pixmap.Format.RGB888);
		p.setColor(0.4f,0.2f,0.8f, 0.5f);
		p.fill();
		style.selection = new TextureRegionDrawable(new TextureRegion(new Texture(p)));
		return style;
	}


	public TextButton.TextButtonStyle getTextButtonStyle() {

		TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
		textButtonStyle.font = Assets.manager.get(Assets.ACMEREGULAR_FONT);

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
		//setScreen(new LoadingScreen(this));
	}

	@Override
	public void resume() {
		super.resume();
		Assets.manager.update();
	}

	@Override
	public void dispose () {
		super.dispose();
		Assets.unload();
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void setScreen(Screen screen) {
		setScreen(screen,true);
	}

	public void setScreenBackByStackPop(){
		if (backButtonStack.size()>1){
			try {
				setScreen((com.mygdx.game.MyBaseClasses.MyScreen) backButtonStack.pop().getConstructor(MyGdxGame.class).newInstance(this),false);
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
			if (pushToStack) {backButtonStack.push(prevScreen.getClass());}
			try {
				prevScreen.dispose();
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
		super.setScreen(screen);
	}

}
