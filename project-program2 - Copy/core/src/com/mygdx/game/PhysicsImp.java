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
    //changable properties
    private int radius; // setting the radius to a public static int as this will allow for it to be changed in the main menu
    private int bombRPM;
    private int planeSpeed ;
    private int criticalAngle;

    //sim properties
    private int startDistance = 12000;
    private float unitScale = 20; // this will scale up all our vector quantities to increase simulation speeds to something more realistic
    private boolean planeFLyAway = false, bombSinks = false, damDestroyed = false, bombHitsWater = false, bombDamaged = false;

    //for destroying layers to enable the bomb to sink or the dam to be destroyed
    public static final short DEFAULT_BIT = 1;
    public static final short BOMB_BIT = 2;
    public static short WATER_BIT = 4;
    public static final short DAM_BIT = 8;
    public static final short DESTROYED_BIT = 16;
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

    public static float CRITICAL_ANGLE(float acceleration){
        return (float) (18/(Math.sqrt(acceleration)));
    }

    public static float ANGLE_OF_INCIDENCE(float x_vel, float y_vel){
        float angleOfIncidence = Math.abs((float) Math.atan (y_vel/x_vel) * 180/ (float)Math.PI);
        return angleOfIncidence;
    }

    public static boolean WillItBounce(float angleOfIncidence, float citicalAngle){
        if (angleOfIncidence < citicalAngle){
            return true;
        }
        else{
            return  false;
        }
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getBombRPM() {
        return bombRPM;
    }

    public void setBombRPM(int bombRPM) {
        this.bombRPM = bombRPM;
    }

    public int getPlaneSpeed() {
        return planeSpeed;
    }

    public void setPlaneSpeed(int planeSpeed) {
        this.planeSpeed = planeSpeed;
    }

    public int getCriticalAngle() {
        return criticalAngle;
    }

    public void setCriticalAngle(int criticalAngle) {
        this.criticalAngle = criticalAngle;
    }

    public int getStartDistance() {
        return startDistance;
    }

    public void setStartDistance(int startDistance) {
        this.startDistance = startDistance;
    }

    public float getUnitScale() {
        return unitScale;
    }

    public void setUnitScale(float unitScale) {
        this.unitScale = unitScale;
    }

    public boolean isPlaneFLyAway() {
        return planeFLyAway;
    }

    public void setPlaneFLyAway(boolean planeFLyAway) {
        this.planeFLyAway = planeFLyAway;
    }

    public boolean isBombSinks() {
        return bombSinks;
    }

    public void setBombSinks(boolean bombSinks) {
        this.bombSinks = bombSinks;
    }

    public boolean isDamDestroyed() {
        return damDestroyed;
    }

    public void setDamDestroyed(boolean damDestroyed) {
        this.damDestroyed = damDestroyed;
    }

    public boolean isBombHitsWater() {
        return bombHitsWater;
    }

    public void setBombHitsWater(boolean bombHitsWater) {
        this.bombHitsWater = bombHitsWater;
    }

    public boolean isBombDamaged() {
        return bombDamaged;
    }

    public void setBombDamaged(boolean bombDamaged) {
        this.bombDamaged = bombDamaged;
    }
}
