/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Horror;
import com.mygdx.game.Scenes.Hud;
import com.mygdx.game.Sprites.EnemyStandard;
import com.mygdx.game.Sprites.Ghost;
import com.mygdx.game.Sprites.Robot;
import com.mygdx.game.Tools.B2WorldCreator;
import com.mygdx.game.Tools.WorldContactListener;
import com.mygdx.game.graph.Background;

/**
 *
 * @author Adolfo
 */
public class Level1Screen extends DefaultScreen{
    
    SpriteBatch batch;
    
    
    //public static final int SCREEN_W = 12 * Resources.TILE_SIZE;
    //public static final int SCREEN_H = 8 * Resources.TILE_SIZE;
    
    private Stage gameSatge;
    private OrthographicCamera gamecam;
    private Background bg;
    private Viewport gameport;
    private Hud hud;
    
    private TmxMapLoader maploader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
            
    
    //Box2d world
    private World world;
    private Box2DDebugRenderer b2dr;
    private B2WorldCreator creator;
    
    //Player
    private Robot player;
    
    //enemies
    
    //Sprites
    private TextureAtlas atlas;
    
    public Level1Screen(Horror _game) {
        super(_game);
        
        //sprites
        atlas = new TextureAtlas("player_and_enemy.pack");
        
        batch = new SpriteBatch();
        //Keep the screen constant for the game
        //ExtendViewport viewport = new ExtendViewport(SCREEN_W, SCREEN_H);
        gamecam = new OrthographicCamera();
        gameport = new FitViewport(Horror.V_WIDTH/Horror.PPM
                , Horror.V_HEIGHT/Horror.PPM, gamecam);
        hud = new Hud(batch);
        
        maploader = new TmxMapLoader();
        map = maploader.load("level1.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1/Horror.PPM);
        
        gamecam.position.set(gameport.getWorldWidth()/2, 
                gameport.getWorldHeight()/2, 0);
        
        //box2d
        world = new World(new Vector2(0, -9.18f), true);
        b2dr = new Box2DDebugRenderer();
        
        creator = new B2WorldCreator(this);
        
        player = new Robot(this);
        //create a window to handle all the ui elements
        //gameSatge = new Stage(gameport, batch);
        //bg = new Background();
        world.setContactListener(new WorldContactListener());
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
    
    public void handleInput(float delta) {
        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            player.b2body.applyLinearImpulse(new Vector2(0,2f), 
                    player.b2body.getWorldCenter(), true);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D) && (player.b2body.getLinearVelocity().x <= 1.2)) {
            player.b2body.applyLinearImpulse(new Vector2(0.1f,0), 
                    player.b2body.getWorldCenter(), true);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A) && (player.b2body.getLinearVelocity().x >= -1.2)) {
            player.b2body.applyLinearImpulse(new Vector2(-0.1f,0), 
                    player.b2body.getWorldCenter(), true);
        }
        
        
    }
    
    public void update(float delta) {
        handleInput(delta);
        
        world.step(1/60f, 6, 2);
        
        player.update(delta);
        hud.update(delta);
        
        for(EnemyStandard enemy : creator.getGhosts()){
            enemy.update(delta);
        }
        
        
        gamecam.position.x = player.b2body.getPosition().x;
        
        gamecam.update();
        renderer.setView(gamecam);
    }
    
    @Override
    public void render (float delta){
        update(delta);
        
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        //render the map of the game
        renderer.render();
        
        //render the box2d object
        b2dr.render(world, gamecam.combined);
        
        //draw player
        batch.setProjectionMatrix(gamecam.combined);
        batch.begin();
        player.draw(batch);
        for(EnemyStandard enemy : creator.getGhosts()){
            enemy.draw(batch);
        }
        batch.end();
        
        //draw hud of the game
        batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
        
        //bg.draw(gameSatge, game.res);
        //gameSatge.draw();
    }
    
    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        gameport.update(width, height);
    }
    
    
    @Override
    public void dispose(){
        super.dispose();
        map.dispose();
        renderer.dispose();
        world.dispose();
        b2dr.dispose();
        hud.dispose();
        batch.dispose();
    }
}
