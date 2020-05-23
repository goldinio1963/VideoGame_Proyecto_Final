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
    
    private Integer level;
    private static Integer score;
    private static Integer lives;
    
    private Label robotLabel;
    private Label worldLabrl;
    private Label levelScoreLebel;
    private static Label scoreLabel;
    private Label levelLabel;
    private static Label livesLabel;
    
    public Hud (SpriteBatch sb) {
        score = 0;
        level = 1;
        lives = 3;
        
        
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
        levelLabel = new Label(String.format("%01d", level), 
                new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        livesLabel = new Label (String.format("%01d", lives), 
                new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        
        table.add(robotLabel).expandX().padTop(10);
        table.add(worldLabrl).expandX().padTop(10);
        table.add(levelScoreLebel).expandX().padTop(10);
        table.row();
        table.add(livesLabel).expandX().padTop(10);
        table.add(levelLabel).expandX().padTop(10);
        table.add(scoreLabel).expandX().padTop(10);
        
        
        stage.addActor(table);
    }
    
    public void update(float delta) {
        
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

    @Override
    public void dispose() {
        stage.dispose();
    }
    
}
