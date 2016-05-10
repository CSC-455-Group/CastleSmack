package com.jeff.game.castlesmack.models.gameplay;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.jeff.game.castlesmack.util.data.MoveState;
import com.jeff.game.castlesmack.util.data.TurnInfo;

public class Human extends Controller {

    public Human(Player player) {
        super(player);
    }

    @Override
    protected void processTurn(TurnInfo info) {

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

        shoot = Gdx.input.isKeyPressed(Input.Keys.SPACE);

    }
}
