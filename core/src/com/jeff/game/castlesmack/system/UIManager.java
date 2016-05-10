package com.jeff.game.castlesmack.system;


import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import com.jeff.game.castlesmack.util.constant.Constants;
import com.jeff.game.castlesmack.util.data.UiInfo;

import static com.jeff.game.castlesmack.util.constant.Constants.meterToPix;

public class UIManager implements Disposable {

    public final UiInfo p1Info = new UiInfo();
    public final UiInfo p2Info = new UiInfo();
    private final BitmapFont font = new BitmapFont();

    private static final float HOUSE_Y_OFF = (Constants.HEIGHT_PLAYER_HOUSE / 2.0f) + 3;
    private static final float HOUSE_X_OFF = (Constants.WIDTH_PLAYER_HOUSE / 2.0f);
    private static final float GUN_Y_OFF = (Constants.HEIGHT_PLAYER_GUN / 2.0f) + 3;
    private static final float GUN_X_OFF = Constants.WIDTH_PLAYER_GUN / 2.0f;
    private static final float P1_FORCE_X_OFF = 5;
    private static final float P2_FORCE_X_OFF = -5;

    public UIManager() {

    }

    public void draw(SpriteBatch batch) {
        draw(p1Info, P1_FORCE_X_OFF, batch);
        draw(p2Info, P2_FORCE_X_OFF, batch);
    }

    private void draw(UiInfo uiInfo, float forceOff, SpriteBatch batch) {
        font.draw(batch, String.format("HP:%s/%s", uiInfo.houseHp, uiInfo.houseMaxHp),
                meterToPix(uiInfo.housePos.x - HOUSE_X_OFF), meterToPix(uiInfo.housePos.y + HOUSE_Y_OFF));
        font.draw(batch, String.format("HP::%s/%s", uiInfo.cannonHp, uiInfo.cannonMaxHp),
                meterToPix(uiInfo.cannonPos.x - GUN_X_OFF), meterToPix(uiInfo.cannonPos.y + GUN_Y_OFF));
        font.draw(batch, String.format("%s/%s", uiInfo.cannonForce, uiInfo.cannonMaxForce),
                meterToPix(uiInfo.cannonPos.x + forceOff), meterToPix(uiInfo.cannonPos.y - (GUN_Y_OFF / 2.0f)));
    }

    @Override
    public void dispose() {
        font.dispose();
    }

}
