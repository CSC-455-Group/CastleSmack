package com.jeff.game.castlesmack.system;

import com.badlogic.gdx.physics.box2d.World;
import com.jeff.game.castlesmack.models.gameplay.Player;
import com.jeff.game.castlesmack.util.constant.Constants;
import com.jeff.game.castlesmack.util.data.ThreadLocalRandom;

public class BetterGameManager {
    private World world;
    private Player[] players;
    private State state;
    private boolean switchedStates;

    public BetterGameManager(World world, Player player1, Player player2) {
        // Set the world
        this.world = world;
        // Set the players
        this.players = new Player[2];
        players[0] = player1;
        players[1] = player2;
        // Set the initial game state
        this.state = State.BETWEEN_TURN;
    }

    public void checkCollisions() {

    }

    public void update(float delta) {
        switch(state) {
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
        if(switchedStates) {
            for (Player player : players) {
                player.houseIsland.setDestination(ThreadLocalRandom.nextInt(0, (int) Constants.HEIGHT_SCREEN));
            }
            switchedStates = false;
        }

        // Check if the islands have reached their destination
        if(players[0].houseIsland.hasReachedDestination() && players[1].houseIsland.hasReachedDestination()) {
            state = state.getNextState();
        }
    }

    private void inTurn(float delta) {
        // Move the cannon
        // Shoot
    }

    private enum State {
        BETWEEN_TURN,
        IN_TURN;

        public State getNextState() {
            int ordinal = this.ordinal() + 1;
            if(ordinal == values().length) {
                return values()[0];
            } else {
                return values()[ordinal];
            }
        }
    }
}
