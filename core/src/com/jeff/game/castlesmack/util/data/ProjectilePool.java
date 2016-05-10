package com.jeff.game.castlesmack.util.data;


import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;
import com.jeff.game.castlesmack.models.items.Projectile;
import com.jeff.game.castlesmack.util.constant.Constants;

import java.util.ArrayList;

public class ProjectilePool {

    private final ArrayList<Projectile> pool = new ArrayList<Projectile>();
    private final World world;
    private final TextureRegion rockTex;

    public ProjectilePool(World world, TextureRegion rockTex) {
        final int initCount = 5;
        this.world = world;
        this.rockTex = rockTex;
        for (int i = 0; i < initCount; i++) {
            Projectile projectile = makeProjectile();
            projectile.body.setActive(false);
            pool.add(projectile);
        }
    }

    public Projectile free() {
        if (pool.isEmpty()) {
            return makeProjectile();
        } else {
            return pool.remove(0);
        }
    }

    public void poolUp(Projectile projectile) {
        projectile.body.setActive(false);
        pool.add(projectile);
    }

    private Projectile makeProjectile() {
        return new Projectile(world, 0, 0, 4, 4, rockTex, Constants.PLAYER_PROJECTILE_DAMAGE_START, Constants.PLAYER_PROJECTILE_WEIGHT);
    }

}
