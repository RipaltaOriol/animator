package cs3500.animator.provider.view;

import cs3500.animator.provider.view.AnimationEdit.EditType;
import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


import cs3500.animator.model.Animation;
import cs3500.animator.model.Motion;
import cs3500.animator.model.Shape;
import cs3500.animator.model.ShapeState;
import javax.swing.JTextArea;
import javax.swing.JTextField;


/**
 * A View for watching and Animation and editing the Animation. This view accepts input from the
 * user and changes its view settings as necessary.
 */
public class VisualEditView implements IAnimatorView, KeyListener {

  private int lastTick;
  private VisualAnimationView view;
  private int tick;
  private boolean pause;
  protected boolean loop;
  private JTextArea instrPane;
  //Giving us access to all elements at all times so we can edit
  private List<Animation> elements;
  // This will be where we store the information for the key frame they are editing
  private AnimationEdit edits;
  // This will be set as a flag for when the user has hit edited
  private boolean editFlag;
  private ArrayList<String> shapeNames;

  /**
   * A Constructor for a new VisualEditView.
   *
   * @param elements - a copy of the model information for display
   * @param tps - the ticks per second
   * @param width - canvas width
   * @param height - canvas height
   */
  public VisualEditView(List<Animation> elements, int tps, int width, int height) {
    this.edits = null;
    this.elements = elements;
    this.pause = false;
    this.loop = true;
    this.lastTick = getLastTick();
    this.view = new VisualAnimationView(tps, width, height);
    this.editFlag = false;
    this.shapeNames = new ArrayList<String>();
    for (int i = 0; i < this.elements.size(); i++) {
      this.shapeNames.add(this.elements.get(i).getName());
    }
    this.view.addKeyListener(this);
    this.instrPane = new JTextArea();
    instrPane.append("Controls: Pause: Space Bar  -  Rewind: Backspace  -  Speed Up: >  -  Slow "
        + "Down: <"
        + "  -  Loop On/Off: L      Press \"e\" when paused to Edit");
    view.add(instrPane, BorderLayout.PAGE_START);
    view.pack();

  }

  /**
   * A continuation constructor - for in progress views that need to update the model information
   * copy they have.
   *
   * @param v - the old view to continue
   * @param elements - the new model information
   */
  public VisualEditView(VisualEditView v, List<Animation> elements) {
    this.elements = elements;
    this.edits = null;
    this.pause = v.pause;
    this.loop = v.loop;
    this.lastTick = getLastTick();
    this.view = v.view;
    this.editFlag = false;
    this.view.addKeyListener(this);
    this.instrPane = new JTextArea();
    instrPane.append("Pause: Space Bar - Rewind: Backspace - Speed Up: > - Slow Down: < - Loop " +
        "On/Off: l     Press \"e\" when paused to edit");
    this.shapeNames = new ArrayList<String>();
    for (int i = 0; i < this.elements.size(); i++) {
      this.shapeNames.add(this.elements.get(i).getName());
    }
    view.add(instrPane, BorderLayout.PAGE_START);
    view.pack();
  }

  /**
   * Handles user key-inputs for controlling how the animation view settings occur.
   *
   * @param e - the KeyEvent
   */
  public void keyTyped(KeyEvent e) {
    switch (e.getKeyChar()) {
      case ' ':
        pause = !pause;
        break;
      case '\b':
        tick = 0;
        break;
      case 'e':
        if (pause = true) {
          pauseMenu();
          pause = false;
          break;
        }
        break;
      case 'l':
        this.loop = !loop;
        break;
      case '<':
        try {
          view.updateTPS(-1);
        } catch (IllegalArgumentException exc) {
          view.updateTPS(0);
        }
        view.pack();
        break;
      case '>':
        view.updateTPS(1);
        break;
      default:
        break;
    }
  }


  @Override
  public void keyPressed(KeyEvent e) {
    /**
     * Not currently being used.
     *
     * @param e the key event.
     */
  }


  @Override
  public void keyReleased(KeyEvent e) {
    /**
     * Not currently being used.
     *
     * @param e the key event.
     */

  }

  /**
   * This is a dynamic view and does not support this function.
   *
   * @param elements - all the Animations to be represented
   * @throws IllegalAccessException - will always throw
   */
  public void getAllView(List<Animation> elements) throws IllegalAccessException {
    throw new IllegalAccessException("Not supported by this view");
  }

