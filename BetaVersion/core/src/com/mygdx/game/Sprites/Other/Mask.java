/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.Sprites.Other;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Horror;
import com.mygdx.game.Scenes.Hud;
import com.mygdx.game.Sprites.Robot;
import com.mygdx.game.screens.DefaultScreen;

/**
 *
 * @author Adolfo
 */
public class Mask extends Sprite{
    
    private World world;
    private DefaultScreen screen;
    private TextureRegion mask;
    private Body b2body;
    private PolygonShape shape = new PolygonShape();
    private FixtureDef fdef = new FixtureDef();
    private boolean setToDestroy;
    private boolean destroy;
    private boolean addLives;
    private Hud hud;

    public Mask(DefaultScreen screen, float x, float y){
        super(screen.getalAtlas().findRegion("bullet"));
        this.world = screen.getWorld();
        this.screen = screen;
        setPosition(x,y);
        setToDestroy = false;
        destroy = false;
        addLives=false;
        defineMask();
        
        //aqui va la imagen
        mask = new TextureRegion(getTexture(), 388, 227, 32, 32);
        setBounds(0, 0, 10 / Horror.PPM, 10 / Horror.PPM);
        setRegion(mask);
    }
    
    public void update(float delta){
        if(setToDestroy && !destroy){
            world.destroyBody(b2body);
            destroy = true;
            hud.addScore(10);
        } else {
            if(!destroy) {
                setPosition(b2body.getPosition().x - getWidth() / 2, 
                        b2body.getPosition().y - getHeight() / 2); 
            }
        }
    }
    
    public void defineMask(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(getX(),getY());
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);
        
        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(6 / Horror.PPM);
        fdef.filter.categoryBits = Horror.MASK_BIT;
        fdef.filter.maskBits = Horror.GROUND_BIT | 
                Horror.ROBOT_BIT |
                Horror.OBJECT_BIT;
        
        fdef.shape = shape;
        b2body.createFixture(fdef).setUserData(this);
    }
    
    public void draw(Batch batch){
        if(!destroy){
            super.draw(batch);
        }
    }

    public void hit() {
        setToDestroy=true;
        addLives=true;
    }

    public boolean isAddLives() {
        return addLives;
    }

    public void setAddLives(boolean addLives) {
        this.addLives = addLives;
    }
    
    
}

