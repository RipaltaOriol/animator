package cs3500.animator.model;

/**
 * Class that represents the state of a shape at a given tick.
 * Required to make the provider's view work.
 */
public class ShapeState {

  private int tick;
  private Shape shape;

  /**
   * Constructor for ShapeState.
   * @param tick the time for which the state of the shape is required.
   * @param shape the Shape to be found at the given time.
   */
  public ShapeState(int tick, Shape shape) {
    this.tick = tick;
    this.shape = shape;
  }

  /**
   * Constructor to initialise a new ShapeState with the given ShapeState.
   * @param state the new ShapeState.
   */
  public ShapeState(ShapeState state) {
    this.tick = state.getTick();
    this.shape = state.getShape();
  }

  /**
   * Getter method for the time.
   * @return time of the shape.
   */
  public int getTick() {
    return this.tick;
  }

  /**
   * Getter method for the shape.
   * @return Shape at the given time.
   */
  public Shape getShape() {
    return this.shape;
  }
}
