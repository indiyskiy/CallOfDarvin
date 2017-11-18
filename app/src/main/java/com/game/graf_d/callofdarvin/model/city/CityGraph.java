package com.game.graf_d.callofdarvin.model.city;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Graf_D on 17.11.2017.
 */

public class CityGraph {
    private int[][] graph;
    private HashMap<Integer, CityLocation> cityLocationHashMap;

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

    public Route findRoute(int from, int to) {
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
                if (distances[i] != Integer.MAX_VALUE) {
                    for (int k = 0; k < distances.length; k++) {
                        if (k == i) {
                            continue;
                        }
                        int distance = graph[i][k];
                        if (distance != -1) {
                            int possibleDistance = distances[i] + distance;
                            if (possibleDistance < distances[k]) {
                                distances[k] = possibleDistance;
                                way[k] = i;
                                edited = true;
                            }
                        }
                    }
                }
            }
        } while (edited);
        for (int distance : distances) {
            System.out.println(distance);
        }
        System.out.println(" ");
        for (int point : way) {
            System.out.println(point);
        }
        return null;
    }

}
