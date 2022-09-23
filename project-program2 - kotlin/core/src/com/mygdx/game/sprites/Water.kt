package com.mygdx.game.sprites

import com.mygdx.game.screens.SimScreen
import com.mygdx.game.sprites.InteractiveTiledObject
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.math.Rectangle
import com.mygdx.game.PhysicsImp

class Water(screen: SimScreen?, bounds: Rectangle?) : InteractiveTiledObject(screen, bounds) {
    fun onWaterCollision() {
//        fixture.setRestitution(0);
        Gdx.app.log("hit water", "")
        PhysicsImp.BOMB_HITS_WATER = true
        setCategoryFilter(PhysicsImp.WATER_BIT)
    }

    init {
        fixture.userData = this
        fixture.friction = 2f
        fixture.restitution = 0.8f
        setCategoryFilter(PhysicsImp.WATER_BIT)
    }
}