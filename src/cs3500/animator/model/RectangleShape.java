package cs3500.animator.model;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Represents a Rectangle Shape.
 */
public class RectangleShape extends AbstractShape {

  Map<String, KeyFrame> keyframes = new LinkedHashMap<>();

  /**
   * Constructs a (@code RectangleShape) object.
   */
  public RectangleShape() {
    // The Constructor does not take any parameter. KeyFrames handle the Shape Constructors.

  }

  @Override
  protected boolean isRectangle() {
    return true;
  }

  @Override
  protected boolean isCircle() {
    return false;
  }

  @Override
  protected boolean isTriangle() {
    return false;
  }

  @Override
  protected boolean isEllipse() {
    return false;
  }
}
