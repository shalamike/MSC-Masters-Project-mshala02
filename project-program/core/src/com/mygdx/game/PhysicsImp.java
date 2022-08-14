package com.mygdx.game;

import com.mygdx.game.sprites.Bomb;
import com.sun.security.sasl.ClientFactoryImpl;

public class PhysicsImp {
    private Bomb bomb;
    // setting up the screen variables
    public static final int S_WIDTH = 1350; // screen width
    public static final int S_HEIGHT = 759; // screeen height
    public static final int W_WIDTH = 10560 * 16; // world width
    public static final int W_HEIGHT = 50 * 16; // world height
    public static final int UNITSCALE = 100; // this will scale up all our vector quantities to increase simulation speeds to something more realistic
    // setting up physical variables
    public static final float AIR_DENSITY = 1.225f;

    //setting the adjustable variables here

    public float liftForce(int radius, int RPM, int velocityX){
        //return vortex(bomb.getRotationalVelocity(), bomb.radius) * AIR_DENSITY * bomb.b2dbody.getLinearVelocity().x;
        return vortex(radius, RPM) * AIR_DENSITY * velocityX;
    }

    public float vortex(int radius, int RPM){
        float rotationalVelocity = (float) (Math.PI * radius*2 * RPM);
        return (float) (rotationalVelocity * radius * Math.PI * 2);
    }

    public float criticalAngle(int density){
        return (float) (18/(Math.sqrt(density)));
    }

    public boolean WillItBounce(int x_vel, int y_vel, int density){
        float angleOfIncidence = (float) Math.atan (x_vel/ y_vel);
        if (angleOfIncidence < criticalAngle(density)){
            return true;
        }
        else{
            return  false;
        }
    }
}
