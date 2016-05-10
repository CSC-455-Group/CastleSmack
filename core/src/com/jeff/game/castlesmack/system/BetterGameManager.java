package com.jeff.game.castlesmack.system;


import com.badlogic.gdx.physics.box2d.World;
import com.jeff.game.castlesmack.models.gameplay.Player;
import com.jeff.game.castlesmack.util.data.UiInfo;

public class BetterGameManager {

    private final Player p1, p2;

    public BetterGameManager(World world, Player p1, Player p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public void checkCollisions() {

    }

    public void update(float delta) {

    }

    public void updateUiP1Info(UiInfo info) {
        updateUiPlayerInfo(info, p1);
    }

    public void updateUiP2Info(UiInfo info) {
        updateUiPlayerInfo(info, p2);
    }

    private void updateUiPlayerInfo(UiInfo info, Player player) {
        info.houseHp = player.house.currentHP;
        info.houseMaxHp = player.house.maxHP;
        info.housePos = player.house.body.getPosition();
        info.cannonHp = player.cannon.currentHP;
        info.cannonMaxHp = player.cannon.maxHP;
        info.cannonForce = player.cannon.currentForce;
        info.cannonMaxForce = player.cannon.maxForce;
        info.cannonPos = player.cannon.body.getPosition();
    }


}
