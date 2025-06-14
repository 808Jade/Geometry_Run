package kr.tuk.spgp.termproject.jade.geometryrun.game;

import kr.ac.tukorea.ge.spgp2025.a2dg.framework.res.BitmapPool;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.scene.Scene;
import kr.tuk.spgp.termproject.jade.geometryrun.R;

public class Obstacle extends MapObject {
    public Obstacle() { }
    private Obstacle init(float left, float top) {
        bitmap = BitmapPool.get(R.mipmap.spike_basic);
        width = 100;
        height = 100;
        dstRect.set(left, top, left + width, top + height);
        return this;
    }
    @Override
    public MainScene.Layer getLayer() {
        return MainScene.Layer.obstacle;
    }
    public static Obstacle get(float left, float top) {
        return Scene.top().getRecyclable(Obstacle.class).init(left, top);
        //return new Obstacle().init(left, top);
    }
}