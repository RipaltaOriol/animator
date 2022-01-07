package cs3500.animator.model;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Represents a Ellipse Shape.
 */
public class EllipseShape extends AbstractShape {

  private Map<String, KeyFrame> keyframes = new LinkedHashMap<>();

  /**
   * Constructs a (@code RectangleShape) object.
   */
  public EllipseShape() {
    // The Constructor does not take any parameter. KeyFrames handle the Shape Constructors.
  }

  @Override
  protected boolean isEllipse() {

    return true;
  }

  @Override
  protected boolean isCircle() {

    return false;
  }

  @Override
  protected boolean isRectangle() {

    return false;
  }

  @Override
  protected boolean isTriangle() {

    return false;
  }
}

