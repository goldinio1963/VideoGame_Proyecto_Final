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
    
    private Label countdownLabel;
    private  static Label scoreLabel;
    private Label levelLabel;
    private Label livesLabel;
    
    public Hud (SpriteBatch sb) {
        score = 0;
        level = 1;
        
        
        viewport = new FitViewport(Horror.V_WIDTH, Horror.V_HEIGHT, 
                new OrthographicCamera());
        
        stage = new Stage(viewport, sb);
        
        Table table = new Table();
        table.top();
        table.setFillParent(true);
        
        scoreLabel = new Label(String.format("%02d", score), 
                new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        levelLabel = new Label("Level-" + String.format("%01d", level), 
                new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        livesLabel = new Label ("Lives", 
                new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        
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

    @Override
    public void dispose() {
        stage.dispose();
    }
    
}
