package com.mygdx.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.PhysicsImp;
import com.mygdx.game.screens.SimScreen;

public class Dam extends InteractiveTiledObject{

private PhysicsImp properties;
    public static boolean explosionHitsDam = false, bombDamaged = false;
    public Dam(SimScreen screen, Rectangle bounds, PhysicsImp properties) {
        super(screen, bounds, properties);

        fixture.setUserData(this);
        setCategoryFilter(PhysicsImp.DAM_BIT);
    }

    public void onExplosionCollision() {
        Gdx.app.log("dam Destroyed", "");
        setCategoryFilter(PhysicsImp.DESTROYED_BIT);
//        getCell().setTile(null);
        clearCells(getCells());
        explosionHitsDam = true;
    }

    public void bombDamaged(){
        bombDamaged = false;
    }




}
