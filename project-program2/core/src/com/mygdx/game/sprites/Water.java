package com.mygdx.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.PhysicsImp;
import com.mygdx.game.screens.SimScreen;

public class Water extends InteractiveTiledObject{
    public Water(SimScreen screen, Rectangle bounds) {
        super(screen, bounds);
        fixture.setUserData(this);
        fixture.setFriction(2f);
        fixture.setRestitution(0.8f);
        setCategoryFilter(PhysicsImp.WATER_BIT);

    }

    public void onWaterCollision(){
//        fixture.setRestitution(0);
        Gdx.app.log("hit water", "");
        PhysicsImp.BOMB_HITS_WATER = true;
        setCategoryFilter(PhysicsImp.WATER_BIT);
    }


}
