package com.mygdx.game.tools

import com.badlogic.gdx.physics.box2d.*
import com.mygdx.game.sprites.Water
import com.mygdx.game.sprites.Dam

class WorldContactListener : ContactListener {
    /*
     * a contact listener is what gets called when two bodies collide with each other in box 2d
     * begin contact gets called when two fixtures begin two colide here is where we will implement the will it bounce method
     * end contact is when those two fixtures disconect from each other, here is where they will either bounce or sink/destroy water tile surface and adjust gravity for water resistance
     * alternitively we could use presolve and post solve to try change the characteristics of our collision if the initial two failed
     * */
    override fun beginContact(contact: Contact) {
        val fixtureA = contact.fixtureA
        val fixtureB = contact.fixtureB

        //checking if collision has occured with the bomb
        if (fixtureA.userData === "bomb" || fixtureB.userData === "bomb") {

            //setting the bomb depending on which is found in its user data to either fixture A or be to
            val bomb = if (fixtureA.userData === "bomb") fixtureA else fixtureB
            val `object` = if (bomb === fixtureA) fixtureB else fixtureA

            //checking to see if the object the bomb collided into is not null and is specificially created from the Water class
            if (`object`.userData != null && Water::class.java.isAssignableFrom(`object`.userData.javaClass)) {
                (`object`.userData as Water).onWaterCollision()
            }
        }
        if (fixtureA.userData === "explosion" || fixtureB.userData === "explosion") {

            //setting the bomb depending on which is found in its user data to either fixture A or be to
            val bomb = if (fixtureA.userData === "explosion") fixtureA else fixtureB
            val `object` = if (bomb === fixtureA) fixtureB else fixtureA

            //checking to see if the object the bomb collided into is not null and is specificially created from the Water class
            if (`object`.userData != null && Dam::class.java.isAssignableFrom(`object`.userData.javaClass)) {
                (`object`.userData as Dam).onExplosionCollision()
            }
        }
        if (fixtureA.userData === "bomb" || fixtureB.userData === "bomb") {

            //setting the bomb depending on which is found in its user data to either fixture A or be to
            val bomb = if (fixtureA.userData === "bomb") fixtureA else fixtureB
            val `object` = if (bomb === fixtureA) fixtureB else fixtureA

            //checking to see if the object the bomb collided into is not null and is specificially created from the Water class
            if (`object`.userData != null && Dam::class.java.isAssignableFrom(`object`.userData.javaClass)) {
                (`object`.userData as Dam).bombDamaged()
            }
        }
    }

    override fun endContact(contact: Contact) {}
    override fun preSolve(contact: Contact, oldManifold: Manifold) {}
    override fun postSolve(contact: Contact, impulse: ContactImpulse) {}
}