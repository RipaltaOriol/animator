package cs3500.animator;

import cs3500.animator.controller.SimpleAnimationController;
import cs3500.animator.controller.SimpleAnimationControllerImpl;
import cs3500.animator.model.ShapeAnimationModel;
import cs3500.animator.util.AnimationBuilder;
import cs3500.animator.util.AnimationBuilderImpl;
import cs3500.animator.util.AnimationReader;

import javax.swing.JPanel;
import javax.swing.JOptionPane;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;


/**
 * This class represents the main part of the animation. It takes in arguments
 * from the user, and according to those arguments forms desired outputs
 * of the animation.
 */
public final class Excellence {

  /**
   * Runs the entire animation software.
   * @param args arguments by clients.
   */
  public static void main(String[] args) {

    try {
      Appendable a = new StringBuffer();
      int speedx = 25;
      BufferedReader br = new BufferedReader(new FileReader(
              "/Users/dhruvibakeri/Documents/OOD S19/HW07_3/toh-3.txt"));
      AnimationBuilder<ShapeAnimationModel> ab = new AnimationBuilderImpl();
      AnimationReader ar = new AnimationReader();
      SimpleAnimationController controller = new SimpleAnimationControllerImpl();
      String fn = "";
      String view = "";

      for (int i = 0; i < args.length; i++) {
        if (args[i].equals("-in")) {
          br = new BufferedReader(new FileReader(args[i + 1]));
        } else if (args[i].equals("-out")) {
          fn = args[i + 1];
        } else if (args[i].equals("-speed")) {
          // changeVal(e, Integer.parseInt(args[i + 1]));
          speedx = Integer.parseInt(args[i + 1]);
        } else if (args[i].equals("-view")) {
          view = args[i + 1];
        }
      }
      if (speedx < 1) {
        final JPanel panel = new JPanel();
        JOptionPane.showMessageDialog(panel, "SPEED CANNOT BE NEGATIVE",
                "Error", JOptionPane.ERROR_MESSAGE);
      } else {
        if (view.equals("text")) {
          view = "text";
          ar.parseFile(br, ab);
          controller.runTextViewAnimation(ab.build(), speedx, fn,a);
        } else if (view.equals("visual")) {
          view = "visual";
          ar.parseFile(br, ab);
          controller.runViewAnimation(ab.build(), speedx, fn, a);
        } else if (view.equals("edit")) {
          view = "edit";
          ar.parseFile(br, ab);
          controller.runEditViewAnimation(ab.build(), speedx, fn, a);
        } else if (view.equals("provider")) {
          view = "provider";
          ar.parseFile(br, ab);
          controller.runProviderViewAnimation(ab.build(), speedx);
        } else if (view.equals("svg")) {
          view = "svg";
          ar.parseFile(br, ab);
          controller.runSVGViewAnimation(ab.build(), speedx, fn, a);
        } else {
          if (view.equals("")) {
            final JPanel panel = new JPanel();
            JOptionPane.showMessageDialog(panel, "A view type needs to be specified. Use -view.",
                    "Error", JOptionPane.ERROR_MESSAGE);
          }

        }

      }
    } catch (FileNotFoundException e) {
      throw new IllegalStateException("no such file");
    }
  }
}




