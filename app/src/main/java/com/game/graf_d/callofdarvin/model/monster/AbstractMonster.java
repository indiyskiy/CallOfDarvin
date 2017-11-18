package com.game.graf_d.callofdarvin.model.monster;

import com.game.graf_d.callofdarvin.model.exception.IllegalParrentException;

import java.util.Random;

/**
 * Created by Graf_D on 11.10.2017.
 */

public abstract class AbstractMonster<T extends AbstractMonster<T>> {
    protected final static Random random = new Random(System.currentTimeMillis());

    public abstract T crossIt(T b1, T b2) throws IllegalParrentException;

    public abstract void mutate();

//    public static AbstractMonster cross(T b1, T b2) throws IllegalParrentException {
//        return b1.crossIt(b1, b2);
//    }

    public abstract void generate();

}
