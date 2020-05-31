/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.Tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Horror;
import com.mygdx.game.Sprites.Enemy.Ghost;
import com.mygdx.game.screens.Level1Screen;

/**
 *
 * @author Adolfo
 */
public class B2WorldCreator {
    private Array<Ghost> ghosts;
   
    public B2WorldCreator (Level1Screen screen) {
        World world = screen.getWorld();
        TiledMap map = screen.getMap();
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;
        
        //create the fixtures of the gorund
        for(MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            
            
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() +  rect.getWidth()/2)/Horror.PPM, 
                    (rect.getY() + rect.getHeight()/2)/Horror.PPM);
            
            body = world.createBody(bdef);
            
            shape.setAsBox(rect.getWidth()/2 / Horror.PPM, 
                    rect.getHeight()/2 / Horror.PPM);
            
            fdef.shape = shape;
            body.createFixture(fdef);
        }
        
        //create the fixtures of the wall
        for(MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            
            
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() +  rect.getWidth()/2)/Horror.PPM, 
                    (rect.getY() + rect.getHeight()/2)/Horror.PPM);
            
            body = world.createBody(bdef);
            
            shape.setAsBox(rect.getWidth()/2 / Horror.PPM, 
                    rect.getHeight()/2 / Horror.PPM);
            
            fdef.shape = shape;
            fdef.filter.categoryBits = Horror.OBJECT_BIT;
            body.createFixture(fdef);
        }
        
        //create the fixtures of the edges for enemy collision
        for(MapObject object : map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            
            
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() +  rect.getWidth()/2)/Horror.PPM, 
                    (rect.getY() + rect.getHeight()/2)/Horror.PPM);
            
            body = world.createBody(bdef);
            
            shape.setAsBox(rect.getWidth()/2 / Horror.PPM, 
                    rect.getHeight()/2 / Horror.PPM);
            
            fdef.shape = shape;
            fdef.filter.categoryBits = Horror.EDGE_BIT;
            body.createFixture(fdef);
        }
        
        //create the fixtures of the limit in sky
        for(MapObject object : map.getLayers().get(7).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            
            
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() +  rect.getWidth()/2)/Horror.PPM, 
                    (rect.getY() + rect.getHeight()/2)/Horror.PPM);
            
            body = world.createBody(bdef);
            
            shape.setAsBox(rect.getWidth()/2 / Horror.PPM, 
                    rect.getHeight()/2 / Horror.PPM);
            
            fdef.shape = shape;
            fdef.filter.categoryBits = Horror.SKY_BIT;
            body.createFixture(fdef);
        }
        
        //create the fixtures for the holes
        for(MapObject object : map.getLayers().get(8).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            
            
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() +  rect.getWidth()/2)/Horror.PPM, 
                    (rect.getY() + rect.getHeight()/2)/Horror.PPM);
            
            body = world.createBody(bdef);
            
            shape.setAsBox(rect.getWidth()/2 / Horror.PPM, 
                    rect.getHeight()/2 / Horror.PPM);
            
            fdef.shape = shape;
            fdef.filter.categoryBits = Horror.HOLE_BIT;
            body.createFixture(fdef);
        }
        
        
        //create all ghosts
        ghosts = new Array<Ghost>();
        for(MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            ghosts.add(new Ghost(screen, rect.getX() / Horror.PPM, rect.getY() / Horror.PPM));
        }
        
    }
    
    public Array<Ghost> getGhosts() {
        return ghosts;
    }
    
}
