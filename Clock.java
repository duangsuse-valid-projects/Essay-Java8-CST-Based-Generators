import static java.lang.System.out;
import java.util.Iterator;

class Clock implements Iterable<Boolean> {
  private boolean state;

  public Clock(boolean initialState) {state = initialState;}
  public Clock() {this(false);}
  
  class ClockIterator implements Iterator<Boolean> {
    @Override public boolean hasNext() {return true;}
    @Override public Boolean next() { state = !state; return state; }
  }
  
  @Override public Iterator<Boolean> iterator() {return new Clock().new ClockIterator();}
  
  public static void main(String... args) throws InterruptedException {
    Clock clock = new Clock();
    for (boolean b : clock) {
      out.print(b ? "🕐Tick" : "🕒Tock");
      Thread.sleep(500);
      out.write(0x1b); out.print("[6D"); // https://en.wikipedia.org/wiki/ANSI_escape_code#DL
    }
  }
}