  /**
   * Calls the super of the VisualAnimationView to display the animation.
   *
   * @param motions - all the motions occuring at a given time
   */
  public void getInstanceView(List<Motion> motions) {
    view.getInstanceView(motions);
  }

  /**
   * Updates the tick appropriatley given the current state of the View. (Paused, looped, etc).
   *
   * @return the updated tick
   */
  public int nextTick() {
    if (tick > lastTick && this.loop) {
      tick = 0;
    } else if (!pause) {
      tick += 1;
    }
    view.tick = tick;
    return tick;
  }

  /**
   * Gets tick per second from the view.
   *
   * @return the ticks per second
   */
  public int getTicksPerSecond() {
    return view.getTicksPerSecond();
  }

  /**
   * Returns an edit the user wants to make.
   *
   * @return the edit field if the edit flag is set, otherwise returns null.
   */
  public AnimationEdit getEdits() {
    if (editFlag) {
      editFlag = false;
      return edits;
    } else {
      return null;
    }
  }

  private int getLastTick() {
    int last = 0;
    for (Animation a : elements) {
      List<ShapeState> states = a.getMotions();
      if (states.get(states.size() - 1).getTick() > last) {
        last = states.get(states.size() - 1).getTick();
      }
    }
    return last;
  }

  private void pauseMenu() {
    Object[] options = {"Set Frame", "Remove Frame", "Add Shape", "Remove Shape",
        "Resume"};
    int n = JOptionPane.showOptionDialog(this.view,
        "Choose an option!",
        "ERR Decision",
        JOptionPane.YES_NO_OPTION,
        JOptionPane.QUESTION_MESSAGE,
        null,     //do not use a custom Icon
        options,  //the titles of buttons
        options[0]);
    if (n == 0) {
      int index = selectShape();
      if (index == -1) {
        return;
      }
      sendEdit(AnimationEdit.EditType.set, index, this.shapeNames.get(index));
    }

    if (n == 1) {
      int index = selectShape();
      if (index == -1) {
        return;
      }
      sendEdit(AnimationEdit.EditType.remove, index, this.shapeNames.get(index));
    }
    if (n == 2) {
      JTextField newName = new JTextField();
      JOptionPane.showConfirmDialog(this.view, newName, "Enter name for shape",
          JOptionPane.OK_OPTION);
      sendEdit(AnimationEdit.EditType.create, -1, newName.getText());
      return;
    }

    if (n == 3) {
      int index = selectShape();
      if (index == -1) {
        return;
      }
      sendEdit(AnimationEdit.EditType.delete, index, this.shapeNames.get(index));
      return;
    } else {
      return;
    }

  }

  private int selectShape() {
    String s = (String) JOptionPane.showInputDialog(this.view,
        "Which shape do you want to edit?", "Shape Selector",
        JOptionPane.PLAIN_MESSAGE, null, this.shapeNames.toArray(), null);
    if (s == null) {
      return -1;
    }
    int index = 0;
    for (int i = 0; i < shapeNames.size(); i++) {
      if (this.shapeNames.get(i).equals(s)) {
        index = i;
      }
    }
    return index;
  }


