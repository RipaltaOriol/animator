package cs3500.animator.model;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Represents a Triangle Shape.
 */
public class TriangleShape extends AbstractShape {

  Map<String, KeyFrame> keyframes = new LinkedHashMap<>();

  /**
   * Constructs a (@code TriangleShape) object.
   */
  public TriangleShape() {
    // The Constructor does not take any parameter. KeyFrames handle the Shape Constructors.
  }

  @Override
  protected boolean isRectangle() {
    return false;
  }

  @Override
  protected boolean isCircle() {
    return false;
  }

  @Override
  protected boolean isTriangle() {
    return true;
  }

  @Override
  protected boolean isEllipse() {
    return false;
  }
}
