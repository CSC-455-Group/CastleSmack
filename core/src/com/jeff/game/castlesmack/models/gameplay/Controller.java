package com.jeff.game.castlesmack.models.gameplay;


import com.jeff.game.castlesmack.util.data.MoveState;
import com.jeff.game.castlesmack.util.data.TurnInfo;

public abstract class Controller {

    public boolean shoot;
    public MoveState cannonMoveState;
    public final Player player;

    public Controller(Player player) {
        this.player = player;
    }

    public final void turnStart(TurnInfo info) {
        shoot = false;
        cannonMoveState = MoveState.NEUTRAL;
        processTurn(info);
    }

    protected abstract void processTurn(TurnInfo info);

    public abstract void update();

}
