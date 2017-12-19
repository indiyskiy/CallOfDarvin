package com.game.graf_d.callofdarvin.model.city.location;

import com.game.graf_d.callofdarvin.model.city.CreatureLocation;

import java.util.ArrayList;

/**
 * Created by Graf_D on 27.11.2017.
 */

public class Location {
    private final int locationId;
    private ArrayList<CreatureLocation> creaturesOnLocation = new ArrayList<>();
    private final CityLocation cityLocation;

    public Location(int locationId, CityLocation cityLocation) {
        this.locationId = locationId;
        this.cityLocation = cityLocation;
    }

    public boolean addCreature(CreatureLocation creatureLocation) {
        creaturesOnLocation.add(creatureLocation);
        creatureLocation.setPosition(locationId);
        creatureLocation.setIdInLocation(creaturesOnLocation.size() - 1);
        if (creatureLocation.getRoute() == null) {
            return false;
        }
        for (Integer id : creatureLocation.getRoute().getRouteIds()) {
            if (locationId == id) {
                return true;
            }
        }
        creatureLocation.setRoute(null);
        return false;
    }

    public boolean removeCreature(CreatureLocation creatureLocation) {
        if (!creatureLocation.isAnywhere()) {
            return false;
        }
        CreatureLocation creatureLocationHere = creaturesOnLocation.get(creatureLocation.getIdInLocation());
        if (!creatureLocationHere.equals(creatureLocation)) {
            return false;
        }
        if (creatureLocation.getPosition() != locationId) {
            return false;
        }
        creaturesOnLocation.remove(creatureLocation.getIdInLocation());
        creatureLocation.setPosition(CreatureLocation.NO_WHERE);
        return true;
    }


    public static void makeStep(CreatureLocation creatureLocation, Location currentLocation, Location destination) {
        currentLocation.removeCreature(creatureLocation);
        destination.addCreature(creatureLocation);
    }
}
