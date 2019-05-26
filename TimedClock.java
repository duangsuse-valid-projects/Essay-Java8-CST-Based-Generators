import static java.lang.System.out;
import java.util.*;

abstract class IntegerClock implements Iterable<Integer> {}

class TimedClock extends IntegerClock {
  private static List<Integer> ticks = new ArrayList<>(24);
  private int state;
  
  private Iterator<Boolean> ticker = new Clock().iterator();
  
  static {
    // Arrays.asList("🕐🕑🕒🕓🕔🕕🕖🕗🕘🕙🕚🕛🕜🕝🕞🕟🕠🕡🕢🕣🕤🕥🕦🕧".split(""));
    final String clocks = "🕐🕑🕒🕓🕔🕕🕖🕗🕘🕙🕚🕛🕜🕝🕞🕟🕠🕡🕢🕣🕤🕥🕦🕧";
    int clockChars = clocks.codePointCount(0, clocks.length());

    for (int i=0; i<24;i++) ticks.add(0);
    
    for (int i=0;i <clockChars; i+=2) {
      ticks.set(i, clocks.codePointAt(i));
    }
  }

  public TimedClock(int initialState) {state = initialState;}
  public TimedClock() {this(-1);}
  
  public Iterator<Boolean> getTicker() {return ticker;}
  
  class ClockIterator implements Iterator<Integer> {
    @Override public boolean hasNext() { return state <ticks.size()-1; }
    @Override public Integer next() { state++; return ticks.get(state); }
  }

  @Override public Iterator<Integer> iterator() {return new TimedClock().new ClockIterator();}

  public static final void main(String... args) throws InterruptedException {
    int to_sleep = 500;
    if (args.length == 1) to_sleep = Integer.parseInt(args[0]);
    TimedClock clock = new TimedClock();
    while (true) for (Integer s : clock) {
      out.print(clock.getTicker().next() ? "Tick" : "Tock");
      out.print(' ');
      char[] cs = Character.toChars(s);
      out.print(cs);
      Thread.sleep(to_sleep);
      int count = 2 + 4 + 1;
      out.write(0x1b); out.print("["+count+"D"); // https://en.wikipedia.org/wiki/ANSI_escape_code#DL
    }
  }
}

