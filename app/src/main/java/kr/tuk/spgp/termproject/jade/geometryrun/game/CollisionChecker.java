package kr.tuk.spgp.termproject.jade.geometryrun.game;

import android.graphics.Canvas;

import java.util.ArrayList;

import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.util.CollisionHelper;

public class CollisionChecker implements IGameObject {
    private final MainScene scene;
    private final Player player;

    public CollisionChecker(MainScene mainScene, Player player) {
        this.scene = mainScene;
        this.player = player;
    }

    @Override
    public void update() {
        ArrayList<IGameObject> floorboxes = scene.objectsAt(MainScene.Layer.floorbox);
        for (int i = floorboxes.size() - 1; i >= 0; i--) {
            IGameObject gobj = floorboxes.get(i);
            if (!(gobj instanceof FloorBox)) {
                continue;
            }
            FloorBox item = (FloorBox) gobj;
            if (CollisionHelper.collides(player, item)) {
                scene.remove(item);
            }
        }
        ArrayList<IGameObject> obstacles = scene.objectsAt(MainScene.Layer.obstacle);
        for (int i = obstacles.size() - 1; i >= 0; i--) {
            Obstacle obstacle = (Obstacle) obstacles.get(i);
            if (CollisionHelper.collides(player, obstacle)) {
                player.hurt(obstacle);
            }
        }
    }

    @Override
    public void draw(Canvas canvas) {}
}
