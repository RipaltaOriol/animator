package cs3500.animator.view;


import cs3500.animator.model.AbstractShape;
import cs3500.animator.model.Motion;


import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * This view creates a visual frame with the given animation data and
 * plays the animation.
 */
public class VisualViewImpl extends JFrame implements View {
  private DrawShape allShapes;

  /**
     * This is the constructor fo this view. It sets up
     * the frame and adds the necessary components to it.
   */
  public VisualViewImpl() {
    super();
    allShapes = new DrawShape();
    this.setPreferredSize(new Dimension(800,800)); //this.sa.getWidth(),this.sa.getHeight()));
    JScrollPane scrollBar = new JScrollPane(allShapes);
    scrollBar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    scrollBar.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    this.add(scrollBar);
    this.pack();
    this.setVisible(true);
  }

  @Override
  public void runAnimation(String fn, Appendable a) {
   // not applicable for a visual view.
  }

  @Override
  public void showShapes(Map<String, AbstractShape> sg) {
    this.allShapes.displayPerTick(sg);
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


  /**
   * This class creates a JPanel instance which constitutes of
   * the drawings of all shapes of the animation.
   */
  private class DrawShape extends JPanel {

    Map<String, AbstractShape> shapeGraphics = new LinkedHashMap<>();

    @Override
    public void paint(Graphics g) {
      Graphics2D gTwoD = (Graphics2D) g;
      for (AbstractShape s : this.shapeGraphics.values()) {
        for (Motion m : s.getBunchOfMotions().values()) {
          if (m.getShapeStr().equals("rectangle")) {
            gTwoD.setColor(new Color(m.getColor().getRed(),
                m.getColor().getGreen(), m.getColor().getBlue()));
            gTwoD.rotate(m.getDegree() / 360 * 2 * Math.PI,
                m.getPosX() + m.getWidth() / 2, m.getPosY()
                    + m.getHeight() / 2);
            gTwoD.fillRect((int) m.getPosX(), (int) m.getPosY(),
                (int) m.getWidth(), (int) m.getHeight());
          } else if (m.getShapeStr().equals("ellipse")) {
            gTwoD.setColor(new Color(m.getColor().getRed(),
                m.getColor().getGreen(), m.getColor().getBlue()));
            gTwoD.rotate(m.getDegree() / 360 * 2 * Math.PI,
                m.getPosX() + m.getWidth() / 2, m.getPosY()
                    + m.getHeight() / 2);
            gTwoD.fillOval((int) m.getPosX(), (int) m.getPosY(),
                (int) m.getWidth(), (int) m.getHeight());
          }
        }
      }
    }

    /**
     * This method basically takes the given Map of AbstractShapes
     * and draws shapes for the visual frame according to the given
     * shapes by calling the repaint method.
     * @param sg Map of shapes that needs to be drawn.
     */
    public void displayPerTick(Map<String, AbstractShape> sg) {
      this.shapeGraphics = sg;
      repaint();
    }
  }
}



