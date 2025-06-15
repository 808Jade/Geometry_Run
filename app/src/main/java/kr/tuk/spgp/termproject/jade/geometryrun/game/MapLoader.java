package kr.tuk.spgp.termproject.jade.geometryrun.game;

import android.graphics.Canvas;

import java.util.Random;

import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.GameView;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;

public class MapLoader implements IGameObject {
    private final MainScene scene;
    private final Random random = new Random();
    private float floor_x, item_x;
    public MapLoader(MainScene mainScene) {
        this.scene = mainScene;
    }

    @Override
    public void update() {
        item_x += MapObject.SPEED * GameView.frameTime;
        while (item_x < Metrics.width) {
            int y = (random.nextInt(6) + 1) * 100;
            int count = 3;
            if (y < 500) {
                FloorBox floor = FloorBox.get(FloorBox.Type.T_1x1, item_x, y+100);
                scene.add(floor);
            } else {
                count = random.nextInt(5) + 1;
            }
            for (int i = 0; i < count; i++) {
                int idx = random.nextInt(6);
                int y2 = y - random.nextInt(3) * 100;
                Obstacle jellyItem = Obstacle.get(item_x, y2);
                scene.add(jellyItem);
                item_x += jellyItem.getWidth();
//                int index = random.nextInt(ObstacleFactory.COUNT);
//                Obstacle obstacle = ObstacleFactory.get(index, item_x, 600);
//                scene.add(obstacle);
            }
        }
    }

    @Override
    public void draw(Canvas canvas) {}
}