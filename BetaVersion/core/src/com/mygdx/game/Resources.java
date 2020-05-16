/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 *
 * @author Adolfo
 */
public class Resources {
    
    
    TextureAtlas gamesprites;
    public TextureRegion base_floor;
    public TextureRegion sky_base;
    public TextureRegion sky_top;
    public TextureRegion backgroung1;
    public TextureRegion backgroung2;
    public TextureRegion backgroung3;
    public TextureRegion Far_mountain_base;
    public TextureRegion Far_mountain;
    public TextureRegion human;
    
    public static final int TILE_SIZE = 32;
    
    public Resources(){
        gamesprites = new TextureAtlas(Gdx.files.internal("pack/game.atlas"));
        base_floor = gamesprites.findRegion("base_floor");
        sky_top = gamesprites.findRegion("sky_top");
        backgroung1 = gamesprites.findRegion("background1");
        backgroung2 = gamesprites.findRegion("background2");
        backgroung3 = gamesprites.findRegion("background3");
        Far_mountain= gamesprites.findRegion("Far_mountain");
        Far_mountain_base = gamesprites.findRegion("Far_mountain_base");
        sky_base = gamesprites.findRegion("sky_base");
        human = gamesprites.findRegion("human_1");
    }
    
    public void dispose(){
        gamesprites.dispose();
    }
}
