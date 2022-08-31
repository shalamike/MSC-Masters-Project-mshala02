package com.mygdx.game.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

public class WorldContactListener implements ContactListener {
    /*
     * a contact listener is what gets called when two bodies collide with each other in box 2d
     * begin contact gets called when two fixtures begin two colide here is where we will implement the will it bounce method
     * end contact is when those two fixtures disconect from each other, here is where they will either bounce or sink/destroy water tile surface and adjust gravity for water resistance
     * alternitively we could use presolve and post solve to try change the characteristics of our collision if the initial two failed
     * */
    @Override
    public void beginContact(Contact contact) {
        Gdx.app.log("begin contact","");

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
