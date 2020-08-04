package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.PhysicsImp;

public class SimScreen implements Screen {
    private MyGdxGame sim;
    Texture texture;
    // camera variables
    private OrthographicCamera simCam;
    private Viewport simPort;

    // tiled variables
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    public SimScreen(MyGdxGame sim){
        this.sim = sim;
        //texture = new Texture("badlogic.jpg");
        simCam = new OrthographicCamera();
        simPort = new FitViewport(50, 40, simCam);
        simPort.apply();


        mapLoader = new TmxMapLoader();
        map = mapLoader.load("projectmap.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1/16f);
        simCam.position.set(simPort.getWorldWidth()/10, simPort.getWorldHeight()/2 , 0 );

    }

    @Override
    public void show() {
    }

    public void handleInput(float dt){
        if (Gdx.input.isTouched())
            simCam.position.x += 1000 * dt;
    }

    public void update(float dt){
        handleInput(dt);
        simCam.update();
        renderer.setView(simCam);
    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(1,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.render();
        sim.batch.setProjectionMatrix(simCam.combined);

    }

    @Override
    public void resize(int width, int height) {
        simCam.setToOrtho(false, width/10, height/10);
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
