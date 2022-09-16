package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
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
import com.mygdx.game.scenes.MenuUI;
import com.mygdx.game.sprites.Bomb;
import com.mygdx.game.sprites.Plane;
import com.mygdx.game.tools.B2WorldCreator;
import com.mygdx.game.tools.WorldContactListener;

import org.lwjgl.Sys;

import java.awt.Menu;

public class SimScreen implements Screen {
    private MyGdxGame sim;
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
    private Plane plane;

    //textureAtlas for animations
    private TextureAtlas atlas;



    public SimScreen(MyGdxGame sim){
        this.sim = sim;
        simCam = new OrthographicCamera();
        simPort = new FitViewport(PhysicsImp.S_WIDTH / PhysicsImp.UNITSCALE, PhysicsImp.S_HEIGHT / PhysicsImp.UNITSCALE, simCam);
        simPort.apply();
        // creating the hud for the simulation
        hud = new Hud(sim.batch);

        //getting the texture atlas for our sprite sheet
        atlas = new TextureAtlas("sprites/sprite_sheet.txt");

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
        bomb = new Bomb(world, this);
        plane = new Plane(world, this);

        new B2WorldCreator(world, map);

        //identifying collisions
        world.setContactListener(new WorldContactListener());



    }

    public TextureAtlas getAtlas(){
        return atlas;
    }



    @Override
    public void show() {
    }

    public void handleInput(float dt){
        if (Hud.isStartPressed){
            //simCam.position.x += 1000 * dt;
            //bomb.setGravity(0);
//            world.setGravity(new Vector2(0,0));
//            bomb.b2dbody.applyLinearImpulse(new Vector2(1,0), bomb.b2dbody.getWorldCenter(), true);
//            plane.b2dbody.applyLinearImpulse(new Vector2(1,0), plane.b2dbody.getWorldCenter(), true);
            bomb.b2dbody.setLinearVelocity(PhysicsImp.PLANE_SPEED,0);
            plane.b2dbody.setLinearVelocity(PhysicsImp.PLANE_SPEED,0);

            if (Hud.isDropBombPressed){
                world.setGravity(new Vector2(0,-PhysicsImp.TOTAL_ACCELERATION( PhysicsImp.TOTAL_DOWNWARD_FORCE(PhysicsImp.LIFT_FORCE(PhysicsImp.RADIUS, PhysicsImp.BOMB_RPM, bomb.b2dbody.getLinearVelocity().x), PhysicsImp.WEIGHT_OF_BOMB(PhysicsImp.RADIUS, PhysicsImp.BOMB_DENSITY, PhysicsImp.BOMB_LENGTH)), PhysicsImp.MASS_OF_BOMB(PhysicsImp.RADIUS, PhysicsImp.BOMB_DENSITY, PhysicsImp.BOMB_LENGTH)) ));
                System.out.println("bombs away");
                Hud.isDropBombPressed = false;
                Hud.isStartPressed = false;
                PhysicsImp.PLANE_FLY_AWAY = true;


            }
        }
        if (PhysicsImp.PLANE_FLY_AWAY){
            plane.b2dbody.applyForceToCenter(new Vector2(0,PhysicsImp.TOTAL_ACCELERATION( PhysicsImp.TOTAL_DOWNWARD_FORCE(PhysicsImp.LIFT_FORCE(PhysicsImp.RADIUS, PhysicsImp.BOMB_RPM, bomb.b2dbody.getLinearVelocity().x), PhysicsImp.WEIGHT_OF_BOMB(PhysicsImp.RADIUS, PhysicsImp.BOMB_DENSITY, PhysicsImp.BOMB_LENGTH)), PhysicsImp.MASS_OF_BOMB(PhysicsImp.RADIUS, PhysicsImp.BOMB_DENSITY, PhysicsImp.BOMB_LENGTH))), true);
            plane.b2dbody.applyLinearImpulse(new Vector2(0,1), plane.b2dbody.getWorldCenter(), true);
//            PhysicsImp.PLANE_FLY_AWAY = false;
        }

        if (PhysicsImp.BOMB_HITS_WATER){
            if (!PhysicsImp.WillItBounce(PhysicsImp.ANGLE_OF_INCIDENCE(bomb.b2dbody.getLinearVelocity().x, bomb.b2dbody.getLinearVelocity().y))){
//                bomb.b2dbody.setLinearVelocity(new Vector2(bomb.b2dbody.getLinearVelocity().x * 0.9f , Math.abs(bomb.b2dbody.getLinearVelocity().y * 0.9f) ));
                bomb.b2dbody.setLinearVelocity(0,0);
                world.setGravity(new Vector2(0,-6));
                PhysicsImp.WATER_BIT = 16;
            }
            PhysicsImp.BOMB_HITS_WATER = false;
        }

        //i was just experimenting with sinking physics here
//        if(bomb.b2dbody.getPosition().y >= 13 && bomb.b2dbody.getPosition().y <= 13.1){
//            world.setGravity(new Vector2(0,-4));
//            bomb.b2dbody.setLinearVelocity(0,0);
//        }

        //returning to main menu and resetting all static variables
        if (hud.isBackToMenuPressed){
            MenuUI.startPressed = false;
            PhysicsImp.WATER_BIT = 4;
            PhysicsImp.PLANE_FLY_AWAY = false;
            PhysicsImp.BOMB_SINKS = false;
            sim.setScreen(new MainMenu(sim, sim));
            dispose();
        }
    }

