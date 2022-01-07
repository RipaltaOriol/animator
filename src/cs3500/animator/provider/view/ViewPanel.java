package cs3500.animator.provider.view;

import java.awt.Graphics;
import java.awt.Color;
import java.util.List;
import javax.swing.JPanel;

import cs3500.animator.model.Shape;

/**
 * A Class that extends a JPanel for drawing an Animation. This Panel has a list of Shapes it
 * will display that can be updated at any point.
 */
public class ViewPanel extends JPanel {
  private List<Shape> toDraw;

  /**
   * Constructor for a ViewPanel.
   * @param toDraw the list of elements to draw
   */
  public ViewPanel(List<Shape> toDraw) {
    super();
    this.toDraw = toDraw;
  }

  /**
   * An OverRide of the JPanel class.
   * Uses graphics to clear the panel and then add the visual representation of the Shapes in the
   * the toDraw list to the panel.
   * @param g - the Graphics object used to draw the shapes
   */
  public void paint(Graphics g) {
    super.paint(g);
    for (Shape s: toDraw) {
      g.setColor(new Color(s.getColor().getRed(),
              s.getColor().getBlue(), s.getColor().getGreen()));
      if (s.getType() == Shape.ShapeType.rectangle) {
        g.fillRect(s.getX(), s.getY(), s.getWidth(), s.getHeight());
      } else {
        g.fillOval(s.getX(), s.getY(), s.getWidth(), s.getHeight());
      }
    }

  }


  /**
   * This function replaces the list of Shapes to be drawn and repaints the panel to reflect the
   * update.
   * @param shapes - the new list of shapes to be drawn
   */
  protected void update(List<Shape> shapes) {
    this.toDraw = shapes;
    this.revalidate();
    this.repaint();
  }



}
