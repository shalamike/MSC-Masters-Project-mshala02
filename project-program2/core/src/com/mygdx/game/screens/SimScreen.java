package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.PhysicsImp;
import com.mygdx.game.scenes.Hud;
import com.mygdx.game.sprites.Bomb;
import com.mygdx.game.tools.B2WorldCreator;
import com.mygdx.game.tools.WorldContactListener;

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

    //Dynamic bodies/ the bomb and plane
    private Bomb bomb;




    public SimScreen(MyGdxGame sim){
        this.sim = sim;
        //texture = new Texture("badlogic.jpg");
        simCam = new OrthographicCamera();
        simPort = new FitViewport(PhysicsImp.S_WIDTH / PhysicsImp.UNITSCALE, PhysicsImp.S_HEIGHT / PhysicsImp.UNITSCALE, simCam);
        simPort.apply();
        // creating the hud for the simulation
        hud = new Hud(sim.batch);

        // initialising tiled variables
        mapLoader = new TmxMapLoader();
        map = mapLoader.load("projectmap.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1 / PhysicsImp.UNITSCALE);
        simCam.position.set(simPort.getWorldWidth()/2, simPort.getWorldHeight()/2, 0 );
        // initiallising box2d variables
        world = new World(new Vector2(0,0), true);



//        BodyDef bdef = new BodyDef();
//        PolygonShape shape = new PolygonShape();
//        FixtureDef fdef = new FixtureDef();
//        Body body;
        b2dr = new Box2DDebugRenderer();

        //initialising the bomb
        bomb = new Bomb(world);

        new B2WorldCreator(world, map);

        //identifying collisions
        world.setContactListener(new WorldContactListener());

    }

    @Override
    public void show() {
    }

    public void handleInput(float dt){
        if (Gdx.input.isTouched()){
            //simCam.position.x += 1000 * dt;
            //bomb.setGravity(0);
            world.setGravity(new Vector2(0,-10));
            bomb.b2dbody.applyLinearImpulse(new Vector2(200,bomb.getGravity()), bomb.b2dbody.getWorldCenter(), true);

        }
    }

    public void update(float dt){
        handleInput(dt);
        world.step(1/60f, 6, 2);
        simCam.position.x = bomb.b2dbody.getPosition().x;
        simCam.position.y = bomb.b2dbody.getPosition().y;
        simCam.update();
        renderer.setView(simCam);
        hud.calcDistance(bomb.b2dbody.getPosition().x);
        hud.calcSpeed(bomb.b2dbody.getLinearVelocity().x);

//        System.out.println("lift force of the bomb : " +  new PhysicsImp().liftForce(Bomb.radius,600, bomb.b2dbody.getLinearVelocity().x));
//        System.out.println("the position on the x axis: " +  bomb.b2dbody.getPosition().x);
//        System.out.println("the position on the y axis: " +bomb.b2dbody.getPosition().y);
//        System.out.println("the linear veloicity in the x axis : " + bomb.b2dbody.getLinearVelocity().x);
//        System.out.println("the linear velocity in the y acis : " + bomb.b2dbody.getLinearVelocity().y);
//        System.out.println("would this have bounced:" + new PhysicsImp().WillItBounce(bomb.b2dbody.getLinearVelocity().x, bomb.b2dbody.getLinearVelocity().y, Bomb.bombDensity));
//        System.out.println("the critical angle is: " + new PhysicsImp().criticalAngle(30));
//        System.out.println("the bombs angle is: " +   Math.abs((float) Math.atan (bomb.b2dbody.getLinearVelocity().y/ bomb.b2dbody.getLinearVelocity().x) * 180/ (float)Math.PI) );
    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0,0,0,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.render();
        sim.batch.setProjectionMatrix(simCam.projection);
        hud.stage.draw();

        b2dr.render(world, simCam.combined);


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
        map.dispose();
        renderer.dispose();
        world.dispose();
        b2dr.dispose();
        hud.dispose();
    }
}
