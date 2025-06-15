package kr.tuk.spgp.termproject.jade.geometryrun.game;

import kr.ac.tukorea.ge.spgp2025.a2dg.framework.res.BitmapPool;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.scene.Scene;
import kr.tuk.spgp.termproject.jade.geometryrun.R;

public class AnimObstacle extends Obstacle {
    public static Obstacle get(int type, float left, float top) {
        return Scene.top().getRecyclable(AnimObstacle.class).init(type, left, top);
    }

    protected static int[] RES_IDS = {
            R.mipmap.spike_basic
    };
    private Obstacle init(int type, float left, float top) {
        bitmap = BitmapPool.get(RES_IDS[type]);
        setObstaclePosition(left, top);
        return this;
    }
}