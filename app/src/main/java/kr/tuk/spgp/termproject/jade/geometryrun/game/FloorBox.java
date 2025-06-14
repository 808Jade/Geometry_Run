package kr.tuk.spgp.termproject.jade.geometryrun.game;

import android.graphics.RectF;

public class FloorBox extends MapObject{
    public FloorBox() {
        // super(R.mipmap.floorbox);
    }

    private FloorBox init(float left, float top) {

        return this;
    }

    public static FloorBox get(float left, float top) {
        return new FloorBox().init(left, top);
    }

    @Override
    public MainScene.Layer getLayer() {
        return MainScene.Layer.floorbox;
    }

    @Override
    public RectF getCollisionRect() {
        return null;
    }
}
