package com.jeff.game.castlesmack.models.items;


import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.*;
import com.jeff.game.castlesmack.util.builders.BodyBuilder;

import static com.jeff.game.castlesmack.util.constant.Constants.VELOCITY_ISLAND;

public class Island extends Entity {

    public final boolean relocates;

    private float destination;
    private Entity entity;

    public Island(World world, float x, float y, float width, float height, TextureRegion texture, boolean relocates) {
        super(world, x, y, width, height, texture);
        this.relocates = relocates;
    }

    @Override
    protected Body createBody(World world, float x, float y, float width, float height) {
        return BodyBuilder.rectangleBody(world, width, height, x, y, BodyDef.BodyType.KinematicBody);
    }

    public void setDestination(float destination) {
        // Set the destination
        this.destination = destination;
        // Get the current y position
        float y = body.getTransform().getPosition().y;
        // Set the velocity toward the destination
        body.setLinearVelocity(0f, (destination > y ? 1 : destination < y ? -1 : 0) * VELOCITY_ISLAND);
    }

    public boolean hasReachedDestination() {
        // Update the entity position that's on the island
        if(entity != null) {
            entity.setPosition(body.getTransform().getPosition().x, body.getTransform().getPosition().y + (height / 2) + (entity.height / 2));
        }
        // Check if the island has reached its destination
        if(((destination - body.getTransform().getPosition().y) * (body.getLinearVelocity().y / Math.abs(body.getLinearVelocity().y))) <= 0 || body.getLinearVelocity().y == 0f) {
            // Destination has been reached, stop moving
            body.setLinearVelocity(0f, 0f);
            return true;
        } else {
            return false;
        }
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public Entity getEntity() {
        return entity;
    }
}
