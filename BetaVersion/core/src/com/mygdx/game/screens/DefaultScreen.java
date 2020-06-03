/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Horror;
import com.mygdx.game.Scenes.Hud;

/**
 *
 * @author Adolfo
 */
public class DefaultScreen implements Screen{
    
    protected Horror game;
    protected World world;
    protected TextureAtlas atlas;
    protected TiledMap map;
    protected boolean gameOver;
    protected int level;
    protected int levelScore;
    protected int level2Score;
    protected boolean newGame;
    protected boolean victory;
    protected boolean endDemo;
    
    protected Hud hud;

    DefaultScreen (Horror game) {
        this.game = game;
        gameOver = false;
        newGame = true;
        victory = false;
        level2Score = 0;
        levelScore = 0;
        endDemo = false;
        level = 1;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevelScore() {
        return levelScore;
    }

    public void setLevelScore(int levelScore) {
        this.levelScore = levelScore;
    }

    public int getLevel2Score() {
        return level2Score;
    }

    public void setLevel2Score(int level2Score) {
        this.level2Score = level2Score;
    }

    public boolean isNewGame() {
        return newGame;
    }

    public void setNewGame(boolean newGame) {
        this.newGame = newGame;
    }

    public boolean isVictory() {
        return victory;
    }

    public void setVictory(boolean victory) {
        this.victory = victory;
    }

    public boolean isEndDemo() {
        return endDemo;
    }

    public void setEndDemo(boolean endDemo) {
        this.endDemo = endDemo;
    }
       
    public void scoreManager(){
        if(isNewGame()){
            setLevelScore(0);
        } else {
            if(level == 1){
                if(isGameOver()){
                    setLevelScore(0);
                } else {
                    if(isVictory()){
                        setLevelScore(getLevelScore()+Hud.getScore());
                    }
                }
            }
        }
    }
    
    public void levelManager(){
        if(isVictory()){
            if(getLevel() == 1){
                setLevel(getLevel() + 1);
            } else {
                setEndDemo(true);
            }
        }
    }
    
    public TiledMap getMap() {
        return map;
    }
    
    public World getWorld() {
        return world;
    }
    public TextureAtlas getalAtlas() {
        return atlas;
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

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }
    
    
    
    
}
