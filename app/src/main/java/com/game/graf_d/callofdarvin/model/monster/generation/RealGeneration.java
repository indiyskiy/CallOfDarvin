package com.game.graf_d.callofdarvin.model.monster.generation;

import com.game.graf_d.callofdarvin.model.exception.IllegalParrentException;
import com.game.graf_d.callofdarvin.model.monster.RealMonster;
import com.game.graf_d.callofdarvin.model.monster.gene.BooleanGene;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Graf_D on 15.11.2017.
 */

public class RealGeneration extends AbstractMeasurableGeneration {

    private int maximum;
    private List<RealMonster> generationList;
    private int currentSize;
    private static final Random random = new Random(System.currentTimeMillis());

    public RealGeneration(int maximum) {
        this.maximum = maximum;
        generationList = new ArrayList<>();
        currentSize = 0;
    }

    public RealGeneration(int maximum, List<RealMonster> generationList) {
        this.maximum = maximum;
        this.generationList = generationList;
        currentSize = generationList.size();
    }

    @Override
    public List<RealMonster> getMonsterList() {
        return generationList;
    }

    @Override
    public int getMaximum() {
        return maximum;
    }

    @Override
    public RealGeneration newGeneration(int maximum) throws IllegalParrentException {
        RealGeneration baseGeneration = new RealGeneration(maximum);
        if (currentSize == 0) {
            return baseGeneration;
        }
        ArrayList<RealMonster> realMonsters = new ArrayList<>();
        int currentSize = 0;
        for (int i = 0; i < this.currentSize; i++) {
            if (currentSize >= maximum) {
                break;
            }
            RealMonster bm1 = getRandomMonster();
            RealMonster bm2 = getRandomMonster();
            RealMonster realMonster = bm1.crossIt(bm1, bm2);
            realMonsters.add(realMonster);
            currentSize++;
        }
        baseGeneration.currentSize = currentSize;
        baseGeneration.generationList = realMonsters;
        return baseGeneration;
    }

    private RealMonster getRandomMonster() {
        if (currentSize == 0) {
            return null;
        }
        int sum = 0;
        ArrayList<Integer> measureList = new ArrayList<>();
        for (RealMonster realMonster : generationList) {
            sum += realMonster.measure();
            measureList.add(sum);
        }
//        System.out.println("sum " + sum);
        if (sum == 0) {
            int randomInt = random.nextInt(generationList.size());
            return generationList.get(randomInt);
        }
        int i = 0;
        int randomInt = random.nextInt(sum);
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
    public RealGeneration newGeneration() throws IllegalParrentException {
        return newGeneration(getMaximum());
    }

    @Override
    public String toString() {
        int mystic = 0;
        int perverting = 0;
        int tentacle = 0;
        int terrifying = 0;
        int measure = 0;
        for (RealMonster realMonster : generationList) {
            BooleanGene booleanGene1 = realMonster.getMystic().getDomainGene();
            if (booleanGene1.isState()) {
                mystic++;
            }
            BooleanGene booleanGene2 = realMonster.getPerverting().getDomainGene();
            if (booleanGene2.isState()) {
                perverting++;
            }
            BooleanGene booleanGene3 = realMonster.getTentacle().getDomainGene();
            if (booleanGene3.isState()) {
                tentacle++;
            }
            BooleanGene booleanGene4 = realMonster.getTerrifying().getDomainGene();
            if (booleanGene4.isState()) {
                terrifying++;
            }
            measure += realMonster.measure();
        }
        return currentSize + "; " + measure / currentSize + "; " + tentacle + "-" + mystic + "-" + perverting + "-" + terrifying;
    }
}
