package com.mygdx.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Horror;
import com.mygdx.game.Sprites.Robot;
import com.mygdx.game.screens.Level1Screen;

public class Bullet extends Sprite {
    public World world;
    public Body b2body;
    private TextureRegion bulletStand;
    float x;
    float y;
    public boolean goingRight;
    Robot robot;

    public Bullet(World world, Level1Screen screen, Robot robot, float x, float y) {
        super(screen.getalAtlas().findRegion("bullet"));
        this.world = world;
        this.x = x;
        this.y = y;
        this.robot = robot;
        defineBullet();

        bulletStand = new TextureRegion(getTexture(), 579, 698, 32, 9);
        setBounds(0, 0, 16 / Horror.PPM, 4 / Horror.PPM);
        setRegion(bulletStand);
        goingRight = robot.getRunning();
    }

    public void update(float dt) {
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
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

        fdef.shape = shape;
        b2body.createFixture(fdef);
    }
}
