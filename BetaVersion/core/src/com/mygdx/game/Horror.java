package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.screens.Level1Screen;
import com.mygdx.game.screens.MenuSrceen;

/**
 *
 * @author Adolfo
 */
public class Horror extends Game {

    public Resources res;
    public SpriteBatch batch;
    public static final int V_WIDTH = 400;
    public static final int V_HEIGHT = 208;
    public static final float PPM = 100;
    
    //collision bits
    public static final short NOTHING_BIT = 0;
    public static final short GROUND_BIT = 1;
    public static final short ROBOT_BIT = 2;
    public static final short DESTROYED_BIT = 16;
    public static final short OBJECT_BIT = 32;
    public static final short EDGE_BIT = 4;
    public static final short SKY_BIT = 8;
    public static final short ENEMY_BIT = 64;
    public static final short HUMAN_BIT = 128;
    public static final short HOLE_BIT = 256;
    public static final short BULLET_BIT = 512;

    @Override
    public void create() {
        res = new Resources();
        batch = new SpriteBatch();
        setScreen(new MenuSrceen(this));
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
