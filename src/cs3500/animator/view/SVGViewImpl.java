package cs3500.animator.view;

import cs3500.animator.model.AbstractShape;
import cs3500.animator.model.Motion;
import cs3500.animator.model.ShapeAnimationModel;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

/**
 * This view creates a text output with xml code for the given animation. This text
 * can be saved in a .xml file and can be opened in any browser to view the animation.
 */
public class SVGViewImpl implements View {

  private ShapeAnimationModel sm;
  private int speed;

  /**
     * Constructor for SVGViewImpl.
     * @param sm ShapeAnimationModel with data of the animation.
     * @param speed speed at which the animation shoud play.
  */
  public SVGViewImpl(ShapeAnimationModel sm, int speed) {
    this.sm = sm;
    this.speed = speed;
  }

  @Override
  public void runAnimation(String fn, Appendable a) {
    try {
      if (fn.isEmpty()) {
        System.out.append(this.render(sm.getShapes(), 1000,1000, speed));
      }
      else {
        FileWriter out = new FileWriter(fn);
        BufferedWriter writer = new BufferedWriter(out);
        writer.write(this.render(sm.getShapes(), 1000,1000, speed));
        writer.close();
      }
      a.append(this.render(sm.getShapes(), 1000,1000, speed));
    } catch (IOException e) {
      throw new IllegalStateException("IO exception");
    }
  }

