package org.worldbuild.core.utils;

import lombok.extern.log4j.Log4j2;
import org.worldbuild.core.enums.DistanceUnit;

@Log4j2
public class DistanceUtils {

    public static double calculateDistance(double[] second, double[] first, DistanceUnit unit) {
        return calculateDistance(first[1], first[0], second[1], second[0], unit);
    }

    public static double calculateDistance(double latitude2, double longitude2, double latitude1, double longitude1, DistanceUnit unit) {
        if (latitude2 == latitude1 && longitude2 == longitude1) {
            return 0.0;
        }
        double theta = longitude2 - longitude1;
        double distance = Math.sin(deg2rad(latitude2)) * Math.sin(deg2rad(latitude1))
                + Math.cos(deg2rad(latitude2)) * Math.cos(deg2rad(latitude1)) * Math.cos(deg2rad(theta));
        distance = Math.acos(distance);
        distance = rad2deg(distance);
        distance = distance * 60 * 1.1515;
        if (unit == DistanceUnit.KM) {
            distance = distance * 1.609344;
        } else if (unit == DistanceUnit.NAUTICAL) {
            distance = distance * 0.8684;
        } else if (unit == DistanceUnit.METER) {
            distance = distance * 1.609344 * 1000;
        }
        if (!Double.isNaN(distance)) {
            return distance;
        }
        return 0;
    }

    /* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
    /* :: This function converts decimal degrees to radians : */
    /* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    /* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
    /* :: This function converts radians to decimal degrees : */
    /* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
    private static double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }

}
