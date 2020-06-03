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
import javax.swing.JOptionPane;

/**
 *
 * @author Adolfo
 */
public class MenuSrceen extends DefaultScreen{
    
    private Viewport viewport;
    private Stage stage;
    private TextureAtlas atlas;
    protected Skin skin;
    private int lastlevel;
    
    Texture background;
    
    
    public MenuSrceen(Horror game) {
        super(game);
        
        viewport = new FitViewport(Horror.V_WIDTH, Horror.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, ((Horror)game).batch);
        atlas = new TextureAtlas("skin/uiskin.atlas");
        skin = new Skin(Gdx.files.internal("skin/uiskin.json"), atlas);
        background = new Texture("Map/png/BG1tiles.png");
        lastlevel = 1;
        
        if(!isNewGame()){
            lastlevel = getLevel();
        }
        
                
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
        
        Label title = new Label("Horror in the Dark", font);
        TextButton playButton = new TextButton("Juego Nuevo", skin);
        TextButton loadButton = new TextButton("Continuar", skin);
        TextButton scoreButton = new TextButton("Puntaje", skin);
        TextButton creditsButton = new TextButton("Creditos", skin);
        TextButton optionsButton = new TextButton("Options", skin);
        TextButton exitButton = new TextButton("Salir", skin);
        
         //Add listeners to buttons
        playButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Horror)Gdx.app.getApplicationListener()).setScreen(new TutorialScreen(game));
                setNewGame(true);
                dispose();
            }
        });
        loadButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(getLevel() == 1){
                    int input = JOptionPane.showConfirmDialog(null,
                            "Se necesita empezar un nuevo Juego", "Ups eso no se puede", JOptionPane.DEFAULT_OPTION);
                    // 0=ok
                } else{
                    ((Horror)Gdx.app.getApplicationListener()).setScreen(new Level1Screen(game));
                    dispose();
                }
            }
        });
        scoreButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Horror)Gdx.app.getApplicationListener()).setScreen(new ScoreScreen(game));
                dispose();
            }
        });
        creditsButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Horror)Gdx.app.getApplicationListener()).setScreen(new CreditsScreen(game));
                dispose();            
            }
        });
        optionsButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("options");
            }
        });
        exitButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        //Add buttons to table
        mainTable.add(title);
        mainTable.row();
        mainTable.add(playButton).pad(5f);
        mainTable.row();
        mainTable.add(loadButton).pad(5f);
        mainTable.row();
        mainTable.add(scoreButton).pad(5f);
        mainTable.row();
        //mainTable.add(optionsButton);
        //mainTable.row();
        mainTable.add(creditsButton).pad(5f);
        mainTable.row();
        mainTable.add(exitButton).pad(5f);
        
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
