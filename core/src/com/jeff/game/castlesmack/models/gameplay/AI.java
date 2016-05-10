package com.jeff.game.castlesmack.models.gameplay;


import com.jeff.game.castlesmack.util.data.TurnInfo;

public class AI extends Controller {

    public AI(Player player) {
        super(player);
    }

    @Override
    protected void processTurn(TurnInfo info) {

    }

    @Override
    public void update() {
        shoot = true;
    }
}
