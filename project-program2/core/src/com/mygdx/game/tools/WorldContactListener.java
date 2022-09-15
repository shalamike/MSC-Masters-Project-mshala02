package com.mygdx.game.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.mygdx.game.sprites.Water;

public class WorldContactListener implements ContactListener {
    /*
     * a contact listener is what gets called when two bodies collide with each other in box 2d
     * begin contact gets called when two fixtures begin two colide here is where we will implement the will it bounce method
     * end contact is when those two fixtures disconect from each other, here is where they will either bounce or sink/destroy water tile surface and adjust gravity for water resistance
     * alternitively we could use presolve and post solve to try change the characteristics of our collision if the initial two failed
     * */
    @Override
    public void beginContact(Contact contact) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        //checking if collision has occured with the bomb
        if ((fixtureA.getUserData() == "bomb" ) || (fixtureB.getUserData() == "bomb" )){

            //setting the bomb depending on which is found in its user data to either fixture A or be to
            Fixture bomb = fixtureA.getUserData() == "bomb" ? fixtureA : fixtureB;
            Fixture object = bomb == fixtureA ? fixtureB : fixtureA;

            //checking to see if the object the bomb collided into is not null and is specificially created from the Water class
            if (object.getUserData() != null && Water.class.isAssignableFrom(object.getUserData().getClass())){
                ((Water) object.getUserData()).onWaterCollision();
            }
        }
    }

    @Override
    public void endContact(Contact contact) {
        Gdx.app.log("end contact","");
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
