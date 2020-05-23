/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.screens;

import com.badlogic.gdx.Screen;
import com.mygdx.game.Horror;

/**
 *
 * @author Adolfo
 */
public class DefaultScreen implements Screen{
    
    protected Horror game;

    DefaultScreen (Horror game) {
        this.game = game;
    }
    
    @Override
    public void show() {
        
    }

    @Override
    public void render(float delta) {
    }

    @Override
    public void resize(int i, int i1) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
    }
    
    
}
