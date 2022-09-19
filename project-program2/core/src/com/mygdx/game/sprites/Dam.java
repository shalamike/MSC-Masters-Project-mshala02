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

    private TextureRegion damDestroyedTextureRegion;
    private Texture damDestroyedTexture;

    public Dam(SimScreen screen, Rectangle bounds) {
        super(screen, bounds);
        fixture.setUserData(this);
        setCategoryFilter(PhysicsImp.DAM_BIT);
        damDestroyedTexture = new Texture("damDestroyed.png");
        damDestroyedTextureRegion = new TextureRegion();
    }


    public void onExplosionCollision() {
        Gdx.app.log("dam Destroyed", "");
        setCategoryFilter(PhysicsImp.DESTROYED_BIT);
        getCell().setTile(null);
        setRegion(damDestroyedTexture);

    }
}
