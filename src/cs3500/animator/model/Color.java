package cs3500.animator.model;

/**
 * Class representing the colors of a shape.
 */
public class Color {

  private int newRed;
  private int newBlue;
  private int newGreen;

  /**
   * Constructor for color.
   * @param newRed Red value of the color.
   * @param newBlue Blue value of the color.
   * @param newGreen Greem value of the color.
   */
  public Color(int newRed, int newBlue, int newGreen) {
    this.newRed = newRed;
    this.newBlue = newBlue;
    this.newGreen = newGreen;
  }

  /**
   * returns the red value of the color.
   * @return red value.
   */
  public int getRed() {
    return this.newRed;
  }

  /**
   * returns the blue value of the color.
   * @return blue value.
   */
  public int getBlue() {
    return this.newBlue;
  }

  /**
   * returns the green value of the color.
   * @return green value.
   */
  public int getGreen() {
    return this.newGreen;
  }

}
