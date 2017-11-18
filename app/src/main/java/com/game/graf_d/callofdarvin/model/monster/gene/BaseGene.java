package com.game.graf_d.callofdarvin.model.monster.gene;

import java.util.Random;

/**
 * Created by Graf_D on 11.10.2017.
 */

public abstract class BaseGene {
    private static Random random = new Random(System.currentTimeMillis());

    protected abstract void mutateThis();

    protected abstract float mutateChance();

    public abstract void generateRandom();

    public void mutate() {
        Float f=random.nextFloat();
//        System.out.println(f);
        boolean isMutate = mutateChance() > f;
        if (isMutate) {
//            System.out.println("Mutate!");
            mutateThis();
        }
    }

    public static BaseGene cross(BaseGene g1, BaseGene g2) {
        boolean isFirst = random.nextBoolean();
        if (isFirst) {
            return g1.cloneGene();
        }
        return g2.cloneGene();
    }

    protected abstract BaseGene cloneGene();

    protected Random getRandom() {
        return random;
    }

    @Override
    public abstract String toString();
}
