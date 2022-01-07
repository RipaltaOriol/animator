package cs3500.animator.util;


import cs3500.animator.model.KeyFrame;

/**
 * Utility class used to tween the motions of the animation to make it smooth.
 * Required to make the provider's view work.
 */
public class ShapeTweener {

  /**
   * This method uses the tweening function to give the new values of any of the attributes
   * of a motion according to their current position, related to the current time.
   * @param time Time at which the motion needs to be tweened.
   * @param kfs starting KeyFrame of the motion.
   * @param kfe ending KeyFrame of the motion.
   * @return a shape with the modified values.
   */
  public static cs3500.animator.model.Shape tweenShape(int time, KeyFrame kfs, KeyFrame kfe) {

    float bx =  (int) kfs.getPosX();
    float by = (int) kfs.getPosY();
    float bcr = kfs.getColor().getRed();
    float bcg = kfs.getColor().getGreen();
    float bcb = kfs.getColor().getBlue();
    float acr = tween((1 / 255) * bcr, (1 / 255) * kfe.getColor().getRed(),
        kfs.getTime(), kfe.getTime(), time);
    float acg = tween((1 / 255) * bcg, (1 / 255) * kfe.getColor().getGreen(),
        kfs.getTime(), kfe.getTime(), time);
    float acb = tween((1 / 255) * bcb, (1 / 255) * kfe.getColor().getBlue(),
        kfs.getTime(), kfe.getTime(), time);
    float ax = tween(bx, (float) kfe.getPosX(),kfs.getTime(), kfe.getTime(),time);
    float ay = tween(by,  (float) kfe.getPosY(),kfs.getTime(), kfe.getTime(), time);
    float bw =  (int) kfs.getWidth();
    float bh = (int) kfs.getHeight();
    float aw = tween(bw, (float) kfe.getWidth(),kfs.getTime(), kfe.getTime(),time);
    float ah = tween(bh,  (float) kfe.getHeight(),kfs.getTime(), kfe.getTime(), time);
    return new cs3500.animator.model.Shape(kfs.getSType(), (int) aw, (int) ah, (int) ax, (int) ay,
        new cs3500.animator.model.Color(kfs.getColor().getRed(),
            kfs.getColor().getGreen(), kfs.getColor().getBlue()) );

  }


  /**
   * This method performs the tweening calculation and returns a new value
   * for the given value according to the given time and starting and ending values.
   * This formula has been taken from the assignment page. It also takes into account
   * if the given time is the same as the start time or the end time of the value. If it
   * is, the value is multiplied by 0 and hence does not change.
   * @param a  start value of the associated attribute.
   * @param b end value of the associated attribute.
   * @param ta start time of the associated attribute.
   * @param tb end time of the associated attribute.
   * @param time time according to which the value needs to be changed.
   * @return the new value with current time taken into consideration.
   */
  protected static float tween(float a, float b, int ta, int tb, int time) {
    float r1 = ((float) (tb - time)) / ((float) (tb - ta));
    float r2 = ((float) (time - ta)) / ((float) (tb - ta));
    if (r1 > 1) {
      r1 = 1;
    }
    if (r1 < 0) {
      r1 = 0;
    }
    if (r2 > 1) {
      r2 = 1;
    }
    if (r2 < 0) {
      r2 = 0;
    }
    return a * r1 + b * r2;
  }


}
