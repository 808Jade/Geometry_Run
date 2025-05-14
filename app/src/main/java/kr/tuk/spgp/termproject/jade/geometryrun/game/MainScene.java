package kr.tuk.spgp.termproject.jade.geometryrun.game;

import android.view.MotionEvent;

import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.HorzScrollBackground;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.VertScrollBackground;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.scene.Scene;
import kr.tuk.spgp.termproject.jade.geometryrun.R;

public class MainScene extends Scene {
    public enum Layer {
        bg, floor, player;
        public static final int COUNT = values().length;
    }

    private final Player player;

    public MainScene() {
        initLayers(Layer.COUNT);

        add(Layer.bg, new HorzScrollBackground(R.mipmap.background_basic, 100f));
//        add(Layer.bg, new HorzScrollBackground(R.mipmap.background_floor, 200f));
//        add(Layer.bg, new VertScrollBackground(R.mipmap.background_floor_gradation, 300f));

        player = new Player();
        add(Layer.player, player);
        add(Layer.floor, new Floor());
    }

    // Overridables
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return player.onTouch(event);
    }
}
