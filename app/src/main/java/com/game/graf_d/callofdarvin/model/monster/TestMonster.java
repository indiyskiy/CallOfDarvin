package com.game.graf_d.callofdarvin.model.monster;

import com.game.graf_d.callofdarvin.model.exception.IllegalParrentException;
import com.game.graf_d.callofdarvin.model.monster.chromosome.BooleanChromosome;
import com.game.graf_d.callofdarvin.model.monster.gene.BooleanGene;

/**
 * Created by Graf_D on 23.10.2017.
 */

public class TestMonster extends AbstractMonster {
    private BooleanChromosome ch1;
    private BooleanChromosome ch2;

    public TestMonster() {
    }

    public TestMonster(BooleanChromosome newCh1, BooleanChromosome newCh2) {
        this.ch1 = newCh1;
        this.ch2 = newCh2;
    }

    @Override
    public AbstractMonster crossIt(AbstractMonster b1, AbstractMonster b2) throws IllegalParrentException {
        if (!(b1 instanceof TestMonster)) {
            throw new IllegalParrentException(TestMonster.class, b1.getClass());
        }
        if (!(b2 instanceof TestMonster)) {
            throw new IllegalParrentException(TestMonster.class, b2.getClass());
        }
        BooleanChromosome newCh1 = new BooleanChromosome(((TestMonster) b1).ch1, ((TestMonster) b2).ch1);
        BooleanChromosome newCh2 = new BooleanChromosome(((TestMonster) b1).ch2, ((TestMonster) b2).ch2);
        return new TestMonster(newCh1, newCh2);
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
    }

    public BooleanChromosome getCh1() {
        return ch1;
    }

    public BooleanChromosome getCh2() {
        return ch2;
    }
}
