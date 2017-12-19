package com.game.graf_d.callofdarvin.model.city;

import java.util.ArrayList;

/**
 * Created by Graf_D on 18.11.2017.
 */

public class Route {
    private ArrayList<Integer> routeIds;
    private int length;
    private int start;
    private int end;

    public Route(ArrayList<Integer> routeIds, int length, int start, int end) {
        this.routeIds = routeIds;
        this.length = length;
        this.start = start;
        this.end = end;
    }

    public ArrayList<Integer> getRouteIds() {
        return routeIds;
    }

    public int getLength() {
        return length;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Route)) return false;

        Route route = (Route) o;

        if (getLength() != route.getLength()) return false;
        if (getStart() != route.getStart()) return false;
        if (getEnd() != route.getEnd()) return false;
        return getRouteIds().equals(route.getRouteIds());
    }

    @Override
    public int hashCode() {
        int result = getRouteIds().hashCode();
        result = 31 * result + getLength();
        result = 31 * result + getStart();
        result = 31 * result + getEnd();
        return result;
    }

    @Override
    public String toString() {
        return "Route{" +
                "routeIds=" + readable(routeIds) +
                ", length=" + length +
                ", start=" + start +
                ", end=" + end +
                '}';
    }

    private String readable(ArrayList<Integer> routeIds) {
        StringBuilder res = new StringBuilder("{");
        for (int i = 0; i < routeIds.size(); i++) {
            res.append(routeIds.get(i));
            res.append("-");
            res.append(CityGraphRealisation.locations.get(routeIds.get(i)).getClass().getSimpleName());
            if (i != routeIds.size() - 1) {
                res.append(", ");
            }
        }
        res.append("}");
        return res.toString();
    }
}
