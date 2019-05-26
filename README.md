# Essay-Java8-CST-Based-Generators

Control structure / java.util.Iterator / class instance private state based suspend functions just like C# yield state machine

## Compile

```bash
javac Clock.java TimedClock.java MostExcited.java
```

## Sample

### Simple Clock

```bash
[DuangSUSE@duangsuse]~/JavaGenerators% java Clock 
🕒Tock^C%                                                                                      [DuangSUSE@duangsuse]~/JavaGenerators% java Clock
🕐Tick^C% 
```

### TimedClock

```bash
[DuangSUSE@duangsuse]~/JavaGenerators% java TimedClock
Tick 🕑^C%                                                                                     [DuangSUSE@duangsuse]~/JavaGenerators% java TimedClock 200
Tick 🕓%  
```

### Most Excited

```bash
[DuangSUSE@duangsuse]~/JavaGenerators% java MostExcited
MostExcited.moveNext(MostExcited.java:30)
3
MostExcited.moveNext(MostExcited.java:33)
2
MostExcited.moveNext(MostExcited.java:36)
1
21
46
222
MostExcited.moveNext(MostExcited.java:45)
MostExcited.moveNext(MostExcited.java:47)
好🕐0
🕑0
🕒0
🕓0
🕔0
🕕0
🕖0
🕗0
🕘0
🕙0
🕚0
🕛0
MostExcited.moveNext(MostExcited.java:30)
3
MostExcited.moveNext(MostExcited.java:33)
2
MostExcited.moveNext(MostExcited.java:36)
1
MostExcited.moveNext(MostExcited.java:45)
MostExcited.moveNext(MostExcited.java:47)
好MostExcited.moveNext(MostExcited.java:30)
3
^C% 
```

## Iterators

### Simple

```java
class Clock implements Iterable<Boolean> {
  private boolean state;

  public Clock(boolean initialState) {state = initialState;}
  public Clock() {this(false);}

  class ClockIterator implements Iterator<Boolean> {
    @Override public boolean hasNext() {return true;}
    @Override public Boolean next() { state = !state; return state; }
  }
```

### Extended state with `int` i32

```java
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
```

### Extended state with `switch` control and code pointer

```java
public class MostExcited implements Iterable<Integer> {
  private int $cst_state;
  private int $moveNext_switch_for_i;
  private Random rand = new Random();
  
  private int yield_infty_count;

  private Iterator<Integer> clk = new TimedClock().iterator();
  
  private int[] ints = new int[] {1,2,3};

  public MostExcited() {}
  
  public Iterator<Integer>  iterator() {
    final MostExcited ms = this;
    return new Iterator<Integer>() {
      public boolean hasNext() { return $cst_state <= 5
      ; }
      public Integer next() { return ms.moveNext(); }
    };
  }
  
  protected void $moveNext() { $cst_state++; System.err.println(Thread.         currentThread().getStackTrace()[2]); }
  
  protected Integer moveNext() { out:do switch ($cst_state) {
    case 0:
      $moveNext();
      return 3;
    case 1:
      $moveNext();
      return 2;
    case 2:
      $moveNext();
      return 1;
    case 3:
      // May convert to if
      for (; $moveNext_switch_for_i <ints.length; ) {
        int val = ints[$moveNext_switch_for_i] * rand.nextInt(100);
        ++$moveNext_switch_for_i;
        return val;
      }
      $moveNext();
    case 4:
      $moveNext();
      return (int) '好';
    case 5:
      if (yield_infty_count < 100) {
        if ( this.clk.hasNext() ) return this.clk.next();
        ++yield_infty_count;
      }
      
      $cst_state = 0; // back to origin

      continue out;
    default: break out;
  } while (true); throw new RuntimeException("Broken iterator"); }
```

## Licence

MIT

>author: duangsuse
