package cs3500.animator.view;


import cs3500.animator.model.AbstractShape;
import cs3500.animator.model.KeyFrame;
import cs3500.animator.model.Motion;
import cs3500.animator.model.ShapeAnimationModel;


import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Button;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.Timer;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.Box;
import javax.swing.JScrollBar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * This view creates a visual frame with the given animation data. This particular
 * view can be edited - pause, play, restart, loop. It can even edit the KeyFrames-
 * Delete Shape, Delete Keyframe, Insert Keyframe, modify Keyframe position, modify
 * Keyframe dimensions and modify Keyframe colors.
 */
public class EditView extends JFrame implements View {
  private ShapeAnimationModel sa;
  private DrawShape allShapes;
  private int speed;
  private int adjustVal;
  private boolean adjust;
  private boolean paused;
  private boolean looping;
  private boolean startA;
  private boolean restartA;
  private int lastTick;
  private Button pause;
  private Button loop;
  private Timer t;
  private JPanel allBtn;
  private JOptionPane getKF;
  private JPanel allKF;
  private boolean editkf = false;


  /**
     * This is the constructor for this view. It creates the Java swing frame,
     * and adds all the necessary buttons and texts to it.
  */
  public EditView(int lastTick) {
    this.lastTick = lastTick;
    allShapes = new DrawShape();
    this.setPreferredSize(new Dimension(800,800)); //this.sa.getWidth(),this.sa.getHeight()));
    JScrollPane scrollBar = new JScrollPane(allShapes);
    scrollBar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    scrollBar.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    Button ekf = new Button("Edit KeyFrame");
    ekf.setActionCommand("ekf");
    ekf.addActionListener(ac);
    pause = new Button("Pause");
    pause.setActionCommand("pause");
    pause.addActionListener(ac);
    Button start = new Button("Start");
    start.setActionCommand("start");
    start.addActionListener(ac);
    Button fast = new Button("+");
    fast.setActionCommand("speedInc");
    fast.addActionListener(ac);
    Button slow = new Button("-");
    slow.setActionCommand("speedDec");
    slow.addActionListener(ac);
    Button restart = new Button("Restart");
    restart.setActionCommand("restart");
    restart.addActionListener(ac);
    loop = new Button("Loop");
    loop.setActionCommand("loop");
    loop.addActionListener(ac);
    allBtn = new JPanel(new BorderLayout());
    JPanel eachBtn = new JPanel(new FlowLayout());
    eachBtn.add(ekf);
    eachBtn.add(pause);
    eachBtn.add(start);
    eachBtn.add(fast);
    eachBtn.add(slow);
    eachBtn.add(restart);
    eachBtn.add(loop);
    allBtn.add(scrollBar, BorderLayout.CENTER);
    allBtn.add(eachBtn,BorderLayout.PAGE_END);
    JScrollBar sb = new JScrollBar(JScrollBar.HORIZONTAL, 0, 1, 0, lastTick);
    sb.setUnitIncrement(2);
    sb.setBlockIncrement(1);
    sb.addAdjustmentListener(al);
    this.add(sb,BorderLayout.PAGE_START);
    this.add(allBtn);
    this.pack();
    this.setVisible(true);
  }

  public void setLastTick(int i) {
    lastTick = i;
  }

  public boolean getAdjust() {
    return this.adjust;
  }

  public int getAdjustVal() {
    return this.adjustVal;
  }

  AdjustmentListener al = new AdjustmentListener() {
    @Override
    public void adjustmentValueChanged(AdjustmentEvent e) {
      if (e.getValueIsAdjusting()) {
        adjust = true;
        adjustVal = e.getValue();
      }
      else if (!e.getValueIsAdjusting()) {
        adjust = false;
      }
    }
  };

  public void setModel(ShapeAnimationModel m) {
    this.sa = m;
  }

  public int getSpeed() {
    return this.speed;
  }

  public void setSpeed(int i) {
    this.speed = i;
  }

