package cs3500.animator.model;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class Simple Shape Animation is the implementation of the Model, Shape Animation Model.
 * <p></p>
 * A Simple Animation has a time that defines the amount of seconds that the animation last. A
 * speed that refers to the speed of the animation is Ticks per Seconds. And a Shape Storage where
 * all the shapes included in the animation are stored. Each shape has a String index value.
 */

public class SimpleShapeAnimation implements ShapeAnimationModel {
  private int x;
  private int y;
  private int speed;
  private int width;
  private int height;
  private Map<String, AbstractShape> shapeStorage = new LinkedHashMap<>();
  private Map<Integer, Motion> storeMotion = new LinkedHashMap<>();
  private Map<Integer, Motion> moveStorage = new LinkedHashMap<>();
  private StringBuilder text = new StringBuilder();

  /**
   * Constructs a (@code SimpleShapeAnimation) object.
   *
   * @param time the time the animation lasts.
   * @param speed the speed of the animation. Ticks/Seconds.
   * @param shapeStorage list where all the shapes in the animation are stored.
   * @para text prints the Shape Animation.
   */

  public SimpleShapeAnimation(int time, int speed, int width, int height, LinkedHashMap<String,
          AbstractShape> shapeStorage) {

    if (time <= 0) {
      throw new IllegalArgumentException("Time is not Valid!");
    }
    if (speed <= 0) {
      throw new IllegalArgumentException("Speed is not Valid!");
    }
    if (shapeStorage == null) {
      throw new IllegalArgumentException("storage cannot be null");
    }

    this.x = x;
    this.y = y;
    this.speed = speed;
    this.width = width;
    this.height = height;
    this.shapeStorage = new LinkedHashMap<>();
    this.text = new StringBuilder();
  }

  @Override
  public String render() {
    int runTime = 0;
    text.append("canvas " + this.x + " " + this.y + " " + this.width + " " + this.height);
    for (AbstractShape aS : shapeStorage.values()) {
      text.append("\nshape " + aS.getBunchOfKeyFrames().entrySet().iterator()
          .next().getValue().getName() + " " + aS.whatShape());
      text.append(aS.printAnimation());
    }
    return text.toString();
  }

  @Override
  public void stopAnimation() {
    // This method is currently empty due that the model is not currently running a real animation.
    // Moreover, this method is designed to be used in a future implementation. Future Assignment.
  }

  @Override
  public void addKeyFrame(String index, KeyFrame kf, int time) {
    if (!shapeStorage.get(index).getBunchOfKeyFrames().isEmpty()) {
      if (kf.getTime() >= shapeStorage.get(index).getLastKey(
          shapeStorage.get(index).getBunchOfKeyFrames())) {
        shapeStorage.get(index).addKeyFrame(kf, time);
      }
      else {
        throw new IllegalArgumentException("Time is overlapping");
      }
    }
    else {
      if (time > 0) {
        shapeStorage.get(index).addKeyFrame(kf, time);
      }
      else {
        throw new IllegalArgumentException("Each shape has to start for tick 1");

      }
    }

  }


  @Override
  public void addMotion(String index, Motion m, int time) {
    if (!shapeStorage.get(index).getBunchOfMotions().isEmpty()) {
      if (m.getTime() >= shapeStorage.get(index).getLastKeyM(
          shapeStorage.get(index).getBunchOfMotions())) {
        shapeStorage.get(index).addMotion(m, time);
      }
      else {
        throw new IllegalArgumentException("Time is overlapping");
      }
    }
    else {
      if (time > 0) {
        shapeStorage.get(index).addMotion(m, time);
      }
      else {
        throw new IllegalArgumentException("Each shape has to start for tick 1");
      }
    }
    if (m.getPosX() != m.getPosX2() || m.getPosY() != m.getPosY2()) {
      this.moveStorage.put(time, m);
    }
  }


  @Override
  public int getLastTick() {
    ArrayList<Integer> getMax = new ArrayList<>();
    for (AbstractShape s: this.getShapes().values()) {
      getMax.add(s.getLastKey(s.getBunchOfKeyFrames()));
    }
    return Collections.max(getMax);
  }


