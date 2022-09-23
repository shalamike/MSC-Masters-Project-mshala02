package com.mygdx.game

import com.mygdx.game.PhysicsImp

object PhysicsImp {
    // setting up the screen variables
    const val S_WIDTH = 1350 // screen width
    const val S_HEIGHT = 759 // screeen height
    const val W_WIDTH = 10560 * 16 // world width
    const val W_HEIGHT = 50 * 16 // world height

    // setting up physical variables
    const val AIR_DENSITY = 1.225f

    //bomb properties
    const val BOMB_DENSITY = 2181
    const val BOMB_LENGTH = 1
    @JvmField
    var RADIUS =
        12 // setting the radius to a public static int as this will allow for it to be changed in the main menu
    @JvmField
    var BOMB_RPM = 600
    @JvmField
    var PLANE_SPEED = 80
    var CRITICAL_ANGLE = 16

    //sim properties
    @JvmField
    var START_DISTANCE = 12000
    @JvmField
    var UNITSCALE =
        20f // this will scale up all our vector quantities to increase simulation speeds to something more realistic
    @JvmField
    var PLANE_FLY_AWAY = false
    @JvmField
    var BOMB_SINKS = false
    @JvmField
    var DAM_DESTROYED = false
    @JvmField
    var BOMB_HITS_WATER = false
    @JvmField
    var BOMB_DAMAGED = false

    //for destroying layers to enable the bomb to sink or the dam to be destroyed
    const val DEFAULT_BIT: Short = 1
    const val BOMB_BIT: Short = 2
    @JvmField
    var WATER_BIT: Short = 4
    const val DAM_BIT: Short = 8
    const val DESTROYED_BIT: Short = 16
    const val EXPLOSION_BIT: Short = 32
    @JvmStatic
    fun WEIGHT_OF_BOMB(
        radius: Int,
        density: Int,
        length: Int
    ): Float {
        return MASS_OF_BOMB(radius, density, length) * 10
    }

    @JvmStatic
    fun MASS_OF_BOMB(radius: Int, density: Int, length: Int): Float {
        return (Math.PI * length * density * (radius * radius)).toFloat()
    }

    @JvmStatic
    fun LIFT_FORCE(radius: Int, RPM: Int, velocityX: Float): Float {
        //return vortex(bomb.getRotationalVelocity(), bomb.radius) * AIR_DENSITY * bomb.b2dbody.getLinearVelocity().x;
        return VORTEX(radius, RPM.toFloat()) * AIR_DENSITY * velocityX
    }

    fun VORTEX(radius: Int, RPM: Float): Float {
        return (2 * AIR_DENSITY * (RPM * 0.10472f) * (radius * radius).toFloat())
    }

    @JvmStatic
    fun TOTAL_DOWNWARD_FORCE(liftForce: Float, downForce: Float): Float {
        return downForce - liftForce
    }

    @JvmStatic
    fun TOTAL_ACCELERATION(totalDownForce: Float, mass: Float): Float {
        return totalDownForce / mass
    }


    @JvmStatic
    fun ANGLE_OF_INCIDENCE(x_vel: Float, y_vel: Float): Float {
        return Math.abs(
            Math.atan((y_vel / x_vel).toDouble()).toFloat() * 180 / Math.PI.toFloat()
        )
    }

    @JvmStatic
    fun WillItBounce(angleOfIncidence: Float): Boolean {
        return if (angleOfIncidence < CRITICAL_ANGLE) {
            true
        } else {
            false
        }
    }
}