package cs3500.animator.model;


import java.io.BufferedReader;
import java.io.StringReader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Objects;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * The Abstract Class Abstract Shape protects all the Shapes that are available in the Shape
 * Animation Model to be Animated.
 * <p></p>
 * The Bunch Of Key Frames is a Hash Map of all the Key Frames that are included in a Shape. This
 * allows that Shape to be animated. Moreover, The Abstract class allows Key Frames to be added to
 * existing shapes, allow existing Key Frames to be modified, and allows existing Key Frames to
 * deleted.
 */

public abstract class AbstractShape {

  private Map<Integer, KeyFrame> bunchOfKeyFrames = new LinkedHashMap<>();
  private Map<Integer, Motion> bunchOfMotions = new LinkedHashMap<>();


  /**
   * The method Add Key Frame adds a new Key Frame to an existing Shape. A Shape can either be a
   * Rectangle, Circle, Ellipse or Triangle.
   *
   * @param kf the Key Frame that wants to be added to the Shape.
   * @param time the time where the Key Frame is to be added. Time is given in Ticks x Seconds.
   */
  protected void addKeyFrame(KeyFrame kf, int time) {

    bunchOfKeyFrames.put(time, kf);
  }


  /**
   * The method Add Motion adds a new Motion to an existing Shape. A Shape can either be a
   * Rectangle, Circle, Ellipse or Triangle.
   *
   * @param m the Motion that wants Motion is to be added. Time is given in Ticks x Seconds.
   */
  protected void addMotion(Motion m, int time) {

    bunchOfMotions.put(time, m);
  }

  /**
   * The method Change Position allows a position modification on an existing Key Frame withing a
   * Shape.
   *
   * @param x new X position of the Key Frame.
   * @param y new Y position of the Key Frame.
   * @param time the index of where the Key Frame is found inside the Bunch of Key Frames.
   */
  protected void changePos(double x, double y, int time) {
    bunchOfKeyFrames.replace(time, new KeyFrame(bunchOfKeyFrames.get(time).getName(),
        this,bunchOfKeyFrames.get(time).getTime(), x, y,
        bunchOfKeyFrames.get(time).getColor(),
        bunchOfKeyFrames.get(time).getHeight(),
        bunchOfKeyFrames.get(time).getWidth(),
        bunchOfKeyFrames.get(time).getDegree()
    ));

  }

  /**
   * The method Change Color allows a color modification on an existing Key Frame within a Shape.
   *
   * @param color new Color of the Key Frame.
   * @param time the index of where the Key Frame is found inside the Bunch of Key Frames.
   */
  protected void changeColor(java.awt.Color color, int time) {
    bunchOfKeyFrames.replace(time, new KeyFrame(bunchOfKeyFrames.get(time).getName(),this,
        bunchOfKeyFrames.get(time).getTime(),
        bunchOfKeyFrames.get(time).getPosX(),
        bunchOfKeyFrames.get(time).getPosY(),
        color,
        bunchOfKeyFrames.get(time).getHeight(),
        bunchOfKeyFrames.get(time).getWidth(),
        bunchOfKeyFrames.get(time).getDegree()
    ));
  }

  /**
   * The method Change Dimensions allows a dimension modification on an existing Key Frame withing
   * a Shape.
   *
   * @param height new Height of the Key Frame.
   * @param width new Width of the Key Frame.
   * @param time the index of where the Key Frame is found inside the Bunch of Key Frames.
   */
  protected void changeDimensions(double height, double width, int time) {
    bunchOfKeyFrames.replace(time, new KeyFrame(bunchOfKeyFrames.get(time).getName(),this,
        bunchOfKeyFrames.get(time).getTime(),
        bunchOfKeyFrames.get(time).getPosX(),
        bunchOfKeyFrames.get(time).getPosY(),
        bunchOfKeyFrames.get(time).getColor(),
        height, width, bunchOfKeyFrames.get(time).getDegree()));
  }

  /**
   * The method Delete Key Fame deletes an existing Key Frame inside a this Shape. Delete Key Frame
   * takes a integer, time, which is the index of the Key Frame that is deleted.
   *
   * @param time the index where the Key Frame is found inside the Bunch of Key Frames.
   */
  protected void deleteKeyFrame(int time) {
    bunchOfKeyFrames.remove(time);
  }


