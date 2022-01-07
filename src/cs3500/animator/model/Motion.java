package cs3500.animator.model;

import java.awt.Color;

/**
 * The Motion represents an instance of a Abstract Shape. A Motion basically contains
 * two consecutive keyframes. An Abstract Shape can either be
 * a Rectangle, Triangle or Circle.
 */
public class Motion {
  private String name;
  private AbstractShape shape;
  private int time;
  private double x;
  private double y;
  private Color color;
  private double height;
  private double width;
  private float degree;
  private int time2;
  private double x2;
  private double y2;
  private Color color2;
  private double height2;
  private double width2;
  private float degree2;

  /**
   * Constructs a (@code KeyFrame) object.
   *
   * @param shape the Shape.
   * @param x the X position of the Shape.
   * @param y the Y position of the Shape.
   * @param color the Color of the Shape.
   * @param height the Height of the Shape.
   * @param width the Width of the Shape.
   * @param time the Time of the Shape.
   * @param x2 the X position of the Shape.
   * @param y2 the Y position of the Shape.
   * @param color2 the Color of the Shape.
   * @param height2 the Height of the Shape.
   * @param width2 the Width of the Shape.
   * @param time2 the Time of the Shape.
   */
  public Motion(String name, AbstractShape shape, int time, double x,
                double y, Color color, double height, double width ,
                float degree, int time2, double x2,
                double y2, Color color2, double height2, double width2,
                float degree2) {
    this.name = name;
    this.shape = shape;
    this.time = time;
    this.x = x;
    this.y = y;
    this.color = color;
    this.height = height;
    this.width = width;
    this.degree = degree;
    this.time2 = time2;
    this.x2 = x2;
    this.y2 = y2;
    this.color2 = color2;
    this.height2 = height2;
    this.width2 = width2;
    this.degree2 = degree2;
  }

  /**
   * A getter method for the name of a Motion.
   *
   * @return the name of a Motion.
   */
  public String getName() {

    return this.name;
  }

  /**
   * A getter method for the degree of a Motion.
   *
   * @return the degree of a Motion.
   */
  public float getDegree() {

    return this.degree;

  }

  /**
   * A getter method for the degree of a Motion.
   *
   * @return the degree of a Motion.
   */
  public float getDegree2() {

    return this.degree2;

  }

  /**
   * A getter method for the shape of a Motion.
   *
   * @return the shape of a Motion.
   */
  public AbstractShape getShape() {
    return this.shape;
  }

  /**
   * A getter method for the X position of a Motion.
   *
   * @return the X position of a Motion.
   */
  public double getPosX() {

    return x;
  }

