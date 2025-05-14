package kr.tuk.spgp.termproject.jade.geometryrun.app;

import android.os.Bundle;

import kr.ac.tukorea.ge.spgp2025.a2dg.framework.activity.GameActivity;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.scene.Scene;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.GameView;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;
import kr.tuk.spgp.termproject.jade.geometryrun.BuildConfig;
import kr.tuk.spgp.termproject.jade.geometryrun.game.MainScene;

public class GeometryRunAcrivity extends GameActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        GameView.drawsDebugStuffs = BuildConfig.DEBUG;
        Metrics.setGameSize(1600, 900);

        super.onCreate(savedInstanceState);
        new MainScene().push();
    }
}