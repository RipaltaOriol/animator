package cs3500.animator.util;

import cs3500.animator.model.ShapeAnimationModel;
import cs3500.animator.model.SimpleShapeAnimation;
import cs3500.animator.model.RectangleShape;
import cs3500.animator.model.EllipseShape;
import cs3500.animator.model.Motion;
import cs3500.animator.model.KeyFrame;
import java.util.LinkedHashMap;

/**
 * Builds up data from the given input to store in our model.
 */
public class AnimationBuilderImpl implements AnimationBuilder {
  private ShapeAnimationModel sm;

  public AnimationBuilderImpl() {
    this.sm = new SimpleShapeAnimation(1,1,
            0,0,new LinkedHashMap<>());
  }

  @Override
  public ShapeAnimationModel build() {
    return this.sm;
  }

  @Override
  public AnimationBuilder setBounds(int x, int y, int width, int height) {
    this.sm.setHeight(height);
    this.sm.setWidth(width);
    this.sm.setX(x);
    this.sm.setY(y);
    return this;
  }

  @Override
  public AnimationBuilder declareShape(String name, String type) {
    if (type.equals("rectangle")) {
      this.sm.createShape(name, new RectangleShape());
    }
    else if (type.equals("ellipse")) {
      this.sm.createShape(name, new EllipseShape());
    }
    return this;
  }

  @Override
  public AnimationBuilder addMotion(String name, int t1, int x1, int y1, int w1,
                                    int h1, int r1, int g1, int b1,int d1, int t2, int x2,
                                    int y2, int w2, int h2, int r2, int g2, int b2, int d2) {
    Motion m = new Motion(name, this.sm.getShapes().get(name),t1,x1,y1,
            new java.awt.Color(r1,g1,b1),h1, w1 ,d1,
            t2,x2,y2,new java.awt.Color(r2,g2,b2),h2, w2,d2);
    this.sm.addMotion(name,m,t1);
    return this;
  }

  @Override
  public AnimationBuilder addKeyframe(String name, int t, int x, int y, int w,
                                      int h, int r, int g, int b, int d) {
    KeyFrame kf = new KeyFrame(name,this.sm.getShapes().get(name),t,x,y,
           new java.awt.Color(r,g,b),h, w,d);
    this.sm.addKeyFrame(name,kf,t);
    return null;
  }
}
