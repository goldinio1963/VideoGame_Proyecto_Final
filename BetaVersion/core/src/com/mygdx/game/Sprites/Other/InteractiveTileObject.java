/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.Sprites.Other;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Horror;
import com.mygdx.game.Sprites.Robot;
import com.mygdx.game.screens.DefaultScreen;

/**
 *
 * @author Adolfo
 */
public abstract class InteractiveTileObject {

    protected World world;
    protected TiledMap map;
    protected Rectangle bounds;
    protected Body body;
    protected DefaultScreen screen;
    protected MapObject object;

    protected Fixture fixture;

    public InteractiveTileObject(DefaultScreen screen, MapObject object) {
        this.object = object;
        this.screen = screen;
        this.world = screen.getWorld();
        this.map = screen.getMap();
        this.bounds = ((RectangleMapObject) object).getRectangle();

        BodyDef bdef = new BodyDef();
        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        bdef.type = BodyDef.BodyType.StaticBody;

        bdef.position.set(
                (bounds.getX() + bounds.getWidth() / 2) / 
                        Horror.PPM, (bounds.getY() + bounds.getHeight() / 2) 
                                / Horror.PPM);

        body = world.createBody(bdef);

        shape.setAsBox(bounds.getWidth() / 2 / Horror.PPM, bounds.getHeight() 
                / 2 / Horror.PPM);
        fdef.shape = shape;
        fixture = body.createFixture(fdef);

    }
    
    public abstract void hit(Robot player);
    
    public void setCategoryFilter(short filterBit){
        Filter filter = new Filter();
        filter.categoryBits = filterBit;
        fixture.setFilterData(filter);
    }
    
    public TiledMapTileLayer.Cell getCell(){
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(1);
        return layer.getCell((int)(body.getPosition().x * Horror.PPM / 16),
                (int)(body.getPosition().y * Horror.PPM / 16));
    }

}
