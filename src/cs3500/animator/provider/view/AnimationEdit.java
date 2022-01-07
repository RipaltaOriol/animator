package cs3500.animator.provider.view;

import cs3500.animator.model.ShapeState;

/**
 * A class to represent an edit of an Animation. Stores all information necessary to edit and
 * specifies the type;
 */
public class AnimationEdit {
  private final ShapeState edits;
  private final String animationName;
  public final EditType type;

  /**
   * Specifies the type of edit being performed.
   *  remove - removing a single keyframe
   *  set - adding or editing a specific keyframe
   *  delete - delete an entire shape
   *  create - creates a new shape and initializes a key frame as given
   */
  public enum EditType { remove, set, delete, create }


  /**
   * Constructs an Animation edit.
   * @param animationName - name of the animation to be edited or created
   * @param type - type of edit
   * @param edits - information for remove, set, create edits
   */
  public AnimationEdit(String animationName, EditType type, ShapeState edits) {
    this.edits = edits;
    this.animationName = animationName;
    this.type = type;
  }

  /**
   * Getter for animation name.
   * @return - name of animation to be edited
   */
  public String getAnimationName() {
    return animationName;
  }

  /**
   * Getter for the edits.
   * @return - a ShapeState containing edit info
   */
  public ShapeState getEdits() {
    return new ShapeState(edits);
  }
}
