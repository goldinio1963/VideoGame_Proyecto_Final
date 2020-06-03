/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.Tools;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.mygdx.game.Horror;
import com.mygdx.game.Scenes.Hud;
import com.mygdx.game.Sprites.Ally.Human;
import com.mygdx.game.Sprites.Enemy.EnemyStandard;
import com.mygdx.game.Sprites.Misc.Bullet;
import com.mygdx.game.Sprites.Other.Ammo;
import com.mygdx.game.Sprites.Other.Mask;
import com.mygdx.game.Sprites.Robot;
import com.mygdx.game.screens.DefaultScreen;
import com.mygdx.game.screens.Level1Screen;

/**
 *
 * @author Adolfo
 */
public class WorldContactListener implements ContactListener {

    
    private DefaultScreen level;
    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();
        
        int cDef = fixA.getFilterData().categoryBits | fixB.getFilterData().categoryBits;
        
        switch (cDef){
            case Horror.ENEMY_BIT | Horror.EDGE_BIT:
            case Horror.ENEMY_BIT | Horror.OBJECT_BIT:
                if(fixA.getFilterData().categoryBits == Horror.ENEMY_BIT)
                    ((EnemyStandard)fixA.getUserData()).reverseVelocity(true, false);
                else
                    ((EnemyStandard)fixB.getUserData()).reverseVelocity(true, false);
                break;
            case Horror.ENEMY_BIT | Horror.ENEMY_BIT:
                ((EnemyStandard)fixA.getUserData()).reverseVelocity(true, false);
                ((EnemyStandard)fixB.getUserData()).reverseVelocity(true, false);
                break;
            case Horror.HUMAN_BIT | Horror.ENEMY_BIT:
                if(fixA.getFilterData().categoryBits == Horror.HUMAN_BIT){
                    ((Human)fixA.getUserData()).hit();
                    ((EnemyStandard)fixB.getUserData()).hitHuman();
                } else {
                    ((EnemyStandard)fixA.getUserData()).hitHuman();
                    ((Human)fixB.getUserData()).hit();
                }
                break;
            case Horror.HUMAN_BIT | Horror.HOLE_BIT:
                if(fixA.getFilterData().categoryBits == Horror.HUMAN_BIT){
                    ((Human)fixA.getUserData()).fall();
                } else {
                    ((Human)fixB.getUserData()).fall();
                }
                break;
            case Horror.ROBOT_BIT | Horror.HOLE_BIT:
                if(fixA.getFilterData().categoryBits == Horror.ROBOT_BIT){
                    ((Robot)fixA.getUserData()).fall();
                } else {
                    ((Robot)fixB.getUserData()).fall();
                }
                break;
            
            case Horror.BULLET_BIT | Horror.ENEMY_BIT:
                if(fixA.getFilterData().categoryBits == Horror.BULLET_BIT){
                    ((Bullet)fixA.getUserData()).hit();
                    ((EnemyStandard)fixB.getUserData()).hitBullet();
                } else {
                    ((EnemyStandard)fixA.getUserData()).hitBullet();
                    ((Bullet)fixB.getUserData()).hit();
                }
                break;
            case Horror.BULLET_BIT | Horror.EDGE_BIT:
            case Horror.BULLET_BIT | Horror.OBJECT_BIT:
            case Horror.BULLET_BIT | Horror.SKY_BIT:
            case Horror.BULLET_BIT | Horror.GROUND_BIT:
                if(fixA.getFilterData().categoryBits == Horror.ENEMY_BIT)
                    ((Bullet)fixA.getUserData()).hit();
                else
                    ((Bullet)fixB.getUserData()).hit();
                break;
                
            case Horror.ROBOT_BIT | Horror.AMMO_BIT:
                if(fixA.getFilterData().categoryBits == Horror.ROBOT_BIT){
                    ((Ammo)fixB.getUserData()).hit();
                    System.out.println("enter");
                } else
                    ((Ammo)fixA.getUserData()).hit();
                break;
            case Horror.ROBOT_BIT | Horror.MASK_BIT:
                if(fixA.getFilterData().categoryBits == Horror.ROBOT_BIT)
                    ((Mask)fixB.getUserData()).hit();
                else
                    ((Mask)fixA.getUserData()).hit();
                break;
                
            case Horror.HUMAN_BIT | Horror.HOUSE_BIT:
                if(fixA.getFilterData().categoryBits == Horror.HUMAN_BIT)
                    ((Human)fixA.getUserData()).victory();
                else
                    ((Human)fixB.getUserData()).victory();
                break;
            
        }
    }

    @Override
    public void endContact(Contact contact) {
    }

    @Override
    public void preSolve(Contact contact, Manifold mnfld) {
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse ci) {
    }
    
}
