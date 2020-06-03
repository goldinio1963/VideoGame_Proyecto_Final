/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Horror;
import com.mygdx.game.Scenes.Hud;

/**
 *
 * @author Adolfo
 */
public class GameOverScreen extends DefaultScreen{
    
    private Viewport viewport;
    private Stage stage;
    private TextureAtlas atlas;
    private Skin skin;
    private int score;
    
    public GameOverScreen(Horror game) {
        super(game);
        viewport = new FitViewport(Horror.V_WIDTH, Horror.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, ((Horror)game).batch);
        atlas = new TextureAtlas("skin/uiskin.atlas");
        skin = new Skin(Gdx.files.internal("skin/uiskin.json"), atlas);
        score = 0;
    }
    
    
    @Override
    public void show() {
        //Stage should controll input:
        Gdx.input.setInputProcessor(stage);
        
        score = Hud.getScore();
        //create the label font
        Label.LabelStyle font = new Label.LabelStyle(new BitmapFont(), Color.WHITE);
        
         //Create Table
        Table table = new Table();
        //Set table to fill stage
        table.setFillParent(true);
        //Set alignment of contents in the table.
        table.center();
        
        Label gameOverLabel = new Label("'Game Over'", font);
        TextButton playButton = new TextButton("Continuar", skin);
        TextButton exitButton = new TextButton("Salir", skin);
        
         //Add listeners to buttons
        playButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(Hud.getLevel() == 1) {
                    ((Horror)Gdx.app.getApplicationListener()).setScreen(new Level1Screen(game));
                } else {
                    ((Horror)Gdx.app.getApplicationListener()).setScreen(new Level2Screen(game));
                    Hud.addLevel(1);
                    Hud.addScore(score);
                }
                dispose();
            }
        });
        exitButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Horror)Gdx.app.getApplicationListener()).setScreen(new MenuSrceen(game));
                dispose();
            }
        });

        table.add(gameOverLabel).expandX();
        table.row();
        table.add(playButton).expandX().padTop(5f);
        table.row();
        table.add(exitButton).expandX().padTop(5f);

        stage.addActor(table);
        
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
    }
    
    
    @Override
    public void dispose(){
        stage.dispose();
    }
}
