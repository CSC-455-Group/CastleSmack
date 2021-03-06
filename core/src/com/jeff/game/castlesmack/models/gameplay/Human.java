package com.jeff.game.castlesmack.models.gameplay;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.jeff.game.castlesmack.util.data.MoveState;

public class Human extends Controller {

    public Human(Player player) {
        super(player);
    }

    @Override
    protected void processTurn() {

    }

    @Override
    public void update() {

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            cannonMoveState = MoveState.POSITIVE;
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            cannonMoveState = MoveState.NEGATIVE;
        } else {
            cannonMoveState = MoveState.NEUTRAL;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            cannonForceState = MoveState.POSITIVE;
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            cannonForceState = MoveState.NEGATIVE;
        } else {
            cannonForceState = MoveState.NEUTRAL;
        }

        shoot = Gdx.input.isKeyPressed(Input.Keys.SPACE);

    }
}
