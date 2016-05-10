package com.jeff.game.castlesmack.models.items;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.jeff.game.castlesmack.util.constant.Constants;
import com.jeff.game.castlesmack.util.data.MoveState;

import static com.jeff.game.castlesmack.util.constant.Constants.meterToPix;

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
        projectile.setPosition((float) (body.getPosition().x + (MathUtils.cosDeg(getAngleDeg()) * (height + 1.5))), (float) (body.getPosition().y + (MathUtils.sinDeg(getAngleDeg()) * (height + 1.5))));
        projectile.setVelocity((MathUtils.cosDeg(getAngleDeg()) * currentForce), (MathUtils.sinDeg(getAngleDeg()) * currentForce));
    }

    @Override
    public void setPosition(float x, float y) {
        body.setTransform(x, y - (height / 2), body.getTransform().getRotation());
    }

    public void rotate(MoveState moveState) {
        int mul;
        switch (moveState) {
            case POSITIVE:
                if (getAngleDeg() <= 10) {
                    mul = 0;
                } else {
                    mul = -1;
                }
                body.setAngularVelocity(mul * Constants.ROTATION_SPEED_CANNON);
                break;
            case NEGATIVE:
                if (getAngleDeg() >= 170) {
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

    public void force(MoveState forceState) {
        switch (forceState) {
            case POSITIVE: {
                if (currentForce <= maxForce) {
                    float smallest = Constants.FORCE_INCREASE_SPEED * Gdx.graphics.getDeltaTime();
                    currentForce += smallest;
                }
                break;
            }
            case NEGATIVE: {
                if (currentForce >= 0) {
                    float smallest = Constants.FORCE_INCREASE_SPEED * Gdx.graphics.getDeltaTime();
                    currentForce -= smallest;
                }
                break;
            }
        }
    }

    @Override
    public void draw(Batch batch) {
        Vector2 pos = body.getPosition();
        final float pixWidth = meterToPix(width);
        final float pixHeight = meterToPix(height);
        oX = pixWidth / 2.0f;
        oY = 0;
        batch.draw(texture, meterToPix(pos.x) - (pixWidth / 2.0f),
                meterToPix(pos.y),
                oX, oY, pixWidth, pixHeight, 1, 1, body.getAngle() * MathUtils.radDeg);
    }
}
