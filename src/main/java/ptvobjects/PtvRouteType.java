package ptvobjects;

public enum PtvRouteType {
  Train(0), Tram(1), Bus(2), VLine(3), NightBus(4);

  private int id;

  PtvRouteType(int id) {
    this.id = id;
  }

  public int getId() {
    return this.id;
  }
}
