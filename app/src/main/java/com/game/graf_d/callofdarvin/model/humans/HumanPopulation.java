package com.game.graf_d.callofdarvin.model.humans;

import com.game.graf_d.callofdarvin.model.humans.humanRealisation.Civilian;
import com.game.graf_d.callofdarvin.model.humans.humanRealisation.Hunter;
import com.game.graf_d.callofdarvin.model.humans.humanRealisation.TaxCollector;

import java.util.ArrayList;

/**
 * Created by Graf_D on 26.11.2017.
 */

public class HumanPopulation {
    private ArrayList<Civilian> civilians;
    private ArrayList<Hunter> hunters;
    private ArrayList<TaxCollector> taxCollectors;
    private int currentSize;

    public HumanPopulation() {
        this.civilians = new ArrayList<>();
        this.hunters = new ArrayList<>();
        this.taxCollectors = new ArrayList<>();
        this.currentSize = 0;
    }

    public void addCivilian(Civilian civilian){
        civilians.add(civilian);
        currentSize++;
    }

    public void removeCivilian(Civilian civilian){
        civilians.remove(civilian);
        currentSize--;
    }

    public void addHunter(Hunter hunter){
        hunters.add(hunter);
        currentSize++;
    }

    public void removeHunter(Hunter hunter){
        hunters.remove(hunter);
        currentSize--;
    }

    public void addTaxCollector(TaxCollector taxCollector){
        taxCollectors.add(taxCollector);
        currentSize++;
    }

    public void removeTaxCollector(TaxCollector taxCollector){
        taxCollectors.remove(taxCollector);
        currentSize--;
    }

    public int getCurrentSize() {
        return currentSize;
    }
}
