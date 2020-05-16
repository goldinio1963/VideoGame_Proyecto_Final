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
import com.mygdx.game.Horror;

/**
 *
 * @author Adolfo
 */
public class B2WorldCreator {
    public B2WorldCreator (World world, TiledMap map) {
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
    }
    
}
