/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.Sprites.Misc;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Horror;
import com.mygdx.game.Sprites.Enemy.EnemyStandard;
import com.mygdx.game.Sprites.Robot;
import com.mygdx.game.screens.Level1Screen;

public class Bullet extends Sprite {
    public World world;
    public Body b2body;
    private TextureRegion bulletStand;
    private final int SPEED = 100;
    float x;
    float y;
    public boolean goingRight;
    Robot robot;
    private boolean setToDestroy;
    private boolean destroy;
    private EnemyStandard enemy;

    public Bullet(Level1Screen screen, Robot robot, float x, float y) {
        super(screen.getalAtlas().findRegion("bullet"));
        this.world = screen.getWorld();
        this.x = x;
        this.y = y;
        this.robot = robot;
        defineBullet();
        setToDestroy = false;
        destroy = false;

        bulletStand = new TextureRegion(getTexture(), 0, 0, 32, 9);
        setBounds(0, 0, 8 / Horror.PPM, 4 / Horror.PPM);
        setRegion(bulletStand);
        goingRight = robot.isRunningRight();
    }

    public void update(float dt) {
        
        if(setToDestroy && !destroy){
            world.destroyBody(b2body);
            destroy = true;
        } else {
            if(!destroy) {
                setPosition(b2body.getPosition().x - getWidth() / 2, 
                        b2body.getPosition().y - getHeight() / 2); 
            }
        }
        
        //x += SPEED * dt;
    }

    public boolean getRight() {
        return goingRight;
    }

    public void defineBullet() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(x, y);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(7 / Horror.PPM);
        fdef.filter.categoryBits = Horror.BULLET_BIT;
        fdef.filter.maskBits = Horror.GROUND_BIT | 
                Horror.ENEMY_BIT |
                Horror.HUMAN_BIT |
                Horror.OBJECT_BIT |
                Horror.SKY_BIT;

        fdef.shape = shape;
        b2body.createFixture(fdef).setUserData(this);
    }
    
    @Override
    public void draw(Batch batch){
        if(!destroy){
            super.draw(batch);
        }
    }
    
    
    public void hit(){
        setToDestroy=true;
    }
}

