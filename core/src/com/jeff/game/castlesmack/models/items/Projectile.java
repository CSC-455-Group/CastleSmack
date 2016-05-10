package com.jeff.game.castlesmack.models.items;


import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.jeff.game.castlesmack.util.builders.BodyBuilder;

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

    @Override
    protected Body createBody(World world, float x, float y, float width, float height) {
        return BodyBuilder.rectangleBody(world, width, height, x, y, BodyDef.BodyType.DynamicBody);
    }
}
