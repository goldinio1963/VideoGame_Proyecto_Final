/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.graph;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.Resources;

/**
 *
 * @author Adolfo
 */
public class Background {

    public Background() {

    }

    public void draw(Stage stage, Resources res) {
        stage.getBatch().begin();       
        for (int j = 0; j <= Resources.TILE_SIZE*2; j += Resources.TILE_SIZE) {
            for (int i = 0; i <= stage.getWidth(); i += Resources.TILE_SIZE) {
                stage.getBatch().draw(res.base_floor, i, j,
                        0,0,Resources.TILE_SIZE, Resources.TILE_SIZE,
                        1.01f, 1.01f,0);
                //stage.getBatch().draw(res.sky_base, 0, 32);
            }
        }
        
        for (int i = 0; i <= stage.getWidth(); i += Resources.TILE_SIZE) {
            stage.getBatch().draw(res.backgroung1, i, 64,
                    0,0,Resources.TILE_SIZE, Resources.TILE_SIZE,
                    1.01f, 1.01f,0);
            stage.getBatch().draw(res.backgroung2, i, 96,
                    0,0,Resources.TILE_SIZE, Resources.TILE_SIZE,
                    1.01f, 1.01f,0);
            stage.getBatch().draw(res.backgroung3, i, 128,
                    0,0,Resources.TILE_SIZE, Resources.TILE_SIZE,
                    1.01f, 1.01f,0);
            stage.getBatch().draw(res.Far_mountain_base, i, 160,
                    0,0,Resources.TILE_SIZE, Resources.TILE_SIZE,
                    1.01f, 1.01f,0);
            stage.getBatch().draw(res.Far_mountain, i, 192,
                    0,0,Resources.TILE_SIZE, Resources.TILE_SIZE,
                    1.01f, 1.01f,0);
            stage.getBatch().draw(res.sky_base, i, 224,
                    0,0,Resources.TILE_SIZE, Resources.TILE_SIZE,
                    1.01f, 1.01f,0);
            stage.getBatch().draw(res.sky_top, i, 
                    stage.getHeight()-Resources.TILE_SIZE,
                    0,0,Resources.TILE_SIZE, Resources.TILE_SIZE,
                    1.01f, 1.01f,0);
        }

        stage.getBatch().end();
    }
}
