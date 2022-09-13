package com.mygdx.game;

public class PhysicsImp {
    // setting up the screen variables
    public static final int S_WIDTH = 1350; // screen width
    public static final int S_HEIGHT = 759; // screeen height
    public static final int W_WIDTH = 10560 * 16; // world width
    public static final int W_HEIGHT = 50 * 16; // world height

    // setting up physical variables
    public static final float AIR_DENSITY = 1.225f;
    public static final int GRAVITY_AND_LIFT_SCALE = 30;

    //bomb properties
    public static final int BOMB_DENSITY = 2181;
    public static final int BOMB_LENGTH = 1;
    public static int RADIUS = 6; // setting the radius to a public static int as this will allow for it to be changed in the main menu
    public static int BOMB_RPM = 600;
    public static int PLANE_SPEED = 200;

    //sim properties
    public static float UNITSCALE = 20; // this will scale up all our vector quantities to increase simulation speeds to something more realistic
    public static int GRAVITY = 0;
    public static boolean planeFlyAway = false, bombSinks = false, damDestroyed = false;

    public static float WEIGHT_OF_BOMB(int radius, int density, int length){
        float weight = MASS_OF_BOMB(radius, density, length) * 10;
        return weight;
    }

    public static float MASS_OF_BOMB(int length, int density, int radius){
        float mass = (float) (Math.PI * length * density * (radius^2));
        return mass;
    }

    public static float LIFT_FORCE(int radius, int RPM, float velocityX ){
        //return vortex(bomb.getRotationalVelocity(), bomb.radius) * AIR_DENSITY * bomb.b2dbody.getLinearVelocity().x;
        return VORTEX(radius, RPM) * AIR_DENSITY * velocityX;
    }

    public static float VORTEX(int radius, float RPM){
        float vortexStrength = (float) (2 * AIR_DENSITY * (RPM  * 0.10472f) * (float)(radius ^ 2)) ;
        return (float) (vortexStrength);
    }

    public static float TOTAL_DOWNWARD_FORCE(float liftForce, float downForce){
        return (downForce - liftForce);
    }

    public static int TOTAL_ACCELERATION(float downForce, float mass){
        return (int)(Math.round(downForce/mass));
    }

    public static float criticalAngle(int density){
        return (float) (18/(Math.sqrt(density)));
    }

    public static boolean WillItBounce(float x_vel, float y_vel, int density){
        float angleOfIncidence = Math.abs((float) Math.atan (y_vel/x_vel) * 180/ (float)Math.PI) ;
        if (angleOfIncidence < criticalAngle(density)){
            return true;
        }
        else{
            return  false;
        }
    }
}
