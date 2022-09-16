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
import com.mygdx.game.PhysicsImp;
import com.mygdx.game.screens.SimScreen;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Bomb extends Sprite{

    public World world; // this will be the world that mario will live inside
    public Body b2dbody; // box2d body


    private BodyDef bdef;
    private FixtureDef fdef;
    private CircleShape shape;

    private TextureRegion stationaryBomb;

    public Bomb(World world, SimScreen screen){
        super(screen.getAtlas().findRegion("circleRotation0"));

        bdef = new BodyDef(); // creating a new body definition for the bomb
        fdef = new FixtureDef(); // creating a new fixture def
        shape = new CircleShape(); // creating a circle for our fixture def for now

        this.world = world;
        defineBomb();

        stationaryBomb = new TextureRegion(getTexture(), 0,0,439, 424);

        setBounds(0,0, PhysicsImp.RADIUS * 2 /PhysicsImp.UNITSCALE, PhysicsImp.RADIUS * 2 /PhysicsImp.UNITSCALE);

        setRegion(stationaryBomb);
    }

    public void update(float dt){
        setPosition(b2dbody.getPosition().x - getWidth() / 2 , b2dbody.getPosition().y - getHeight() /2);
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

        fdef.isSensor = false;
//        fdef.restitution = 0.9f;
        //creating an edge shape at the bottom of the bomb to detect collions
//        EdgeShape base = new EdgeShape();
//        base.set(new Vector2(radius);

    }



//    public void render(){
//        Gdx.gl.glClearColor(1,1,1,1);
//        Gdx.gl.glClear();
//    }

}
