package com.jeff.game.castlesmack.models.items;


import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

import static com.jeff.game.castlesmack.util.constant.Constants.meterToPix;

public abstract class Entity {

    protected float oX;
    protected float oY;

    public final TextureRegion texture;
    public final Body body;

    public final float width;
    public final float height;

    public Entity(World world, float x, float y, float width, float height, TextureRegion texture) {
        this.texture = texture;
        this.width = width;
        this.height = height;
        oX = width / 2.0f;
        oY = height / 2.0f;
        body = createBody(world, x, y, width, height);
        body.setUserData(this);
    }

    protected abstract Body createBody(World world, float x, float y, float width, float height);

    public void draw(Batch batch) {
        Vector2 pos = body.getPosition();
        final float pixWidth = meterToPix(width);
        final float pixHeight = meterToPix(height);
        batch.draw(texture, meterToPix(pos.x) - (pixWidth / 2.0f),
                meterToPix(pos.y) - (pixHeight / 2.0f),
                oX, oY, pixWidth, pixHeight, 1, 1, body.getAngle() * MathUtils.radDeg);
    }

    public void setPosition(float x, float y) {
        body.setTransform(x, y, body.getTransform().getRotation());
    }
}
