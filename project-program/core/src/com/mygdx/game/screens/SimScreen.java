package com.mygdx.game.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyGdxGame;


/* this screen is where the main simulation of the bouncing bomb will take place*/
public class SimScreen implements Screen {
    private MyGdxGame sim; //

    private OrthographicCamera simCam;
    private Viewport simPort;
    private Texture texture;

    public SimScreen(MyGdxGame sim){
        this.sim = sim;

        simCam = new OrthographicCamera();
        simPort = new ScreenViewport();

        texture = new Texture("badlogic.jph");
    }



    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

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
