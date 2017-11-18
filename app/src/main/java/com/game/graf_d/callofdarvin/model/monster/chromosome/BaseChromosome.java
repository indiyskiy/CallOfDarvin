package com.game.graf_d.callofdarvin.model.monster.chromosome;

import com.game.graf_d.callofdarvin.model.monster.gene.BaseGene;

import java.util.Random;

/**
 * Created by Graf_D on 13.10.2017.
 */
public class BaseChromosome {
    private static final Random random = new Random(System.currentTimeMillis());
    protected BaseGene domainGene;
    protected BaseGene recessiveGene;

    public BaseChromosome(BaseGene domainGene, BaseGene recessiveGene) {
        this.domainGene = domainGene;
        this.recessiveGene = recessiveGene;
    }

    public BaseChromosome(BaseChromosome ch1, BaseChromosome ch2) {
            domainGene=BaseGene.cross(ch1.getDomainGene(), ch1.getRecessiveGene());
            recessiveGene=BaseGene.cross(ch2.getDomainGene(), ch2.getRecessiveGene());
        shuffle();
        mutate();
    }

    public static BaseChromosome createRandom(BaseGene domainGene, BaseGene recessiveGene) {
        BaseChromosome baseChromosome = new BaseChromosome(domainGene, recessiveGene);
        baseChromosome.mutate();
        return baseChromosome;
    }

    public static BaseChromosome createWithShuffle(BaseGene g1, BaseGene g2) {
        BaseChromosome baseChromosome = new BaseChromosome(g1, g2);
        baseChromosome.shuffle();
        return baseChromosome;
    }

    public static BaseChromosome createRandomWithShuffle(BaseGene g1, BaseGene g2) {
        BaseChromosome baseChromosome = new BaseChromosome(g1, g2);
        baseChromosome.shuffle();
        baseChromosome.mutate();
        return baseChromosome;
    }

    private void shuffle() {
        if (random.nextBoolean()) {
            BaseGene baseGene = domainGene;
            domainGene = recessiveGene;
            recessiveGene = baseGene;
        }
    }

    public void mutate() {
        domainGene.mutate();
        recessiveGene.mutate();
    }

    public BaseGene getDomainGene() {
        return domainGene;
    }

    public BaseGene getRecessiveGene() {
        return recessiveGene;
    }

    @Override
    public String toString() {
        return "{" +
                "domainGene = " + domainGene +
//                ", recessiveGene=" + recessiveGene +
                '}';
    }
}
