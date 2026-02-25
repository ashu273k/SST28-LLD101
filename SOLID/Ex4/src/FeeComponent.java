/*
 * ─── OCP REFACTOR: FeeComponent (Interface / Abstraction) ───
 *
 * PROBLEM SOLVED:
 *   Originally, HostelFeeCalculator.calculate() had a SWITCH-CASE on room types:
 *     switch(roomType) { case SINGLE: ... case DOUBLE: ... }
 *   and separate branching for add-ons. Adding a new room type or add-on
 *   required editing the switch (OCP violation). The fee calculation logic
 *   was tangled with room-type knowledge.
 *
 * WHY WE CREATED THIS INTERFACE:
 *   FeeComponent is a UNIFORM abstraction for anything that contributes to
 *   the monthly hostel fee — rooms, add-ons, late fees, etc.
 *   - Every component just exposes monthlyFee().
 *   - The calculator iterates over a list of FeeComponent objects and sums them.
 *   - It doesn't know (or care) what types of components exist.
 *
 * HOW IT ENABLES OCP:
 *   - Adding a new fee type (e.g., LateFee, ParkingFee) = create a class
 *     implementing FeeComponent. The calculator loop stays UNCHANGED.
 *   - Adding a new room type = register it in RoomFee. No switch edits.
 *   - Adding a new add-on = add an enum value in AddOn. No calculator edits.
 *
 * DESIGN PATTERN:
 *   This is the STRATEGY pattern: each FeeComponent is a pricing strategy.
 *   The calculator is the context that sums all strategies uniformly.
 */
public interface FeeComponent {
    Money monthlyFee();
}
