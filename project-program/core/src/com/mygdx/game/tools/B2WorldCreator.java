package com.mygdx.game.tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.PhysicsImp;

public class B2WorldCreator {
    public B2WorldCreator(World world, TiledMap map){
        BodyDef bodyDef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fixtureDef = new FixtureDef();
        Body body;

        // getting the water layer
        for(MapObject object :map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            bodyDef.type = BodyDef.BodyType.StaticBody;
            bodyDef.position.set((rect.getX() + rect.getWidth()/2) / PhysicsImp.UNITSCALE, (rect.getY() + rect.getHeight()/2) / PhysicsImp.UNITSCALE);
            body = world.createBody(bodyDef);
            // creating the fixtures
            shape.setAsBox((rect.getWidth()/2) / PhysicsImp.UNITSCALE, (rect.getHeight()/2)/ PhysicsImp.UNITSCALE);
            fixtureDef.shape = shape;
            body.createFixture(fixtureDef);
        }

        // getting the dam layer
        for(MapObject object :map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            bodyDef.type = BodyDef.BodyType.StaticBody;
            bodyDef.position.set((rect.getX() + rect.getWidth()/2) / PhysicsImp.UNITSCALE, (rect.getY() + rect.getHeight()/2) / PhysicsImp.UNITSCALE);
            body = world.createBody(bodyDef);
            // creating the fixtures
            shape.setAsBox((rect.getWidth()/2) / PhysicsImp.UNITSCALE, (rect.getHeight()/2)/ PhysicsImp.UNITSCALE);
            fixtureDef.shape = shape;
            body.createFixture(fixtureDef);
        }
    }
}
