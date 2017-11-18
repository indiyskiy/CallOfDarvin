package com.game.graf_d.callofdarvin.model.monster;

/**
 * Created by Graf_D on 13.11.2017.
 */

public abstract class MeasurableMonster<T extends MeasurableMonster<T>> extends AbstractMonster<T> {
    public abstract int measure();
}
