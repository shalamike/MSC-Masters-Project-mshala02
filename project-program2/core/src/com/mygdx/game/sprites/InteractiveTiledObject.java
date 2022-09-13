package com.mygdx.game.sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.PhysicsImp;

public abstract class InteractiveTiledObject {
    protected World world;
    protected TiledMap map;
    protected TiledMapTile tile;
    protected Rectangle bounds;
    protected Body body;

    protected Fixture fixture;

    public InteractiveTiledObject(World world, TiledMap map, Rectangle bounds){
        this.world = world;
        this.map = map;
        this.bounds = bounds;

        BodyDef bodyDef = new BodyDef();
        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set((bounds.getX() + bounds.getWidth()/2) / PhysicsImp.UNITSCALE, (bounds.getY() + bounds.getHeight()/2) / PhysicsImp.UNITSCALE);

        body = world.createBody(bodyDef);
        // creating the fixtures
        shape.setAsBox((bounds.getWidth()/2) / PhysicsImp.UNITSCALE, (bounds.getHeight()/2)/ PhysicsImp.UNITSCALE);
        fixtureDef.shape = shape;
        fixture = body.createFixture(fixtureDef);

    }

}
