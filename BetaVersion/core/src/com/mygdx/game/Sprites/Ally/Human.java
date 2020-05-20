/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.Sprites.Ally;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.screens.Level1Screen;



/**
 *
 * @author Adolfo
 */
public class Human extends Sprite{
    
    public World world;
    public Body b2body;
    
    
    private Animation humanWalking;
    
    
    public Human(Level1Screen screen){
        
    }
}
