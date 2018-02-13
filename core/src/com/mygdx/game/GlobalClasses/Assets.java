//https://github.com/tuskeb/mester
package com.mygdx.game.GlobalClasses;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.ObjectMap;

import javax.xml.soap.Text;


public class Assets {
	// https://github.com/libgdx/libgdx/wiki/Managing-your-assets
	// http://www.jacobplaster.net/using-libgdx-asset-manager
	// https://www.youtube.com/watch?v=JXThbQir2gU
	// https://github.com/Matsemann/libgdx-loading-screen/blob/master/Main/src/com/matsemann/libgdxloadingscreen/screen/LoadingScreen.java

	public static AssetManager manager;
	public static final String CHARS = "0123456789öüóqwertzuiopőúasdfghjkléáűíyxcvbnm'+!%/=()ÖÜÓQWERTZUIOPŐÚASDFGHJKLÉÁŰÍYXCVBNM?:_*<>#&@{}[],-.";


	static final FreetypeFontLoader.FreeTypeFontLoaderParameter fontParameter = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
	static {
		fontParameter.fontFileName = "alegreyaregular.otf";
		fontParameter.fontParameters.size = 50;
		fontParameter.fontParameters.characters = CHARS;
		fontParameter.fontParameters.color = Color.WHITE;
	}
	static final SkinLoader.SkinParameter skinparameter;
	static {
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("arial.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter p = new FreeTypeFontGenerator.FreeTypeFontParameter();
		p.color = Color.WHITE;
		p.size = (1280 * 22 / 1280);
		p.characters = CHARS;

		BitmapFont font = generator.generateFont(p);
		generator.dispose();
		ObjectMap<String, Object> fontMap = new ObjectMap<String, Object>();
		fontMap.put("default-font", font);
		skinparameter = new SkinLoader.SkinParameter(fontMap);
	}
	public static final AssetDescriptor<Skin> SKIN = new AssetDescriptor<Skin>("uiskin.json",Skin.class,skinparameter);
	public static final AssetDescriptor<BitmapFont> ALEGREYAREGULAR_FONT
			= new AssetDescriptor<BitmapFont>(fontParameter.fontFileName, BitmapFont.class, fontParameter);


	public static final AssetDescriptor<Texture> SETTINGSBUTTON_TEXTURE
			= new AssetDescriptor<Texture>("settingsButton.png", Texture.class);
	public static final AssetDescriptor<Texture> LOADINGBACKGROUND_TEXTURE
			= new AssetDescriptor<Texture>("loading/jedlik.png", Texture.class);
	public static final AssetDescriptor<Texture> BACKGROUND_TEXTURE
			= new AssetDescriptor<Texture>("menu/background.png", Texture.class);
	public static final AssetDescriptor<Texture> HEART_TEXTURE
			= new AssetDescriptor<Texture>("menu/heart.png", Texture.class);
	// Témák
	public static final AssetDescriptor<Texture> CONFLICT_TEXTURE
			= new AssetDescriptor<Texture>("menu/conflict.png", Texture.class);
	public static final AssetDescriptor<Texture> INTERNET_TEXTURE
			= new AssetDescriptor<Texture>("menu/internet.png", Texture.class);
	public static final AssetDescriptor<Texture> KITCHEN_TEXTURE
			= new AssetDescriptor<Texture>("menu/kitchen.png", Texture.class);
	public static final AssetDescriptor<Texture> PLAYGROUND_TEXTURE
			= new AssetDescriptor<Texture>("menu/playground.png", Texture.class);
	public static final AssetDescriptor<Texture> ROOM_TEXTURE
			= new AssetDescriptor<Texture>("menu/room.png", Texture.class);
	public static final AssetDescriptor<Texture> SCHOOL_TEXTURE
			= new AssetDescriptor<Texture>("menu/school.png", Texture.class);
	public static final AssetDescriptor<Texture> STRANGERS_TEXTURE
			= new AssetDescriptor<Texture>("menu/strangers.png", Texture.class);
	public static final AssetDescriptor<Texture> TRANSPORT_TEXTURE
			= new AssetDescriptor<Texture>("menu/transport.png", Texture.class);


	public static final AssetDescriptor<TextureAtlas> EXPLOSION_TEXTUREATLAS
			= new AssetDescriptor<TextureAtlas>("pre.atlas", TextureAtlas.class);
	public static final AssetDescriptor<TextureAtlas> EMOJIS_TEXTUREATLAS
			= new AssetDescriptor<TextureAtlas>("menu/emojis.atlas", TextureAtlas.class);

	//zene
    public static final AssetDescriptor<Music> MUSIC
            = new AssetDescriptor<Music>("music/music.mp3", Music.class);
	public static final AssetDescriptor<Texture> MINUS_TEXTURE
			= new AssetDescriptor<Texture>("music/sminus.png", Texture.class);
	public static final AssetDescriptor<Texture> PLUS_TEXTURE
			= new AssetDescriptor<Texture>("music/splus.png", Texture.class);
	public static final AssetDescriptor<Texture> EMPTYBAR_TEXTURE
			= new AssetDescriptor<Texture>("music/sempty.png", Texture.class);
	public static final AssetDescriptor<Texture> FULLBAR_TEXTURE
			= new AssetDescriptor<Texture>("music/sfull.png", Texture.class);
	//zene vége

    public static final AssetDescriptor<Sound> STAR_SOUND
            = new AssetDescriptor<Sound>("star.wav", Sound.class);



    public static void prepare() {
		manager = new AssetManager();
		Texture.setAssetManager(manager);
	}

	public static void load() {
		FileHandleResolver resolver = new InternalFileHandleResolver();
		manager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
		manager.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver));
		manager.setLoader(BitmapFont.class, ".otf", new FreetypeFontLoader(resolver));

		manager.load(SKIN);
		manager.load(EXPLOSION_TEXTUREATLAS);
		manager.load(EMOJIS_TEXTUREATLAS);
		manager.load(HEART_TEXTURE);
		manager.load(SETTINGSBUTTON_TEXTURE);
		manager.load(ALEGREYAREGULAR_FONT);
		manager.load(LOADINGBACKGROUND_TEXTURE);
		manager.load(BACKGROUND_TEXTURE);
		manager.load(CONFLICT_TEXTURE);
		manager.load(INTERNET_TEXTURE);
		manager.load(KITCHEN_TEXTURE);
		manager.load(PLAYGROUND_TEXTURE);
		manager.load(ROOM_TEXTURE);
		manager.load(SCHOOL_TEXTURE);
		manager.load(STRANGERS_TEXTURE);
		manager.load(TRANSPORT_TEXTURE);

		//zene
		manager.load(MUSIC);
		manager.load(MINUS_TEXTURE);
		manager.load(PLUS_TEXTURE);
		manager.load(EMPTYBAR_TEXTURE);
		manager.load(FULLBAR_TEXTURE);
		//zene vége
	}

    public static void afterLoaded() {
        //manager.get(MUSIC).setLooping(true);
    }

	public static void unload() {
		manager.dispose();
	}

}
