package com.mygdx.game.sprites;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

public class Bomb {
    public static final float radius = 12.5f; // will make it private with getters and setters once i create the menu screen
    public static final float bombDensity = 3.6f; // will make it private variable with getters and setters once i create the menu screen
    public static final float RPM = 500;

    public World world; // this will be the world that mario will live inside
    public Body b2dbody; // box2d body

    private int rotationalVelocity;
    private int gravity;

    public int getBombspeed() {
        return bombspeed;
    }

    public void setBombspeed(int bombspeed) {
        this.bombspeed = bombspeed;
    }

    private int bombspeed;



    public int getRotationalVelocity(){
        return this.rotationalVelocity;
    }

    public void setRotationalVelocity(int num){
        this.rotationalVelocity = num;
    }


    public Bomb(World world){
        this.world = world;
        defineBomb();
    }

    public void defineBomb(){
        BodyDef bdef = new BodyDef(); // creating a new body definition for the bomb
        bdef.position.set(800, 550); // temporarily setting bomb position

        bdef.type = BodyDef.BodyType.DynamicBody; // setting the bombs body to dynamic body
        b2dbody = world.createBody(bdef);//now we have the box2d body defined, we can create the body in our game world
        //defining the fixtures
        FixtureDef fdef = new FixtureDef(); // creating a new fixture def
        CircleShape shape = new CircleShape(); // creating a circle for our fixture def for now
        shape.setRadius(radius); // setting the circles radius (subject to change)
        fdef.shape = shape; // setting our shapes radius to the fixure def
        b2dbody.createFixture(fdef); // setting the fixture def to our body.
    }

    public int getGravity() {
        return gravity;
    }

    public void setGravity(int gravity) {
        this.gravity = gravity;
    }
}
