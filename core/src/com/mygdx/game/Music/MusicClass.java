package com.mygdx.game.Music;

import com.badlogic.gdx.audio.Music;
import com.mygdx.game.GlobalClasses.Assets;

import static com.mygdx.game.GlobalClasses.Assets.MUSIC;

/**
 * Created by Vince on 2017. 11. 02..
 */

public class MusicClass {

    public MusicClass() {
        musicVolume(0.5f);
        loop();
    }

    private final static Music music = Assets.manager.get(MUSIC);

    public void musicVolume(float f){
        music.setVolume(f);
    }
    public void pauseMusic(){
        music.pause();
    }
    public void playMusic(){
        music.play();
    }
    public void loop(){
        music.setLooping(true);
    }

    //getterek
    public float getVolume(){return music.getVolume();}
    public boolean getPlaying() {return music.isPlaying();}

}