    public void update(float dt) throws InterruptedException {
        handleInput(dt);
        bomb.update(dt);
        plane.update(dt);
        world.step(1/60f, 6, 2);
        simCam.position.x = bomb.b2dbody.getPosition().x;
        simCam.position.y = (PhysicsImp.S_HEIGHT / PhysicsImp.UNITSCALE)/2;
        simCam.update();
        renderer.setView(simCam);
        hud.calcDistance(bomb.b2dbody.getPosition().x);
        hud.calcSpeed(bomb.b2dbody.getLinearVelocity().x);
        hud.stage.act();

//        System.out.println(bomb.b2dbody.getPosition().y);
//        System.out.println("mass of bomb: " +  PhysicsImp.MASS_OF_BOMB( PhysicsImp.RADIUS, PhysicsImp.BOMB_DENSITY,PhysicsImp.BOMB_LENGTH));
//        System.out.println("total vortex strength :" + PhysicsImp.VORTEX(PhysicsImp.RADIUS, PhysicsImp.BOMB_RPM));
        System.out.println("total downward acceleration of the bomb: " + PhysicsImp.TOTAL_ACCELERATION( PhysicsImp.TOTAL_DOWNWARD_FORCE(PhysicsImp.LIFT_FORCE(PhysicsImp.RADIUS, PhysicsImp.BOMB_RPM, bomb.b2dbody.getLinearVelocity().x), PhysicsImp.WEIGHT_OF_BOMB(PhysicsImp.RADIUS, PhysicsImp.BOMB_DENSITY, PhysicsImp.BOMB_LENGTH)), PhysicsImp.MASS_OF_BOMB(PhysicsImp.RADIUS, PhysicsImp.BOMB_DENSITY, PhysicsImp.BOMB_LENGTH)));
//        System.out.println("total downward force of bomb :" + ( PhysicsImp.WEIGHT_OF_BOMB(PhysicsImp.RADIUS, PhysicsImp.BOMB_DENSITY, PhysicsImp.BOMB_LENGTH) - PhysicsImp.LIFT_FORCE(PhysicsImp.RADIUS,PhysicsImp.BOMB_RPM, bomb.b2dbody.getLinearVelocity().x))  );
//        System.out.println("lift force of the bomb : " +  PhysicsImp.LIFT_FORCE(PhysicsImp.RADIUS,PhysicsImp.BOMB_RPM, bomb.b2dbody.getLinearVelocity().x));
//        System.out.println("weight of bomb: " +  PhysicsImp.WEIGHT_OF_BOMB(PhysicsImp.RADIUS, PhysicsImp.BOMB_DENSITY, PhysicsImp.BOMB_LENGTH));
//        System.out.println("the position on the x axis: " +  bomb.b2dbody.getPosition().x);
//        System.out.println("the position on the y axis: " +bomb.b2dbody.getPosition().y);
//        System.out.println("the linear veloicity in the x axis : " + bomb.b2dbody.getLinearVelocity().x);
//        System.out.println("the linear velocity in the y acis : " + bomb.b2dbody.getLinearVelocity().y);
//        System.out.println("would this have bounced:" + new PhysicsImp().WillItBounce(bomb.b2dbody.getLinearVelocity().x, bomb.b2dbody.getLinearVelocity().y, Bomb.bombDensity));
//        System.out.println("the critical angle is: " + new PhysicsImp().criticalAngle(30));
//        System.out.println("the bombs angle is: " +   Math.abs((float) Math.atan (bomb.b2dbody.getLinearVelocity().y/ bomb.b2dbody.getLinearVelocity().x) * 180/ (float)Math.PI) );
//        System.out.println("bombs angle from method is: " + PhysicsImp.ANGLE_OF_INCIDENCE(bomb.b2dbody.getLinearVelocity().x, bomb.b2dbody.getLinearVelocity().y) );
//        System.out.println("will it bounce at current time : " + PhysicsImp.WillItBounce(PhysicsImp.ANGLE_OF_INCIDENCE(bomb.b2dbody.getLinearVelocity().x, bomb.b2dbody.getLinearVelocity().y)));

    }

    @Override
    public void render(float delta) {
        try {
            update(delta);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Gdx.gl.glClearColor(0,0,0,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.render();

        hud.stage.draw();

        sim.batch.setProjectionMatrix(simCam.combined);
        sim.batch.begin();
        bomb.draw(sim.batch);
        plane.draw(sim.batch);
        sim.batch.end();



        b2dr.render(world, simCam.combined);


    }

    @Override
    public void resize(int width, int height) {
        simCam.setToOrtho(false, width, height);
        simPort.update(width, height);
        hud.resize(width,height);
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
        hud.dispose();
//        renderer.dispose();
//        world.dispose();
//        b2dr.dispose();

    }
}
