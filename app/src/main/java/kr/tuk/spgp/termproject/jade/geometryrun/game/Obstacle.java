package kr.tuk.spgp.termproject.jade.geometryrun.game;


import android.graphics.RectF;

import kr.ac.tukorea.ge.spgp2025.a2dg.framework.res.BitmapPool;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.scene.Scene;
import kr.tuk.spgp.termproject.jade.geometryrun.R;

public class Obstacle extends MapObject {
    public Obstacle() { }

    @Override
    public MainScene.Layer getLayer() {
        return MainScene.Layer.obstacle;
    }

    public static Obstacle get(float left, float top) {
        return Scene.top().getRecyclable(Obstacle.class).init(left, top);
    }

    private Obstacle init(float left, float top) {
        bitmap = BitmapPool.get(R.mipmap.spike_basic);
        float w = 63 * 1.25f, h = 99 * 1.25f;
        float hw = w / 2;
        dstRect.set(left + 50 - hw, top + 100 - h, left + 50 + hw, top + 100);
        return this;
    }
}