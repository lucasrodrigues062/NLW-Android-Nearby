package com.example.nearby.utils

import com.google.android.gms.maps.model.LatLng

fun findSouthWestPoint(points: List<LatLng>): LatLng {
    if (points.isEmpty()) return LatLng(0.0, 0.0)
    var southWestMostPoint = points[0]

    for (point in points) {
        if (point.latitude < southWestMostPoint.latitude ||
            (point.latitude == southWestMostPoint.latitude
                    && point.longitude < southWestMostPoint.longitude)
        ) {
            southWestMostPoint = point
        }
    }
    return southWestMostPoint
}

fun findNorthEastPoint(points: List<LatLng>): LatLng {
    if (points.isEmpty()) return LatLng(0.0, 0.0)
    var northEastPoint = points[0]

    for (point in points) {
        if (point.latitude > northEastPoint.latitude ||
            (point.latitude == northEastPoint.latitude
                    && point.longitude > northEastPoint.longitude)
        ) {
            northEastPoint = point
        }
    }
    return northEastPoint
}
