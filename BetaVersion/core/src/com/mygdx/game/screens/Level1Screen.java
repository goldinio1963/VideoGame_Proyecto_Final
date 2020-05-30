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
import com.mygdx.game.Sprites.Ally.Human;
import com.mygdx.game.Sprites.Bullet;
import com.mygdx.game.Sprites.Enemy.EnemyStandard;
import com.mygdx.game.Sprites.Enemy.Ghost;
import com.mygdx.game.Sprites.Robot;
import com.mygdx.game.Tools.B2WorldCreator;
import com.mygdx.game.Tools.WorldContactListener;
import com.mygdx.game.graph.Background;

import java.util.ArrayList;

/**
 *
 * @author Adolfo
 */
public class Level1Screen extends DefaultScreen{
    
    SpriteBatch batch;
    
    
    //public static final int SCREEN_W = 12 * Resources.TILE_SIZE;
    //public static final int SCREEN_H = 8 * Resources.TILE_SIZE;

    //Basic playscreen variables
    private Stage gameSatge;
    private OrthographicCamera gamecam;
    private Background bg;
    private Viewport gameport;
    private Hud hud;

    //Tiled map variables
    private TmxMapLoader maploader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
            
    
    //Box2d world
    private World world;
    private Box2DDebugRenderer b2dr;
    private B2WorldCreator creator;
    
    //Player
    private Robot player;
    
    //ally
    private Human human;

    // Bullets array
    ArrayList<Bullet> bullets;
    Level1Screen screen;
    private int bulletsCount;
    
    //enemies
    
    //Sprites
    private TextureAtlas atlas;
    
    public Level1Screen(Horror _game) {
        super(_game);
        
        //sprites
        atlas = new TextureAtlas("human_robot_pack.pack");
        
        batch = new SpriteBatch();
        //Keep the screen constant for the game
        //ExtendViewport viewport = new ExtendViewport(SCREEN_W, SCREEN_H);
        gamecam = new OrthographicCamera();

        //create a FitViewport to maintain virtual aspect ratio despite screen size
        gameport = new FitViewport(Horror.V_WIDTH/Horror.PPM
                , Horror.V_HEIGHT/Horror.PPM, gamecam);
        
        //create our game HUD for scores/timers/level info
        hud = new Hud(batch);

        //Load out map and setup our map renderer
        maploader = new TmxMapLoader();
        map = maploader.load("Map/level1.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1/Horror.PPM);

        //Initially set our game cam to be c entered correctly at the start of our game
        gamecam.position.set(gameport.getWorldWidth()/2, 
                gameport.getWorldHeight()/2, 0);
        
        //Creates world
        world = new World(new Vector2(0, -9.18f), true);
        b2dr = new Box2DDebugRenderer();
        
        creator = new B2WorldCreator(this);

        //Creates player in world
        player = new Robot(world,this);
        human = new Human(this);
        this.screen = this;
        //Creates bullets array
        bullets = new ArrayList<Bullet>();
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
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            player.b2body.applyLinearImpulse(new Vector2(0,3f),
                    player.b2body.getWorldCenter(), true);
            human.b2body.applyLinearImpulse(new Vector2(0,3f),
                    human.b2body.getWorldCenter(), true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D) && (player.b2body.getLinearVelocity().x <= 1.2)) {
            player.b2body.applyLinearImpulse(new Vector2(0.1f,0), 
                    player.b2body.getWorldCenter(), true);
            if(human.getX() < player.getX()){
                human.b2body.applyLinearImpulse(new Vector2(0.1f,0), 
                       human.b2body.getWorldCenter(), true);
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A) && (player.b2body.getLinearVelocity().x >= -1.2)) {
            player.b2body.applyLinearImpulse(new Vector2(-0.1f,0), 
                    player.b2body.getWorldCenter(), true);
            if(human.getX() < player.getX() && human.getX() > player.getX()-32/Horror.PPM){
                human.b2body.applyLinearImpulse(new Vector2(-0.1f,0), 
                        human.b2body.getWorldCenter(), true);
            }
        }
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            // Checks if bullets available
            if (hud.getBulletsCount() > 0) {
                // Check direction of robot moving
                if (player.getRunning()) {
                    bullets.add(new Bullet(world, screen, player, player.b2body.getPosition().x + (35 / Horror.PPM), player.b2body.getPosition().y));
                } else {
                    bullets.add(new Bullet(world, screen, player,
                            player.b2body.getPosition().x - (35 / Horror.PPM), player.b2body.getPosition().y));
                }
                // Updates bullet count
                hud.setBulletsCount(hud.getBulletsCount() - 1);
            }
        }
        
        
    }
    
    public void update(float delta) {
        //handel user input 
        handleInput(delta);
        
        world.step(1/60f, 6, 2);

        //Update player, human and hud
        player.update(delta);
        human.update(delta);
        hud.update(delta);
        
        //update enemies
        for (EnemyStandard enemy : creator.getGhosts()){
            enemy.update(delta);
        }

        // Update bullet
        ArrayList<Bullet> bulletsToRemove = new ArrayList<Bullet>();
        for (Bullet bullet : bullets) {
            // Checks direction of bullet and add force
            if (bullet.getRight()) {
                bullet.b2body.applyForce(new Vector2(3, -(-10)), new Vector2(bullet.b2body.getWorldCenter()), true);
            } else {
                bullet.b2body.applyForce(new Vector2(-3, -(-10)), new Vector2(bullet.b2body.getWorldCenter()), true);
            }

            bullet.update(delta);
            // If bullet collision with wall or enemy add to bulletsToRemoveArray
            // if (collision) {
            // bulletsToRemove.add(bullet);
            // }
        }

        // Removes bullets in bulletsToRemoveArray
        bullets.removeAll(bulletsToRemove);
        
        //Move position of camera depending of main player
        gamecam.position.x = player.b2body.getPosition().x;

        //update our game cam with correct coordinates
        gamecam.update();

        //tell our renderer to draw only what out camera can see in our game world.
        renderer.setView(gamecam);
    }
    
    @Override
    public void render (float delta){
        //separate out update logic from render
        update(delta);

        //Clear the game screen with black
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        //render the map of the game
        renderer.render();
        
        //render the box2d debug object
        //b2dr.render(world, gamecam.combined);
        
        //draw player
        batch.setProjectionMatrix(gamecam.combined);
        batch.begin();
        player.draw(batch);
        human.draw(batch);
        for(EnemyStandard enemy : creator.getGhosts()){
            enemy.draw(batch);
        }
        for (Bullet bullet : bullets) {
            bullet.draw(batch);
        }
        batch.end();
        
        //draw hud of the game
        batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
        
        //bg.draw(gameSatge, game.res);
        //gameSatge.draw();
    }
    
    //update screen gameport when screen resizes
    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        gameport.update(width, height);
    }
    
    //Dispose variables 
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
