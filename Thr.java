package exch_bet_thr;

import java.util.AbstractMap;
import java.util.concurrent.ConcurrentSkipListMap;
/*
интерфейс для передачи map
*/

public interface Thr extends Runnable
{
    public AbstractMap<Integer, Integer> map = new ConcurrentSkipListMap<>();
}
