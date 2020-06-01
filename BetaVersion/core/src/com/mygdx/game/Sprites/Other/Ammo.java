/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.Sprites.Other;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.mygdx.game.Horror;
import com.mygdx.game.Sprites.Robot;
import com.mygdx.game.screens.DefaultScreen;

/**
 *
 * @author Adolfo
 */
public class Ammo extends InteractiveTileObject{
    private static TiledMapTileSet tileSet;
    private final int BLANK_COIN = 28;

    public Ammo(DefaultScreen screen, MapObject object){
        super(screen, object);
        tileSet = map.getTileSets().getTileSet("tileset_gutter");
        fixture.setUserData(this);
        setCategoryFilter(Horror.MASK_BIT);
    }

    public void hit(Robot player) {
        
    }
}
