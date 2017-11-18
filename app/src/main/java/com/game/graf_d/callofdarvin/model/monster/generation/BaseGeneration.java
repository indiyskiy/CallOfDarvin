package com.game.graf_d.callofdarvin.model.monster.generation;

import com.game.graf_d.callofdarvin.model.exception.IllegalParrentException;
import com.game.graf_d.callofdarvin.model.monster.AbstractMonster;
import com.game.graf_d.callofdarvin.model.monster.MeasurableMonster;
import com.game.graf_d.callofdarvin.model.monster.TestMonster;
import com.game.graf_d.callofdarvin.model.monster.gene.BooleanGene;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Graf_D on 13.11.2017.
 */

public class BaseGeneration extends AbstractGeneration {

    private int maximum;
    private List<AbstractMonster> generationList;
    private int currentSize;
    private static final Random random = new Random(System.currentTimeMillis());

    public BaseGeneration(int maximum) {
        this.maximum = maximum;
        generationList = new ArrayList<>();
        currentSize = 0;
    }

    public BaseGeneration(int maximum, List<AbstractMonster> generationList) {
        this.maximum = maximum;
        this.generationList = generationList;
        currentSize = generationList.size();
    }

    @Override
    public List<AbstractMonster> getMonsterList() {
        return generationList;
    }

    @Override
    public int getMaximum() {
        return maximum;
    }

    @Override
    public AbstractGeneration newGeneration(int maximum) throws IllegalParrentException {
        BaseGeneration baseGeneration = new BaseGeneration(maximum);
        if (currentSize == 0) {
            return baseGeneration;
        }
        ArrayList<AbstractMonster> abstractMonsters = new ArrayList<>();
        int currentSize = 0;
        for (int i = 0; i < this.currentSize; i++) {
            if (currentSize >= maximum) {
                break;
            }
            AbstractMonster bm1 = getRandomMonster();
            AbstractMonster bm2 = getRandomMonster();
            AbstractMonster abstractMonster = bm1.crossIt(bm1, bm2);
            abstractMonsters.add(abstractMonster);
            currentSize++;
        }
        baseGeneration.currentSize = currentSize;
        baseGeneration.generationList = abstractMonsters;
        return baseGeneration;
    }

    private AbstractMonster getRandomMonster() {
        if (currentSize == 0) {
            return null;
        }
        int randomInt = random.nextInt(currentSize);
        return generationList.get(randomInt);
    }

    @Override
    public int getCurrentSize() {
        return currentSize;
    }

    @Override
    public AbstractGeneration newGeneration() throws IllegalParrentException {
        return newGeneration(getMaximum());
    }

    @Override
    public String toString() {
        int i1 = 0;
        int i2 = 0;
        for (AbstractMonster abstractMonster : generationList) {
            TestMonster testMonster = (TestMonster) abstractMonster;
            BooleanGene booleanGene1 =  testMonster.getCh1().getDomainGene();
            if (booleanGene1.isState()) {
                i1++;
            }
            BooleanGene booleanGene2 = testMonster.getCh2().getDomainGene();
            if (booleanGene2.isState()) {
                i2++;
            }
        }
        return currentSize+"; "+i1 + "-" + i2;
    }
//    @Override
//    public String toString() {
//        StringBuilder generationListString = new StringBuilder();
//        for (AbstractMonster baseMonster : generationList) {
//            generationListString.append(baseMonster).append(", ");
//        }
//        return "BaseGeneration{" +
//                "generationList=" + generationListString.toString() +
//                '}';
//    }
}
