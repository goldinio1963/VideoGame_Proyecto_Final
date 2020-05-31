/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.Sprites;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Horror;
import com.mygdx.game.Scenes.Hud;
import com.mygdx.game.screens.Level1Screen;

/**
 *
 * @author Adolfo
 */
public class Robot extends Sprite {
    public World world;
    public Body b2body;
    private TextureRegion playerStand;
    public enum State { FALLING, JUMPING, STANDING, RUNNING};
    public State currentState;
    public State previousState;
    private Animation<TextureRegion> playeRunRight;
    private Animation<TextureRegion> playeRunLeft;
    private Animation<TextureRegion> playerStanding;
    private float stateTimer;
    private boolean runningRight; 
    public float shootDelay = 0.5f;
    public float timeSinceLastShot = 0f;
    
    
    public Robot (World world, Level1Screen screen) {
        super(screen.getalAtlas().findRegion("robot"));
        this.world = world;
        currentState = State.STANDING;
        previousState = State.STANDING;
        stateTimer = 0;
        runningRight = true;

        //Array for storing frames
        Array<TextureRegion> frames = new Array<TextureRegion>();

        //Loop to get robot running frames
        for (int i = 4; i < 8; i++){
            frames.add(new TextureRegion(getTexture(), i * 32, 131, 32, 64));
        }
        playeRunRight = new Animation<TextureRegion>(0.1f, frames);
        frames.clear();

        //Loop to get robot standing frames
        for (int i = 1; i < 4; i++){
            frames.add(new TextureRegion(getTexture(), i * 32, 195, 32, 64));
        }
        playerStanding = new Animation<TextureRegion>(0.1f, frames);
        frames.clear();


        defineRobot();
        
//        playerStand = new TextureRegion(getTexture(), 166,65,32,60);
        setBounds(0, 0, 16/Horror.PPM, 32/Horror.PPM);
//        setRegion(playerStand);
    }
    
    public void defineRobot(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(32/Horror.PPM,64/Horror.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);
        
        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(14 / Horror.PPM);

        fdef.filter.categoryBits = Horror.ROBOT_BIT;
        fdef.filter.maskBits = Horror.GROUND_BIT | 
                Horror.HOLE_BIT |
                Horror.HUMAN_BIT |
                Horror.OBJECT_BIT |
                Horror.SKY_BIT;
        
        
        
        fdef.shape = shape;
        b2body.createFixture(fdef);
        
        EdgeShape head = new EdgeShape();
        head.set(new Vector2(-2/Horror.PPM,6/Horror.PPM), 
                new Vector2(2/Horror.PPM,5/Horror.PPM));
        
        fdef.shape = head;
        fdef.isSensor = true;
        b2body.createFixture(fdef).setUserData("head");
    }
    
    public void update(float delta) {
        setPosition(b2body.getPosition().x-getWidth()/2, 
                b2body.getPosition().y- getHeight()/2);
        
        setRegion(getFrame(delta));
    }
    
    public TextureRegion getFrame(float delta) {
        currentState = getState();
        
        TextureRegion region;
        switch(currentState){
            case JUMPING:
                region = playerStanding.getKeyFrame(stateTimer);
                break;
            
            case RUNNING:
                region = playeRunRight.getKeyFrame(stateTimer, true);
                break;
            case FALLING:
            case STANDING:
                region = playerStanding.getKeyFrame(stateTimer, true);
                break;
            default:
                region = playerStanding.getKeyFrame(stateTimer, true);
                break;
        }
        
        if ((b2body.getLinearVelocity().x < 0 || !runningRight) && !region.isFlipX()){
            region.flip(true, false);
            runningRight = false;
        }
        else if ((b2body.getLinearVelocity().x > 0 || runningRight) && region.isFlipX()) {
            region.flip(true, false);
            runningRight = true;
        }
        
        stateTimer = currentState == previousState ? stateTimer + delta : 0;
        previousState = currentState;
        return region;
    }
    
    public State getState() {
        if (b2body.getLinearVelocity().y > 0 ||
                (b2body.getLinearVelocity().y < 0 && previousState == 
                State.JUMPING)) {
            return State.JUMPING;
        }
        else if(b2body.getLinearVelocity().y < 0) {
            return State.FALLING;
        } else if(b2body.getLinearVelocity().x != 0) {
            return State.RUNNING;
        } else
            return State.STANDING;
    }

    public boolean getRunning() {
        return runningRight;
    }
    
    public void fall(){
        //defineRobot();
        Hud.addLives(-1);
        Hud.addScore(0);
    }
}