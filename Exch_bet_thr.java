package exch_bet_thr;

import java.util.AbstractMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/*
Передаём в конструктор класса количество потоков и массив с этими потоками,
методом res() вызываем потоки для обработки
Processing получает от каждого потока его карту и выводит её на консоль
*/

public class Exch_bet_thr {
    
    int count;//поле с количеством потоков
    Thr[] t;//массив с потоками
    public Exch_bet_thr(int count, Thr[] t)//конструктор получает количество потоков и запускает потоки-обработчики
    {
        this.count = count;
        this.t = t;
    }
        
    public void res()//метод для вызова потоков для обработки       
    {    
    ExecutorService threadExec = Executors.newFixedThreadPool(count);//исполнитель для потоков-обработчиков    
    Runnable[] pr = new Runnable[count];
    for(int i = 0; i < count; i++)
    {
        pr[i] = new Processing(t[i]);//
        threadExec.execute(pr[i]);//передаём исполнителю поток
    }
    threadExec.shutdown();//завершаем все потоки после выполнения
    }
    class Processing implements Runnable//поток получает карту, сортирует и печатает её в консоль
    {
        AbstractMap<Integer, Integer> full_map = new ConcurrentSkipListMap<>();
        Thr t;
        public Processing(Thr t)
        {
            this.t = (Thr) t;
        }
        @Override
        public void run()
        {
            full_map = Thr.map;
            for(AbstractMap.Entry<Integer, Integer> entry : full_map.entrySet())
            {
                System.out.println(entry.getKey() + "/" + entry.getValue());
            }
            full_map.clear();
        }
    }
  
}
