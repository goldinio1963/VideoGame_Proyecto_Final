/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.Sprites.Ally;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Horror;
import com.mygdx.game.Scenes.Hud;
import com.mygdx.game.Sprites.Robot;
import com.mygdx.game.screens.DefaultScreen;
import com.mygdx.game.screens.Level1Screen;



/**
 *
 * @author Adolfo
 */
public class Human extends Sprite{
    
    public World world;
    public Body b2body;
    private Robot player;
    private float stateTime;
    private Animation humanWalking;
    private Array<TextureRegion> frames;
    public enum State { FALLING, JUMPING, STANDING, RUNNING};
    public State currentState;
    public State previousState;
    private float stateTimer;
    private boolean runningRight;
    private TextureRegion humanStand;
    
    
    public Human(DefaultScreen screen){
        this.world = screen.getWorld();
        currentState = State.STANDING;
        previousState = State.STANDING;
        stateTimer = 0;
        runningRight = true;
        
        
        frames = new Array<TextureRegion>();
        for(int i=0; i <3; i++){
            frames.add(new TextureRegion(screen.getalAtlas().findRegion("Human")
            , i*32,0,32,64));
        }
        humanWalking = new Animation(0.4f, frames);
        stateTime = 0;
        
        humanStand = new TextureRegion(screen.getalAtlas().findRegion("Human")
                    ,32,0,32,64);
        setRegion(humanStand);
        
        setBounds(getX(), getY(), 12/Horror.PPM, 24/Horror.PPM);

        defineHuman();
    }
    
    public void update(float delta){
        
        
        setPosition(b2body.getPosition().x - getWidth()/2, 
                b2body.getPosition().y - getHeight()/2);
        
        setRegion(getFrame(delta));
    }
    
    protected void defineHuman() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(16/Horror.PPM,64/Horror.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);
        
        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(10 / Horror.PPM);
        fdef.filter.categoryBits = Horror.HUMAN_BIT;
        fdef.filter.maskBits = Horror.GROUND_BIT | 
                Horror.ROBOT_BIT |
                Horror.ENEMY_BIT |
                Horror.OBJECT_BIT |
                Horror.HOLE_BIT |
                Horror.SKY_BIT; 
        
        fdef.shape = shape;
        b2body.createFixture(fdef).setUserData(this);
    }
    
    
    public TextureRegion getFrame(float delta) {
        currentState = getState();
        
        TextureRegion region;
        switch(currentState){
            case JUMPING:
                region = humanStand;
                break;
            
            case RUNNING:
                region = (TextureRegion) humanWalking.getKeyFrame(stateTimer, true);
                break;
                
            case FALLING:
            case STANDING:
            default:
                region = humanStand;
                break;
        }
        
        if((b2body.getLinearVelocity().x < 0 || !runningRight) && !region.isFlipX()){
            region.flip(true, false);
            runningRight = false;
        }
        else if((b2body.getLinearVelocity().x > 0 || runningRight) && region.isFlipX()) {
            region.flip(true, false);
            runningRight = true;
        }
        
        stateTimer = currentState == previousState ? stateTimer + delta : 0;
        previousState = currentState;
        return region;
    }
    
    public void hit() {
        Hud.addLives(-1);
    }
    
    public State getState() {
        if(b2body.getLinearVelocity().y > 0 || 
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
    
    public void fall(){
        Hud.addLives(-1);
    }
}
