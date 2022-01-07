package cs3500.animator.view;

import cs3500.animator.model.ShapeAnimationModel;
import cs3500.animator.model.AbstractShape;

import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Map;

/**
 * This class represents the textual view of the animation. It converts
 * the input into logs of motions and displays it.
 */
public class TextViewImpl implements View {
  private ShapeAnimationModel sm;

  /**
   * Constructor for this class.
   * @param sm ShapeAnimationModel containing the data for this animation.
   *
   */
  public TextViewImpl(ShapeAnimationModel sm, int speed) {
    this.sm = sm;
  }

  @Override
  public void runAnimation(String fn, Appendable a) {
    try {
      a.append(sm.render());
      if (fn.isEmpty()) {
        System.out.append(sm.render());
      }
      else {
        FileWriter out = new FileWriter(fn);
        BufferedWriter writer = new BufferedWriter(out);
        writer.write(sm.render());
        writer.close();
      }
    } catch (IOException e) {
      throw new IllegalStateException("IO exception");
    }
  }

  @Override
  public void showShapes(Map<String, AbstractShape> sg) {
    //not applicable for this view

  }

  @Override
  public boolean getStartA() {
    return false;
  }

  @Override
  public boolean geRestartA() {
    return false;
  }

  @Override
  public boolean getPaused() {
    return false;
  }

  @Override
  public boolean getLooping() {
    return false;
  }
}
