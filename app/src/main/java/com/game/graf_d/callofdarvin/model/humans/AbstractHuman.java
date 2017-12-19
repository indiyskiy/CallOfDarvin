package com.game.graf_d.callofdarvin.model.humans;

import com.game.graf_d.callofdarvin.model.city.Creature;

/**
 * Created by Graf_D on 26.11.2017.
 */

public abstract class AbstractHuman implements Creature {
    private boolean dead = false;

    public boolean isDead() {
        return dead;
    }

    public void killIt() {
        dead = true;
    }
}
