package com.mygdx.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.PhysicsImp;
import com.mygdx.game.screens.SimScreen;

public abstract class InteractiveTiledObject extends Sprite {
    private int tileSize;
    private PhysicsImp properties;

    protected World world;
    protected TiledMap map;
    protected TiledMapTile tile;
    protected Rectangle bounds;
    protected Body body;

    BodyDef bodyDef;
    FixtureDef fixtureDef;
    PolygonShape shape;

    protected Fixture fixture;

    public InteractiveTiledObject(SimScreen screen, Rectangle bounds, PhysicsImp properties){
        this.properties = properties;
        tileSize = 16;
        this.world = screen.getWorld();
        this.map = screen.getMap();
        this.bounds = bounds;

        BodyDef bodyDef = new BodyDef();
        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set((bounds.getX() + bounds.getWidth()/2) / properties.getUnitScale(), (bounds.getY() + bounds.getHeight()/2) /properties.getUnitScale());

        body = world.createBody(bodyDef);
        // creating the fixtures
        shape.setAsBox((bounds.getWidth()/2) / properties.getUnitScale(), (bounds.getHeight()/2)/ properties.getUnitScale());
        fixtureDef.shape = shape;
        fixture = body.createFixture(fixtureDef);
    }

    public void setCategoryFilter(short filterBit){
        Filter filter = new Filter();
        filter.categoryBits = filterBit;
        fixture.setFilterData(filter);
    }

    public TiledMapTileLayer.Cell getCell(){
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(1);
        System.out.println("width of dam is : " +  bounds.getWidth());
        System.out.println("height of dam is :" + bounds.getHeight());
        System.out.println("center of dam is :" + bounds.getCenter(new Vector2(bounds.getWidth(), bounds.height)));
//        System.out.println(body.getPosition().x + " " + body.getPosition().y);
        return layer.getCell((int)(body.getPosition().x * properties.getUnitScale()/tileSize), (int)(body.getPosition().y * properties.getUnitScale()/tileSize));
    }

    public Array<TiledMapTileLayer.Cell> getCells(){
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(1);
        Array<TiledMapTileLayer.Cell> cells = new Array<TiledMapTileLayer.Cell>();
        for ( int j = -3 ; j<= -1; j++){
            for (int i = -6 ; i <= 6; i++){
                cells.add(layer.getCell((int)(body.getPosition().x * properties.getUnitScale()/tileSize + i), (int)(body.getPosition().y * properties.getUnitScale()/tileSize + j)));
            }
        }
        return cells;
    }

    public void clearCells(Array<TiledMapTileLayer.Cell> cells){
        for (int i = 0; i < cells.size; i++){
//            TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(1);
            TiledMapTileLayer.Cell cell= cells.get(i);
            cell.setTile(null);
        }
    }

}
