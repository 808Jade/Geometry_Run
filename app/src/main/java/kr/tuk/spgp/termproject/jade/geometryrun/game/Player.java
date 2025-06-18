package kr.tuk.spgp.termproject.jade.geometryrun.game;

import android.graphics.Canvas;
import android.graphics.RectF;
import android.util.Log;
import android.view.MotionEvent;

import java.util.ArrayList;

import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IBoxCollidable;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.Sprite;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.res.Sound;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.scene.Scene;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.util.CollisionHelper;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.GameView;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;
import kr.tuk.spgp.termproject.jade.geometryrun.R;

public class Player extends Sprite implements IBoxCollidable {
    public enum State {
        running, jump, falling, hurt
    }
    public enum Face{
        up, right, left, down
    }
    protected  Face face = Face.up;
    protected State state = State.running;
    private final float ground;
    private final RectF collisionRect = new RectF();
    private Obstacle obstacle;
    private FloorBox floorbox;
    private float jumpSpeed;
    private static final float JUMP_POWER = 900f;
    private static final float GRAVITY = 1700f;
    private float rotation = 0f;
    private float rotationSpeed = 180f;

    public Player() {
        super(R.mipmap.player_basic);
        setPosition(400f, 650f, 100f, 100f);
        ground = y;
    }
    @Override
    public void update() {
        super.update();
        updateCollisionRect();

        // 점프 상태일 때만 회전 적용
        if (state == State.jump || state == State.falling) {
            rotation += rotationSpeed * GameView.frameTime;
            if (rotation >= 360f) rotation -= 360f; // 360도 이상이면 초기화
        }

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
                        adjustRotationOnLanding(); // 착지 시 회전 보정
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
                if ((obstacle != null && !CollisionHelper.collides(this, obstacle)) ||
                        (floorbox != null && !CollisionHelper.collides(this, floorbox))) {
                    state = State.running;
                    Sound.playEffect(R.raw.hurt);

                    Scene.pop();

                    obstacle = null;
                    floorbox = null;
                }
                break;
        }
    }
    private void adjustRotationOnLanding() {
        // 착지 시 rotation을 가장 가까운 표준 각도(0°, 90°, 180°, 270°)로 보정
        float normalizedRotation = rotation % 360f;
        if (normalizedRotation < 0) normalizedRotation += 360f;

        if (normalizedRotation >= 315 || normalizedRotation < 45) {
            rotation = 0f;
            face = Face.up;
        } else if (normalizedRotation >= 45 && normalizedRotation < 135) {
            rotation = 90f;
            face = Face.right;
        } else if (normalizedRotation >= 135 && normalizedRotation < 225) {
            rotation = 180f;
            face = Face.down;
        } else { // 225 ~ 315
            rotation = 270f;
            face = Face.left;
        }
    }
    private float findNearestFloorTop(float foot) {
        // 플레이어 발의 y 좌표에서 아래쪽으로 가장 가까운 floor 의 좌표를 찾는다.
        MainScene scene = (MainScene) Scene.top();
        if (scene == null) return Metrics.height;

        ArrayList<IGameObject> floors = scene.objectsAt(MainScene.Layer.floorbox);
        float top = ground + 50; // 못 찾으면 디폴트 값은 ground이다.
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
                top = rect.top - width * 0.1f;
            }
        }
        return top;
    }
    private void updateCollisionRect() {
        float halfWidth = width / 2;
        float halfHeight = height / 2;
        float centerX = dstRect.left + halfWidth;
        float centerY = dstRect.top + halfHeight;

        // 약간의 여유를 둔 정사각형 (예: width/height의 80% 사용)
        float inset = width * 0.1f;
        collisionRect.set(
                centerX - halfWidth + inset,    // left
                centerY - halfHeight + inset,   // top
                centerX + halfWidth - inset,    // right
                centerY + halfHeight - inset    // bottom
        );
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
    public void hurt(FloorBox floorbox) {
        if (state == State.hurt) return;
        state = State.hurt;
        this.floorbox = floorbox;
    }
    @Override
    public RectF getCollisionRect() {
        return collisionRect;
    }
    @Override
    public void draw(Canvas canvas) {
        //super.draw(canvas); // 회전 여부에 따라 다르게 처리됨
        canvas.save();
        canvas.rotate(rotation, dstRect.centerX(), dstRect.centerY());
        canvas.drawBitmap(bitmap, srcRect, dstRect, null);
        canvas.restore();
        // 추가적인 Player 드로잉 로직
    }
}