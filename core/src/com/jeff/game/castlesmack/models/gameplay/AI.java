package com.jeff.game.castlesmack.models.gameplay;


import com.badlogic.gdx.math.MathUtils;
import com.jeff.game.castlesmack.util.constant.Constants;
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
        force = ThreadLocalRandom.nextInt(0, (int) Constants.PLAYER_GUN_MAX_FORCE_START);
        targAngle = ThreadLocalRandom.nextInt(0, 90);
    }

    @Override
    public void update() {

        float angle = player.cannon.body.getAngle() * MathUtils.degRad;
        if (Math.abs(angle - targAngle) > 5) {
            if (angle > targAngle) {
                cannonMoveState = MoveState.NEGATIVE;
            } else {
                cannonMoveState = MoveState.POSITIVE;
            }
        } else {
            cannonMoveState = MoveState.NEUTRAL;
            float curForce = player.cannon.currentForce;
            if (Math.abs(curForce - force) > 5) {
                if (curForce > force) {
                    cannonForceState = MoveState.NEGATIVE;
                } else {
                    cannonForceState = MoveState.POSITIVE;
                }
            } else {
                shoot = true;
            }
        }

    }
}
