package com.game.graf_d.callofdarvin.model.monster.generation;

import com.game.graf_d.callofdarvin.model.exception.IllegalParrentException;
import com.game.graf_d.callofdarvin.model.monster.TestMeasurableMonster;
import com.game.graf_d.callofdarvin.model.monster.TestMeasurableMonster;
import com.game.graf_d.callofdarvin.model.monster.gene.BooleanGene;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Graf_D on 14.11.2017.
 */

public class BaseMeasurableGeneration extends AbstractMeasurableGeneration {

    private int maximum;
    private List<TestMeasurableMonster> generationList;
    private int currentSize;
    private static final Random random = new Random(System.currentTimeMillis());

    public BaseMeasurableGeneration(int maximum) {
        this.maximum = maximum;
        generationList = new ArrayList<>();
        currentSize = 0;
    }

    public BaseMeasurableGeneration(int maximum, List<TestMeasurableMonster> generationList) {
        this.maximum = maximum;
        this.generationList = generationList;
        currentSize = generationList.size();
    }

    @Override
    public List<TestMeasurableMonster> getMonsterList() {
        return generationList;
    }

    @Override
    public int getMaximum() {
        return maximum;
    }

    @Override
    public AbstractGeneration newGeneration(int maximum) throws IllegalParrentException {
        BaseMeasurableGeneration baseGeneration = new BaseMeasurableGeneration(maximum);
        if (currentSize == 0) {
            return baseGeneration;
        }
        ArrayList<TestMeasurableMonster> testMeasurableMonsters = new ArrayList<>();
        int currentSize = 0;
        for (int i = 0; i < this.currentSize; i++) {
            if (currentSize >= maximum) {
                break;
            }
            TestMeasurableMonster bm1 = getRandomMonster();
            TestMeasurableMonster bm2 = getRandomMonster();
            TestMeasurableMonster testMeasurableMonster = bm1.crossIt(bm1, bm2);
            testMeasurableMonsters.add(testMeasurableMonster);
            currentSize++;
        }
        baseGeneration.currentSize = currentSize;
        baseGeneration.generationList = testMeasurableMonsters;
        return baseGeneration;
    }

    private TestMeasurableMonster getRandomMonster() {
        if (currentSize == 0) {
            return null;
        }
        int sum = 0;
        ArrayList<Integer> measureList = new ArrayList<>();
        for (TestMeasurableMonster testMeasurableMonster : generationList) {
            sum += testMeasurableMonster.measure();
            measureList.add(sum);
        }
        int randomInt = random.nextInt(sum);
        int i = 0;
        for (; i < measureList.size(); i++) {
            if (randomInt < measureList.get(i)) {
                break;
            }
        }
        return generationList.get(i);
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
        int measure=0;
        for (TestMeasurableMonster testMeasurableMonster : generationList) {
            BooleanGene booleanGene1 =  testMeasurableMonster.getCh1().getDomainGene();
            if (booleanGene1.isState()) {
                i1++;
            }
            BooleanGene booleanGene2 =  testMeasurableMonster.getCh2().getDomainGene();
            if (booleanGene2.isState()) {
                i2++;
            }
            measure+=testMeasurableMonster.measure();
        }
        return currentSize + "; "+measure/currentSize+"; " + i1 + "-" + i2;
    }
}
