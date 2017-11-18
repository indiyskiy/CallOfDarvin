package com.game.graf_d.callofdarvin.model.monster;

import com.game.graf_d.callofdarvin.model.exception.IllegalParrentException;
import com.game.graf_d.callofdarvin.model.monster.chromosome.BooleanChromosome;
import com.game.graf_d.callofdarvin.model.monster.gene.BooleanGene;

/**
 * Created by Graf_D on 14.11.2017.
 */

public class TestMeasurableMonster extends MeasurableMonster {
    private BooleanChromosome ch1;
    private BooleanChromosome ch2;
    private int measure;
    private int maxMeasure;

    public TestMeasurableMonster() {

    }

    public TestMeasurableMonster(BooleanChromosome newCh1, BooleanChromosome newCh2, int measure, int maxMeasure) {
        this.ch1 = newCh1;
        this.ch2 = newCh2;
        this.maxMeasure = maxMeasure;
        this.measure = measure;
    }


    @Override
    public TestMeasurableMonster crossIt(AbstractMonster b1, AbstractMonster b2) throws IllegalParrentException {
        if (!(b1 instanceof TestMeasurableMonster)) {
            throw new IllegalParrentException(TestMeasurableMonster.class, b1.getClass());
        }
        if (!(b2 instanceof TestMeasurableMonster)) {
            throw new IllegalParrentException(TestMeasurableMonster.class, b2.getClass());
        }
        TestMeasurableMonster tmm1 = (TestMeasurableMonster) b1;
        TestMeasurableMonster tmm2 = (TestMeasurableMonster) b2;
        BooleanChromosome newCh1 = new BooleanChromosome((tmm1).ch1, (tmm2).ch1);
        BooleanChromosome newCh2 = new BooleanChromosome((tmm1).ch2, (tmm2).ch2);
        int maxMeasure = (tmm1.maxMeasure + tmm2.maxMeasure) / 2;
        int measure = (tmm1.measure + tmm2.measure) / 2;
        return new TestMeasurableMonster(newCh1, newCh2, measure, maxMeasure);
    }

    @Override
    public void mutate() {
        ch1.mutate();
        ch2.mutate();
    }

    @Override
    public String toString() {
        return "{" +
                "ch1 = " + ch1 +
                ", ch2 = " + ch2 +
                '}';
    }


    public void generate() {
        BooleanGene booleanGene11 = new BooleanGene();
        booleanGene11.generateRandom();
        BooleanGene booleanGene12 = new BooleanGene();
        booleanGene12.generateRandom();
        BooleanChromosome booleanChromosome1 = new BooleanChromosome(booleanGene11, booleanGene12);
        BooleanGene booleanGene21 = new BooleanGene();
        booleanGene21.generateRandom();
        BooleanGene booleanGene22 = new BooleanGene();
        booleanGene22.generateRandom();
        BooleanChromosome booleanChromosome2 = new BooleanChromosome(booleanGene21, booleanGene22);
        ch1 = booleanChromosome1;
        ch2 = booleanChromosome2;
        maxMeasure = 100;
        measure = random.nextInt(maxMeasure);
    }

    public BooleanChromosome getCh1() {
        return ch1;
    }

    public BooleanChromosome getCh2() {
        return ch2;
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
    }
}
