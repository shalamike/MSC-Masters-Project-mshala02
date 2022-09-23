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
    private PhysicsImp properties;
    public Water(SimScreen screen, Rectangle bounds, PhysicsImp properties) {
        super(screen, bounds, properties);
        this.properties= properties;
        fixture.setUserData(this);
        fixture.setFriction(2f);
        fixture.setRestitution(0.8f);
        setCategoryFilter(PhysicsImp.WATER_BIT);

    }

    public void onWaterCollision(){
//        fixture.setRestitution(0);
        Gdx.app.log("hit water", "");
        properties.setBombHitsWater(true);
        setCategoryFilter(PhysicsImp.WATER_BIT);
    }


}
