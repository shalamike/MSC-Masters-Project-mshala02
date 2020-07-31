package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyGdxGame;


/* this screen is where the main simulation of the bouncing bomb will take place*/
public class SimScreen implements Screen {
    private MyGdxGame sim;
    // creating the camera and view port
    private OrthographicCamera simCam;
    private Viewport simPort;

    private Texture texture;

    public SimScreen(MyGdxGame sim){
        this.sim = sim;

        simCam = new OrthographicCamera();
        simPort = new ScreenViewport(simCam);

        texture = new Texture("badlogic.jpg");

    }



    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        sim.batch.begin();
        sim.batch.draw(texture, 0, 0);
        sim.batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
