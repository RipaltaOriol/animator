package cs3500.animator.provider.view;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;


import cs3500.animator.model.Animation;
import cs3500.animator.model.Motion;
import cs3500.animator.model.Shape;
import cs3500.animator.util.ShapeTweener;

/**
 * A View that represents an Animation by animating it in a JFrame.
 */
public class VisualAnimationView extends JFrame implements IAnimatorView {
  private int ticksPerSecond;
  private ViewPanel panel;
  protected int tick;

  /**
   * Constructor for the VisualAnimationView.
   * @param ticksPerSecond - the speed of the Animation
   * @param width - width of canvas
   * @param height - height of canvas
   */
  public VisualAnimationView(int ticksPerSecond, int width, int height) {
    super();
    if (ticksPerSecond <= 0) {
      throw new IllegalArgumentException("Invalid ticks per second");
    }
    this.tick = 0;
    List<Shape> s = new ArrayList<>();
    this.panel = new ViewPanel(s);
    this.ticksPerSecond = ticksPerSecond;
    this.setSize(width,height);
    panel.setPreferredSize(new Dimension(width, height));
    JScrollPane scrollPane = new JScrollPane(panel);
    scrollPane.setSize(width, height);
    this.add(scrollPane);
    this.pack();
    this.setVisible(true);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

  }

  /**
   * This is a time based View so it cannot implement this function.
   * @param elements - all the Animations to be represented
   * @throws IllegalAccessException - always throws this.
   */

  public void getAllView(List<Animation> elements) throws IllegalAccessException {
    throw new IllegalAccessException("Method not supported by view");
  }

  /**
   * Updates the shapes that the JFrame will display by accepting a new list of motions. This will
   * use the motions to determine the shapes to be drawn using tweening and display them.
   * @param motions the list of motions happening at a time
   */
  public void getInstanceView(List<Motion> motions) {
    List<Shape> s = new ArrayList<>();
    for (Motion m: motions) {
      if (m.getStart().getTick() > tick || m.getEnd().getTick() < tick) {
        throw new IllegalArgumentException("Motions not valid for this tick");
      }
      Shape toAdd = ShapeTweener.tweenShape(tick, m.getStart(), m.getEnd());
      s.add(toAdd);
    }
    this.panel.update(s);
    this.revalidate();
  }

  /**
   * Updates the tick counter by 1.
   * @return the updated tick
   */
  public int nextTick() {
    tick = tick + 1;
    return tick;
  }

  public int getTicksPerSecond() {
    return ticksPerSecond;
  }


  /**
   * Updates the ticks per second.
   * @param dT the change in ticks per second
   */
  protected void updateTPS(int dT) {
    if (ticksPerSecond + dT <= 0) {
      throw new IllegalArgumentException("TPS can't be less than 0");
    }
    ticksPerSecond += dT;

  }

  public AnimationEdit getEdits() {
    return null;
  }

}
