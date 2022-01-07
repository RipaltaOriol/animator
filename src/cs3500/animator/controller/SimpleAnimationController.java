package cs3500.animator.controller;

import cs3500.animator.model.ShapeAnimationModel;

/**
 * This is the interface for the SimpleAnimationController.
 * It contains methods to control different views.
 * which are implemented in its implementation.
 */
public interface SimpleAnimationController {

  /**
     * This method runs the Editor View Animation, with the use of the model and speed
     * it runs a frame which has options through which a user can edit the animation.
     * @param model the model containing the data for the animation.
     * @param speed the speed the animation needs to be played on.
     * @param fn  name of the file where the text output needs to be saved.
     *           (not needed in this case).
     * @param a to store the output in a textViewcase.
   */
  void runEditViewAnimation(ShapeAnimationModel model ,int speed, String fn, Appendable a);

  /**
     * This method runs the Visual View Animation, with the use of the model and speed
     * it runs a frame with the given animation.
     * @param model the model containing the data for the animation.
     * @param speed the speed the animation needs to be played on.
     * @param fn  name of the file where the text output needs to be saved.
     *            (not needed in this case).
     * @param a to store the output in a textViewcase.
     */
  void runViewAnimation(ShapeAnimationModel model ,int speed, String fn, Appendable a);

  /**
     * This method runs the Text View Animation, with the use of the model and speed
     * it outputs a text string containing the motions of the animation
     * in the console or saves it to a file.
     * @param model the model containing the data for the animation.
     * @param speed the speed the animation needs to be played on.
     * @param fn  name of the file where the text output needs to be saved.
     * @param a to store the output in a textViewcase.
   */
  void runTextViewAnimation(ShapeAnimationModel model ,  int speed, String fn, Appendable a);

  /**
     * This method runs the SVG View Animation, with the use of the model and speed
     * it outputs a text string containing the SVG xml code for the animations
     * in the console or saves it to a file. You can then take that file and open it in
     * a browser to view the animation.
     * @param model the model containing the data for the animation.
     * @param speed the speed the animation needs to be played on.
     * @param fn  name of the file where the text output needs to be saved.
     * @param a to store the output in a textViewcase.
   */
  void runSVGViewAnimation(ShapeAnimationModel model ,  int speed, String fn, Appendable a);

  /**
   * This method runs the ProviderEditorView, with the use of the model and speed
   * it runs a frame which has options through which a user can edit the animation.
   * @param model the model containing the data for the animation.
   * @param speed the speed the animation needs to be played on.
   */
  void runProviderViewAnimation(ShapeAnimationModel model ,  int speed);

}
