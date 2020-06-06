package utils;

import java.util.HashMap;
import java.util.function.BiFunction;

public class HM {
    public static <K,V> void merge(HashMap<K,V> hm1, HashMap<K,V> hm2, BiFunction<V,V,V> op){
        hm2.forEach((key, val) -> {
            if (hm1.containsKey(key)){
                hm1.put(key, op.apply(hm1.get(key),val));
            } else {
                hm1.put(key, val);
            }
        });
    }

    public static void main(String[] args) {
        HashMap<Long,Long> hm1 = new HashMap<>();
        HashMap<Long,Long> hm2 = new HashMap<>();
        HashMap<Long,Long> hm3 = new HashMap<>();
        for (long i = 1; i < 19; i++) {
            hm1.put(i, i);
        }

        for (long i = 1; i < 10; i++) {
            hm2.put(2*i, i);
        }

        for (long i = 1; i < 7; i++) {
            hm3.put(3*i, i);
        }

        HashMap<Long,Long> mergedHm = new HashMap<>();

        hm1.forEach((k, v) -> System.out.println("hm1: "+ k + ", "+v));

        merge(mergedHm, hm1, (x,y) -> x+y);
        mergedHm.forEach((k,v) -> System.out.println(k + ": "+v));
        merge(mergedHm, hm2, (x,y) -> x+y);
        mergedHm.forEach((k,v) -> System.out.println(k + ": "+v));
        merge(mergedHm, hm3, (x,y) -> x+y);
        mergedHm.forEach((k,v) -> System.out.println(k + ": "+v));
    }
}
