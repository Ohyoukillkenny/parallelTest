package utils;

public class KV <K,V>{
    public K key;
    public V val;

    public KV(K key, V val) {
        this.key = key;
        this.val = val;
    }

    @Override
    public String toString() {
        return "KV: {" + key.toString() + ", " + val.toString() + "}";
    }
}
