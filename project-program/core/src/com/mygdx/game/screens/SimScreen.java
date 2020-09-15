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
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.PhysicsImp;
import com.mygdx.game.scenes.Hud;

public class SimScreen implements Screen {
    private MyGdxGame sim;
    Texture texture;
    // camera variables
    private OrthographicCamera simCam;
    private Viewport simPort;

    //hud
    private Hud hud;

    // tiled variables
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    //box 2d variables
    private World world;
    private Box2DDebugRenderer b2dr;





    public SimScreen(MyGdxGame sim){
        this.sim = sim;
        //texture = new Texture("badlogic.jpg");
        simCam = new OrthographicCamera();
        simPort = new FitViewport(PhysicsImp.S_WIDTH, PhysicsImp.S_HEIGHT, simCam);
        simPort.apply();
        // creating the hud for the simulation
        hud = new Hud(sim.batch);

        // initialising tiled variables
        mapLoader = new TmxMapLoader();
        map = mapLoader.load("projectmap.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        simCam.position.set(PhysicsImp.W_WIDTH, PhysicsImp.W_HEIGHT, 0 );
        // initiallising box2d variables
        world = new World(new Vector2(0,-10), true);



    }

    @Override
    public void show() {
    }

    public void handleInput(float dt){
        if (Gdx.input.isTouched()){
            simCam.position.x += 100 * dt;
        }
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
        sim.batch.setProjectionMatrix(simCam.projection);
        hud.stage.draw();



    }

    @Override
    public void resize(int width, int height) {
        simCam.setToOrtho(false, width, height);
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
