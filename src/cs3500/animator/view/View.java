package cs3500.animator.view;


import cs3500.animator.model.AbstractShape;

import java.util.Map;

/**
 * This interface represents all the necessary functions of a View.
 * It helps display the given motions
 * with different styles of views, taking into account its size,
 * location and speed.
 */

public interface View  {

  /**
   * This method runs the given animation according to its desired view type.
   * @param fn file to store text or SVG view output.
   * @param a  to store the output in a textViewcase.
   */
  public void runAnimation(String fn, Appendable a);

  /**
   * Displays the shape Panel and required by the animation
   * during a certain tick.
   * @param sg the hashMap with the shape values.
   */
  public void showShapes(Map<String, AbstractShape> sg);

  /**
   * Returns the start flag.
   * @return true/false according the start flag.
   */
  public boolean getStartA();

  /**
   * Returns the restart flag.
   * @return true/false according the restart flag.
   */
  public boolean geRestartA();

  /**
   * Returns the pause flag.
   * @return true/false according the pause flag.
   */
  public boolean getPaused();

  /**
   * Returns the looping flag.
   * @return true/false according the looping flag.
   */
  public boolean getLooping();
}
