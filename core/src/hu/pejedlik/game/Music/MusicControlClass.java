package hu.pejedlik.game.Music;

import com.badlogic.gdx.audio.Music;
import hu.pejedlik.game.GlobalClasses.Assets;

//import static hu.pejedlik.game.GlobalClasses.Assets.MUSIC;

/**
 * Created by Vince on 2017. 11. 02..
 */

public class MusicControlClass {

    private static Music music;

    public MusicControlClass() {
        music = Assets.manager.get(Assets.MUSIC);
        musicVolume(0.5f);
        loop();
    }

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
