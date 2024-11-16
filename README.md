# EGM96  <img src="https://travis-ci.org/matthiaszimmermann/EGM96.svg">

Java Library to compute the EGM96 offset against WGS84 altitudes reported by simple GPS receivers. 

Default use case: The Android location manager API does not return
altitues above mean sea level (what would be most useful for normal
users) but rather the altitue above the WGS84 reference ellipsoid
[1]. To transform the WGS84 altitued to an estimate for the altiude
above mean sea level, the offset described by the Earth Gravitational
Model 1996 (EGM96) [2,7] can be subtracted from the WGS84 altitude.

See the example below for the conversion described above:

```
import org.matthiaszimmermann.location.Location;
import org.matthiaszimmermann.location.egm96.Geoid;

...

/** 
 * call this before any calculation with class Geoid
 */
private void init() {
  Geoid.init();
}

/**
 * convert the wgs84 altitude to the egm96 altitude at the provided location.
 */
public double egm96Altitude(double wgs84altitude, double latitude, double longitude) {
  return wgs84altitude - Geoid.getOffset(new Location(latitude, longitude));
}
```

## Disclaimer
You might use this code as a starting point for your private projects. It is not (yet) suitable for critical/productive use cases. Specifically in the following areas more work/effort is needed:

1. Verification of grid offset points in file EGM96complete.dat.gz
2. Interpolation is a hack. A Java version of the GeographicLib [3] would be cool.
3. More and better test cases.

## Data Sources
The EGM96 offsets have been captured from the NGA EGM96 Geoid Calculator [4] (see [5] for an alternative) and saved to file EGM96complete.dat.gz in folder /src/main/resources. The file format has not been chosen for minimum size but to maximize readability. 

```
// estimate (average over offsets at 89.74) for north pole
90.0 0.0 13.68
// 89.74 north
89.74 0.0 13.92
89.74 0.25 13.92
89.74 0.5 13.92
```

Each non-comment line has the format <latitude> <longitude> <offset>. offsets are in meters and positions correspond to the standard grid for the available EGM96 offsets.

For test data the file GeoidHeights.dat.gz made available with the GeographicLib [6] is used.

## References
[1] http://developer.android.com/reference/android/location/Location.html#getAltitude()<br>
[2] https://en.wikipedia.org/wiki/EGM96<br>
[3] http://geographiclib.sourceforge.net/html/classGeographicLib_1_1Geoid.html<br>
[4] http://earth-info.nga.mil/GandG/wgs84/gravitymod/egm96/intpt.html<br>
[5] http://geographiclib.sourceforge.net/cgi-bin/GeoidEval<br>
[6] http://geographiclib.sourceforge.net/html/geoid.html#testgeoid<br>
[7] https://vdatum.noaa.gov/docs/datums.html<br>
