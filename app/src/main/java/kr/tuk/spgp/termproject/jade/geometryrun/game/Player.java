package kr.tuk.spgp.termproject.jade.geometryrun.game;

import android.view.MotionEvent;

import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.Sprite;
import kr.tuk.spgp.termproject.jade.geometryrun.R;

public class Player extends Sprite {
    public enum State {
        running, jump
    }
    protected State state = State.jump;
    public Player() {
        super(R.mipmap.player_basic);
        setPosition(400f, 650f, 100f, 100f);
    }
    public void jump() {
        if (state == State.running) {
            setPosition(x, y, 100f, 100f);
            state = State.jump;
        } else {
            setPosition(x, y, 100f, 100f);
            state = State.running;
        }
    }
    public boolean onTouch(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            jump();
        }
        return false;
    }
}