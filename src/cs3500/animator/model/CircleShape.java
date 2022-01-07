package cs3500.animator.model;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Represents a Circle Shape.
 */
public class CircleShape extends AbstractShape {

  private Map<String, KeyFrame> keyframes = new LinkedHashMap<>();

  /**
   * Constructs a (@code CircleShape) object.
   */
  public CircleShape() {
    // The Constructor does not take any parameter. KeyFrames handle the Shape Constructors.
  }

  @Override
  protected boolean isRectangle() {
    return false;
  }

  @Override
  protected boolean isCircle() {
    return true;
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
