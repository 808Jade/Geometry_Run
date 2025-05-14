package kr.tuk.spgp.termproject.jade.geometryrun.game;


public class Obstacle extends MapObject {
    public Obstacle() {
        super(MainScene.Layer.obstacle);
    }

    public static Obstacle get(float left, float top) {
        return Scene.top().getRecyclable(Obstacle.class).init(left, top);
    }

    private Obstacle init(float left, float top) {
        bitmap = BitmapPool.get(R.mipmap.epn01_tm01_jp1a);
        float w = 63 * 1.25f, h = 99 * 1.25f;
        float hw = w / 2;
        dstRect.set(left + 50 - hw, top + 100 - h, left + 50 + hw, top + 100);
        return this;
    }
}