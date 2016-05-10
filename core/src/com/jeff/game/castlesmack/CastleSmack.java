package com.jeff.game.castlesmack;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.jeff.game.castlesmack.models.gameplay.AI;
import com.jeff.game.castlesmack.models.gameplay.Controller;
import com.jeff.game.castlesmack.models.gameplay.Human;
import com.jeff.game.castlesmack.models.gameplay.Player;
import com.jeff.game.castlesmack.models.items.Entity;
import com.jeff.game.castlesmack.models.items.Island;
import com.jeff.game.castlesmack.system.GameManager;
import com.jeff.game.castlesmack.system.UIManager;
import com.jeff.game.castlesmack.util.builders.GameBuilder;
import com.jeff.game.castlesmack.util.constant.Constants;
import com.jeff.game.castlesmack.util.constant.Constants.TexConstants;
import com.jeff.game.castlesmack.util.data.Pair;


public class CastleSmack extends ApplicationAdapter {

    private World world;
    private Box2DDebugRenderer renderer;
    private Matrix4 debugMatrix;
    private AssetManager manager;
    private GameManager gameManager;
    private UIManager uiManager;
    private Array<Entity> entities = new Array<Entity>(false, 10);
    private SpriteBatch batch;

    @Override

    public void create() {
        manager = new AssetManager();
        batch = new SpriteBatch();
        uiManager = new UIManager();
        OrthographicCamera camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        world = new World(new Vector2(0, 9.81f), true);
        debugMatrix = new Matrix4(camera.combined);
        debugMatrix.scale(Constants.M_TO_PIX, Constants.M_TO_PIX, 1f);
        renderer = new Box2DDebugRenderer();

        manager.load(TexConstants.HOUSE, Texture.class);
        manager.load(TexConstants.ISLAND, Texture.class);
        manager.load(TexConstants.PIPE, Texture.class);
        manager.load(TexConstants.ROCK, Texture.class);

        manager.finishLoading();

        ObjectMap<String, Texture> map = new ObjectMap<String, Texture>();
        map.put(TexConstants.HOUSE, manager.get(TexConstants.HOUSE, Texture.class));
        map.put(TexConstants.ISLAND, manager.get(TexConstants.ISLAND, Texture.class));
        map.put(TexConstants.PIPE, manager.get(TexConstants.PIPE, Texture.class));
        map.put(TexConstants.ROCK, manager.get(TexConstants.ROCK, Texture.class));

        GameBuilder builder = new GameBuilder(world, map);
        Pair<Island, Island> p1Islands = builder.makeHouseGunIslands(new Pair<Vector2, Vector2>(
                new Vector2(9, 18),
                new Vector2(21, 18)
        ));
        Pair<Island, Island> p2Islands = builder.makeHouseGunIslands(new Pair<Vector2, Vector2>(
                new Vector2(91, 18),
                new Vector2(79, 18)
        ));

        Controller c1 = new Human();
        Controller c2 = new AI();

        Array<Island> islands = new Array<Island>();

        Island i1 = builder.makeIsland(41, 18, 8, 6.1f, true);
        Island i2 = builder.makeIsland(58, 18, 3, 5.6f, true);

        islands.addAll(
                p1Islands._1,
                p1Islands._2,
                p2Islands._1,
                p2Islands._2,
                i1, i2);

        Player player1 = builder.makePlayer(p1Islands, c1, 0);
        Player player2 = builder.makePlayer(p2Islands, c2, 1);
        gameManager = new GameManager(world, player1, player2, islands);

        entities.addAll(
                p1Islands._1,
                p1Islands._2,
                p2Islands._1,
                p2Islands._2,
                player1.cannon,
                player1.house,
                player2.cannon,
                player2.house
        );

    }

    @Override
    public void render() {
        update();
        draw();
    }

    private void update() {
        world.step(1 / 60f, 10, 8);
        gameManager.checkCollisions();
        gameManager.update(Gdx.graphics.getDeltaTime());
        gameManager.updateUiP1Info(uiManager.p1Info);
        gameManager.updateUiP2Info(uiManager.p2Info);
    }

    private void draw() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.render(world, debugMatrix);

        /*batch.begin();
        for (int i = 0; i < entities.size; i++) {
            Entity entity = entities.get(i);
            if (entity.body.isActive()) {
                entity.draw(batch);
            }
        }
        batch.end();*/

        batch.begin();
        uiManager.draw(batch);
        batch.end();

    }

    @Override
    public void dispose() {
        super.dispose();
        manager.dispose();
        renderer.dispose();
        world.dispose();
        uiManager.dispose();
    }
}
