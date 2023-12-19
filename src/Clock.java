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

  public void update() {
    long currentUpdate = getCurrentTime();
    float delta = (float)(currentUpdate - update) + cyclesLeft;

    if(!isPaused) {
      this.cyclesPassed += (int)Math.floor(delta / msPerCycle);
      this.cyclesLeft = delta % msPerCycle;
    }
    this.update = currentUpdate;
  }
  
  public void setPaused(boolean paused) {
		this.isPaused = paused;
	}

  public boolean isPaused() {
    return isPaused;
  }

  public boolean hasCyclePassed() {
    if(cyclesPassed > 0) {
      this.cyclesPassed--;
      return true;
    }
    return false;
  }

  public boolean cyclePassed() {
    return (cyclesPassed > 0);
  }

  public static final long getCurrentTime() {
      return (System.nanoTime() / 1000000L);
  }
  
}
