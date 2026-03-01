/**
 * Capability: devices that accept an external input source.
 * Only Projector needs this.
 */
public interface InputConnectable {
    void connectInput(String port);
}
