package kr.tuk.spgp.termproject.jade.geometryrun.game;

import android.graphics.RectF;
import android.util.Log;
import android.view.MotionEvent;

import java.util.ArrayList;

import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IBoxCollidable;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.Sprite;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.scene.Scene;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.util.CollisionHelper;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.GameView;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;
import kr.tuk.spgp.termproject.jade.geometryrun.R;

public class Player extends Sprite implements IBoxCollidable {
    public enum State {
        running, jump, falling, hurt
    }
    protected State state = State.running;
    private final float ground;
    private final RectF collisionRect = new RectF();
    private Obstacle obstacle;
    private float jumpSpeed;
    private static final float JUMP_POWER = 900f;
    private static final float GRAVITY = 1700f;

    public Player() {
        super(R.mipmap.player_basic);
        setPosition(400f, 650f, 100f, 100f);
        ground = y;
    }
    @Override
    public void update() {
        switch (state) {
            case jump:
            case falling:
                float dy = jumpSpeed * GameView.frameTime;
                jumpSpeed += GRAVITY * GameView.frameTime;
                if (jumpSpeed >= 0) { // 낙하하고 있다면 발밑에 땅이 있는지 확인한다
                    float foot = collisionRect.bottom;
                    float floor = findNearestFloorTop(foot);
                    if (foot + dy >= floor) {
                        dy = floor - foot;
                        state = State.running;
                    }
                }
                y += dy;
                setPosition(x, y, width, height);
                updateCollisionRect();
                break;
            case running:
                float foot = collisionRect.bottom;
                float floor = findNearestFloorTop(foot);
                if (foot < floor) {
                    // 달리는 중에 발밑 floor 좌표가 발보다 아래에 있다면 떨어지자
                    state = State.falling;
                    jumpSpeed = 0; // 자유낙하이므로 속도가 0 부터 시작한다.
                }
                break;
            case hurt:
                if (!CollisionHelper.collides(this, obstacle)) {
                    state = State.running;
                    obstacle = null;
                }
                break;
        }
    }
    private float findNearestFloorTop(float foot) {
        // 플레이어 발의 y 좌표에서 아래쪽으로 가장 가까운 floor 의 좌표를 찾는다.
        MainScene scene = (MainScene) Scene.top();
        if (scene == null) return Metrics.height;
        ArrayList<IGameObject> floors = scene.objectsAt(MainScene.Layer.floorbox);
        float top = ground - 50; // 못 찾으면 디폴트 값은 ground이다.
        for (IGameObject obj: floors) {
            FloorBox floor = (FloorBox) obj;
            RectF rect = floor.getCollisionRect();
            if (rect.left > x + width/2 || x - width/2 > rect.right) {
                // floor 의 좌우 좌표 범위가 player 의 x 좌표를 포함하지 않으면 대상에서 제외한다.
                continue;
            }
            if (rect.top < foot) {
                // 발보다 위에 있는 floor 는 대상에서 제외한다
                continue;
            }
            if (top > rect.top) {
                // 더 가까운 것을 찾았다.
                top = rect.top;
            }
        }
        return top;
    }
    private void updateCollisionRect() {
        collisionRect.set(
                dstRect.left + width,
                dstRect.top + height,
                dstRect.right - width,
                dstRect.bottom - height);
    }
    public void jump(boolean startsJump) {
        if (state == State.running && startsJump) {
            state = State.jump;
            jumpSpeed = -JUMP_POWER;
        }
    }
    public void hurt(Obstacle obstacle) {
        if (state == State.hurt) return;
        state = State.hurt;
        this.obstacle = obstacle;
    }
    @Override
    public RectF getCollisionRect() {
        return collisionRect;
    }


}