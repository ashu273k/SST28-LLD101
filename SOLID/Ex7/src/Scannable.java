/**
 * Capability: devices that can scan attendance.
 * Only AttendanceScanner needs this.
 */
public interface Scannable {
    int scanAttendance();
}
