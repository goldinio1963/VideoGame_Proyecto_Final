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

/**
 *
 * @author Adolfo
 */
public class CreditsScreen extends DefaultScreen{
    
    private Viewport viewport;
    private Stage stage;
    private TextureAtlas atlas;
    protected Skin skin;
    
    Texture background;
       
    public CreditsScreen(Horror game) {
        super(game);
        
        viewport = new FitViewport(Horror.V_WIDTH, Horror.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, ((Horror)game).batch);
        atlas = new TextureAtlas("skin/uiskin.atlas");
        skin = new Skin(Gdx.files.internal("skin/uiskin.json"), atlas);
        background = new Texture("Map/png/BG1tiles.png");
    }
    
    @Override
    public void show() {
        //Stage should controll input:
        Gdx.input.setInputProcessor(stage);
        
        
        //create the label font
        Label.LabelStyle font = new Label.LabelStyle(new BitmapFont(), Color.WHITE);
        
         //Create Table
        Table mainTable = new Table();
        //Set table to fill stage
        mainTable.setFillParent(true);
        //Set alignment of contents in the table.
        mainTable.center();
        
        Label title = new Label("Creditos", font);
        Label adolfo = new Label("Adolfo Lerma A00822029", font);
        Label adrian = new Label("Jesus Adrian A001193991", font);
        Label andre = new Label("Andre Lujan A01540245", font);
        Label daniel = new Label("Daniel Castro A01089938", font);
        TextButton returnbutton = new TextButton("Exit", skin);
        
        
        returnbutton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
               ((Horror)Gdx.app.getApplicationListener()).setScreen(new MenuSrceen(game));
            }
        });

        //Add buttons to table
        mainTable.add(title).expandX();
        mainTable.row();
        mainTable.add(adolfo).expandX();
        mainTable.row();
        mainTable.add(adrian).expandX();
        mainTable.row();
        mainTable.add(andre).expandX();
        mainTable.row();
        mainTable.add(daniel).expandX();
        mainTable.row();
        mainTable.add(returnbutton).pad(5f);
        
        stage.addActor(mainTable);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        game.batch.begin();
        game.batch.draw(background, 0, 0,Horror.V_WIDTH, Horror.V_HEIGHT);
        game.batch.end();
        
        stage.draw();
        
    }

    @Override
    public void resize(int i, int i1) {
    }
    
    @Override
    public void dispose(){
        //game.batch.dispose();
        stage.dispose();
    }
    
    
    
    
}
