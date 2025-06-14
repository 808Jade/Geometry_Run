package kr.tuk.spgp.termproject.jade.geometryrun.game;


import android.graphics.Rect;

import kr.ac.tukorea.ge.spgp2025.a2dg.framework.res.BitmapPool;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.scene.Scene;
import kr.tuk.spgp.termproject.jade.geometryrun.R;

public class Obstacle extends MapObject {
    public static final int OBSTACLE_COUNT = 60;
    private static final int OBSTACLES_IN_A_ROW = 30;
    private static final int SIZE = 66;
    private static final int BORDER = 2;

    public Obstacle() {
        bitmap = BitmapPool.get(R.mipmap.spike_basic);
        srcRect = new Rect();
        width = height = 100;
    }
    private Obstacle init(int index, float left, float top) {
        setSrcRect(index);
        dstRect.set(left, top, left + width, top + height);

//        bitmap = BitmapPool.get(R.mipmap.spike_basic);
//        float w = 63 * 1.25f, h = 99 * 1.25f;
//        float hw = w / 2;
//        dstRect.set(left + 50 - hw, top + 100 - h, left + 50 + hw, top + 100);
        return this;
    }
    @Override
    public MainScene.Layer getLayer() {
        return MainScene.Layer.obstacle;
    }

    public static Obstacle get(int index, float left, float top) {
        return Scene.top().getRecyclable(Obstacle.class).init(index, left, top);
    }

    private void setSrcRect(int index) {
        int x = index % OBSTACLES_IN_A_ROW;
        int y = index / OBSTACLES_IN_A_ROW;
        int left = x * (SIZE + BORDER) + BORDER;
        int top = y * (SIZE + BORDER) + BORDER;
        srcRect.set(left, top, left + SIZE, top + SIZE);
    }
}