  @Override
  public void showShapes(Map<String, AbstractShape> sg) {
  // not applicable for this view
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
     * This method creates the svg code for an animation of a particular shape
     * according to its given attributes.
     * @param shape shape to be drawn.
     * @param speed speed of the animation.
     * @return text output of the svg code representing the above.
   */
  private String svgChangeCode(Motion shape, float speed) {
    String result = "";
    if (shape.getShapeStr().equals("rectangle")) {
      result = String.format("  <animate attributeType=\"xml\" begin=\"%.0fms\" dur=\"%.0fms\" " +
                      "attributeName=\"x\" " +
                      "from=\"%.0f\" to=\"%.0f\" fill=\"freeze\" />\n" +
                            "  <animate attributeType=\"xml\" begin=\"%.0fms\" dur=\"%.0fms\"" +
                      " attributeName=\"y\" " +
                      "from=\"%.0f\" to=\"%.0f\" fill=\"freeze\" />\n" +
                            "  <animate attributeType=\"xml\" begin=\"%.0fms\" dur=\"%.0fms\" " +
                      "attributeName=\"width\" " +
                      "from=\"%.0f\" to=\"%.0f\" fill=\"freeze\" />\n" +
                            "  <animate attributeType=\"xml\" begin=\"%.0fms\" dur=\"%.0fms\"" +
                      " attributeName=\"height\" " +
                      "from=\"%.0f\" to=\"%.0f\" fill=\"freeze\" />\n" +
                            "  <animate attributeType=\"xml\" begin=\"%.0fms\" dur=\"%.0fms\" " +
                      "attributeName=\"fill\"" +
                      " from=\"rgb(%d,%d,%d)\" to=\"rgb(%d,%d,%d)\" fill=\"freeze\" />\n" +
                      "  <animateTransform attributeName=\"transform\"" +
              " attributeType=\"xml\"" +
              " type=\"rotate\"" +
              " from=\"%.0f %.0f %.0f\"" +
              " to=\"%.0f %.0f %.0f\"" +
              " dur=\"%.0fms\"" +
              " repeatCount=\"indefinite\"/>"
                    , shape.getTime() * 500 / speed, (float) (shape.getTime2()
              - shape.getTime())
                      * 500 / speed, shape.getPosX(), shape.getPosX2(),
          shape.getTime() * 500 / speed,
                    (float) (shape.getTime2() - shape.getTime()) *
                        500 / speed, shape.getPosY(),
              shape.getPosY2(),
                    shape.getTime() * 500 / speed, (float) (shape.getTime2()
              - shape.getTime()) * 500 / speed,
              shape.getWidth(), shape.getWidth2(), shape.getTime() * 500
              / speed,
                    (float) (shape.getTime2() - shape.getTime()) * 500 /
                        speed, shape.getHeight(),
              shape.getHeight2(),shape.getTime() * 500 / speed,
              (float) (shape.getTime2() - shape.getTime()) * 500 / speed,
          shape.getColor().getRed(),
              shape.getColor().getGreen(), shape.getColor().getBlue(),
                    shape.getColor2().getRed(), shape.getColor2().getGreen(),
          shape.getColor2().getBlue(), shape.getDegree() / 360 * 2 * Math.PI,
          shape.getPosX() + shape.getWidth() / 2,
          shape.getPosY() + shape.getHeight() / 2,
          shape.getDegree2() / 360 * 2 * Math.PI,
          shape.getPosX() + shape.getWidth() / 2,
          shape.getPosY() + shape.getHeight() / 2,
          (float) (shape.getTime2()
              - shape.getTime()) * 500 / speed);

    }
    else if (shape.getShapeStr().equals("ellipse")) {
      result = String.format("  <animate attributeType=\"xml\" begin=\"%.0fms\" dur=\"%.0fms\" " +
                      "attributeName=\"cx\" from=\"%.0f\" to=\"%.0f\" fill=\"freeze\" />\n" +
                            "  <animate attributeType=\"xml\" begin=\"%.0fms\" dur=\"%.0fms\" " +
                      "attributeName=\"cy\" from=\"%.0f\" to=\"%.0f\" fill=\"freeze\" />\n" +
                            "  <animate attributeType=\"xml\" begin=\"%.0fms\" dur=\"%.0fms\" " +
                      "attributeName=\"rx\" from=\"%.0f\" to=\"%.0f\" fill=\"freeze\" />\n" +
                            "  <animate attributeType=\"xml\" begin=\"%.0fms\" dur=\"%.0fms\" " +
                      "attributeName=\"ry\" from=\"%.0f\" to=\"%.0f\" fill=\"freeze\" />\n" +
                            "  <animate attributeType=\"xml\" begin=\"%.0fms\" dur=\"%.0fms\" " +
                      "attributeName=\"fill\" from=\"rgb(%d,%d,%d)\"" +
              " to=\"rgb(%d,%d,%d)\" fill=\"freeze\" />"
                    , shape.getTime() * 500 / speed, (float) (shape.getTime2()
              - shape.getTime())
                      * 500 / speed, shape.getPosX(), shape.getPosX2(),
          shape.getTime() * 500 /
                      speed,
                    (float) (shape.getTime2() - shape.getTime()) * 500 /
                        speed, shape.getPosY(),
              shape.getPosY2(),
                    shape.getTime() * 500 / speed, (float) (shape.getTime2()
              - shape.getTime()) *
                      500 / speed, shape.getWidth(), shape.getWidth2(),
          shape.getTime() * 500 / speed,
                    (float) (shape.getTime2() - shape.getTime()) * 500 /
                        speed, shape.getHeight(),
              shape.getHeight2(),shape.getTime() * 500 / speed, (float)
              (shape.getTime2() - shape.getTime())
                      * 500 / speed, shape.getColor().getRed(),
          shape.getColor().getGreen(),
              shape.getColor().getBlue(),
                    shape.getColor2().getRed(), shape.getColor2().
              getGreen(), shape.getColor2().getBlue());
    }
    return result;
  }


  /**
     * This method renders the text output for the xml code of the SVG animation view.
     * @param shapes Shapes to be animated.
     * @param width Width of the screen.
     * @param height Height of the screen.
     * @param speed Speed of the animation.
     * @return The string output of the SVG view.
   */
  public String render(Map<String, AbstractShape> shapes,
                       int width, int height, float speed) {
    String result = "<svg width=\"" + Integer.toString(width) + "\" height=\""
            + Integer.toString(height)
            + "\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">\n";
    for (AbstractShape sh : shapes.values()) {
      sh.getMotion();
      result += svgShapeCode(sh.getBunchOfMotions().entrySet().iterator().next().getValue(),
              sh.getBunchOfMotions().get(sh.getLastKeyM(sh.getBunchOfMotions())).getTime2(),
              speed) ;
      for (Motion s: sh.getBunchOfMotions().values()) {
        result += svgChangeCode(s,speed) + "\n";
      }
      if (sh.whatShape().equals("rectangle")) {
        result += String.format("</%s>", "rect") + "\n";
      }
      if (sh.whatShape().equals("ellipse")) {
        result += String.format("</%s>", "ellipse") + "\n";
      }
    }
    return result + "</svg>";
  }


  /**
     * This method gives out the text output for the SVG code of a particular shape.
     * It describes the shape's dimensions, color and when it appears and
     * disappears.
     * @param shape shape to be drawn.
     * @param time time of the shape.
     * @param speed speed of the animation.
     * @return string output of the given animation.
     */
  public String svgShapeCode(Motion shape, int time, float speed) {

    String result = "";
    if (shape.getShapeStr().equals("rectangle")) {
      result = String.format("<rect id=\"%s\" x=\"%.0f\"" +
              " y=\"%.0f\" width=\"%.0f\" height=\"%.0f\" " +
                      "fill=\"rgb(%d,%d,%d)\" visibility= \"hidden\">\n",
              shape.getName(), shape.getPosX(),
          shape.getPosY(), shape.getWidth(), shape.getHeight(),
              shape.getColor().getRed(),
              shape.getColor().getGreen(), shape.getColor().getBlue());
    } else if (shape.getShapeStr().equals("ellipse")) {
      result = String.format("<ellipse id=\"%s\" cx=\"%.0f\" " +
              "cy=\"%.0f\" rx=\"%.0f\" ry=\"%.0f\" " +
                      "fill=\"rgb(%d,%d,%d)\" visibility= \"hidden\">\n",
              shape.getName(), shape.getPosX(), shape.getPosY(),
          shape.getWidth(), shape.getHeight(),
              shape.getColor().getRed(),
              shape.getColor().getGreen(), shape.getColor().getBlue());
    }
    result += String.format("  <animate attributeType=\"xml\"" +
              " begin=\"%.0fms\" dur=\"1ms\" "
                      + "attributeName=\"visibility\" from=\"hidden\" "
                      + "to=\"visible\" fill=\"freeze\" />\n"
                      + "  <animate attributeType=\"xml\" " +
              "begin=\"%.0fms\" dur=\"1ms\" "
                      + "attributeName=\"visibility\" " +
              "from=\"visible\""
                      + " to=\"hidden\" fill=\"freeze\" />\n",
              shape.getTime() * 1250 / speed,
              time * 1250 / speed);
    return result;
  }
}
