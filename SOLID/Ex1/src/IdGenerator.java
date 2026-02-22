public class IdGenerator {

    private int counter = 0;
    public String nextId() {
        counter++;
        return String.format("SST-2026-%04d", counter);
    }
}
