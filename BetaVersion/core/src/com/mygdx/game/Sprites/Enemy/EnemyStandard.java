/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.Sprites.Enemy;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.screens.DefaultScreen;
import com.mygdx.game.screens.Level1Screen;

/**
 *
 * @author Adolfo
 */
public abstract class EnemyStandard extends Sprite{
    
    protected World world;
    protected DefaultScreen screen;
    
    public Body b2body;
    
    public Vector2 velocity;
    
    public EnemyStandard(DefaultScreen screen, float x, float y){
        this.world = screen.getWorld();
        this.screen = screen;
        setPosition(x,y);
        defineEnemy();
        velocity = new Vector2(1,0);
        //b2body.setActive(false);
    }
    
    public abstract void update(float delta);
    
    protected abstract void defineEnemy();

    public void reverseVelocity(boolean x, boolean y){
        if(x) {
            velocity.x = -velocity.x;
        }
        if(y) {
            velocity.y = -velocity.y;
        }
    }
    
    public abstract void hitBullet();
    
    public abstract void hitHuman();
}
