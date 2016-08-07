package exch_bet_thr;
/*
Этот файл я написал только для проверки
*/
import static exch_bet_thr.Thr.map;

public class test//для тестирования работоспособности Exch_bet_thr
{   
private static volatile int count = 0;
private final static int count_of_threads = 0;   //количество используемых потоков
   public static class test_threads implements Thr
   {
       
       @Override
       public void run()
       {
           for(int i = 0; i < count_of_threads; i++)
           {
               map.put(count++, (int)Thread.currentThread().getId());
           }
           
       }
       
   }
    public static void main(String[] args)
    {
        
        
        Thr[] ar = new Thr[count_of_threads];
        Thread[] th = new Thread[count_of_threads];
        for(int i = 0; i < 5; i++)
        {
            ar[i] = new test_threads();
            th[i] = new Thread(ar[i]);
            th[i].start();
        }
        Exch_bet_thr test_th = new Exch_bet_thr(count_of_threads, ar);
        test_th.res();
       
    }
}