package cs3500.animator.model;

/**
 * Class representing a Shape which is basically similar to a KeyFrame and consists of
 * the attributes a Shape in an animation.
 * Required to make the provider's view work.
 */
public class Shape {
  private ShapeType type;
  private int width;
  private int height;
  private int x;
  private int y;
  private Color color;

  /**
   * Constructor for Shape.
   * @param type the type of Shape(rectangle / ellipse).
   * @param width the width of the shape.
   * @param height the height of the shape.
   * @param x x position of the shape.
   * @param y y position of the shape.
   * @param color color of the shape.
   */
  public Shape(ShapeType type, int width, int height,  int x, int y, Color color) {
    this.type = type;
    this.x = x;
    this.y = y;
    this.color = color;
    this.height = height;
    this.width = width;
  }

  /**
   * Gets the type of the shape(rectangle/ellipse).
   * @return type of the shape.
   */
  public ShapeType getType() {
    return type;
  }

  /**
   * A getter method for the X position of a Motion.
   *
   * @return the X position of a Motion.
   */
  public int getX() {
    return x;
  }

  /**
   * A getter method for the Y position of a Motion.
   *
   * @return the Y position of a Motion.
   */
  public int getY() {
    return y;
  }

  /**
   * A getter method for the Color of a Motion.
   *
   * @return the Color of a Motion.
   */
  public Color getColor() {
    return color;
  }

  /**
   * A getter method for the Color of a Motion.
   *
   * @return the Color of a Motion.
   */
  public java.awt.Color getColorA() {
    return new java.awt.Color(color.getRed(), color.getGreen(), color.getBlue());
  }

  /**
   * A getter method for the Height of a Motion.
   *
   * @return the Height of a Motion.
   */
  public int getHeight() {
    return height;
  }

  /**
   * A getter method for the Width of a Motion.
   *
   * @return the Width of a Motion.
   */
  public int getWidth() {
    return width;
  }

  /**
   * Initialises the enum ShapeType which consists of two
   * types of Shapes - rectangle or ellipse.
   */
  public enum ShapeType {
    rectangle, ellipse;
  }
}
