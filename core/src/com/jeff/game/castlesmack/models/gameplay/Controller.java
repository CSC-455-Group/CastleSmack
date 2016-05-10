package com.jeff.game.castlesmack.models.gameplay;


import com.jeff.game.castlesmack.util.data.MoveState;
import com.jeff.game.castlesmack.util.data.TurnInfo;

public abstract class Controller {

    public boolean shoot;
    public MoveState cannonMoveState;
    public MoveState cannonForceState;
    public final Player player;

    public Controller(Player player) {
        this.player = player;
        this.cannonMoveState = MoveState.NEUTRAL;
    }

    public final void turnStart() {
        shoot = false;
        cannonMoveState = MoveState.NEUTRAL;
        processTurn();
    }

    protected abstract void processTurn();

    public abstract void update();

}
