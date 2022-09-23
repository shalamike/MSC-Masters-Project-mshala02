package com.mygdx.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.PhysicsImp;
import com.mygdx.game.screens.SimScreen;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Bomb extends Sprite{

    public World world; // this will be the world that mario will live inside
    public Body b2dbody; // box2d body

    //creating states depending on whether or not the bomb is sinking or bouncing
    public enum State{BOUNCING, SINKING, EXPLODE}
    public State currentState; // creating two states to keep track of when to change states
    public State previousState;
    private Animation<TextureRegion> bombSpinning; //
    private TextureRegion stationaryBomb;
    private TextureRegion explosion;

    private boolean hasBombExploded, isBouncing, isSinking;

    private float stateTimer;

    private BodyDef bdef;
    private FixtureDef fdef;
    private CircleShape shape;

    private SimScreen screen;



    public Bomb(SimScreen screen){
        super(screen.getAtlas().findRegion("circleRotation0"));

        this.screen = screen;
        bdef = new BodyDef(); // creating a new body definition for the bomb
        fdef = new FixtureDef(); // creating a new fixture def
        shape = new CircleShape(); // creating a circle for our fixture def for now

        currentState = State.BOUNCING;
        previousState = State.BOUNCING;
        stateTimer = 0;

        Array<TextureRegion> frames = new Array<TextureRegion>(); //creating an array for our frames to animate
        for (int i =0 ; i<3 ; i++)
            frames.add(new TextureRegion(getTexture(), i * 439, 0, 439,439));  //creating a for loop to add the appropriate images to our animation
        bombSpinning = new Animation<>(0.1f, frames); // animating our frames like a flip book with each one lasting 0.1f will change according to rpm
        frames.clear();


        this.world = screen.getWorld();
        defineBomb();

        stationaryBomb = new TextureRegion(getTexture(), 0,0,439, 439);


        setBounds(0,0, PhysicsImp.RADIUS * 2 /PhysicsImp.UNITSCALE, PhysicsImp.RADIUS * 2 /PhysicsImp.UNITSCALE);

        setRegion(stationaryBomb);

        explosion = new TextureRegion(screen.getAtlas().findRegion("explosion", 4), 500, 424, 512, 512);
    }

    //checking to see if the bombs state is sinking or bouncing
    public State getState(){
        if(b2dbody.getLinearVelocity().x < 0.1 ) //if bombs velocity is 0 then it must be sinking
            return State.SINKING;
        else if (hasBombExploded)
            return State.EXPLODE;
        else
            return State.BOUNCING;
    }

    public TextureRegion getFrame(float dt){
        currentState = getState();

        TextureRegion region;
        switch (currentState){
            case EXPLODE:
                region = explosion;
                break;
            case SINKING:
                region = stationaryBomb;
                break;
            case BOUNCING:  //setting the bouncing case as the Default case
            default:
                region = bombSpinning.getKeyFrame(stateTimer);
                break;

        }

        stateTimer = currentState == previousState ? stateTimer + dt : 0; // setting our state to either current state and checking to see if states have changed so that we reset our state timer
        previousState = currentState;
        return region;
    }

    public void update(float dt){
        setPosition(b2dbody.getPosition().x - getWidth() / 2 , b2dbody.getPosition().y - getHeight() /2);
        setRegion(getFrame(dt));
//        if (PhysicsImp.BOMB_EXPLODES){
//            fdef.filter.categoryBits = PhysicsImp.DESTROYED_BIT;
//
//        }
    }

    public void defineBomb(){

//        BodyDef bdef = new BodyDef(); // creating a new body definition for the bomb
        bdef.position.set(PhysicsImp.START_DISTANCE / PhysicsImp.UNITSCALE, 600 / PhysicsImp.UNITSCALE); // temporarily setting bomb position

        bdef.type = BodyDef.BodyType.DynamicBody; // setting the bombs body to dynamic body
        b2dbody = world.createBody(bdef);//now we have the box2d body defined, we can create the body in our game world

        //defining the fixtures

        shape.setRadius(PhysicsImp.RADIUS/ PhysicsImp.UNITSCALE); // setting the circles radius (subject to change)

        fdef.filter.categoryBits = PhysicsImp.BOMB_BIT; //setting the bombs fixture def to the bomb bit
        fdef.filter.maskBits = (short) (PhysicsImp.DEFAULT_BIT | PhysicsImp.WATER_BIT | PhysicsImp.DAM_BIT);



        fdef.shape = shape; // setting our shapes radius to the fixure def
        b2dbody.createFixture(fdef).setUserData("bomb"); // setting the fixture def to our body as well as the user data being bomb

        fdef.isSensor = true;


    }

    public void explosion(){

//        BodyDef bdef = new BodyDef(); // creating a new body definition for the bomb
        Vector2 currentPosition = b2dbody.getPosition();
        world.destroyBody(b2dbody);
        bdef.position.set(currentPosition); // temporarily setting bomb position

        bdef.type = BodyDef.BodyType.DynamicBody; // setting the bombs body to dynamic body
        b2dbody = world.createBody(bdef);//now we have the box2d body defined, we can create the body in our game world

        //defining the fixtures

        shape.setRadius(32/ PhysicsImp.UNITSCALE); // setting the circles radius (subject to change)

        fdef.filter.categoryBits = PhysicsImp.EXPLOSION_BIT; //setting the bombs fixture def to the bomb bit
        fdef.filter.maskBits = (short) (PhysicsImp.DEFAULT_BIT | PhysicsImp.WATER_BIT | PhysicsImp.DAM_BIT);



        fdef.shape = shape; // setting our shapes radius to the fixure def
        b2dbody.createFixture(fdef).setUserData("explosion"); // setting the fixture def to our body as well as the user data being bomb

        fdef.isSensor = true;


    }

    public void explode(){
        hasBombExploded = true;
        setRegion(new TextureRegion(screen.getAtlas().findRegion("explosion", 4), 500, 424, 512, 512));
        setBounds(0,0,64/PhysicsImp.UNITSCALE,64/PhysicsImp.UNITSCALE);
//        System.out.println("bomb Explodes");
        explosion();

    }

    public boolean isHasBombExploded() {
        return hasBombExploded;
    }

    public void setHasBombExploded(boolean hasBombExploded) {
        this.hasBombExploded = hasBombExploded;
    }

    public boolean isBouncing() {
        return isBouncing;
    }

    public void setBouncing(boolean bouncing) {
        isBouncing = bouncing;
    }

    public boolean isSinking() {
        return isSinking;
    }

    public void setSinking(boolean sinking) {
        isSinking = sinking;
    }



//    public void render(){
//        Gdx.gl.glClearColor(1,1,1,1);
//        Gdx.gl.glClear();
//    }



}
