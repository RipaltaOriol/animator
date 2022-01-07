package cs3500.animator.model;

import java.util.List;
import java.util.Map;

/**
 * This is the Model for a Shape Animator.
 * <p></p>
 * The main method will run the animation and give the output.
 * The stop animation is designed to stop the current animation before it finishes.
 * <p></p>
 * The Add, Modify and Delete Key Frame is used to extend an animation, to modify a current
 * animation, and to delete a segment of a current animation.
 * <p></p>
 * The speed of the animation can be modified through the methods: Increase Speed and Decrease
 * Speed.
 * <p></p>
 * The method CreateShape is used to create another shape to be animated.
 */

public interface ShapeAnimationModel {

  /**
   * The function of this method is to run the animation. It calls the
   * Shape Storage and processes all the shapes. For the purpose of this Assignment, just
   * for now, this method is going to return the output of the shape animation as a String.
   *
   * @return Animation output in String
   */
  public String render();

  /**
   * This method is designed to stop the animation that is currently in process and hasn't been
   * yet completed.
   */
  public void stopAnimation();

  /**
   * The method Add KeyFrame adds a new key frame to an existing shape in the Shape Storage. This
   * method allows the animation to be elaborated.
   *
   * @param index name by which the shape is found and stored in the Shape Storage.
   * @param kf the key frame that is to be added within the shape.
   * @param time the time when the key frame is supposed to appear.
   */
  public void addKeyFrame(String index, KeyFrame kf, int time);

  /**
   * The method Add Motion adds a new motion to an existing shape in the Shape Storage. This
   * method allows the animation to be elaborated.
   *
   * @param index name by which the shape is found and stored in the Shape Storage.
   * @param m the motion that is to be added within the shape.
   * @param time the time when the motion is supposed to appear.
   */
  public void addMotion(String index, Motion m, int time);

  /**
   * The method Modify Key Frame Pos modifies the position of a shape in an existing Key Frame.
   *
   * @param index name by which the shape is found and stored in the Shape Storage.
   * @param x new X position for the existing Key Frame within the Shape.
   * @param y new Y position for the existing Key Frame within the Shape.
   * @param time the time where the existing Key Frame is found within the shape.
   */
  public void modifyKeyFramePos(String index, double x, double y, int time);

  /**
   * The method Modifies Key Frame Color modifies the color of a shape in an existing Key Frame.
   *
   * @param index name by which the shape is found and stored in the Shape Storage.
   * @param newColor new Color for the existing Key Frame within the Shape.
   * @param time the time where the existing Key Frame is found within the shape.
   */
  public void modifyKeyFrameColor(String index, java.awt.Color newColor, int time);

  /**
   * The method Modifies Key Frame Dimensions modifies the dimensions of a shape in an existing Key
   * Frame.
   *
   * @param index name by which the shape is found and stored in the Shape Storage.
   * @param width new Width for the existing Key Frame within the Shape.
   * @param height new Height for the existing Key Frame within the Shape.
   * @param time the time where the existing Key Frame is found.
   */
  public void modifyKeyFrameDimensions(String index, double height, double width, int time);

  /**
   * The method Delete Key Frame deletes and existing Key Fame of Shape.
   *
   * @param index name by which the shape is found and stored in the Shape Storage.
   * @param time the time where the existing Key Frame is found.
   */
  public void deleteKeyFrame(String index, int time);

  /**
   * This method deletes all animations of a given shape.
   *
   * @param name shape to be deleted.
   */

  public void deleteShape(String name);

  /**
   * IncreaseSpeed increases the speed of the animation by one unit every time.
   */
  public void increaseSpeed();

  /**
   * DecreaseSpeed decreases the speed of the animation by one unit every time.
   */
  public void decreaseSpeed();

  /**
   * Returns a Linked HashMap of the Shapes.
   * @return Linked HashMap of the Shapes.
   */
  public Map<String,AbstractShape> getShapes();

  /**
   * Returns a List of the Motions of the given shape.
   * @param s the given shape.
   * @return a List of the Motions of the given shape.
   */
  public List<KeyFrame> getMotionsForShape(AbstractShape s);

  /**
   * Creates a new Shape to be animated. The shape is stored in the a HashMap to be used,
   * modified, or deleted. The method takes a shape name and shape type in oder to store the shape
   * in the HashMap and know what shape to create.
   *
   * @param name Name given to the Shape to be stored. Name is used as Index.
   * @param shape Type of Shape that wants to be created.
   */
  public void createShape(String name, AbstractShape shape);

  /**
   * Returns the height of the ShapeAnimationModel.
    * @return Height in integer.
   */
  public int getHeight();

  /**
   * Returns the width of the ShapeAnimationModel.
   * @return Width in integer.
   */
  public int getWidth();

  /**
   * Sets the height of the ShapeAnimationModel.
   * @param h Height to be set.
   */
  public void setHeight(int h);

  /**
   * Sets the width of the ShapeAnimationModel.
   * @param w Width to be set in integer.
   */
  public void setWidth(int w);

  /**
   * Returns the X Position of the ShapeAnimationModel.
   * @return X Position in integer.
   */
  public int getX();

  /**
   * Returns the Y Position of the ShapeAnimationModel.
   * @return Y Position in integer.
   */
  public int getY();

  /**
   * Sets the X Position of the ShapeAnimationModel.
   * @param h X-position to be set.
   */
  public void setX(int h);

  /**
   * Sets the Y Position of the ShapeAnimationModel.
   * @param w Y-position to be set.
   */
  public void setY(int w);

  /**
   * This method gives out a LinkedHashMap of Motions with their attributes changed according to
   * the given time.
   * @param time The time according to which the calculations have to be made.
   * @return The map of motions with the changed values associated with the given time.
   */
  public Map<String,AbstractShape> tweenAllMotion(int time);

  /**
   * This method inserts a KeyFrame into the given shape at the given time,
   * while editing the keyframes in the visual view.
   * @param index name of the shape of the keyframe.
   * @param time during which the keyframe needs to be added.
   * @param kf the keyframe that needs to be added.
   */
  public void insertKeyFrame(String index, int time, KeyFrame kf);

  /**
   * In order to make an animation loop we need to know when it ends, so
   * we can start it again. This method gives the highest time out of all the
   * keyframes in the storage, which would be the last tick associated to any
   * of the shapes.
   * @return last tick of the animation
   */
  public int getLastTick();

}
