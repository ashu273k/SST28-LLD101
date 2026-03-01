/**
 * Capability: devices that can be powered on and off.
 * Segregated from the fat SmartClassroomDevice interface (ISP).
 */
public interface PowerControllable {
    void powerOn();
    void powerOff();
}
