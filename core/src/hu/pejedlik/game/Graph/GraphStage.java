package hu.pejedlik.game.Graph;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * Created by Felhasznalo on 2018. 02. 26..
 */

public class GraphStage  extends Stage implements GestureDetector.GestureListener {
    OrthographicCamera c;
    public GraphStage()
    {
        c = (OrthographicCamera) this.getCamera();
    }

    @Override
    public void draw() {
        super.draw();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        c.update();
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        c.position.x-=deltaX;
        c.position.y+=deltaY;
        System.out.println();
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    private int zoomcount = 2;
    private float zoom;
    @Override
    public boolean zoom(float initialDistance, float distance) {
        if (zoomcount==0){
            zoom = c.zoom;
        }else {
            c.zoom = zoom * initialDistance / distance;
        }
        if (c.zoom<0.5f){
            c.zoom = 0.5f;
        }
        if (c.zoom>2f){
            c.zoom = 2f;
        }
        c.update();
        zoomcount = 2;
        System.out.println("asd");
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    @Override
    public void pinchStop() {

    }
}
