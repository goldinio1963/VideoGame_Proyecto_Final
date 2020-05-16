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
import com.mygdx.game.Sprites.Ghost;
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
        
        for(MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)){
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
        
        //create all ghosts
        ghosts = new Array<Ghost>();
        for(MapObject object : map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            ghosts.add(new Ghost(screen, rect.getX() / Horror.PPM, rect.getY() / Horror.PPM));
        }
        
    }
    
    public Array<Ghost> getGhosts() {
        return ghosts;
    }
    
}