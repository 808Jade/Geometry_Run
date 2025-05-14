package kr.tuk.spgp.termproject.jade.geometryrun.game;

import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.Sprite;
import kr.tuk.spgp.termproject.jade.geometryrun.R;

public class Player extends Sprite {
    public Player() {
        super(R.mipmap.player_basic);
        setPosition(400f, 700f, 100f, 100f);
    }
}