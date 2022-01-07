package cs3500.animator.controller;

import cs3500.animator.model.ShapeAnimationModel;
import cs3500.animator.model.AbstractShape;
import cs3500.animator.model.Animation;
import cs3500.animator.model.RectangleShape;
import cs3500.animator.model.EllipseShape;
import cs3500.animator.model.Motion;
import cs3500.animator.model.KeyFrame;
import cs3500.animator.model.Shape;
import cs3500.animator.provider.view.AnimationEdit;
import cs3500.animator.provider.view.VisualEditView;
import cs3500.animator.view.EditView;
import cs3500.animator.view.TextViewImpl;
import cs3500.animator.view.View;
import cs3500.animator.view.VisualViewImpl;
import cs3500.animator.view.SVGViewImpl;


import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


/**
 * Class to implement the methods of the SimpleAnimationController interface.
 * It contains implementations,
 * of methods which run various animations,
 * according to the model and speed.
 */
public class SimpleAnimationControllerImpl implements SimpleAnimationController {

  Timer t;
  AnimationEdit ae = null;

  public SimpleAnimationControllerImpl() {
    //just needed to initialise a controller.

  }



  @Override
  public void runEditViewAnimation(ShapeAnimationModel model , int speed, String fn, Appendable a) {
    View v = new EditView(model.getLastTick());
    ((EditView) v).setModel(model);
    ((EditView) v).setSpeed(speed);

    t = new Timer(speed, new ActionListener() {
      int tick = 0;
      @Override
      public void actionPerformed(ActionEvent e) {
        if ( v.getStartA()) {
          if (v.geRestartA()) {
            tick = 0;
            ((EditView) v).setRestartA(false);
          }
          if (((EditView) v).getAdjust()) {
            v.showShapes(((EditView) v).getModel().tweenAllMotion((((EditView) v).getAdjustVal())));
            tick = ((EditView) v).getAdjustVal();
          }
          if ( v.getPaused()) {
            v.showShapes(((EditView) v).getModel().tweenAllMotion(tick));
          }
          if (!v.getPaused() && !((EditView) v).getAdjust()) {
            if (v.getLooping() && tick > ((EditView) v).getModel().getLastTick()) {
              tick = 0;
            }
            t.setDelay(((EditView) v).getSpeed());
            v.showShapes(((EditView) v).getModel().tweenAllMotion(tick));
            tick++;
          }
        }
      }
    });
    t.start();
  }

  @Override
  public void runViewAnimation(ShapeAnimationModel model , int speed, String fn, Appendable a) {
    View v = new VisualViewImpl();
    Timer t = new Timer(speed, new ActionListener() {
      int tick = 0;
      @Override
      public void actionPerformed(ActionEvent e) {
        v.showShapes(model.tweenAllMotion(tick));
        tick++;
      }
    });
    t.start();
  }

  @Override
  public void runTextViewAnimation(ShapeAnimationModel model ,
                                   int speed, String fn, Appendable a) {
    View v = new TextViewImpl(model, speed);
    v.runAnimation(fn, a);
  }

  @Override
  public void runSVGViewAnimation(ShapeAnimationModel model,  int speed, String fn, Appendable a) {
    View v = new SVGViewImpl(model, speed);
    v.runAnimation(fn, a);
  }

  @Override
  public void runProviderViewAnimation(ShapeAnimationModel model, int speed) {
    //VisualAnimationView v = new VisualAnimationView(speed, model.getWidth(), model.getHeight());


    java.util.List<Animation> loa = new ArrayList<>();

    for (AbstractShape x: model.getShapes().values()) {
      loa.add(new Animation(x.getBunchOfKeyFrames().get(x.getLastKey(
          x.getBunchOfKeyFrames())).getName(),
          x.getBunchOfKeyFrames().get(x.getLastKey(x.getBunchOfKeyFrames())).getSType(),
          x.getShapeStates()));
    }


    VisualEditView vx = new VisualEditView(loa,speed, model.getWidth(), model.getHeight() );


    t = new Timer(speed, new ActionListener() {
      int ticks = 0;

      @Override
      public void actionPerformed(ActionEvent e) {
        java.util.List<Motion> m = new ArrayList<>();

        ae = vx.getEdits();

        if (ae != null) {
          if (ae.type.equals(AnimationEdit.EditType.set)) {
            model.insertKeyFrame(ae.getAnimationName(),ae.getEdits().getTick(),
                new KeyFrame(ae.getAnimationName(),
                    model.getShapes().get(ae.getAnimationName()),
                    ae.getEdits().getTick(),
                    ae.getEdits().getShape().getX(),
                    ae.getEdits().getShape().getY(),
                    ae.getEdits().getShape().getColorA(),
                    ae.getEdits().getShape().getHeight(),
                    ae.getEdits().getShape().getWidth(), 0));
          }

          if (ae.type.equals(AnimationEdit.EditType.create)) {
            AbstractShape as = null;
            if (ae.getEdits().getShape().getType().equals(Shape.ShapeType.rectangle)) {
              as = new RectangleShape();
            }
            else {
              as = new EllipseShape();
            }
            model.createShape(ae.getAnimationName(),as);
          }


          if (ae.type.equals(AnimationEdit.EditType.remove)) {
            model.deleteKeyFrame(ae.getAnimationName(), ae.getEdits().getTick());
          }

          if (ae.type.equals(AnimationEdit.EditType.delete)) {
            model.deleteShape(ae.getAnimationName());
          }
        }

        for (AbstractShape as: model.getShapes().values()) {
          as.getMotion();
          for (Motion ms: as.getBunchOfMotions().values()) {
            if (ticks >= ms.getTime() && ticks <= ms.getTime2()) {
              m.add(ms);
            }
          }
        }

        t.setDelay(vx.getTicksPerSecond());
        vx.getInstanceView(m);
        ticks = vx.nextTick();
      }
    });
    t.start();
  }
}

