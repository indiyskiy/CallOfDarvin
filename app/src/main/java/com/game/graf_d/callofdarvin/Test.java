package com.game.graf_d.callofdarvin;

import com.game.graf_d.callofdarvin.model.city.CityGraph;
import com.game.graf_d.callofdarvin.model.city.CityGraphRealisation;
import com.game.graf_d.callofdarvin.model.exception.IllegalParrentException;
import com.game.graf_d.callofdarvin.model.monster.RealMonster;
import com.game.graf_d.callofdarvin.model.monster.generation.RealGeneration;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Graf_D on 11.10.2017.
 */

public class Test {
    private static final Random random = new Random(System.currentTimeMillis());

    public static void main(String[] args) {
//        testMonsters();
        testGraph();
    }

    private static void testGraph() {
        CityGraph cityGraph = new CityGraph(CityGraphRealisation.route, CityGraphRealisation.locations);
        cityGraph.findRoute(0,1);
    }

    private static void testMonsters() {
        ArrayList<RealMonster> abstractMonsters = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            RealMonster testMonster = new RealMonster();
            testMonster.generate();
            abstractMonsters.add(testMonster);
        }
        RealGeneration baseGeneration = new RealGeneration(1000, abstractMonsters);
        for (int i = 0; i < 10000; i++) {
            try {
                feed(baseGeneration);
                System.out.println(i + " " + baseGeneration);
                baseGeneration = baseGeneration.newGeneration();
            } catch (IllegalParrentException e) {
                e.printStackTrace();
            }
        }
    }

    private static void feed(RealGeneration baseGeneration) {
        for (RealMonster realMonster : baseGeneration.getMonsterList()) {
            feed(realMonster);
        }
    }

    private static void feed(RealMonster realMonster) {
        int meat = 0;
        meat = random.nextInt(50);
        realMonster.addMeasure(meat);
        if (realMonster.getTentacle().getDomainGene().isState()) {
            meat = random.nextInt(10);
            realMonster.addMeasure(meat);
        }
        if (realMonster.getMystic().getDomainGene().isState()) {
            meat = random.nextInt(20);
            realMonster.addMeasure(meat);
        }

        if (realMonster.getPerverting().getDomainGene().isState()) {
            meat = random.nextInt(30);
            realMonster.addMeasure(meat);
        }
        if (realMonster.getTerrifying().getDomainGene().isState()) {
            meat = random.nextInt(40);
            realMonster.addMeasure(-1 * meat);
        }
    }

}
