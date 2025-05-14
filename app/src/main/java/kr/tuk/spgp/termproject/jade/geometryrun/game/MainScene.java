package kr.tuk.spgp.termproject.jade.geometryrun.game;

import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.HorzScrollBackground;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.VertScrollBackground;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.scene.Scene;
import kr.tuk.spgp.termproject.jade.geometryrun.R;

public class MainScene extends Scene {
    public enum Layer {
        bg, player;
        public static final int COUNT = values().length;
    }

    public MainScene() {
        initLayers(Layer.COUNT);

        add(Layer.bg, new HorzScrollBackground(R.mipmap.background_basic, 100f));
//        add(Layer.bg, new HorzScrollBackground(R.mipmap.background_floor, 200f));
//        add(Layer.bg, new VertScrollBackground(R.mipmap.background_floor_gradation, 300f));

        add(Layer.player, new Player());
    }
}
