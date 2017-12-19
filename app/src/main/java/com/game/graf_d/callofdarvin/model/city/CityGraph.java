package com.game.graf_d.callofdarvin.model.city;

import com.game.graf_d.callofdarvin.model.city.location.CityLocation;
import com.game.graf_d.callofdarvin.model.city.location.MercenaryLocation;
import com.game.graf_d.callofdarvin.model.city.location.ShopLocation;
import com.game.graf_d.callofdarvin.model.city.location.finalLocation.CityHallLocation;
import com.game.graf_d.callofdarvin.model.city.location.finalLocation.CivilLocation;
import com.game.graf_d.callofdarvin.model.city.location.finalLocation.PortalInLocation;
import com.game.graf_d.callofdarvin.model.city.location.finalLocation.PortalOutLocation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by Graf_D on 17.11.2017.
 */

public class CityGraph {
    private int[][] graph;
    private HashMap<Integer, CityLocation> cityLocationHashMap;
    private static final Random random = new Random(System.currentTimeMillis());

    public CityGraph(int[][] graph, HashMap<Integer, CityLocation> cityLocationHashMap) {
        this.graph = graph;
        this.cityLocationHashMap = cityLocationHashMap;
    }

    public ArrayList<Integer> getAvailableLocations(int currentLocation) {
        int[] locations = graph[currentLocation];
        ArrayList<Integer> availableLocations = new ArrayList<>();
        for (int i = 0; i < locations.length; i++) {
            int length = locations[i];
            if (length != -1) {
                availableLocations.add(i);
            }
        }
        return availableLocations;
    }

    public int getLength(int from, int to) {
        return graph[from][to];
    }

    public CityLocation getCityLocation(int locationNumber) {
        return cityLocationHashMap.get(locationNumber);
    }

    public Route findRoute(int from, int to, SearchMethod searchMethod, UnitType unitType) {
        int[] distances = new int[graph.length];
        int[] way = new int[graph.length];
        for (int i = 0; i < distances.length; i++) {
            distances[i] = Integer.MAX_VALUE;
        }
        distances[from] = 0;
        boolean edited;
        do {
            edited = false;
            for (int i = 0; i < distances.length; i++) {
                if (distances[i] == Integer.MAX_VALUE) {
                    continue;
                }
                for (int k = 0; k < distances.length; k++) {
                    if (k == i) {
                        continue;
                    }
                    CityLocation cityLocation = cityLocationHashMap.get(k);
                    boolean accept = true;
                    switch (unitType) {
                        case Hunter: {
                            if (cityLocation instanceof PortalInLocation || cityLocation instanceof PortalOutLocation || cityLocation instanceof CityHallLocation || cityLocation instanceof ShopLocation || cityLocation instanceof CivilLocation) {
                                accept = false;
                            }
                            break;
                        }
                        case Civilian: {
                            if (cityLocation instanceof PortalInLocation || cityLocation instanceof PortalOutLocation || cityLocation instanceof CityHallLocation || cityLocation instanceof MercenaryLocation) {
                                accept = false;
                            }
                            break;
                        }
                        case Monster: {
                            if (cityLocation instanceof CityHallLocation || cityLocation instanceof MercenaryLocation || cityLocation instanceof ShopLocation) {
                                accept = false;
                            }
                            break;
                        }
                        case TaxCollector: {
                            if (cityLocation instanceof MercenaryLocation || cityLocation instanceof PortalInLocation || cityLocation instanceof PortalOutLocation) {
                                accept = false;
                            }
                            break;
                        }
                    }
                    if (!accept) {
                        continue;
                    }
                    int distance = graph[i][k];
                    if (distance != -1) {
                        int possibleDistance = distances[i] + distance;
                        if (possibleDistance == Integer.MAX_VALUE) {
                            continue;
                        }
                        if (possibleDistance < distances[k]) {
                            distances[k] = possibleDistance;
                            way[k] = i;
                            edited = true;
                            continue;
                        }
                        if (possibleDistance == distances[k]) {
                            switch (searchMethod) {
                                case First: {
                                    break;
                                }
                                case Lower: {
                                    if (way[k] < i) {
                                        distances[k] = possibleDistance;
                                        way[k] = i;
                                    }
                                    break;
                                }
                                case Bigger: {
                                    if (way[k] > i) {
                                        distances[k] = possibleDistance;
                                        way[k] = i;
                                    }
                                    break;
                                }
                                case Random: {
                                    if (random.nextBoolean()) {
                                        distances[k] = possibleDistance;
                                        way[k] = i;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } while (edited);
        ArrayList<Integer> route = new ArrayList<>();
        getRoute(way, from, to, route);
        route = upsidedown(route);
        return new Route(route, distances[to], from, to);
    }

    private ArrayList<Integer> upsidedown(ArrayList<Integer> route) {
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = route.size() - 1; i >= 0; i--) {
            result.add(route.get(i));
        }
        return result;
    }

    private void getRoute(int[] way, int from, int to, ArrayList<Integer> route) {
        if (from == to) {
            route.add(from);
            return;
        }
        int nextLocation = way[to];
        route.add(to);
        getRoute(way, from, nextLocation, route);
    }

}
