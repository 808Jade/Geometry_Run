package kr.tuk.spgp.termproject.jade.geometryrun.game;

import android.graphics.Canvas;

import java.util.Random;

import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.GameView;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;

public class MapLoader implements IGameObject {
    private final MainScene scene;
    private final Random random = new Random();
    private float floor_x, item_x;
    public MapLoader(MainScene mainScene) {
        this.scene = mainScene;
    }

    @Override
    public void update() {
        floor_x += -200.0f * GameView.frameTime;
        while (floor_x < Metrics.width) {
        }

        item_x += -200.0f * GameView.frameTime;
        while (item_x < Metrics.width) {
        }
    }

    @Override
    public void draw(Canvas canvas) {}
}