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
import com.mygdx.game.Sprites.Enemy.EnemyStandard;

/**
 *
 * @author Adolfo
 */
public class WorldContactListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();
        
        int cDef = fixA.getFilterData().categoryBits | fixB.getFilterData().categoryBits;
        
        switch (cDef){
            
            case Horror.ENEMY_BIT | Horror.OBJECT_BIT:
                if(fixA.getFilterData().categoryBits == Horror.ENEMY_BIT)
                    ((EnemyStandard)fixA.getUserData()).reverseVelocity(true, false);
                else
                    ((EnemyStandard)fixB.getUserData()).reverseVelocity(true, false);
                break;
            case Horror.ENEMY_BIT | Horror.ENEMY_BIT:
                if(fixA.getFilterData().categoryBits == Horror.ENEMY_BIT)
                    ((EnemyStandard)fixA.getUserData()).reverseVelocity(true, false);
                else
                    ((EnemyStandard)fixB.getUserData()).reverseVelocity(true, false);
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
