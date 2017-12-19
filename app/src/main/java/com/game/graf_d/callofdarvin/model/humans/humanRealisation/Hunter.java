package com.game.graf_d.callofdarvin.model.humans.humanRealisation;

import com.game.graf_d.callofdarvin.model.humans.AbstractHuman;

/**
 * Created by Graf_D on 26.11.2017.
 */
public class Hunter extends AbstractHuman {
    private HunterType hunterType;

    public Hunter(HunterType hunterType) {
        this.hunterType = hunterType;
    }


    public HunterType getHunterType() {
        return hunterType;
    }

    public void setHunterType(HunterType hunterType) {
        this.hunterType = hunterType;
    }
}
