package kr.tuk.spgp.termproject.jade.geometryrun.game;

import android.graphics.RectF;
import android.view.MotionEvent;

import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.Sprite;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.GameView;
import kr.tuk.spgp.termproject.jade.geometryrun.R;

public class Player extends Sprite {
    public enum State {
        running, jump
    }
    protected State state = State.running;
    private final float ground;
    private RectF collisionRect = new RectF();
    private float jumpSpeed;
    private static final float JUMP_POWER = 900f;
    private static final float GRAVITY = 1700f;
    protected static float[][] edgeInsetRatios = {
            { 0.3f, 0.5f, 0.3f, 0.0f }, // State.running
            { 0.3f, 0.6f, 0.3f, 0.0f }, // State.jump
            { 0.3f, 0.6f, 0.3f, 0.0f }, // State.doubleJump
            { 0.3f, 0.5f, 0.3f, 0.0f }, // State.falling
    };
    public Player() {
        super(R.mipmap.player_basic);
        setPosition(400f, 650f, 100f, 100f);
        ground = y;
    }
    @Override
    public void update() {
        if (state == State.jump) {
            float dy = jumpSpeed * GameView.frameTime;
            jumpSpeed += GRAVITY * GameView.frameTime;
            if (y + dy >= ground) {
                dy = ground - y;
                state = State.running;
            }
            y += dy;
            setPosition(x, y, width, height);
            updateCollisionRect();
        }
    }
    private void updateCollisionRect() {

    }
    public void jump() {
        if (state == State.running) {
            state = State.jump;
            jumpSpeed = -JUMP_POWER;
        }
    }
    @Override
    public RectF getCollisionRect() {
        return collisionRect;
    }
    public boolean onTouch(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            jump();
        }
        return false;
    }
}