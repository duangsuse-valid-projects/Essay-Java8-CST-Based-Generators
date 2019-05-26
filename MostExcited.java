import java.io.PrintStream;
import java.util.*;

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
  
  protected void $moveNext() { $cst_state++; System.err.println(Thread. 	currentThread().getStackTrace()[2]); }
  
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

  public static final void main(String... args) throws InterruptedException {
    int speed = 500;
    
    if (args.length == 1) speed = Integer.parseInt(args[0]);
    
    final MostExcited frog = new MostExcited();
    
    for (Integer i: frog) {
      if (i > 20000) printInt(System.out, i);
      else System.out.println(i);
      
      Thread.sleep(speed); // TODO: Use promise?
    }
  }
  
  public static void printInt(PrintStream stm, int cp) { stm.print(Character.toChars(cp)); }
}
