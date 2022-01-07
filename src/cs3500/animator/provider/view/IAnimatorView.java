package cs3500.animator.provider.view;

import java.util.List;

import cs3500.animator.model.Animation;
import cs3500.animator.model.Motion;
import cs3500.animator.provider.view.AnimationEdit;

/**
 * An interface for getting a representation of an Animation. Views can either provide entire
 * representations of an Animation, or be instance-based.
 */
public interface IAnimatorView {

  /**
   * Creates a representation of an entire Animation over time.
   * @param elements - all the Animations to be represented
   */
  void getAllView(List<Animation> elements) throws IllegalAccessException;

  /**
   * Creates a representation of an Animation at a single instance in time.
   * @param motions - all the motions occuring at a given time
   */
  void getInstanceView(List<Motion> motions) throws IllegalAccessException;

  /**
   * Increments the tick State for Views that are Instance based.
   * @return the updated tick of the view.
   */
  int nextTick();

  /**
   * Returns the tick per second.
   * @return the tick per second
   */
  int getTicksPerSecond();

  /**
   * Implemented by views that allow editing.
   * @return edits the user wishes to make
   */
  AnimationEdit getEdits();

}
