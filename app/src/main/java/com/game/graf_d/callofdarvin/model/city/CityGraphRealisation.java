package com.game.graf_d.callofdarvin.model.city;

import java.util.HashMap;

/**
 * Created by Graf_D on 17.11.2017.
 */

public class CityGraphRealisation {
    public static int[][] route = new int[][]{
            {0, -1, 3, 1, -1},
            {-1, 0, 4, -1, -1},
            {3, 4, 0, 1, 1},
            {1, -1, 1, 0, 1},
            {-1, -1, 1, 1, 0}
    };

    public static HashMap<Integer, CityLocation> locations = createMap();

    private static HashMap<Integer, CityLocation> createMap() {
        HashMap<Integer, CityLocation> city = new HashMap<>();
        city.put(0, new CityLocation());
        city.put(1, new CityLocation());
        city.put(2, new CityLocation());
        return city;
    }
}
