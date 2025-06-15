package kr.tuk.spgp.termproject.jade.geometryrun.game;

import android.graphics.RectF;
import android.util.Log;

import kr.ac.tukorea.ge.spgp2025.a2dg.framework.res.BitmapPool;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.scene.Scene;
import kr.tuk.spgp.termproject.jade.geometryrun.R;

public class Obstacle extends MapObject {
    protected static final float IMAGE_SIZE_RATIO = 1.25f;
    public Obstacle() {
        super(MainScene.Layer.obstacle);
    }
    private Obstacle init(float left, float top) {
        bitmap = BitmapPool.get(R.mipmap.spike_basic);
        width = 100;
        height = 100;
        dstRect.set(left, top, left + width, top + height);
        collisionRect = new RectF();
        return this;
    }
    @Override
    public MainScene.Layer getLayer() {
        return MainScene.Layer.obstacle;
    }

    @Override
    public void update() {
        super.update();
        updateCollisionRect(0.30f);

    }
    public static Obstacle get(float left, float top) {
        return Scene.top().getRecyclable(Obstacle.class).init(left, top);
        //return new Obstacle().init(left, top);
    }

    protected void setObstaclePosition(float left, float top) {
//        float cx = left + 50, bottom = top + 100;
//        float half_w = bitmap.getWidth() * IMAGE_SIZE_RATIO / 2;
//        float h = bitmap.getHeight() * IMAGE_SIZE_RATIO;
//        dstRect.set(cx - half_w, bottom - h, cx + half_w, bottom);
        //Log.d("Obs", "dstRect="+dstRect);
    }

    @Override
    public RectF getCollisionRect() {
        return collisionRect;
    }
}