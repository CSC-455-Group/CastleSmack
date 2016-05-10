package com.jeff.game.castlesmack.models.gameplay;


import com.badlogic.gdx.math.MathUtils;
import com.jeff.game.castlesmack.util.data.MoveState;
import com.jeff.game.castlesmack.util.data.ThreadLocalRandom;

public class AI extends Controller {

    private float force;
    private float targAngle;

    public AI(Player player) {
        super(player);
    }

    @Override
    protected void processTurn() {
        force = ThreadLocalRandom.nextInt(3, 25);
        targAngle = ThreadLocalRandom.nextInt(90, 180);
    }

    @Override
    public void update() {
        float angle = getAngleDeg();

        if (Math.abs(angle - targAngle) > 5) {
            if (angle > targAngle) {
                cannonMoveState = MoveState.POSITIVE;
            } else {
                cannonMoveState = MoveState.NEGATIVE;
            }
        } else {
            cannonMoveState = MoveState.NEUTRAL;
            other();
        }
    }

    private void other() {
        cannonMoveState = MoveState.NEUTRAL;
        float curForce = player.cannon.currentForce;
        if (Math.abs(curForce - force) > 5) {
            if (curForce > force) {
                cannonForceState = MoveState.NEGATIVE;
            } else {
                cannonForceState = MoveState.POSITIVE;
            }
        } else {
            cannonForceState = MoveState.NEUTRAL;
            shoot = true;
        }
    }

    private float getAngleDeg() {
        float angle = player.cannon.body.getTransform().getRotation() * MathUtils.radiansToDegrees;
        angle += 90;
        if (angle < 0) {
            angle = 360 - Math.abs(angle);
        }
        return angle;
    }

}
