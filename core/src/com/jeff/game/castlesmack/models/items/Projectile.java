package com.jeff.game.castlesmack.models.items;


import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.jeff.game.castlesmack.util.builders.BodyBuilder;
import com.jeff.game.castlesmack.util.constant.Constants;

public class Projectile extends Entity {

    public float damage;
    private float weight;
    public boolean collided = false;

    public Projectile(World world, float x, float y, float width, float height, TextureRegion texture, float damage, float weight) {
        super(world, x, y, width, height, texture);
        this.damage = damage;
        this.weight = weight;
    }

    public boolean isActive() {
        return body.isActive();
    }

    public void setActive(boolean flag) {
        body.setActive(flag);
    }

    public void setVelocity(float x, float y) {
        body.setLinearVelocity(x, y);
    }

    public boolean isOffScreen() {
        float x = body.getTransform().getPosition().x;
        float y = body.getTransform().getPosition().y;

        // Check if the projectile is off screen
        if(x < 0 || x > Constants.WIDTH_SCREEN || y < 0 || y > Constants.HEIGHT_SCREEN + 100) {
            setActive(false);
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected Body createBody(World world, float x, float y, float width, float height) {
        return BodyBuilder.circleBody(world, width, x, y, BodyDef.BodyType.DynamicBody);
    }
}
