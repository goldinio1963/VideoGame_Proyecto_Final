package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
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
public class ScoreScreen extends DefaultScreen{
    
    private Viewport viewport;
    private Stage stage;
    private TextureAtlas atlas;
    private Skin skin;
    private int score;
    
    public ScoreScreen(Horror game) {
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
        
        //create the label font
        Label.LabelStyle font = new Label.LabelStyle(new BitmapFont(), Color.WHITE);
        
        //getScore
        score = getLevelScore();
        
         //Create Table
        Table table = new Table();
        //Set table to fill stage
        table.setFillParent(true);
        //Set alignment of contents in the table.
        table.center();
        
        Label title = new Label("'Maximo Puntaje'", font);
        Label puntaje = new Label("'Ultimo Puntaje:  '" + 
                String.format("%02d", score) , font);
        TextButton exitButton = new TextButton("Regresar", skin);
        
        setLevel(getLevel()+1);
        
         //Add listeners to buttons
        exitButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Horror)Gdx.app.getApplicationListener()).setScreen(new MenuSrceen(game));
                dispose();
            }
        });

        table.add(title).expandX();
        table.row();
        table.add(puntaje).expandX().padTop(5f);
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
    