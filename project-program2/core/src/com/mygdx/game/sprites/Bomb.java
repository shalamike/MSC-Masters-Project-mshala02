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
    public static int radius = 3; // setting the radius to a public static int as this will allow for it to be changed in the main menu

    public World world; // this will be the world that mario will live inside
    public Body b2dbody; // box2d body

    private int bombDensity;
    //    private int radius;
    private int rotationalVelocity;
    private int gravity;
    private int bombspeed;


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
        shape.setRadius(radius/ PhysicsImp.UNITSCALE); // setting the circles radius (subject to change)
        fdef.shape = shape; // setting our shapes radius to the fixure def
        b2dbody.createFixture(fdef).setUserData("bomb"); // setting the fixture def to our body.#
//        fdef.restitution = 0.1f;
        fdef.isSensor = true;

        //creating an edge shape at the bottom of the bomb to detect collions
//        EdgeShape base = new EdgeShape();
//        base.set(new Vector2(radius);

    }

//    public void render(){
//        Gdx.gl.glClearColor(1,1,1,1);
//        Gdx.gl.glClear();
//    }



    public int getGravity() {
        return gravity;
    }

    public void setGravity(int gravity) {
        this.gravity = gravity;
    }

    public int getBombspeed() {
        return bombspeed;
    }

    public void setBombspeed(int bombspeed) {
        this.bombspeed = bombspeed;
    }

    public int getRotationalVelocity(){
        return this.rotationalVelocity;
    }

    public void setRotationalVelocity(int num){
        this.rotationalVelocity = num;
    }

    public int getBombDensity() {
        return bombDensity;
    }

    public void setBombDensity(int bombDensity) {
        this.bombDensity = bombDensity;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }


}