  /**
     * This is an ActionListener created to add actions that buttons
     * on the frame would contain. It helps giving tasks to each of the
     * editing buttons and changing them accordingly.
   */
  ActionListener ac = new ActionListener() {

    @Override
    public void actionPerformed(ActionEvent e) {

      if (e.getActionCommand().equals("pause")) {
        paused = true;
        pause.setLabel("Play");
        pause.setActionCommand("play");
      }
      if (e.getActionCommand().equals("play")) {
        paused = false;
        pause.setLabel("Pause");
        pause.setActionCommand("pause");
      }
      if (e.getActionCommand().equals("start")) {
        startA = true;
      }
      if (e.getActionCommand().equals("speedInc")) {
        speed =  (speed - 1);
      }
      if (e.getActionCommand().equals("speedDec")) {
        speed =  (speed + 1);
      }
      if (e.getActionCommand().equals("restart")) {
        restartA = true;
      }
      if (e.getActionCommand().equals("loop")) {
        looping = true;
        loop.setLabel("X Loop");
        loop.setActionCommand("noloop");
      }
      if (e.getActionCommand().equals("noloop")) {
        looping = false;
        loop.setLabel("Loop");
        loop.setActionCommand("loop");
      }
      if (e.getActionCommand().equals("ekf")) {
        editkf = true;
        String[] options = {"Delete Shape", "Delete KeyFrame",
            "Add Keyframe", "Modify KeyFrame"};
        int option = getKF.showOptionDialog(allBtn, "How" +
                " would you like to edit the " +
                "Animation? Make sure to hit restart once you're done.",
                "Click a button", getKF.DEFAULT_OPTION, getKF.INFORMATION_MESSAGE,
                null, options, options[0]);
        if (option == 0) {
          String s = getKF.showInputDialog(allBtn, "Which " +
              "Shape would you " +
              "like to delete?");
          sa.deleteShape(s);
        }
        else if (option == 1) {
          JPanel myPanel = new JPanel();
          JTextField time = new JTextField(5);
          JTextField shape = new JTextField(30);
          myPanel.add(new JLabel("Time:"));
          myPanel.add(time);
          myPanel.add(Box.createHorizontalStrut(15));
          myPanel.add(new JLabel("Shape:"));
          myPanel.add(shape);
          int s = getKF.showConfirmDialog(allBtn, myPanel,
                  "Which KeyFrame would you like to delete?", getKF.OK_CANCEL_OPTION);
          sa.deleteKeyFrame(shape.getText(), Integer.parseInt(time.getText()));

        }
        else if (option == 2) {
          JPanel myPanel2 = new JPanel();
          JTextField time2 = new JTextField(5);
          JTextField shapen = new JTextField(10);
          JTextField x = new JTextField(10);
          JTextField y = new JTextField(10);
          JTextField w = new JTextField(10);
          JTextField h = new JTextField(10);
          JTextField cr = new JTextField(10);
          JTextField cg = new JTextField(10);
          JTextField cb = new JTextField(10);
          JTextField d = new JTextField(10);
          myPanel2.add(new JLabel("Time:"));
          myPanel2.add(time2);
          myPanel2.add(Box.createVerticalStrut(5));
          myPanel2.add(new JLabel("Shape:"));
          myPanel2.add(shapen);
          myPanel2.add(Box.createVerticalStrut(5));
          myPanel2.add(new JLabel("X Pos:"));
          myPanel2.add(x);
          myPanel2.add(Box.createVerticalStrut(5));
          myPanel2.add(new JLabel("Y Pos:"));
          myPanel2.add(y);
          myPanel2.add(Box.createVerticalStrut(5));
          myPanel2.add(new JLabel("Width:"));
          myPanel2.add(w);
          myPanel2.add(Box.createVerticalStrut(5));
          myPanel2.add(new JLabel("Height:"));
          myPanel2.add(h);
          myPanel2.add(Box.createVerticalStrut(5));
          myPanel2.add(new JLabel("Color-Red:"));
          myPanel2.add(cr);
          myPanel2.add(Box.createVerticalStrut(5));
          myPanel2.add(new JLabel("Color-Green:"));
          myPanel2.add(cg);
          myPanel2.add(Box.createVerticalStrut(5));
          myPanel2.add(new JLabel("Color-Blue:"));
          myPanel2.add(cb);
          myPanel2.add(Box.createVerticalStrut(5));
          myPanel2.add(new JLabel("Degree"));
          myPanel2.add(d);
          int s = getKF.showConfirmDialog(allBtn, myPanel2,"Which KeyFrame would you like " +
              "to add?", getKF.OK_CANCEL_OPTION);
          KeyFrame kf = new KeyFrame(shapen.getText(), sa.getShapes().get(shapen.getText()),
                  Integer.parseInt(time2.getText()), Double.parseDouble(x.getText()),
              Double.parseDouble(y.getText()),
                  new Color(Integer.parseInt(cr.getText()), Integer.parseInt(cg.getText()),
                      Integer.parseInt(cb.getText())),
                  Double.parseDouble(w.getText()), Double.parseDouble(w.getText()),
              Float.parseFloat(w.getText()));
          sa.insertKeyFrame(shapen.getText(), Integer.parseInt(time2.getText()), kf);
        }
        else if (option == 3) {
          JPanel myPanel3 = new JPanel();
          JTextField time3 = new JTextField(5);
          JTextField shapen2 = new JTextField(5);
          JTextField x2 = new JTextField(5);
          JTextField y2 = new JTextField(5);
          JTextField w2 = new JTextField(5);
          JTextField h2 = new JTextField(5);
          JTextField cr2 = new JTextField(5);
          JTextField cg2 = new JTextField(5);
          JTextField cb2 = new JTextField(5);
          myPanel3.add(new JLabel("Time:"));
          myPanel3.add(time3);
          myPanel3.add(Box.createVerticalStrut(5));
          myPanel3.add(new JLabel("Shape:"));
          myPanel3.add(shapen2);
          myPanel3.add(Box.createVerticalStrut(5));
          myPanel3.add(new JLabel("Change Position:"));
          myPanel3.add(new JLabel("X Pos:"));
          myPanel3.add(x2);
          myPanel3.add(Box.createVerticalStrut(5));
          myPanel3.add(new JLabel("Y Pos:"));
          myPanel3.add(y2);
          myPanel3.add(Box.createVerticalStrut(5));
          myPanel3.add(new JLabel("Change Structure:"));
          myPanel3.add(new JLabel("Width:"));
          myPanel3.add(w2);
          myPanel3.add(Box.createVerticalStrut(5));
          myPanel3.add(new JLabel("Height:"));
          myPanel3.add(h2);
          myPanel3.add(Box.createVerticalStrut(5));
          myPanel3.add(new JLabel("Change Color:"));
          myPanel3.add(new JLabel("Color-Red:"));
          myPanel3.add(cr2);
          myPanel3.add(Box.createVerticalStrut(5));
          myPanel3.add(new JLabel("Color-Green:"));
          myPanel3.add(cg2);
          myPanel3.add(Box.createVerticalStrut(5));
          myPanel3.add(new JLabel("Color-Blue:"));
          myPanel3.add(cb2);
          int s = getKF.showConfirmDialog(allBtn, myPanel3,"Which KeyFrame " +
              "would you modify?\n Be sure to fill out all columns of desired change." +
              "", getKF.OK_CANCEL_OPTION);
          if (!x2.getText().isEmpty()) {
            sa.modifyKeyFramePos(shapen2.getText(),
                Double.parseDouble(x2.getText()),
                    Double.parseDouble(y2.getText()),
                Integer.parseInt(time3.getText()));
          }
          if (!w2.getText().isEmpty())  {
            sa.modifyKeyFrameDimensions(shapen2.getText(),
                Double.parseDouble(h2.getText()),
                    Double.parseDouble(w2.getText()),
                Integer.parseInt(time3.getText()));
          }
          if (!cr2.getText().isEmpty()) {
            sa.modifyKeyFrameColor(shapen2.getText(),
                new Color(Integer.parseInt(cr2.getText()),
                            Integer.parseInt(cg2.getText()),
                    Integer.parseInt(cb2.getText())),
                    Integer.parseInt(time3.getText()));
          }
        }
      }
    }
  };

  /**
   * Getter method for the model being used by the view.
   * @return the model of this view.
   */
  public ShapeAnimationModel getModel() {
    return this.sa;
  }

  @Override
  public boolean getStartA() {
    return this.startA;
  }

  /**
   * Sets the restart flag to false.
   * @param v the boolean value that needs to be set to false.
   */
  public void setRestartA(boolean v) {
    this.restartA = false;
  }

  @Override
  public boolean geRestartA() {
    return this.restartA;
  }

  @Override
  public boolean getPaused() {
    return this.paused;
  }

  @Override
  public boolean getLooping() {
    return this.looping;
  }



  @Override
  public void runAnimation(String fn, Appendable a) {
    //not applicable for this view

  }

  @Override
  public void showShapes(Map<String, AbstractShape> sg) {
    this.allShapes.displayPerTick(sg);
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



