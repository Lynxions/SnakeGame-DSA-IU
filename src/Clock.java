public class Clock {
  private float msPerCycle;

  private long update;

  private int cyclesPassed;

  private float cyclesLeft;

  private boolean isPaused;

  public Clock(float cyclesPerSecond) {
    setCyclesPerSecond(cyclesPerSecond);
    reset();
  }

  public void setCyclesPerSecond(float cyclesPerSecond) {
    this.msPerCycle = (1.0f / cyclesPerSecond) * 1000;
  }

  public void reset() {
    this.cyclesPassed = 0;
    this.cyclesLeft = 0.0f;
    this.update = getCurrentTime();
    this.isPaused = false;
  }

  
}
