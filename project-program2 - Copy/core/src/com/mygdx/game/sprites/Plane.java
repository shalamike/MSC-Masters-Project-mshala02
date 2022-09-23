package com.mygdx.game.sprites;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.PhysicsImp;
import com.mygdx.game.screens.SimScreen;

import java.awt.Rectangle;

public class Plane  extends Sprite {

    public World world; // this will be the world that mario will live inside
    public Body b2dbody; // box2d body

    private TextureRegion planeUpright;

    private PhysicsImp properties;

    public Plane(SimScreen screen, PhysicsImp properties){
        super(screen.getAtlas().findRegion("plane_upright"));
        this.properties = properties;
        this.world = screen.getWorld();

        definePlane();

        planeUpright = new TextureRegion(getTexture(), 0, 936, 744, 336);
        setBounds(0,0, 20 * 2/ properties.getUnitScale(), 20 * 2/ properties.getUnitScale());
        setRegion(planeUpright);
    }

    public void definePlane(){
        BodyDef bdef = new BodyDef(); // creating a new body definition for the bomb
        bdef.position.set(properties.getStartDistance()/ properties.getUnitScale(), ((600 + properties.getRadius()) / properties.getUnitScale())); // temporarily setting bomb position

        bdef.type = BodyDef.BodyType.DynamicBody; // setting the bombs body to dynamic body
        b2dbody = world.createBody(bdef);//now we have the box2d body defined, we can create the body in our game world
        //defining the fixtures
        FixtureDef fdef = new FixtureDef(); // creating a new fixture def
        CircleShape shape = new CircleShape(); // creating a circle for our fixture def for now
        shape.setRadius(20/ properties.getUnitScale()); // setting the circles radius (subject to change)
        fdef.shape = shape; // setting our shapes radius to the fixure def
        b2dbody.createFixture(fdef); // setting the fixture def to our body.#
//        fdef.restitution = 0.1f;

    }

    public void update(float dt) {
        setPosition(b2dbody.getPosition().x - getWidth() / 2 , b2dbody.getPosition().y - getHeight() /2);
    }
}
