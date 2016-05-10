package com.jeff.game.castlesmack.system;

import com.badlogic.gdx.physics.box2d.World;
import com.jeff.game.castlesmack.models.gameplay.Player;
import com.jeff.game.castlesmack.util.constant.Constants;
import com.jeff.game.castlesmack.util.data.ThreadLocalRandom;
import com.jeff.game.castlesmack.util.data.UiInfo;

public class GameManager {
    private World world;
    private Player[] players;
    private State state;
    private boolean switchedStates;

    public GameManager(World world, Player player1, Player player2) {
        // Set the world
        this.world = world;
        // Set the players
        this.players = new Player[2];
        players[0] = player1;
        players[1] = player2;
        // Set the initial game state
        this.state = State.BETWEEN_TURN;
        switchedStates = true;
    }

    public void checkCollisions() {

    }

    public void update(float delta) {
        switch (state) {
            case BETWEEN_TURN:
                betweenTurn(delta);
                break;
            case IN_TURN:
                switchedStates = true;
                inTurn(delta);
                break;
        }
    }

    private void betweenTurn(float delta) {
        // Move the islands
        if (switchedStates) {
            for (Player player : players) {
                player.houseIsland.setDestination(ThreadLocalRandom.nextInt(0, (int) Constants.HEIGHT_SCREEN));
            }
            switchedStates = false;
        }

        // Check if the islands have reached their destination
        if (players[0].houseIsland.hasReachedDestination() && players[1].houseIsland.hasReachedDestination()) {
            state = state.getNextState();
        }
    }

    private void inTurn(float delta) {
        // Move the cannon
        // Shoot
    }

    public void updateUiP1Info(UiInfo info) {
        updateUiPlayerInfo(info, players[0]);
    }

    public void updateUiP2Info(UiInfo info) {
        updateUiPlayerInfo(info, players[1]);
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

    private enum State {
        BETWEEN_TURN,
        IN_TURN;

        public State getNextState() {
            int ordinal = this.ordinal() + 1;
            if (ordinal == values().length) {
                return values()[0];
            } else {
                return values()[ordinal];
            }
        }
    }
}
