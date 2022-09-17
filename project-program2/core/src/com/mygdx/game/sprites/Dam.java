package com.mygdx.game.sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.PhysicsImp;
import com.mygdx.game.screens.SimScreen;

public class Dam extends InteractiveTiledObject{
    public Dam(SimScreen screen, Rectangle bounds) {
        super(screen, bounds);
        fixture.setUserData(this);
        setCategoryFilter(PhysicsImp.DAM_BIT);

    }
}