  /**
   * A getter method for the Y position of a Motion.
   *
   * @return the Y position of a Motion.
   */
  public double getPosY() {

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
   * A getter method for the Height of a Motion.
   *
   * @return the Height of a Motion.
   */
  public double getHeight() {

    return height;
  }

  /**
   * A getter method for the Shape of a Motion.
   *
   * @return the Shape of a Motion.
   * */
  public String getShapeStr() {

    return this.shape.whatShape();
  }

  /**
   * A getter method for the Width of a Motion.
   *
   * @return the Width of a Motion.
   */
  public double getWidth() {

    return width;
  }

  public boolean isRotate() {
    return false;
  }

  /**
   * A getter method for the Time of a Motion.
   *
   * @return the Time of a Motion.
   */
  public int getTime() {

    return time;
  }


  /**
   * Gets the start keyFrame of the motion.
   * @return the start KeyFrame of the motion.
   */
  public KeyFrame getStart() {
    return new KeyFrame(this.name, this.shape, this.time,
        this.x, this.y, this.color, this.height, this.width, this.degree);
  }

  /**
   * A setter method for the X position of a Motion.
   *
   * @param newX the new X position of a Motion.
   */
  public void setX(double newX) {

    this.x = newX;
  }

  /**
   * A setter method for the Y position of a Motion.
   *
   * @param newY the new Y position of a Motion.
   */
  public void setY(double newY) {

    this.y = newY;
  }

  /**
   * A setter method for the Color of a Motion.
   *
   * @param newColor the new Color of a Motion.
   */
  public void setColor(Color newColor) {

    this.color = newColor;
  }

  /**
   * A setter method for the Height of a Motion.
   *
   * @param newHeight the new Height of a Motion.
   */
  public void setHeight(double newHeight) {

    this.height = newHeight;
  }

  /**
   * A setter method for the Width of a Motion.
   *
   * @param newWidth the new Width of a Motion.
   */
  public void setWidth(double newWidth) {

    this.width = newWidth;
  }

  /**
   * A setter method for the Time of a Motion.
   *
   * @param newTime the new Time of a Motion.
   */
  public void setTime(int newTime) {

    this.time = newTime;
  }

  /**
   * A getter method for the X position of a Motion.
   *
   * @return the X position of a Motion.
   */
  public double getPosX2() {

    return x2;
  }

  /**
   * A getter method for the Y position of a Motion.
   *
   * @return the Y position of a Motion.
   */
  public double getPosY2() {

    return y2;
  }

  /**
   * A getter method for the Color of a Motion.
   *
   * @return the Color of a Motion.
   */
  public Color getColor2() {

    return color2;
  }

  /**
   * A getter method for the Height of a Motion.
   *
   * @return the Height of a Motion.
   */
  public double getHeight2() {

    return height2;
  }

  /**
   * A getter method for the Width of a Motion.
   *
   * @return the Width of a Motion.
   */
  public double getWidth2() {

    return width2;
  }

  /**
   * A getter method for the Time of a Motion.
   *
   * @return the Time of a Motion.
   */
  public int getTime2() {

    return time2;
  }

  /**
   * Gets the end keyFrame of the motion.
   * @return the end KeyFrame of the motion.
   */
  public KeyFrame getEnd() {

    return new KeyFrame(this.name, this.shape, this.time2, this.x2,
        this.y2, this.color2, this.height2, this.width2, this.degree2);
  }

  /**
   * This method uses the tweening function to give the new values of any of the attributes
   * of a motion according to their current position, related to the current time.
   * @param m the motion who's values need to be changed.
   * @param time the time for which the new values need to be calculated.
   * @return A new Motion with the changed values.
   */
  public Motion giveNewAttr(Motion m , int time) {

    float bx =  (int) m.getPosX();
    float by = (int) m.getPosY();
    float bcr = m.getColor().getRed();
    float bcg = m.getColor().getGreen();
    float bcb = m.getColor().getBlue();
    float acr = tween((1 / 255) * bcr, (1 / 255) * this.getColor2().getRed(),
        this.getTime(), this.getTime2(), time);
    float acg = tween((1 / 255) * bcg, (1 / 255) * this.getColor2().getGreen(),
        this.getTime(), this.getTime2(), time);
    float acb = tween((1 / 255) * bcb, (1 / 255) * this.getColor2().getBlue(),
        this.getTime(), this.getTime2(), time);
    float ax = tween(bx, (float) this.getPosX2(),this.getTime(), this.getTime2(),time);
    float ay = tween(by,  (float) this.getPosY2(),this.getTime(), this.getTime2(), time);
    float bw =  (int) m.getWidth();
    float bh = (int) m.getHeight();
    float aw = tween(bw, (float) this.getWidth2(),this.getTime(), this.getTime2(),time);
    float ah = tween(bh,  (float) this.getHeight2(),this.getTime(), this.getTime2(), time);
    float bd = m.getDegree();
    float ad = tween(bd,  this.getDegree2(),this.getTime(), this.getTime2(), time);
    return new Motion(m.getName(),
        m.getShape(), time, ax,ay,m.getColor(),ah,aw,ad,
        m.getTime2(), this.getPosX2(),this.getPosY2(),
        new Color(acr, acg, acb),m.getHeight2(),m.getWidth2(),m.getDegree2());

  }


  /**
   * This method performs the tweening calculation and returns a new value
   * for the given value according to the given time and starting and ending values.
   * This formula has been taken from the assignment page. It also takes into account
   * if the given time is the same as the start time or the end time of the value. If it
   * is, the value is multiplied by 0 and hence does not change.
   * @param a  start value of the associated attribute.
   * @param b end value of the associated attribute.
   * @param ta start time of the associated attribute.
   * @param tb end time of the associated attribute.
   * @param time time according to which the value needs to be changed.
   * @return the new value with current time taken into consideration.
   */
  protected float tween(float a, float b, int ta, int tb, int time) {
    float r1 = ((float) (tb - time)) / ((float) (tb - ta));
    float r2 = ((float) (time - ta)) / ((float) (tb - ta));
    if (r1 > 1) {
      r1 = 1;
    }
    if (r1 < 0) {
      r1 = 0;
    }
    if (r2 > 1) {
      r2 = 1;
    }
    if (r2 < 0) {
      r2 = 0;
    }
    return a * r1 + b * r2;
  }

}

