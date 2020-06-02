/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.Sprites.Enemy;

import com.mygdx.game.Sprites.Enemy.EnemyStandard;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Horror;
import com.mygdx.game.screens.DefaultScreen;
import com.mygdx.game.screens.Level1Screen;

/**
 *
 * @author Adolfo
 */
public class Ghost extends EnemyStandard{
    
    private float stateTime;
    private Animation walk;
    private Array<TextureRegion> frames;
    private boolean setToDestroy;
    private boolean destroy;

    public Ghost(DefaultScreen screen, float x, float y) {
        super(screen, x, y);
        setToDestroy = false;
        destroy = false;
        
        frames = new Array<TextureRegion>();
        for(int i=4; i <11 ; i++){
            frames.add(new TextureRegion(screen.getalAtlas().findRegion("robot")
            , i*32,64,32,64));
        }
        walk = new Animation(0.4f, frames);
        stateTime=0;
        
        setBounds(getX(), getY(), 16/Horror.PPM, 32/Horror.PPM);
    }
    
    public void update(float delta){
        stateTime += delta;
        
        if(setToDestroy && !destroy){
            world.destroyBody(b2body);
            destroy = true;
        } else {
            if(!destroy) {
                setPosition(b2body.getPosition().x - getWidth() / 2,
                        b2body.getPosition().y - getHeight() / 2);
                setRegion((TextureRegion) walk.getKeyFrame(stateTime, true));
                b2body.setLinearVelocity(velocity);
            }
        }
    }
    
    @Override
    protected void defineEnemy() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(getX(),getY());
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);
        
        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(7 / Horror.PPM);
        fdef.filter.categoryBits = Horror.ENEMY_BIT;
        fdef.filter.maskBits = Horror.GROUND_BIT | 
                Horror.ROBOT_BIT |
                Horror.ENEMY_BIT |
                Horror.HUMAN_BIT |
                Horror.OBJECT_BIT |
                Horror.SKY_BIT |
                Horror.BULLET_BIT |
                Horror.EDGE_BIT; 
        
        fdef.shape = shape;
        b2body.createFixture(fdef).setUserData(this);
    }
    
    public void draw(Batch batch){
        if(!destroy){
            super.draw(batch);
        }
    }
    
    public void hitBullet(){
        setToDestroy=true;
    }
    
    
    public void hitHuman(){
        setToDestroy=true;
    }
    
    
}
