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
public class Ammo extends Sprite{
    
    private World world;
    private DefaultScreen screen;
    private TextureRegion ammo;
    private Body b2body;
    private float x;
    private float y;
    private boolean setToDestroy;
    private boolean destroy;
    private boolean addBullets;
    private Hud hud;

    public Ammo(DefaultScreen screen, float x, float y){
        super(screen.getalAtlas().findRegion("robot"));
        this.world = screen.getWorld();
        this.x = x;
        this.y = y;
        setToDestroy = false;
        destroy = false;
        addBullets = false;
        defineAmmo();
        
        ammo = new TextureRegion(getTexture(), 352, 227, 32, 32);
        setBounds(0, 0, 10 / Horror.PPM, 10 / Horror.PPM);
        setRegion(ammo);
        //aqui va la imagen
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
    
    public void defineAmmo(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(x,y);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);
        
        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(6 / Horror.PPM);
        fdef.filter.categoryBits = Horror.AMMO_BIT;
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
        addBullets = true;
    }

    public boolean isAddBullets() {
        return addBullets;
    }

    public void setAddBullets(boolean addBullets) {
        this.addBullets = addBullets;
    }
    
    
}