  private void sendEdit(AnimationEdit.EditType editType, int index, String name) {
    if (editType.equals(AnimationEdit.EditType.remove)) {
      Object[] ticks = new Integer[elements.get(index).getMotions().size()];
      for (int i = 0; i < elements.get(index).getMotions().size(); i++) {
        ticks[i] = elements.get(index).getMotions().get(i).getTick();
      }
      int s = (int) JOptionPane.showInputDialog(this.view, "Which frame (at the given"
              + "tick) do you want to remove?", "Tick Selector", JOptionPane.PLAIN_MESSAGE, null,
          ticks, ticks[0]);
      ShapeState ss = new ShapeState(elements.get(index).getPrevState(s));
      AnimationEdit edit = new AnimationEdit(name, editType, ss);
      System.out.println("Remove done");
      this.edits = edit;
      this.editFlag = true;
      return;
    }
    if (editType.equals(AnimationEdit.EditType.delete)) {
      ShapeState ss = new ShapeState(elements.get(index).getNextState(-1));
      if (this.shapeNames.size() == 1) {
        for (Animation a : elements) {
          if (a.getName().equals(name)) {
            ss = new ShapeState(a.getNextState(-1));
          }
        }

      }
      this.editFlag = false;
      AnimationEdit edit = new AnimationEdit(name, EditType.delete, ss);
      System.out.println(name);
      this.edits = edit;
      this.editFlag = true;
      this.shapeNames.remove(name);
      return;
    }
    JTextField tickInput = new JTextField();
    JTextField xInput = new JTextField();
    JTextField yInput = new JTextField();
    JTextField widthInput = new JTextField();
    JTextField heightInput = new JTextField();
    JTextField redInput = new JTextField();
    JTextField greenInput = new JTextField();
    JTextField blueInput = new JTextField();
    Object[] input = {
        "Input tick value:", tickInput,
        "Input x value:", xInput,
        "Input y value:", yInput,
        "Input width value:", widthInput,
        "Input height value:", heightInput,
        "Input red value:", redInput,
        "Input green value:", greenInput,
        "Input blue value:", blueInput
    };
    int newTick = 0;
    int newX = 0;
    int newY = 0;
    int newWidth = 0;
    int newHeight = 0;
    int newRed = 0;
    int newGreen = 0;
    int newBlue = 0;
    while (true) {
      JOptionPane.showConfirmDialog(this.view, input, "Enter all values",
          JOptionPane.OK_OPTION);
      try {
        newTick = Integer.parseInt(tickInput.getText());
        newX = Integer.parseInt(xInput.getText());
        newY = Integer.parseInt(yInput.getText());
        newWidth = Integer.parseInt(widthInput.getText());
        newHeight = Integer.parseInt(heightInput.getText());
        newRed = Integer.parseInt(redInput.getText());
        newGreen = Integer.parseInt(greenInput.getText());
        newBlue = Integer.parseInt(blueInput.getText());
      } catch (NumberFormatException e) {
        JOptionPane
            .showMessageDialog(this.view, "You must enter valid values for each field!",
                "Error", JOptionPane.ERROR_MESSAGE);
        continue;
      }
      try {
        cs3500.animator.model.Color color = new cs3500.animator.model.Color(newRed, newBlue,
            newGreen);
        cs3500.animator.model.Shape newShape = null;
        if (editType.equals(AnimationEdit.EditType.create)) {
          Object[] shapeTypes = {
              "Rectangle",
              "Ellipse"
          };
          String s = (String) JOptionPane.showInputDialog(this.view,
              "What type of shape will this be?", "Shape Selector",
              JOptionPane.PLAIN_MESSAGE, null, shapeTypes, shapeTypes[0]);
          if (s.equals("Rectangle")) {
            newShape = new cs3500.animator.model.Shape(
                cs3500.animator.model.Shape.ShapeType.rectangle,
                newWidth, newHeight, newX, newY, color);
          } else {
            new cs3500.animator.model.Shape(cs3500.animator.model.Shape.ShapeType.ellipse,
                newWidth, newHeight, newX, newY, color);
          }
        } else {
          newShape = new Shape(elements.get(index).type,
              newWidth, newHeight, newX, newY, color);
        }
        ShapeState ss = new ShapeState(newTick, newShape);
        if (editType.equals(AnimationEdit.EditType.create)) {
          AnimationEdit edit = new AnimationEdit(name, AnimationEdit.EditType.create, ss);
          this.edits = edit;
          this.editFlag = true;
          this.shapeNames.add(name);
          if (edit == null) {
            System.out.println("It's null!");
          }
          return;
        }
        AnimationEdit edit = new AnimationEdit(elements.get(index).getName(),
            AnimationEdit.EditType.set, ss);
        System.out.println("Set done!");
        this.edits = edit;
        this.editFlag = true;
        break;
      } catch (IllegalArgumentException e) {
        JOptionPane.showMessageDialog(this.view, "Invalid value entered!\n"
                + "Please try again!",
            "alert",
            JOptionPane.ERROR_MESSAGE);
        continue;
      }
    }
  }


  private int getNextIndex() {
    int count = 0;
    for (String s : this.shapeNames) {
      if (s != null) {
        count++;
      }
    }
    return count;
  }

}
