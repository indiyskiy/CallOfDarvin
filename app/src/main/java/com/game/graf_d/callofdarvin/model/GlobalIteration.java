package com.game.graf_d.callofdarvin.model;

import com.game.graf_d.callofdarvin.model.city.CityGraph;
import com.game.graf_d.callofdarvin.model.city.CityGraphRealisation;
import com.game.graf_d.callofdarvin.model.city.CreatureLocation;
import com.game.graf_d.callofdarvin.model.city.location.CityLocation;
import com.game.graf_d.callofdarvin.model.city.location.Location;
import com.game.graf_d.callofdarvin.model.humans.HumanPopulation;
import com.game.graf_d.callofdarvin.model.monster.AbstractMonster;
import com.game.graf_d.callofdarvin.model.monster.RealMonster;
import com.game.graf_d.callofdarvin.model.monster.generation.AbstractMeasurableGeneration;
import com.game.graf_d.callofdarvin.model.monster.generation.RealGeneration;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Graf_D on 26.11.2017.
 */

public class GlobalIteration {
    private final int startMaxGenerationSize = 100;

    private RealGeneration currentGeneration;
    private RealGeneration lastGeneration;
    private final HumanPopulation humanPopulation;

    private final CityGraph cityGraph;
    //    private final CityGraphRealisation cityGraphRealisation;
    private ArrayList<CreatureLocation> creatureLocations;
    private HashMap<Integer, Location> allLocations;

    private int monstersSpawnedThisDay = 0;
    private int humanSpawnedThisDay = 0;

    DistanationService distanationService;

    public GlobalIteration(HumanPopulation humanPopulation, CityGraph cityGraph
//            , CityGraphRealisation cityGraphRealisation
    ) {
        this.humanPopulation = humanPopulation;
        this.cityGraph = cityGraph;
//        this.cityGraphRealisation = cityGraphRealisation;
        creatureLocations = new ArrayList<>();
        allLocations = new HashMap<>();
        monsterDistanationService = new MonsterDistanationService();
        generateBaseGeneration();
        generateLocationList();
    }

    private void generateLocationList() {
        for (Integer key : CityGraphRealisation.locations.keySet()) {
            CityLocation cityLocation = CityGraphRealisation.locations.get(key);
            Location location = new Location(key, cityLocation);
            allLocations.put(key, location);
        }
    }

    private void generateBaseGeneration() {
        ArrayList<RealMonster> abstractMonsters = new ArrayList<>();
        for (int i = 0; i < startMaxGenerationSize; i++) {
            RealMonster testMonster = new RealMonster();
            testMonster.generate();
            abstractMonsters.add(testMonster);
        }
        currentGeneration = new RealGeneration(startMaxGenerationSize, abstractMonsters);
    }

    private void globalIterate() {
        boolean needToContinue = false;
        do {
            needToContinue = iterateStep();
        } while (needToContinue);
    }

    public boolean iterateStep() {
        iterateMonsterHuman();
        iterateHumanSpawn();
        iterateCreatureInCity();
        boolean needToContinue = checkAlliveMonsterThatStillHere();
        return needToContinue;
    }

    private void iterateCreatureInCity() {
        for (CreatureLocation creatureLocation : creatureLocations) {
            if (creatureLocation.getCreature().isDead()) {
                continue;
            }
            if (creatureLocation.getRoute() == null || creatureLocation.getRoute().getEnd() == creatureLocation.getPosition()) {
                makeDecision(creatureLocation);
            }
            makeStep(creatureLocation);
            checkCollisions(creatureLocation);
        }
    }

    private void makeStep(CreatureLocation creatureLocation) {
        int currentPosition = creatureLocation.getPosition();
        Location currentLocation = allLocations.get(currentPosition);
        Location destination = allLocations.get(creatureLocation.getRoute().next(currentPosition));
        Location.makeStep(creatureLocation, currentLocation, destination);
    }

    private void makeDecision(CreatureLocation creatureLocation) {
            distanationService.findDistanation(creatureLocation);
    }

    private void iterateHumanSpawn() {
        for (int i = 0; i < CityGraphRealisation.CIVIL.length; i++) {
            if (humanPopulation.getCurrentSize() > humanSpawnedThisDay) {
                spawnHuman(i);
            } else {
                break;
            }
        }
    }

    private void iterateMonsterHuman() {
        for (int i = 0; i < CityGraphRealisation.PORTALS_IN.length; i++) {
            if (currentGeneration.getCurrentSize() > monstersSpawnedThisDay) {
                spawnMonster(i);
            } else {
                break;
            }
        }
    }

    private void spawnMonster(int i) {
        RealMonster realMonster = currentGeneration.getMonsterList().get(monstersSpawnedThisDay);
        int portalInId = CityGraphRealisation.PORTALS_IN[i];
//        CityLocation cityLocation = CityGraphRealisation.locations.get(portalInId);
        CreatureLocation creatureLocation = new CreatureLocation(realMonster, portalInId);
        creatureLocations.add(creatureLocation);
        creatureLocation.setPosition(portalInId);
        Location location = allLocations.get(portalInId);
        location.addCreature(creatureLocation);
        monstersSpawnedThisDay++;
    }
}