  @Override
  public Map<String,AbstractShape> tweenAllMotion(int tick) {

    Map<String,AbstractShape> result = new LinkedHashMap<>();

    for (AbstractShape s: this.getShapes().values()) {
      //converts keyframes to motions
      s.getMotion();
      for (Motion m: s.getBunchOfMotions().values()) {
        if (tick >= m.getTime() && tick <= m.getTime2()) {
          result.put(m.getName(), this.createShapeNew(m.getName(), m.getShape()));
          Motion newVal = new Motion(m.getName(),m.getShape(),m.getTime(),
                  m.getPosX(),m.getPosY(),m.getColor(),
                  m.getHeight(),m.getWidth(), m.getDegree(),m.getTime2(),
                  m.getPosX2(),m.getPosY2(),m.getColor2(),
                  m.getHeight2(),m.getWidth2(), m.getDegree2());

          for (Motion m2: this.getShapes().get(m.getName()).getBunchOfMotions().values()) {
            newVal = m2.giveNewAttr(newVal, tick);
            result.get(newVal.getName()).addMotion(newVal, tick);
          }
        }
      }
    }
    return result;
  }

  @Override
  public void modifyKeyFramePos(String index, double x, double y, int time) {
    shapeStorage.get(index).changePos(x,y, time);
  }

  @Override
  public void modifyKeyFrameColor(String index, java.awt.Color color, int time) {
    shapeStorage.get(index).changeColor(color, time);
  }

  @Override
  public void modifyKeyFrameDimensions(String index, double height, double width, int time) {
    shapeStorage.get(index).changeDimensions(height, width, time);
  }

  @Override
  public void deleteKeyFrame(String index, int time) {
    shapeStorage.get(index).deleteKeyFrame(time);
  }

  public void insertKeyFrame(String index, int time, KeyFrame kf) {
    shapeStorage.get(index).insertKeyFrame(time, kf);
  }

  @Override
  public void deleteShape(String name) {
    shapeStorage.remove(name);
  }

  @Override
  public void increaseSpeed() {
    this.speed = this.speed + 1;
  }

  @Override
  public void decreaseSpeed() {
    this.speed = this.speed - 1;
  }

  @Override
  public Map<String,AbstractShape> getShapes() {
    return this.shapeStorage;
  }

  @Override
  public List<KeyFrame> getMotionsForShape(AbstractShape s) {
    List<KeyFrame> lkf = new ArrayList<>();

    for (AbstractShape sh: this.shapeStorage.values()) {
      if (sh.whatShape().equals(s.whatShape())) {
        sh.getKeyFrames(lkf);
      }
    }
    return lkf;
  }

  @Override
  public int getWidth() {
    return this.width;
  }

  @Override
  public int getHeight() {
    return this.height;
  }

  @Override
  public void setWidth(int w) {
    this.width = w;
  }

  @Override
  public void setHeight(int h) {
    this.height = h;
  }

  @Override
  public int getX() {
    return this.x;
  }

  @Override
  public int getY() {
    return this.y;
  }

  @Override
  public void setX(int x) {
    this.x = x;
  }

  @Override
  public void setY(int y) {
    this.y = y;
  }

  @Override
  public void createShape(String name, AbstractShape shape) {
    if (shape.whatShape().equals("rectangle")) {
      shapeStorage.put(name, new RectangleShape());
    }
    else if (shape.whatShape().equals("circle")) {
      shapeStorage.put(name, new CircleShape());
    }
    else if (shape.whatShape().equals("triangle")) {
      shapeStorage.put(name, new TriangleShape());
    }
    else if (shape.whatShape().equals("ellipse")) {
      shapeStorage.put(name, new EllipseShape());
    }
    else {
      throw new IllegalArgumentException("Shape can't be created");
    }
  }


  /**
   * This method creates a new shape.
   *
   * @param name the name of the shape.
   * @param shape the shape type.
   * @return a new instance of an abstract shape.
   */
  public AbstractShape createShapeNew(String name, AbstractShape shape) {
    AbstractShape s = null;
    if (shape.whatShape().equals("rectangle")) {
      s = new RectangleShape();
    }
    else if (shape.whatShape().equals("ellipse")) {
      s = new EllipseShape();
    }
    return s;
  }
}
