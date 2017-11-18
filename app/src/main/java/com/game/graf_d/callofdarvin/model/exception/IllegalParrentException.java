package com.game.graf_d.callofdarvin.model.exception;

import com.game.graf_d.callofdarvin.model.monster.AbstractMonster;
import com.game.graf_d.callofdarvin.model.monster.TestMonster;

/**
 * Created by Graf_D on 01.11.2017.
 */

public class IllegalParrentException extends Exception {
    public IllegalParrentException(Class firstClass, Class secondClass) {
                super(firstClass.getSimpleName()+" cant be child of "+secondClass.getSimpleName());
    }
}
