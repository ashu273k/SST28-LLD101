/**
 * Capability: devices whose brightness can be adjusted.
 * Only LightsPanel needs this — no reason to force it on Projector or AC.
 */
public interface BrightnessControllable {
    void setBrightness(int pct);
}
