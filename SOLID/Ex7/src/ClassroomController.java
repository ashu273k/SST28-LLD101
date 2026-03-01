public class ClassroomController {
    private final DeviceRegistry reg;

    public ClassroomController(DeviceRegistry reg) { this.reg = reg; }

    public void startClass() {
        // Projector — needs PowerControllable + InputConnectable
        PowerControllable pjPower = (PowerControllable) reg.getFirstOfType("Projector");
        pjPower.powerOn();
        ((InputConnectable) reg.getFirstOfType("Projector")).connectInput("HDMI-1");

        // Lights — only BrightnessControllable needed here
        ((BrightnessControllable) reg.getFirstOfType("LightsPanel")).setBrightness(60);

        // AC — only TemperatureControllable needed here
        ((TemperatureControllable) reg.getFirstOfType("AirConditioner")).setTemperatureC(24);

        // Scanner — only Scannable needed
        Scannable scan = (Scannable) reg.getFirstOfType("AttendanceScanner");
        System.out.println("Attendance scanned: present=" + scan.scanAttendance());
    }

    public void endClass() {
        System.out.println("Shutdown sequence:");
        // Shut down every device that supports power control
        for (PowerControllable p : reg.allWith(PowerControllable.class)) {
            p.powerOff();
        }
    }
}
