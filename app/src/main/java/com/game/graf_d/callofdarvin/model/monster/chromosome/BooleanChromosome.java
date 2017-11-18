package com.game.graf_d.callofdarvin.model.monster.chromosome;

import com.game.graf_d.callofdarvin.model.monster.gene.BaseGene;
import com.game.graf_d.callofdarvin.model.monster.gene.BooleanGene;

/**
 * Created by Graf_D on 23.10.2017.
 */

public class BooleanChromosome extends BaseChromosome {
    public BooleanChromosome(BooleanGene domainGene, BooleanGene recessiveGene) {
        super(domainGene, recessiveGene);
    }

    public BooleanChromosome(BooleanChromosome ch1, BooleanChromosome ch2) {
        super(ch1, ch2);
    }

    public BooleanGene getDomainGene() {
        return (BooleanGene) domainGene;
    }

    public BooleanGene getRecessiveGene() {
        return (BooleanGene) recessiveGene;
    }

}
