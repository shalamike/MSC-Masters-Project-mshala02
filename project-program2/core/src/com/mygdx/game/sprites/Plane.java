package com.mygdx.game.sprites;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.PhysicsImp;

import java.awt.Rectangle;

public class Plane {

    public World world; // this will be the world that mario will live inside
    public Body b2dbody; // box2d body

    public Plane(World world){
        this.world = world;
        definePlane();
    }

    public void definePlane(){
        BodyDef bdef = new BodyDef(); // creating a new body definition for the bomb
        bdef.position.set(120000 / PhysicsImp.UNITSCALE, ((400 + PhysicsImp.radius) / PhysicsImp.UNITSCALE)); // temporarily setting bomb position

        bdef.type = BodyDef.BodyType.DynamicBody; // setting the bombs body to dynamic body
        b2dbody = world.createBody(bdef);//now we have the box2d body defined, we can create the body in our game world
        //defining the fixtures
        FixtureDef fdef = new FixtureDef(); // creating a new fixture def
        CircleShape shape = new CircleShape(); // creating a circle for our fixture def for now
        shape.setRadius(4/ PhysicsImp.UNITSCALE); // setting the circles radius (subject to change)
        fdef.shape = shape; // setting our shapes radius to the fixure def
        b2dbody.createFixture(fdef).setUserData("bomb"); // setting the fixture def to our body.#
//        fdef.restitution = 0.1f;
        fdef.isSensor = true;

        //creating an edge shape at the bottom of the bomb to detect collions
//        EdgeShape base = new EdgeShape();
//        base.set(new Vector2(radius);

    }
}
