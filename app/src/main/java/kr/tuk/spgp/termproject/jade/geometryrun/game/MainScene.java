package kr.tuk.spgp.termproject.jade.geometryrun.game;

import android.util.Log;
import android.view.MotionEvent;

import java.util.Random;

import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.Button;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.HorzScrollBackground;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.VertScrollBackground;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.res.Sound;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.scene.Scene;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;
import kr.tuk.spgp.termproject.jade.geometryrun.R;

public class MainScene extends Scene {
    public enum Layer {
        bg, floor, floorbox, item, controller, player, obstacle, touch;
        public static final int COUNT = values().length;
    }
    private final Player player;
    private static final String TAG = MainScene.class.getSimpleName();
    public MainScene() {
        initLayers(Layer.COUNT);

        add(Layer.bg, new HorzScrollBackground(R.mipmap.background_basic, 100f));
//        add(Layer.bg, new HorzScrollBackground(R.mipmap.background_floor, 200f));
//        add(Layer.bg, new VertScrollBackground(R.mipmap.background_floor_gradation, 300f));

        player = new Player();
        add(Layer.player, player);

        add(Layer.floor, new Floor());

        add(Layer.touch, new Button(R.mipmap.btn_jump_transparent, Metrics.width / 2, Metrics.height / 2, Metrics.width, Metrics.height, new Button.OnTouchListener() {
            @Override
            public boolean onTouch(boolean pressed) {
                player.jump(pressed);
                return false;
            }
        }));

        add(Layer.controller, new MapLoader(this));
        add(Layer.controller, new CollisionChecker(this, player));
    }

    // Overridables
    @Override
    protected int getTouchLayerIndex() {
        return Layer.touch.ordinal();
    }

    @Override
    public void onEnter() {
        Sound.playMusic(R.raw.stage_1);
    }
    @Override
    public void onPause() {
        Sound.pauseMusic();
    }
    @Override
    public void onResume() {
        Sound.resumeMusic();
    }
    @Override
    public void onExit() {
        Sound.stopMusic();
    }
}
