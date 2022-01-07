/*import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


import cs3500.animator.controller.SimpleAnimationController;
import cs3500.animator.controller.SimpleAnimationControllerImpl;
import cs3500.animator.model.AbstractShape;
import cs3500.animator.model.RectangleShape;
import cs3500.animator.model.CircleShape;
import cs3500.animator.model.SimpleShapeAnimation;
import cs3500.animator.model.KeyFrame;
import cs3500.animator.model.TriangleShape;
import cs3500.animator.model.ShapeAnimationModel;
import cs3500.animator.util.AnimationBuilder;
import cs3500.animator.util.AnimationBuilderImpl;
import cs3500.animator.util.AnimationReader;
import org.junit.Test;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Tests for the Abstract Shape Class.
 */
/*public class AbstractShapeTest {

  @Test
  public void testWhatShapeRectangle() {
    AbstractShape rectangle = new RectangleShape();
    assertEquals("rectangle", rectangle.whatShape());
    assertNotEquals("circle", rectangle.whatShape());
    assertNotEquals("triangle", rectangle.whatShape());
  }

  @Test
  public void testWhatShapeCircle() {
    AbstractShape circle = new CircleShape();
    assertEquals("circle", circle.whatShape());
    assertNotEquals("rectangle", circle.whatShape());
    assertNotEquals("triangle", circle.whatShape());
  }

  @Test
  public void testWhatShapeTriangle() {
    AbstractShape triangle = new TriangleShape();
    assertEquals("triangle", triangle.whatShape());
    assertNotEquals("reactangle", triangle.whatShape());
    assertNotEquals("circle", triangle.whatShape());
  }


  @Test(expected = IllegalArgumentException.class)
  public void testSimpleAnimation() throws Exception {
    ShapeAnimationModel sa = new SimpleShapeAnimation(0,1,0,0,new LinkedHashMap<>());
    ShapeAnimationModel sa1 = new SimpleShapeAnimation(1,0,0,0,new LinkedHashMap<>());
    ShapeAnimationModel sa2 = new SimpleShapeAnimation(1,1,0,0,null);

  }

  @Test
  public void testAddKeyFrames() {
    ShapeAnimationModel sa1 = new SimpleShapeAnimation(1,1,0,0, new LinkedHashMap<>());
    AbstractShape r = new RectangleShape();
    sa1.createShape("rectangle", r);
    assertEquals(r.whatShape(), "rectangle");
    KeyFrame kf1 = new KeyFrame("rectangle",r, 1, 200, 200,
            new Color(255,0,0), 100, 50);
    KeyFrame kf2 = new KeyFrame("rectangle",r, 10, 200, 200,
            new Color(255,0,0), 100, 50);
    KeyFrame kf3 = new KeyFrame("rectangle",r, 50 ,300 ,300 ,
            new Color(255,0,0), 100 ,50);
    sa1.addKeyFrame("rectangle", kf1, 1);
    sa1.addKeyFrame("rectangle", kf2, 10);
    sa1.addKeyFrame("rectangle", kf3, 50);
    assertEquals(sa1.render(), "canvas 0 0 0 0\nshape rectangle" +
        " rectangle\nmotion rectangle 1 200.0 " +
            "200.0 50.0 100.0 255 0 0   10 200.0 200.0 50.0 " +
        "100.0 255 0 0\n" +
            "motion rectangle 10 200.0 200.0 50.0 100.0 255 0 0   " +
            "50 300.0 300.0 50.0 100.0 255 0 0");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddKeyFrameExcep() throws Exception {
    ShapeAnimationModel sa1 = new SimpleShapeAnimation(1,1,0,0, new LinkedHashMap<>());
    AbstractShape r = new RectangleShape();
    sa1.createShape("rectangle", r);
    assertEquals(r.whatShape(), "rectangle");
    KeyFrame kf1 = new KeyFrame("rectangle",r, 1, 200, 200,
            new Color(255,0,0), 100, 50);
    KeyFrame kf2 = new KeyFrame("rectangle", r, 10, 200, 200,
            new Color(255,0,0), 100, 50);
    KeyFrame kf3 = new KeyFrame("rectangle",r, 50 ,300 ,300 ,
            new Color(255,0,0), 100 ,50);
    KeyFrame kf4 = new KeyFrame("rectangle",r, 45 ,300 ,400 ,
            new Color(255,0,0), 100 ,50);
    sa1.addKeyFrame("rectangle", kf1, 1);
    sa1.addKeyFrame("rectangle", kf2, 10);
    sa1.addKeyFrame("rectangle", kf3, 50);
    sa1.addKeyFrame("rectangle", kf4, 45 );
    assertEquals(sa1.render(), "canvas 0 0 0 0\nshape " +
        "rectangle rectangle\nmotion rectangle 1 200.0 " +
            "200.0 50.0 100.0 255 0 0   10 200.0 200.0 50.0 " +
        "100.0 255 0 0\n" +
            "motion rectangle 10 200.0 200.0 50.0 100.0 255 0 0   " +
            "50 300.0 300.0 50.0 100.0 255 0 0");
  }




  @Test()
  public void testCreateShape() {
    ShapeAnimationModel sa1 = new SimpleShapeAnimation(1,1,0,0, new LinkedHashMap<>());
    AbstractShape r = new RectangleShape();
    sa1.createShape("rectangle", r);
    assertEquals(r.whatShape(), "rectangle");
    KeyFrame kf1 = new KeyFrame("rectangle",r, 1, 200, 200,
            new Color(255,0,0), 100, 50);
    KeyFrame kf2 = new KeyFrame("rectangle",r, 10, 200, 200,
            new Color(255,0,0), 100, 50);
    KeyFrame kf3 = new KeyFrame("rectangle",r, 50 ,300 ,300 ,
            new Color(255,0,0), 100 ,50);
    AbstractShape c = new CircleShape();
    sa1.createShape("circle", c);
    assertEquals(c.whatShape(), "circle");
    KeyFrame kf11 = new KeyFrame("circle",c, 1, 200, 200,
            new Color(255,0,0), 100, 50);
    KeyFrame kf22 = new KeyFrame("circle",c, 10, 200, 200,
            new Color(255,0,0), 100, 50);
    KeyFrame kf33 = new KeyFrame("circle",c, 50 ,300 ,300 ,
            new Color(255,0,0), 100 ,50);
    sa1.addKeyFrame("rectangle", kf1, 1);
    sa1.addKeyFrame("rectangle", kf2, 10);
    sa1.addKeyFrame("rectangle", kf3, 50);
    sa1.addKeyFrame("circle", kf11, 1);
    sa1.addKeyFrame("circle", kf22, 10);
    sa1.addKeyFrame("circle", kf33, 50);
    assertEquals(sa1.render(), "canvas 0 0 0 0\n" +
            "shape rectangle rectangle\nmotion rectangle 1 200.0 200.0 " +
        "50.0 100.0 255 0 0   " +
            "10 200.0 200.0 50.0 100.0 255 0 0\n" +
            "motion rectangle 10 200.0 200.0 50.0 100.0 255 0 0   " +
            "50 300.0 300.0 50.0 100.0 255 0 0\n" +
            "shape circle circle\n" +
            "motion circle 1 200.0 200.0 50.0 100.0 255 0 0   " +
            "10 200.0 200.0 50.0 100.0 255 0 0\n" +
            "motion circle 10 200.0 200.0 50.0 100.0 255 0 0   " +
            "50 300.0 300.0 50.0 100.0 255 0 0");


  }

  @Test(expected = IllegalArgumentException.class)
  public void testShapesException() throws Exception {
    ShapeAnimationModel sa1 = new SimpleShapeAnimation(1,1,
        0,0, new LinkedHashMap<>());
    AbstractShape r = new RectangleShape();
    sa1.createShape("rectangle", r);
    KeyFrame kf2 = new KeyFrame("rectangle",r, 0, 200, 200,
        new Color(255,0,0), 100, 50);
    KeyFrame kf3 = new KeyFrame("rectangle",r, 0 ,300 ,300 ,
        new Color(255,0,0), 100 ,50);
    sa1.addKeyFrame("rectangle", kf2, 10);
    sa1.addKeyFrame("rectangle", kf3, 50);
  }


  @Test
  public void testRender() {

    ShapeAnimationModel sa1 = new SimpleShapeAnimation(1,1,
        0,0, new LinkedHashMap<>());
    AbstractShape r = new RectangleShape();
    sa1.createShape("rectangle", r);
    KeyFrame kf1 = new KeyFrame("rectangle",r, 1, 200,
        200, new Color(255,0,0), 100, 50);
    KeyFrame kf2 = new KeyFrame("rectangle",r, 10, 200,
        200, new Color(255,0,0), 100, 50);
    KeyFrame kf3 = new KeyFrame("rectangle",r, 50 ,300 ,
        300 , new Color(255,0,0), 100 ,50);

    sa1.addKeyFrame("rectangle", kf1, 1);
    sa1.addKeyFrame("rectangle", kf2, 10);
    sa1.addKeyFrame("rectangle", kf3, 50);

    assertEquals(sa1.render(), "canvas 0 0 0 0\nshape rectangle " +
        "rectangle\nmotion rectangle 1 200.0 200.0 " +
            "50.0 100.0 255 0 0   10 200.0 200.0 50.0 100.0 255 0 0\n" +
            "motion rectangle 10 200.0 200.0 50.0 100.0 255 0 0   " +
            "50 300.0 300.0 50.0 100.0 255 0 0");


  }

  @Test
  public void testDeleteShape() {
    ShapeAnimationModel sa1 = new SimpleShapeAnimation(1,
        1,0,0, new LinkedHashMap<>());
    AbstractShape r = new RectangleShape();
    sa1.createShape("rectangle", r);
    assertEquals(r.whatShape(), "rectangle");
    KeyFrame kf1 = new KeyFrame("rectangle",r, 1, 200, 200,
            new Color(255,0,0), 100, 50);
    KeyFrame kf2 = new KeyFrame("rectangle",r, 10, 200, 200,
            new Color(255,0,0), 100, 50);
    KeyFrame kf3 = new KeyFrame("rectangle",r, 50 ,300 ,300 ,
            new Color(255,0,0), 100 ,50);
    AbstractShape c = new CircleShape();
    sa1.createShape("circle", c);
    assertEquals(c.whatShape(), "circle");
    KeyFrame kf11 = new KeyFrame("circle",c, 1, 200, 200,
            new Color(255,0,0), 100, 50);
    KeyFrame kf22 = new KeyFrame("circle",c, 10, 200, 200,
            new Color(255,0,0), 100, 50);
    KeyFrame kf33 = new KeyFrame("circle",c, 50 ,300 ,300 ,
            new Color(255,0,0), 100 ,50);
    sa1.addKeyFrame("rectangle", kf1, 1);
    sa1.addKeyFrame("rectangle", kf2, 10);
    sa1.addKeyFrame("rectangle", kf3, 50);
    sa1.addKeyFrame("circle", kf11, 1);
    sa1.addKeyFrame("circle", kf22, 10);
    sa1.addKeyFrame("circle", kf33, 50);
    sa1.deleteShape("rectangle");
    assertEquals(sa1.render(), "canvas 0 0 0 0\nshape circle circle\nmotion " +
        "circle 1 200.0 200.0 50.0 100.0 255 0 0   10 200.0 200.0 50.0 100.0 255 0 0\n" +
            "motion circle 10 200.0 200.0 50.0 100.0 255 0 0   50 300.0 300.0 50.0 " +
        "100.0 255 0 0");


  }

  @Test
  public void testGetMotion() {
    ShapeAnimationModel sa1 = new SimpleShapeAnimation(1,1,0,
        0, new LinkedHashMap<>());
    AbstractShape r = new RectangleShape();
    sa1.createShape("rectangle", r);
    assertEquals(r.whatShape(), "rectangle");
    KeyFrame kf1 = new KeyFrame("rectangle",r, 1, 200, 200,
            new Color(255,0,0), 100, 50);
    KeyFrame kf2 = new KeyFrame("rectangle",r, 10, 200, 200,
            new Color(255,0,0), 100, 50);
    KeyFrame kf3 = new KeyFrame("rectangle",r, 50 ,300 ,300 ,
            new Color(255,0,0), 100 ,50);
    AbstractShape c = new CircleShape();
    sa1.createShape("circle", c);
    assertEquals(c.whatShape(), "circle");
    KeyFrame kf11 = new KeyFrame("circle",c, 1, 200, 200,
            new Color(255,0,0), 100, 50);
    KeyFrame kf22 = new KeyFrame("circle",c, 10, 200, 200,
            new Color(255,0,0), 100, 50);
    KeyFrame kf33 = new KeyFrame("circle",c, 50 ,300 ,300 ,
            new Color(255,0,0), 100 ,50);
    sa1.addKeyFrame("rectangle", kf1, 1);
    sa1.addKeyFrame("rectangle", kf2, 10);
    sa1.addKeyFrame("rectangle", kf3, 50);
    sa1.addKeyFrame("circle", kf11, 1);
    sa1.addKeyFrame("circle", kf22, 10);
    sa1.addKeyFrame("circle", kf33, 50);
    List<KeyFrame> kf = new ArrayList<>();
    kf.add(kf1);
    kf.add(kf2);
    kf.add(kf3);
    assertEquals(sa1.getMotionsForShape(r), kf);
  }

  @Test
  public void testTextView() {
    StringBuffer outx = new StringBuffer();
    String s = "# initializes the canvas, with top-left corner (200,70) and\n" +
            "# dimensions 360x360\n" +
            "canvas 200 70 360 360\n" +
            "# declares a rectangle shape named R\n" +
            "shape R rectangle\n" +
            "# describes the motions of shape R, between two moments of animation:\n" +
            "# t == tick\n" +
            "# (x,y) == position\n" +
            "# (w,h) == dimensions\n" +
            "# (r,g,b) == color (with values between 0 and 255)\n" +
            "#                  start                           end\n" +
            "#        --------------------------    ----------------------------\n" +
            "#        t  x   y   w  h   r   g  b    t   x   y   w  h   r   g  b\n" +
            "motion R 1  200 200 50 100 255 0  0    10  200 200 50 100 255 0  0\n" +
            "motion R 10 200 200 50 100 255 0  0    50  300 300 50 100 255 0  0\n" +
            "motion R 50 300 300 50 100 255 0  0    51  300 300 50 100 255 0  0\n" +
            "motion R 51 300 300 50 100 255 0  0    70  300 300 25 100 255 0  0\n" +
            "motion R 70 300 300 25 100 255 0  0    100 200 200 25 100 255 0  0\n" +
            "\n" +
            "shape C ellipse\n" +
            "motion C 6  440 70 120 60 0 0 255 # start state\n" +
            "         20 440 70 120 60 0 0 255 # end state\n" +
            "motion C 20 440 70 120 60 0 0 255      50 440 250 120 60 0 0 255\n" +
            "motion C 50 440 250 120 60 0 0 255     70 440 370 120 60 0 170 85\n" +
            "motion C 70 440 370 120 60 0 170 85    80 440 370 120 60 0 255 0\n" +
            "motion C 80 440 370 120 60 0 255 0     100 440 370 120 60 0 255 0";
    AnimationReader ar = new AnimationReader();
    AnimationBuilder<SimpleShapeAnimation> ab = new AnimationBuilderImpl();
    BufferedReader br = new BufferedReader(new StringReader(s));
    ar.parseFile(br,ab);
    SimpleAnimationController c = new SimpleAnimationControllerImpl();
    c.runTextViewAnimation(ab.build(), 25 , "/Users/dhruvibakeri/" +
        "Documents/OOD S19/HW07_3/output.txt", outx);
    assertEquals(outx.toString(), "canvas 200 70 360 360\n" +
            "shape R rectangle\n" +
            "motion R 1 200.0 200.0 50.0 100.0 255 0 0   10 200.0 200.0 50.0 100.0 255 0 0\n" +
            "motion R 10 200.0 200.0 50.0 100.0 255 0 0   50 300.0 300.0 50.0 100.0 255 0 0\n" +
            "motion R 50 300.0 300.0 50.0 100.0 255 0 0   51 300.0 300.0 50.0 100.0 255 0 0\n" +
            "motion R 51 300.0 300.0 50.0 100.0 255 0 0   70 300.0 300.0 25.0 100.0 255 0 0\n" +
            "motion R 70 300.0 300.0 25.0 100.0 255 0 0   100 200.0 200.0 25.0 100.0 255 0 0\n" +
            "shape C ellipse\n" +
            "motion C 6 440.0 70.0 120.0 60.0 0 0 255   20 440.0 70.0 120.0 60.0 0 0 255\n" +
            "motion C 20 440.0 70.0 120.0 60.0 0 0 255   50 440.0 250.0 120.0 60.0 0 0 255\n" +
            "motion C 50 440.0 250.0 120.0 60.0 0 0 255   70 440.0 370.0 120.0 60.0 0 170 85\n" +
            "motion C 70 440.0 370.0 120.0 60.0 0 170 85   80 440.0 370.0 120.0 60.0 0 255 0\n" +
            "motion C 80 440.0 370.0 120.0 60.0 0 255 0   100 440.0 370.0 120.0 60.0 0 255 0");
  }

  @Test
  public void testSVGView() {
    StringBuffer outx = new StringBuffer();
    String s = "# initializes the canvas, with top-left corner (200,70) and\n" +
          "# dimensions 360x360\n" +
          "canvas 200 70 360 360\n" +
          "# declares a rectangle shape named R\n" +
          "shape R rectangle\n" +
          "# describes the motions of shape R, between two moments of animation:\n" +
          "# t == tick\n" +
          "# (x,y) == position\n" +
          "# (w,h) == dimensions\n" +
          "# (r,g,b) == color (with values between 0 and 255)\n" +
          "#                  start                           end\n" +
          "#        --------------------------    ----------------------------\n" +
          "#        t  x   y   w  h   r   g  b    t   x   y   w  h   r   g  b\n" +
          "motion R 1  200 200 50 100 255 0  0    10  200 200 50 100 255 0  0\n" +
          "motion R 10 200 200 50 100 255 0  0    50  300 300 50 100 255 0  0\n" +
          "motion R 50 300 300 50 100 255 0  0    51  300 300 50 100 255 0  0\n" +
          "motion R 51 300 300 50 100 255 0  0    70  300 300 25 100 255 0  0\n" +
          "motion R 70 300 300 25 100 255 0  0    100 200 200 25 100 255 0  0\n" +
          "\n" +
          "shape C ellipse\n" +
          "motion C 6  440 70 120 60 0 0 255 # start state\n" +
          "         20 440 70 120 60 0 0 255 # end state\n" +
          "motion C 20 440 70 120 60 0 0 255      50 440 250 120 60 0 0 255\n" +
          "motion C 50 440 250 120 60 0 0 255     70 440 370 120 60 0 170 85\n" +
          "motion C 70 440 370 120 60 0 170 85    80 440 370 120 60 0 255 0\n" +
          "motion C 80 440 370 120 60 0 255 0     100 440 370 120 60 0 255 0";
    AnimationReader ar = new AnimationReader();
    AnimationBuilder<SimpleShapeAnimation> ab = new AnimationBuilderImpl();
    BufferedReader br = new BufferedReader(new StringReader(s));
    ar.parseFile(br,ab);
    SimpleAnimationController c = new SimpleAnimationControllerImpl();
    c.runSVGViewAnimation(ab.build(), 25 , "/Users/dhruvibakeri/" +
        "Documents/OOD S19/HW07_3/outputsvg.xml", outx);
    assertEquals(outx.toString(), "<svg width=\"1000\" height=\"1000\"" +
        " version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">\n" +
          "<rect id=\"R\" x=\"200\" y=\"200\" width=\"50\" height=\"100\" fill=\"" +
        "rgb(255,0,0)\" visibility= \"hidden\">\n" +
          "  <animate attributeType=\"xml\" begin=\"50ms\" dur=\"1ms\" attributeName" +
        "=\"visibility\" from=\"hidden\" to=\"visible\" fill=\"freeze\" />\n" +
          "  <animate attributeType=\"xml\" begin=\"5000ms\" dur=\"1ms\" attributeName=" +
        "\"visibility\" from=\"visible\" to=\"hidden\" fill=\"freeze\" />\n" +
          "  <animate attributeType=\"xml\" begin=\"20ms\" dur=\"180ms\" attributeName=" +
        "\"x\" from=\"200\" to=\"200\" fill=\"freeze\" />\n" +
          "  <animate attributeType=\"xml\" begin=\"20ms\" dur=\"180ms\" attributeName=" +
        "\"y\" from=\"200\" to=\"200\" fill=\"freeze\" />\n" +
          "  <animate attributeType=\"xml\" begin=\"20ms\" dur=\"180ms\" attributeName=" +
        "\"width\" from=\"50\" to=\"50\" fill=\"freeze\" />\n" +
          "  <animate attributeType=\"xml\" begin=\"20ms\" dur=\"180ms\" attributeName=" +
        "\"height\" from=\"100\" to=\"100\" fill=\"freeze\" />\n" +
          "  <animate attributeType=\"xml\" begin=\"20ms\" dur=\"180ms\" attributeName=" +
        "\"fill\" from=\"rgb(255,0,0)\" to=\"rgb(255,0,0)\" fill=\"freeze\" />\n" +
          "  <animate attributeType=\"xml\" begin=\"200ms\" dur=\"800ms\" attributeName=" +
        "\"x\" from=\"200\" to=\"300\" fill=\"freeze\" />\n" +
          "  <animate attributeType=\"xml\" begin=\"200ms\" dur=\"800ms\" attributeName=" +
        "\"y\" from=\"200\" to=\"300\" fill=\"freeze\" />\n" +
          "  <animate attributeType=\"xml\" begin=\"200ms\" dur=\"800ms\" attributeName=" +
        "\"width\" from=\"50\" to=\"50\" fill=\"freeze\" />\n" +
          "  <animate attributeType=\"xml\" begin=\"200ms\" dur=\"800ms\" attributeName=" +
        "\"height\" from=\"100\" to=\"100\" fill=\"freeze\" />\n" +
          "  <animate attributeType=\"xml\" begin=\"200ms\" dur=\"800ms\" attributeName=" +
        "\"fill\" from=\"rgb(255,0,0)\" to=\"rgb(255,0,0)\" fill=\"freeze\" />\n" +
          "  <animate attributeType=\"xml\" begin=\"1000ms\" dur=\"20ms\" attributeName=" +
        "\"x\" from=\"300\" to=\"300\" fill=\"freeze\" />\n" +
          "  <animate attributeType=\"xml\" begin=\"1000ms\" dur=\"20ms\" attributeName=" +
        "\"y\" from=\"300\" to=\"300\" fill=\"freeze\" />\n" +
          "  <animate attributeType=\"xml\" begin=\"1000ms\" dur=\"20ms\" attributeName=" +
        "\"width\" from=\"50\" to=\"50\" fill=\"freeze\" />\n" +
          "  <animate attributeType=\"xml\" begin=\"1000ms\" dur=\"20ms\" attributeName=" +
        "\"height\" from=\"100\" to=\"100\" fill=\"freeze\" />\n" +
          "  <animate attributeType=\"xml\" begin=\"1000ms\" dur=\"20ms\" attributeName=" +
        "\"fill\" from=\"rgb(255,0,0)\" to=\"rgb(255,0,0)\" fill=\"freeze\" />\n" +
          "  <animate attributeType=\"xml\" begin=\"1020ms\" dur=\"380ms\" attributeName=" +
        "\"x\" from=\"300\" to=\"300\" fill=\"freeze\" />\n" +
          "  <animate attributeType=\"xml\" begin=\"1020ms\" dur=\"380ms\" attributeName=" +
        "\"y\" from=\"300\" to=\"300\" fill=\"freeze\" />\n" +
          "  <animate attributeType=\"xml\" begin=\"1020ms\" dur=\"380ms\" attributeName=" +
        "\"width\" from=\"50\" to=\"25\" fill=\"freeze\" />\n" +
          "  <animate attributeType=\"xml\" begin=\"1020ms\" dur=\"380ms\" attributeName=" +
        "\"height\" from=\"100\" to=\"100\" fill=\"freeze\" />\n" +
          "  <animate attributeType=\"xml\" begin=\"1020ms\" dur=\"380ms\" attributeName=" +
        "\"fill\" from=\"rgb(255,0,0)\" to=\"rgb(255,0,0)\" fill=\"freeze\" />\n" +
          "  <animate attributeType=\"xml\" begin=\"1400ms\" dur=\"600ms\" attributeName=" +
        "\"x\" from=\"300\" to=\"200\" fill=\"freeze\" />\n" +
          "  <animate attributeType=\"xml\" begin=\"1400ms\" dur=\"600ms\" attributeName=" +
        "\"y\" from=\"300\" to=\"200\" fill=\"freeze\" />\n" +
          "  <animate attributeType=\"xml\" begin=\"1400ms\" dur=\"600ms\" attributeName=" +
        "\"width\" from=\"25\" to=\"25\" fill=\"freeze\" />\n" +
          "  <animate attributeType=\"xml\" begin=\"1400ms\" dur=\"600ms\" attributeName=" +
        "\"height\" from=\"100\" to=\"100\" fill=\"freeze\" />\n" +
          "  <animate attributeType=\"xml\" begin=\"1400ms\" dur=\"600ms\" attributeName=" +
        "\"fill\" from=\"rgb(255,0,0)\" to=\"rgb(255,0,0)\" fill=\"freeze\" />\n" +
          "</rect>\n" +
          "<ellipse id=\"C\" cx=\"440\" cy=\"70\" rx=\"120\" ry=\"60\" fill=\"rgb(0,0,255)\"" +
        " " +
        "visibility= \"hidden\">\n" +
          "  <animate attributeType=\"xml\" begin=\"300ms\" dur=\"1ms\" attributeName=\"visib" +
        "ility\" from=\"hidden\" to=\"visible\" fill=\"freeze\" />\n" +
          "  <animate attributeType=\"xml\" begin=\"5000ms\" dur=\"1ms\" attributeName=\"visi" +
        "bility\" from=\"visible\" to=\"hidden\" fill=\"freeze\" />\n" +
          "  <animate attributeType=\"xml\" begin=\"120ms\" dur=\"280ms\" attributeName=" +
        "\"cx\" from=\"440\" to=\"440\" fill=\"freeze\" />\n" +
          "  <animate attributeType=\"xml\" begin=\"120ms\" dur=\"280ms\" attributeName=" +
        "\"cy\" from=\"70\" to=\"70\" fill=\"freeze\" />\n" +
          "  <animate attributeType=\"xml\" begin=\"120ms\" dur=\"280ms\" attributeName=" +
        "\"rx\" from=\"120\" to=\"120\" fill=\"freeze\" />\n" +
          "  <animate attributeType=\"xml\" begin=\"120ms\" dur=\"280ms\" attributeName=" +
        "\"ry\" from=\"60\" to=\"60\" fill=\"freeze\" />\n" +
          "  <animate attributeType=\"xml\" begin=\"120ms\" dur=\"280ms\" attributeName=" +
        "\"fill\" from=\"rgb(0,0,255)\" to=\"rgb(0,0,255)\" fill=\"freeze\" />\n" +
          "  <animate attributeType=\"xml\" begin=\"400ms\" dur=\"600ms\" attributeName=" +
        "\"cx\" from=\"440\" to=\"440\" fill=\"freeze\" />\n" +
          "  <animate attributeType=\"xml\" begin=\"400ms\" dur=\"600ms\" attributeName=" +
        "\"cy\" from=\"70\" to=\"250\" fill=\"freeze\" />\n" +
          "  <animate attributeType=\"xml\" begin=\"400ms\" dur=\"600ms\" attributeName" +
        "=\"rx\" from=\"120\" to=\"120\" fill=\"freeze\" />\n" +
          "  <animate attributeType=\"xml\" begin=\"400ms\" dur=\"600ms\" attributeName=" +
        "\"ry\" from=\"60\" to=\"60\" fill=\"freeze\" />\n" +
          "  <animate attributeType=\"xml\" begin=\"400ms\" dur=\"600ms\" attributeName=" +
        "\"fill\" from=\"rgb(0,0,255)\" to=\"rgb(0,0,255)\" fill=\"freeze\" />\n" +
          "  <animate attributeType=\"xml\" begin=\"1000ms\" dur=\"400ms\" attributeName=\"" +
        "cx\" from=\"440\" to=\"440\" fill=\"freeze\" />\n" +
          "  <animate attributeType=\"xml\" begin=\"1000ms\" dur=\"400ms\" attributeName=" +
        "\"cy\" from=\"250\" to=\"370\" fill=\"freeze\" />\n" +
          "  <animate attributeType=\"xml\" begin=\"1000ms\" dur=\"400ms\" attributeName=" +
        "\"rx\" from=\"120\" to=\"120\" fill=\"freeze\" />\n" +
          "  <animate attributeType=\"xml\" begin=\"1000ms\" dur=\"400ms\" attributeNa" +
        "me=\"ry\" from=\"60\" to=\"60\" fill=\"freeze\" />\n" +
          "  <animate attributeType=\"xml\" begin=\"1000ms\" dur=\"400ms\" attributeName" +
        "=\"fill\" from=\"rgb(0,0,255)\" to=\"rgb(0,170,85)\" fill=\"freeze\" />\n" +
          "  <animate attributeType=\"xml\" begin=\"1400ms\" dur=\"200ms\" attributeNa" +
        "me=\"cx\" from=\"440\" to=\"440\" fill=\"freeze\" />\n" +
          "  <animate attributeType=\"xml\" begin=\"1400ms\" dur=\"200ms\" attributeNa" +
        "me=\"cy\" from=\"370\" to=\"370\" fill=\"freeze\" />\n" +
          "  <animate attributeType=\"xml\" begin=\"1400ms\" dur=\"200ms\" attributeNa" +
        "me=\"rx\" from=\"120\" to=\"120\" fill=\"freeze\" />\n" +
          "  <animate attributeType=\"xml\" begin=\"1400ms\" dur=\"200ms\" attributeN" +
        "ame=\"ry\" from=\"60\" to=\"60\" fill=\"freeze\" />\n" +
          "  <animate attributeType=\"xml\" begin=\"1400ms\" dur=\"200ms\" attributeN" +
        "ame=\"fill\" from=\"rgb(0,170,85)\" to=\"rgb(0,255,0)\" fill=\"freeze\" />\n" +
          "  <animate attributeType=\"xml\" begin=\"1600ms\" dur=\"400ms\" attribute" +
        "Name=\"cx\" from=\"440\" to=\"440\" fill=\"freeze\" />\n" +
          "  <animate attributeType=\"xml\" begin=\"1600ms\" dur=\"400ms\" attributeN" +
        "ame=\"cy\" from=\"370\" to=\"370\" fill=\"freeze\" />\n" +
          "  <animate attributeType=\"xml\" begin=\"1600ms\" dur=\"400ms\" attribute" +
        "Name=\"rx\" from=\"120\" to=\"120\" fill=\"freeze\" />\n" +
          "  <animate attributeType=\"xml\" begin=\"1600ms\" dur=\"400ms\" attribute" +
        "Name=\"ry\" from=\"60\" to=\"60\" fill=\"freeze\" />\n" +
          "  <animate attributeType=\"xml\" begin=\"1600ms\" dur=\"400ms\" attribute" +
        "Name=\"fill\" from=\"rgb(0,255,0)\" to=\"rgb(0,255,0)\" fill=\"freeze\" />\n" +
          "</ellipse>\n" +
          "</svg>");
  }


}*/