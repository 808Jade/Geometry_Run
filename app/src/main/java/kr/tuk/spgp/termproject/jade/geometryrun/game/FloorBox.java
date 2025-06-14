package kr.tuk.spgp.termproject.jade.geometryrun.game;

import android.graphics.Bitmap;
import android.graphics.RectF;

import kr.ac.tukorea.ge.spgp2025.a2dg.framework.res.BitmapPool;
import kr.tuk.spgp.termproject.jade.geometryrun.R;

public class FloorBox extends MapObject{
    public enum Type {
        T_1x1;
        Bitmap bitmap() { return BitmapPool.get(resId()); }
        int resId() { return resIds[this.ordinal()]; }
        int width() { return sizes[this.ordinal()][0]; }
        int height() { return sizes[this.ordinal()][1]; }
        static final int[] resIds = {
                R.mipmap.floorbox
        };
        static final int[][] sizes = {
                { 100, 100 }
        };
    }
    public FloorBox() { }

    private FloorBox init(Type type,float left, float top) {
        bitmap = type.bitmap();
        width = 100;
        height = 100;
        dstRect.set(left, top, left + width, top + height);
        return this;
    }

    public static FloorBox get(Type type, float left, float top) {
        return new FloorBox().init(type, left, top);
    }

    @Override
    public MainScene.Layer getLayer() {
        return MainScene.Layer.floorbox;
    }

    //@Override
    public RectF getCollisionRect() {
        return null;
    }
}
