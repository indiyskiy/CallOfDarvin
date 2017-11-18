package com.game.graf_d.callofdarvin.model.monster;

import android.support.annotation.NonNull;

import com.game.graf_d.callofdarvin.model.exception.IllegalParrentException;
import com.game.graf_d.callofdarvin.model.monster.chromosome.BooleanChromosome;
import com.game.graf_d.callofdarvin.model.monster.gene.BooleanGene;

public class RealMonster extends MeasurableMonster {
    private BooleanChromosome tentacle;
    private BooleanChromosome mystic;
    private BooleanChromosome perverting;
    private BooleanChromosome terrifying;

    private int measure;
    private int maxMeasure;

    public RealMonster() {

    }

    public RealMonster(BooleanChromosome tentacle, BooleanChromosome mystic, BooleanChromosome perverting, BooleanChromosome terrifying, int measure, int maxMeasure) {
        this.tentacle = tentacle;
        this.mystic = mystic;
        this.perverting = perverting;
        this.terrifying = terrifying;
        this.maxMeasure = maxMeasure;
        this.measure = measure;
    }


    @Override
    public RealMonster crossIt(AbstractMonster b1, AbstractMonster b2) throws IllegalParrentException {
        if (!(b1 instanceof RealMonster)) {
            throw new IllegalParrentException(RealMonster.class, b1.getClass());
        }
        if (!(b2 instanceof RealMonster)) {
            throw new IllegalParrentException(RealMonster.class, b2.getClass());
        }
        RealMonster tmm1 = (RealMonster) b1;
        RealMonster tmm2 = (RealMonster) b2;
        BooleanChromosome newCh1 = new BooleanChromosome((tmm1).tentacle, (tmm2).tentacle);
        BooleanChromosome newCh2 = new BooleanChromosome((tmm1).mystic, (tmm2).mystic);
        BooleanChromosome newCh3 = new BooleanChromosome((tmm1).perverting, (tmm2).perverting);
        BooleanChromosome newCh4 = new BooleanChromosome((tmm1).terrifying, (tmm2).terrifying);
        int maxMeasure = (tmm1.maxMeasure + tmm2.maxMeasure) / 2;
        int measure = 0;
        return new RealMonster(newCh1, newCh2, newCh3, newCh4, measure, maxMeasure);
    }

    @Override
    public void mutate() {
        tentacle.mutate();
        mystic.mutate();
        perverting.mutate();
        terrifying.mutate();
    }

    @Override
    public String toString() {
        return "{" +
                "tentacle = " + tentacle +
                ", mystic = " + mystic +
                ", perverting = " + perverting +
                ", terrifying = " + terrifying +
                '}';
    }


    public void generate() {
        tentacle = generateBooleanGene();
        mystic = generateBooleanGene();
        perverting = generateBooleanGene();
        terrifying = generateBooleanGene();
        maxMeasure = 150;
        measure = 0;
    }

    @NonNull
    private BooleanChromosome generateBooleanGene() {
        BooleanGene booleanGene21 = new BooleanGene();
        booleanGene21.generateRandom();
        BooleanGene booleanGene22 = new BooleanGene();
        booleanGene22.generateRandom();
        return new BooleanChromosome(booleanGene21, booleanGene22);
    }

    public BooleanChromosome getTentacle() {
        return tentacle;
    }

    public BooleanChromosome getMystic() {
        return mystic;
    }

    public BooleanChromosome getPerverting() {
        return perverting;
    }

    public BooleanChromosome getTerrifying() {
        return terrifying;
    }

    @Override
    public int measure() {
        return measure;
    }

    public int getMeasure() {
        return measure;
    }

    public void addMeasure(int add) {
        measure += add;
        if (measure >= maxMeasure) {
            measure = maxMeasure - 1;
        }
        if (measure < 0) {
            measure = 0;
        }
    }
}
