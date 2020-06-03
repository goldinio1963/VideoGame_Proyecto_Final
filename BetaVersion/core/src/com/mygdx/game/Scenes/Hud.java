/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.Scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Horror;

/**
 *
 * @author Adolfo
 */
public class Hud implements Disposable{
    public Stage stage;
    private Viewport viewport;
    
    private static Integer level;
    private static Integer score;
    private static Integer lives;
    private Integer bulletsAvailable;

    private Label robotLabel;
    private Label worldLabrl;
    private Label levelScoreLebel;
    private static Label scoreLabel;
    private static Label levelLabel;
    private static Label livesLabel;
    private Label bulletsLabel;
    private Label bulletsCountLabel;
    
    public Hud (SpriteBatch sb) {
        score = 0;
        level = 1;
        lives = 3;
        bulletsAvailable = 8;
        
        viewport = new FitViewport(Horror.V_WIDTH, Horror.V_HEIGHT, 
                new OrthographicCamera());
        
        stage = new Stage(viewport, sb);
        
        Table table = new Table();
        table.top();
        table.setFillParent(true);
        
        
         robotLabel = new Label("Vidas", 
                new Label.LabelStyle(new BitmapFont(), Color.WHITE));
         worldLabrl = new Label("Nivel", 
                new Label.LabelStyle(new BitmapFont(), Color.WHITE));
         levelScoreLebel = new Label ("Score", 
                new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        
        scoreLabel = new Label(String.format("%02d", score), 
                new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        bulletsCountLabel =new Label(String.format("%01d", bulletsAvailable), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        levelLabel = new Label(String.format("%01d", level),
                new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        livesLabel = new Label (String.format("%01d", lives), 
                new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        bulletsLabel = new Label("Balas", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        table.add(robotLabel).expandX().padTop(10);
        table.add(worldLabrl).expandX().padTop(10);
        table.add(levelScoreLebel).expandX().padTop(10);
        table.add(bulletsLabel).expandX().padTop(10);
        //add a second row to our table
        table.row();
        table.add(livesLabel).expandX().padTop(10);
        table.add(levelLabel).expandX().padTop(10);
        table.add(scoreLabel).expandX().padTop(10);
        table.add(bulletsCountLabel).expandX();

        //add our table to the stage
        stage.addActor(table);
    }

    //Function to get bullets available
    public int getBulletsCount() {
        return bulletsAvailable;
    }

    //Function to set bullets and update label
    public void setBulletsCount(int bulletsAvailable) {
        this.bulletsAvailable = bulletsAvailable;
        bulletsCountLabel.setText(String.format("%01d", bulletsAvailable));
    }
    
    //add bullest
    public void addBullets(int value){
        setBulletsCount(getBulletsCount()+value);
    }
    
    public void update(float delta) {
        
    }
    
    public static void addLevel(int value){
        level += value;
        levelLabel.setText(String.format("%01d", level));
    } 
    
    public static void addScore(int value){
        score += value;
        scoreLabel.setText(String.format("%02d", score));
    }
    
    public static void addLives(int value){
        lives += value;
        if(lives >= 0 ){
            livesLabel.setText(String.format("%01d", lives));
        }
    }

    public static Integer getScore() {
        return score;
    }

    public static void setScore(Integer score) {
        Hud.score = score;
    }

    public static Label getLivesLabel() {
        return livesLabel;
    }

    public static void setLivesLabel(Label livesLabel) {
        Hud.livesLabel = livesLabel;
    }

    public static Integer getLevel() {
        return level;
    }

    public static void setLevel(Integer level) {
        Hud.level = level;
    }
    
    
    
    

    @Override
    public void dispose() {
        stage.dispose();
    }

    public static Integer getLives() {
        return lives;
    }

    public static void setLives(Integer lives) {
        Hud.lives = lives;
    }
    
}
