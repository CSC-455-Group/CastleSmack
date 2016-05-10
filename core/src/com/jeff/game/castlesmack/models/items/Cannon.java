package com.jeff.game.castlesmack.models.items;


import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.jeff.game.castlesmack.util.constant.Constants;
import com.jeff.game.castlesmack.util.data.MoveState;

public class Cannon extends DamageAbleEntity {

    public final int playerID;

    public float maxForce;
    public float currentForce;

    public Cannon(World world, float x, float y, float width, float height, TextureRegion texture, float maxHP, float maxForce, int playerID) {
        super(world, x, y, width, height, texture, maxHP);
        this.maxForce = maxForce;
        this.playerID = playerID;
    }

    @Override
    protected Body createBody(World world, float x, float y, float width, float height) {
        FixtureDef def = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        Vector2[] vertices = new Vector2[4];
        vertices[0] = new Vector2(-1 * (width / 2), 0);
        vertices[1] = new Vector2((width / 2), 0);
        vertices[2] = new Vector2(-1 * (width / 2), height);
        vertices[3] = new Vector2((width / 2), height);
        shape.set(vertices);
        def.shape = shape;

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.position.set(x, y - (height / 2));

        Body body = world.createBody(bodyDef);
        body.createFixture(def);
        shape.dispose();
        return body;
    }

    public void shootProjectile(Projectile projectile) {

    }

    @Override
    public void setPosition(float x, float y) {
        body.setTransform(x, y - (height / 2), body.getTransform().getRotation());
    }

    public void rotate(MoveState moveState) {
        int mul;
        switch (moveState) {
            case POSITIVE:
                if (body.getTransform().getRotation() <= -1.54) {
                    mul = 0;
                } else {
                    mul = -1;
                }
                body.setAngularVelocity(mul * Constants.ROTATION_SPEED_CANNON);
                break;
            case NEGATIVE:
                if (body.getTransform().getRotation() >= 1.54) {
                    mul = 0;
                } else {
                    mul = 1;
                }
                body.setAngularVelocity(mul * Constants.ROTATION_SPEED_CANNON);
                break;
            case NEUTRAL:
                body.setAngularVelocity(0f);
                break;
        }

    }
}
