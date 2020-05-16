package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.screens.Level1Screen;

/**
 *
 * @author Adolfo
 */
public class Horror extends Game {

    public Resources res;
    public static final int V_WIDTH = 400;
    public static final int V_HEIGHT = 208;
    public static final float PPM = 100;
    
    //
    public static final short NOTHING_BIT = 0;
    public static final short GROUND_BIT = 1;
    public static final short ROBOT_BIT = 2;
    public static final short DESTROYED_BIT = 16;
    public static final short OBJECT_BIT = 32;
    public static final short ENEMY_BIT = 64;

    @Override
    public void create() {
        res = new Resources();
        setScreen(new Level1Screen(this));
    }
    
    @Override
    public void render() {
        super.render();
    }


    @Override
    public void dispose() {
        res.dispose();
    }
}
