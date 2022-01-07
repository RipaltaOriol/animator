package cs3500.animator.model;

import java.util.List;

/**
 * Class added to make provider's view work with our model. An Animation
 * represents a single shape's behavior over time.
 */
public class Animation {
  private String name;
  public Shape.ShapeType type;
  private List<ShapeState> los;

  /**
   * Constructor for Animation.
   * @param name unique name of the animation.
   * @param type Shape type of the animation(rectangle/ellipse).
   * @param los List of ShapeStates that describe the behaviour of the animation
   *            of the given shape.
   */
  public Animation(String name, Shape.ShapeType type, List<ShapeState> los) {
    this.name = name;
    this.type = type;
    this.los = los;
  }

  /**
   * Returns the unique name of the Animation.
   * @return string of the name.
   */
  public String getName() {
    return this.name;
  }

  /**
   * Returns a list of Shapestates.
   * @return List of ShapeStates that describe the behaviour of the animation.
   *         of the given shape.
   */
  public List<ShapeState> getMotions() {
    return this.los;
  }

  /**
   * Returns the previous state of the shape according to the given tick.
   * @param i current tick.
   * @return previous ShapeState.
   */
  public ShapeState getPrevState(int i) {
    ShapeState xo = null;
    for (int x = 0; x < los.size(); x++) {
      if (los.get(x).getTick() == i && x == 0) {
        xo = los.get(x);
      }
      else if (los.get(x).getTick() == i) {
        xo = los.get(x - 1);
      }
    }
    return xo;
  }

  /**
   * Returns the next state of the shape according to the given tick.
   * @param i current tick.
   * @return next ShapeState.
   */
  public ShapeState getNextState(int i) {
    return this.los.get(i + 1);
  }
}
