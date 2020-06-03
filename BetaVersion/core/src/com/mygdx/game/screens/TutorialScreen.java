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
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Horror;

/**
 *
 * @author Adolfo
 */
public class TutorialScreen extends DefaultScreen{
    
    private Viewport viewport;
    private Stage stage;
    private TextureAtlas atlas;
    private Skin skin;
    Texture banner, banner2;
    
    public TutorialScreen(Horror game) {
        super(game);
        
        viewport = new FitViewport(Horror.V_WIDTH, Horror.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, ((Horror)game).batch);
        atlas = new TextureAtlas("skin/uiskin.atlas");
        skin = new Skin(Gdx.files.internal("skin/uiskin.json"), atlas);
        banner = new Texture("Screen/Banner/play.jpg");
        banner2 = new Texture("Screen/Banner/score.jpg");
        
    }
    
    public void show() {
        //Stage should controll input:
        Gdx.input.setInputProcessor(stage);
        
        //create the label font
        Label.LabelStyle font = new Label.LabelStyle(new BitmapFont(), Color.WHITE);
        
        Table table = new Table();
        //Set table to fill stage
        table.setFillParent(true);
        //Set alignment of contents in the table.
        table.center();
        
        Label gameOverLabel = new Label("Click para continuar", font);
        
        table.add(gameOverLabel).expandX();
        
        stage.addActor(table);
    }
    
    @Override
    public void render(float delta) {
        if(Gdx.input.justTouched()) {
            game.setScreen(new Tutorial2((Horror) game));
            dispose();
        }
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        game.batch.begin();
        
        game.batch.draw(banner, 0, 0, Horror.V_WIDTH, Horror.V_HEIGHT);
        
        game.batch.end();
        
        stage.draw();
        
    }
    
    public void dispose(){
        stage.dispose();
    }
    
}
