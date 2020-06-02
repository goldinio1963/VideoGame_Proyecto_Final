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

    public Ammo(DefaultScreen screen, MapObject object){
        super(screen, object);
        tileSet = map.getTileSets().getTileSet("");
        fixture.setUserData(this);
        setCategoryFilter(Horror.AMMO_BIT);
    }

    public void hit(Robot player) {
        
    }
}
