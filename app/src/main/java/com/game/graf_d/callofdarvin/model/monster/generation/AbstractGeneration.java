package com.game.graf_d.callofdarvin.model.monster.generation;

import com.game.graf_d.callofdarvin.model.exception.IllegalParrentException;
import com.game.graf_d.callofdarvin.model.monster.AbstractMonster;
import com.game.graf_d.callofdarvin.model.monster.MeasurableMonster;

import java.util.List;

/**
 * Created by Graf_D on 13.11.2017.
 */

public abstract class AbstractGeneration <T extends AbstractMonster> {
    public abstract List<T> getMonsterList();

    public abstract int getMaximum();

    public abstract AbstractGeneration newGeneration(int maximum) throws IllegalParrentException;

    public abstract int getCurrentSize();

    public abstract AbstractGeneration newGeneration() throws IllegalParrentException;
}
