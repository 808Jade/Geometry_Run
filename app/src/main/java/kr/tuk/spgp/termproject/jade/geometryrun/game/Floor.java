package kr.tuk.spgp.termproject.jade.geometryrun.game;

import android.graphics.RectF;

import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.Sprite;
import kr.tuk.spgp.termproject.jade.geometryrun.R;

public class Floor extends Sprite {
    public Floor() {
        super(R.mipmap.background_floor_gradation);
        setPosition(800f, 800f, 1600f, 200f);
    }
}

