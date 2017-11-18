package com.game.graf_d.callofdarvin.model.monster.gene;

import com.game.graf_d.callofdarvin.model.monster.gene.BaseGene;

/**
 * Created by Graf_D on 11.10.2017.
 */

public class BooleanGene extends BaseGene {
    private boolean state;

    @Override
    protected void mutateThis() {
        this.state = !this.state;
    }

    @Override
    protected float mutateChance() {
        return 0.0025F;
    }

    @Override
    public void generateRandom() {
        this.state = getRandom().nextBoolean();
    }

    @Override
    protected BaseGene cloneGene() {
        BooleanGene booleanGene = new BooleanGene();
        booleanGene.state = this.state;
        return booleanGene;
    }

    public boolean isState() {
        return state;
    }

    @Override
    public String toString() {
        return "BooleanGene{" +
                "state=" + state +
                '}';
    }
}
