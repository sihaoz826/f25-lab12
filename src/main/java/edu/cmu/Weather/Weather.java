package edu.cmu.Weather;

public class Weather {
    private WeatherService weatherService;
    private LengthUnit lengthUnit;

    /**
     * Constructs a new Weather instance with the specified WeatherService.
     * Defaults to millimeters as the length unit.
     *
     * @param weatherService the service to use for retrieving weather data
     */
    public Weather(WeatherService weatherService) {
        if (weatherService == null) {
            throw new IllegalArgumentException("WeatherService cannot be null");
        }
        this.weatherService = weatherService;
        this.lengthUnit = LengthUnit.MILLIMETERS;
    }

    /**
     * Sets the length unit for rainfall measurements.
     *
     * @param lengthUnit the unit to use (INCHES or MILLIMETERS)
     */
    public void setLengthScale(LengthUnit lengthUnit) {
        if (lengthUnit == null) {
            throw new IllegalArgumentException("LengthUnit cannot be null");
        }
        this.lengthUnit = lengthUnit;
    }

    /**
     * Retrieves the rainfall measurement over the last 24 hours from the weather service in the preferred unit.
     * The weather service returns rainfall in millimeters. This method converts to inches if requested.
     * 
     * @return the rainfall amount in the preferred unit (inches or millimeters)
     */
    public double getRainfall() {
        double wsRainfall = weatherService.getRainfall();
        if (lengthUnit == LengthUnit.INCHES) {
            return wsRainfall / 25.4;
        } else {
            return wsRainfall;
        }
    }
}
