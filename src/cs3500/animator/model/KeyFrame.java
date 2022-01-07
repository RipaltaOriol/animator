package cs3500.animator.model;

import java.awt.Color;

/**
 * The Key Frame represents an instance of a Abstract Shape. A KeyFrame contains
 * the attributes of the desired motion.An Abstract Shape can either be
 * a Rectangle, Triangle or Circle.
 */
public class KeyFrame {
  private String name;
  private AbstractShape shape;
  private int time;
  private double x;
  private double y;
  private Color color;
  private double height;
  private double width;
  private float degree;

  /**
   * Constructs a (@code KeyFrame) object.
   *
   * @param name the Name.
   * @param shape the Shape.
   * @param x the X position of the Shape.
   * @param y the Y position of the Shape.
   * @param color the Color of the Shape.
   * @param height the Height of the Shape.
   * @param width the Width of the Shape.
   * @param time the Time of the Shape.
   */
  public KeyFrame(String name,AbstractShape shape, int time, double x,
                  double y, Color color, double height, double width, float degree) {
    this.name = name;
    this.shape = shape;
    this.time = time;
    this.x = x;
    this.y = y;
    this.color = color;
    this.height = height;
    this.width = width;
    this.degree = degree;

  }

  public float getDegree() {
    return this.degree;
  }

  /**
   * Returns the enum ShapeType of the giveShape.
   * @return shapeType of the shape.
   */
  public Shape.ShapeType getSType() {
    if (this.shape.whatShape().equals("rectangle")) {
      return Shape.ShapeType.rectangle;
    }
    else {
      return Shape.ShapeType.ellipse;
    }
  }

  /**
   * A getter method for the name of a KeyFrame.
   *
   * @return the name of a KeyFrame.
   */
  public String getName() {
    return this.name;
  }

  /**
   * A getter method for the X position of a KeyFrame.
   *
   * @return the X position of a KeyFrame.
   */
  public double getPosX() {
    return x;
  }

  /**
   * A getter method for the Y position of a KeyFrame.
   *
   * @return the Y position of a KeyFrame.
   */
  public double getPosY() {
    return y;
  }

  /**
   * A getter method for the Color of a KeyFrame.
   *
   * @return the Color of a KeyFrame.
   */
  public Color getColor() {
    return color;
  }

  public cs3500.animator.model.Color getColorM() {

    return new cs3500.animator.model.Color(color.getRed(), color.getBlue(), color.getGreen());
  }

  /**
   * A getter method for the Height of a KeyFrame.
   *
   * @return the Height of a KeyFrame.
   */
  public double getHeight() {
    return height;
  }

  /**
   * A getter method for the Shape of a KeyFrame.
   *
   * @return the Shape of a KeyFrame.
  * */
  public String getShapeStr() {
    return this.shape.whatShape();
  }

  /**
   * A getter method for the Width of a KeyFrame.
   *
   * @return the Width of a KeyFrame.
   */
  public double getWidth() {
    return width;
  }

  /**
   * A getter method for the Time of a KeyFrame.
   *
   * @return the Time of a KeyFrame.
   */
  public int getTime() {
    return time;
  }

  public boolean isRotate() {
    return false;
  }


  /**
   * A getter method for the Time of a KeyFrame.
   *
   * @return the Time of a KeyFrame.
   */
  public int getTick() {
    return time;
  }

  /**
   * A setter method for the X position of a KeyFrame.
   *
   * @param newX the new X position of a KeyFrame.
   */
  public void setX(double newX) {
    this.x = newX;
  }

  /**
   * A setter method for the Y position of a KeyFrame.
   *
   * @param newY the new Y position of a KeyFrame.
   */
  public void setY(double newY) {
    this.y = newY;
  }

  /**
   * A setter method for the Color of a KeyFrame.
   *
   * @param newColor the new Color of a KeyFrame.
   */
  public void setColor(Color newColor) {
    this.color = newColor;
  }

  /**
   * A setter method for the Height of a KeyFrame.
   *
   * @param newHeight the new Height of a KeyFrame.
   */
  public void setHeight(double newHeight) {
    this.height = newHeight;
  }

  /**
   * A setter method for the Width of a KeyFrame.
   *
   * @param newWidth the new Width of a KeyFrame.
   */
  public void setWidth(double newWidth) {
    this.width = newWidth;
  }

  /**
   * A setter method for the Time of a KeyFrame.
   *
   * @param newTime the new Time of a KeyFrame.
   */
  public void setTime(int newTime) {
    this.time = newTime;
  }
}
