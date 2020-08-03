package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyGdxGame;

public class SimScreen implements Screen {
    private MyGdxGame sim;
    Texture texture;
    private OrthographicCamera simCam;
    private Viewport simPort;

    public SimScreen(MyGdxGame sim){
        this.sim = sim;
        texture = new Texture("badlogic.jpg");
        simCam = new OrthographicCamera();
        simPort = new ScreenViewport(simCam);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        sim.batch.setProjectionMatrix(simCam.combined);
        sim.batch.begin();
        sim.batch.draw(texture, 0,0);
        sim.batch.end();
    }

    @Override
    public void resize(int width, int height) {
        simPort.update(width, height);
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
