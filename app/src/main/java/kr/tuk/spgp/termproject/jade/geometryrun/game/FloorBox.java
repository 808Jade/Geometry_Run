package kr.tuk.spgp.termproject.jade.geometryrun.game;

import android.graphics.Bitmap;
import android.graphics.RectF;

import kr.ac.tukorea.ge.spgp2025.a2dg.framework.res.BitmapPool;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.scene.Scene;
import kr.tuk.spgp.termproject.jade.geometryrun.R;

public class FloorBox extends MapObject{
    public enum Type {
        T_1x1;
        Bitmap bitmap() { return BitmapPool.get(resId()); }
        int resId() { return resIds[this.ordinal()]; }
        int width() { return sizes[this.ordinal()][0]; }
        int height() { return sizes[this.ordinal()][1]; }
        public static final int COUNT = values().length;
        static final int[] resIds = {
                R.mipmap.block_basic
        };
        static final int[][] sizes = {
                { 100, 100 }
        };
    }
    public FloorBox() {
        collisionRect = new RectF();
    }

    private FloorBox init(Type type,float left, float top) {
        bitmap = type.bitmap();
        width = type.width();
        height = type.height();
        dstRect.set(left, top, left + width, top + height);
        return this;
    }

    public static FloorBox get(Type type, float left, float top) {
        //return new FloorBox().init(type, left, top);
        return Scene.top().getRecyclable(FloorBox.class).init(type, left, top);
    }

    @Override
    public MainScene.Layer getLayer() {
        return MainScene.Layer.floorbox;
    }
    @Override
    public void update() {
        super.update();
        updateCollisionRect(0.15f);
    }
    @Override
    public RectF getCollisionRect() {
        return collisionRect;
    }

}
