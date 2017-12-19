package com.game.graf_d.callofdarvin.model.city;

import com.game.graf_d.callofdarvin.model.monster.AbstractMonster;

/**
 * Created by Graf_D on 26.11.2017.
 */

public class CreatureLocation {
    public static final int NO_WHERE = -1;
    private final Creature creature;
    private int position;
    private Route route;
    private UnitType unitType;
    private int idInLocation;

    public CreatureLocation(Creature creature, UnitType unitType, int position) {
        this.creature = creature;
        this.unitType = unitType;
        route = null;
        this.position = position;
    }

    public CreatureLocation(AbstractMonster monster, int position) {
        this.creature = monster;
        this.unitType = UnitType.Monster;
        route = null;
        this.position = position;
    }

    public Creature getCreature() {
        return creature;
    }

    public int getPosition() {
        return position;
    }

    public Route getRoute() {
        return route;
    }

    public UnitType getUnitType() {
        return unitType;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public void setIdInLocation(int idInLocation) {
        this.idInLocation = idInLocation;
    }

    public int getIdInLocation() {
        return idInLocation;
    }

    public boolean isAnywhere() {
        return position!=NO_WHERE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreatureLocation)) return false;

        CreatureLocation that = (CreatureLocation) o;

        if (getPosition() != that.getPosition()) return false;
        if (getIdInLocation() != that.getIdInLocation()) return false;
        if (!getRoute().equals(that.getRoute())) return false;
        return getUnitType() == that.getUnitType();
    }

    @Override
    public int hashCode() {
        int result = 0;
        result = 31 * result + getPosition();
        result = 31 * result + getRoute().hashCode();
        result = 31 * result + getUnitType().hashCode();
        result = 31 * result + getIdInLocation();
        return result;
    }
}
