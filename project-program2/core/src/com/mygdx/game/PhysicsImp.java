package com.mygdx.game;

public class PhysicsImp {
    // setting up the screen variables
    public static final int S_WIDTH = 1350; // screen width
    public static final int S_HEIGHT = 759; // screeen height
    public static final int W_WIDTH = 10560 * 16; // world width
    public static final int W_HEIGHT = 50 * 16; // world height

    // setting up physical variables
    public static final float AIR_DENSITY = 1.225f;

    //bomb properties
    public static final int BOMB_DENSITY = 2181;
    public static final int BOMB_LENGTH = 1;
    public static int RADIUS = 12; // setting the radius to a public static int as this will allow for it to be changed in the main menu
    public static int BOMB_RPM = 600;
    public static int PLANE_SPEED = 00;
    public static int CRITICAL_ANGLE = 16;

    //sim properties
    public static int START_DISTANCE = 168660;
    public static float UNITSCALE = 20; // this will scale up all our vector quantities to increase simulation speeds to something more realistic
    public static boolean PLANE_FLY_AWAY = false, BOMB_SINKS = false, DAM_DESTROYED = false, BOMB_HITS_WATER = false, BOMB_EXPLODES = false;

    //for destroying layers to enable the bomb to sink or the dam to be destroyed
    public static final short DEFAULT_BIT = 1;
    public static final short BOMB_BIT = 2;
    public static short WATER_BIT = 4;
    public static final short DAM_BIT = 8;
    public static final short DESTROYED_BIT = 16;//may not need this
    public static final short EXPLOSION_BIT = 32;

    public static float WEIGHT_OF_BOMB(int radius, int density, int length){
        float weight = MASS_OF_BOMB(radius, density, length) * 10;
        return weight;
    }

    public static float MASS_OF_BOMB(int radius, int density, int length){
        float mass = (float) (Math.PI * length * density * (radius*radius));
        return mass;
    }

    public static float LIFT_FORCE(int radius, int RPM, float velocityX ){
        //return vortex(bomb.getRotationalVelocity(), bomb.radius) * AIR_DENSITY * bomb.b2dbody.getLinearVelocity().x;
        return VORTEX(radius, RPM) * AIR_DENSITY * velocityX;
    }

    public static float VORTEX(int radius, float RPM){
        float vortexStrength = (float) (2 * AIR_DENSITY * (RPM  * 0.10472f) * (float)(radius * radius)) ;
        return (float) (vortexStrength);
    }

    public static float TOTAL_DOWNWARD_FORCE(float liftForce, float downForce){
        return (downForce - liftForce);
    }

    public static float TOTAL_ACCELERATION(float totalDownForce, float mass){
        return ((totalDownForce /mass));
    }

//    public static float CRITICAL_ANGLE(int density){
//        return (float) (18/(Math.sqrt(density)));
//    }

    public static float ANGLE_OF_INCIDENCE(float x_vel, float y_vel){
        float angleOfIncidence = Math.abs((float) Math.atan (y_vel/x_vel) * 180/ (float)Math.PI);
        return angleOfIncidence;
    }

    public static boolean WillItBounce(float angleOfIncidence){
        if (angleOfIncidence < CRITICAL_ANGLE){
            return true;
        }
        else{
            return  false;
        }
    }

}
