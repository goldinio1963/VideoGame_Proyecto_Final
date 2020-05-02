package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

public class MyGdxGame extends ApplicationAdapter {
	private SpriteBatch batch;
	private Texture img1, img2, img3, img4, img5, img6, img7, img8,
                img9, img10;
	
	private boolean DEBUG = false;
        private final float SCALE = 2.0f;
	
        private OrthographicCamera camera;
        
        private Box2DDebugRenderer b2dr;
        private World world;
        private Body player, platform;
        private int index = 0;
        
	@Override
	public void create () {
            float w= Gdx.graphics.getWidth();
            float h = Gdx.graphics.getHeight();
                
            camera = new OrthographicCamera();
            camera.setToOrtho(false, w/SCALE, h/SCALE);
            
            world = new World(new Vector2(0, -9.8f), false);
            b2dr = new Box2DDebugRenderer();
            //player = createBox(8, 10, 32,32,false);
            //platform = createBox(0, 0, 64,32,true);
            
            batch = new SpriteBatch();
            img1 = new Texture("Intrucciones.jpg");
            img2 = new Texture("Menu.jpg");
            img3 = new Texture("Score.jpg");
            img4 = new Texture("Loading.jpg");
            img5 = new Texture("Credits.jpg");
            img6 = new Texture("Game.jpg");
            img7 = new Texture("Game2.jpg");
            img8 = new Texture("Game3.jpg");
            img9 = new Texture("GameOVER.jpg");
            img10 = new Texture("final.jpg");
        }

	@Override
	public void render () {
            update(Gdx.graphics.getDeltaTime());
            Texture images[] = {img1,img2,img3,img4,
                                img5,img6,img7,img8,
                                img9,img10};
		//Render
            Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            //System.out.println(images[index]);
            batch.begin();
            
            batch.draw(images[index], 0, 0,720,480);
            
            batch.end();
            
	}
        
        @Override
        public void resize(int width, int height) {
            camera.setToOrtho(false, width/SCALE, height/SCALE);
        }
	
	@Override
	public void dispose () {
            world.dispose();
            b2dr.dispose();
            batch.dispose();
	}
        
        public void update (float delta) {
            //update the display
            world.step(1/60f, 6, 2);
            inputUpdate(delta);
            //batch.setProjectionMatrix(camera.combined);
        }
        
        public void inputUpdate(float delta) {
            
            if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
                //System.out.println(index);
                if(index < 9){
                    index++;
                } else {
                    index = 1;
                }
            }
            
        }
}
