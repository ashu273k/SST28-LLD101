/**
 * Capability: devices whose temperature can be set.
 * Only AirConditioner needs this.
 */
public interface TemperatureControllable {
    void setTemperatureC(int c);
}
