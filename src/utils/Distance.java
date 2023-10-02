package utils;

import model.Location;

public class Distance {
    private static final Distance distance = new Distance();
    private Distance(){};

    public static Distance getInstance(){
        return distance;
    }


    public int euclideanDistance(Location a, Location b){
        return (int)Math.sqrt((Math.pow((a.getX() - b.getX()), 2) + Math.pow((a.getY() - b.getY()), 2)));
    }
}
