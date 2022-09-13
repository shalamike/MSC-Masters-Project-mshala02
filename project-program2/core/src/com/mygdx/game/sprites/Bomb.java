package com.mygdx.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.PhysicsImp;

public class Bomb {
    //    public static final float radius = 3; // will make it private with getters and setters once i create the menu screen
//    public static final int bombDensity = 30; // will make it private variable with getters and setters once i create the menu screen
//    public static final float RPM = 500;

    public World world; // this will be the world that mario will live inside
    public Body b2dbody; // box2d body

    public Bomb(World world){
        this.world = world;
        defineBomb();
    }

    public void defineBomb(){
        BodyDef bdef = new BodyDef(); // creating a new body definition for the bomb
        bdef.position.set(120000 / PhysicsImp.UNITSCALE, 400 / PhysicsImp.UNITSCALE); // temporarily setting bomb position

        bdef.type = BodyDef.BodyType.DynamicBody; // setting the bombs body to dynamic body
        b2dbody = world.createBody(bdef);//now we have the box2d body defined, we can create the body in our game world
        //defining the fixtures
        FixtureDef fdef = new FixtureDef(); // creating a new fixture def
        CircleShape shape = new CircleShape(); // creating a circle for our fixture def for now
        shape.setRadius(PhysicsImp.RADIUS/ PhysicsImp.UNITSCALE); // setting the circles radius (subject to change)
        fdef.shape = shape; // setting our shapes radius to the fixure def
        b2dbody.createFixture(fdef).setUserData("bomb"); // setting the fixture def to our body.#

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
