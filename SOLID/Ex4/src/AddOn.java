public enum AddOn implements FeeComponent {
    MESS(1000.0), LAUNDRY(500.0), GYM(300.0);

    private final Money price;

    AddOn(double price) { this.price = new Money(price); }

    @Override
    public Money monthlyFee() { return price; }
}