  /**
   * This method inserts a keyFrame into the model, while he user is trying to
   * edit the animation in real time.
   * @param time time at which KeyFrame is to be inserted.
   * @param kf The KeyFrame to be inserted.
   */
  protected void insertKeyFrame(int time, KeyFrame kf) {
    if (bunchOfKeyFrames.containsKey(time)) {
      throw new IllegalArgumentException("There already is a KeyFrame set for this time. Use" +
          "the Modify KeyFrame option if you wish to modify it.");
    }
    else {
      bunchOfKeyFrames.put(time, kf);
      bunchOfKeyFrames = bunchOfKeyFrames.entrySet().stream()
          .sorted(Map.Entry.comparingByKey())
          .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
              (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }
  }

  /**
   * The method whatShape is used to know what type of shape the AbstractShape belong to.
   *
   * @return a String with the name of the shape.
   */
  public String whatShape() {
    if (isRectangle()) {
      return "rectangle";
    }
    else if (isCircle()) {
      return "circle";
    }
    else if (isTriangle()) {
      return "triangle";
    }
    else if (isEllipse()) {
      return "ellipse";
    }
    else {
      throw new IllegalArgumentException("Shape not found!");
    }
  }

  /**
   * The method isRectangle returns a boolean that represents whether the AbstractShape is a
   * rectangle.
   *
   * @return true if the AbstractShape is a Rectangle.
   */
  protected abstract boolean isRectangle();

  /**
   * The method isCircle returns a boolean that represents whether the AbstractShape is a circle.
   *
   * @return true if the AbstractShape is a Circle.
   */
  protected abstract boolean isCircle();

  /**
   * The method isTriangle returns a boolean that represents whether the AbstractShape is a
   * triangle.
   *
   * @return true if the AbstractShape is a Triangle.
   */
  protected abstract boolean isTriangle();

  /**
   * The method isTriangle returns a boolean that represents whether the AbstractShape is a
   * triangle.
   *
   * @return true if the AbstractShape is a Triangle.
   */
  protected abstract boolean isEllipse();


  /**
   * Gets the last key of the given Linked Hash Map of KeyFrames.
   * @param bunchOfKeyFrames given list of KeyFrames.
   * @return returns the last key, which in this case is integer value.
   */
  public Integer getLastKey(Map<Integer, KeyFrame> bunchOfKeyFrames) {
    Iterator<Map.Entry<Integer, KeyFrame>> entries = bunchOfKeyFrames.entrySet().iterator();
    int mapKey = 0;

    while (entries.hasNext()) {
      Map.Entry<Integer, KeyFrame> entry = entries.next();
      mapKey = entry.getKey();
    }
    return mapKey;
  }

  /**
   * Gets the last key of the given Linked Hash Map of Motions.
   * @param bunchOfMotions given list of Motions.
   * @return returns the last key, which in this case is integer value.
   */
  public Integer getLastKeyM(Map<Integer, Motion> bunchOfMotions) {
    Iterator<Map.Entry<Integer, Motion>> entries = bunchOfMotions.entrySet().iterator();
    int mapKey = 0;

    while (entries.hasNext()) {
      Map.Entry<Integer, Motion> entry = entries.next();
      mapKey = entry.getKey();
    }
    return mapKey;
  }

  /**
   * The method Print Animation gives the Key Frames as a String. It converts the whole animation
   * into a String.
   *
   * @return the animation of this Shape converted to a String.
   */
  public String printAnimation() {
    StringBuilder shapeAnimation = new StringBuilder();

    for (KeyFrame kf: this.bunchOfKeyFrames.values()) {
      if (kf.getTime() == bunchOfKeyFrames.entrySet().iterator().next().getKey()) {
        shapeAnimation.append("\nmotion " + kf.getName() + " "
            + kf.getTime() + " " + kf.getPosX()
            + " " + kf.getPosY() + " " + kf.getWidth() + " "
            + kf.getHeight() + " " +
            kf.getColor().getRed() + " " + kf.getColor().getGreen() + " "
            + kf.getColor().getBlue() + " " + kf.getDegree() );
      }
      else if (kf.getTime() == this.getLastKey(bunchOfKeyFrames)) {
        shapeAnimation.append( "   " + kf.getTime() + " " + kf.getPosX()
            + " " + kf.getPosY() + " " + kf.getWidth() + " "
            + kf.getHeight() + " " +
            kf.getColor().getRed() + " " + kf.getColor().getGreen() + " "
            + kf.getColor().getBlue() + " " + kf.getDegree());
      }
      else {
        shapeAnimation.append("   " + kf.getTime() + " " + kf.getPosX()
            + " " + kf.getPosY() + " " + kf.getWidth() + " "
            + kf.getHeight() + " " +
            kf.getColor().getRed() + " " + kf.getColor().getGreen() + " "
            + kf.getColor().getBlue() + " " + kf.getDegree() + "\n" +
            "motion " + kf.getName() + " " + kf.getTime() + " "
            + kf.getPosX()
            + " " + kf.getPosY() + " " + kf.getWidth() + " "
            + kf.getHeight() + " " +
            kf.getColor().getRed() + " " + kf.getColor().getGreen() + " "
            + kf.getColor().getBlue() + " " + kf.getDegree());
      }
    }
    return shapeAnimation.toString();
  }


  /**
   * This method computes the string output of the TextView of the KeyFrames.
   * Then it identifies the word motion, in the output and for that particular
   * motion, creates a new Motion in the given shape.
   */
  public void getMotion() {
    String kfs = this.printAnimation();
    BufferedReader sc = new BufferedReader(new StringReader(kfs));
    Scanner s = new Scanner(sc);
    Objects.requireNonNull(sc, "Must have non-null readable source");
    s.useDelimiter(Pattern.compile("(\\p{Space}+|#.*)+"));
    while (s.hasNext()) {
      String word = s.next();
      if (word.equals("motion")) {
        readMotion(s);
      }
    }
  }

  /**
   * This method reads motion from the text output the given scanner is scanning,
   * and adds that motion to this shape.
   * @param s Scanner used for scanning the text output.
   */
  public void readMotion(Scanner s) {
    String[] fieldNames = new String[]{
        "initial time",
        "initial x-coordinate", "initial y-coordinate",
        "initial width", "initial height",
        "initial red value", "initial green value", "initial blue value", "initial degree",
        "final time",
        "final x-coordinate", "final y-coordinate",
        "final width", "final height",
        "final red value", "final green value", "final blue value", "final degree"
    };
    int[] vals = new int[18];
    String name;
    if (s.hasNext()) {
      name = s.next();
    } else {
      throw new IllegalStateException("Motion: Expected a shape name, but no more input available");
    }
    for (int i = 0; i < 18; i++) {
      vals[i] = getInt(s, "Motion", fieldNames[i]);
    }
    this.addMotion(new Motion(name,this,
        vals[0], vals[1], vals[2 ], new java.awt.Color(vals[5 ],
        vals[6 ], vals[7 ]), vals[4 ], vals[3 ], vals[8],
        vals[9], vals[10], vals[11 ],new java.awt.Color( vals[14 ],
        vals[15 ], vals[16 ]), vals[13], vals[12 ], vals[17]), vals[0]);
  }

  private static int getInt(Scanner s, String label, String fieldName) {
    if (s.hasNextDouble()) {
      return (int) s.nextDouble();
    } else if (s.hasNext()) {
      throw new IllegalStateException(String.format("%s: expected integer " +
          "for %s, got: %s", label, fieldName, s.next()));
    } else {
      throw new IllegalStateException(
          String.format("%s: expected integer for %s, but no more input available",
              label, fieldName));
    }
  }

  /**
   * Returns a list of KeyFrames of this model.
   * @param kf The list of KeyFrame to be returned with added values.
   * @return The list of KeyFrame of this shape.
   */
  public List<KeyFrame> getKeyFrames(List<KeyFrame> kf) {

    for (KeyFrame lkf: bunchOfKeyFrames.values()) {
      kf.add(lkf);
    }

    return kf;
  }

  /**
   * This method return the KeyFrame HashMap of this shape.
   * @return Map of KeyFrames with an Integer time as the key.
   */
  public Map<Integer, KeyFrame> getBunchOfKeyFrames() {
    return this.bunchOfKeyFrames;
  }

  /**
   * This method return the Motion HashMap of this shape.
   * @return Map of Motions with an Integer time as the key.
   */
  public Map<Integer, Motion> getBunchOfMotions() {
    return this.bunchOfMotions;
  }

  /**
   * Getter method for the states of Shapes.
   * @return a list of ShapeStates.
   */
  public List<ShapeState> getShapeStates() {
    List<ShapeState> result = new ArrayList<>();

    for (KeyFrame kf: this.getBunchOfKeyFrames().values()) {
      result.add(new ShapeState(kf.getTick(), new Shape(kf.getSType(),
          (int) kf.getWidth(), (int)kf.getHeight(),
          (int)kf.getPosX(),(int) kf.getPosY(), kf.getColorM())));
    }
    return result;
  }

}
