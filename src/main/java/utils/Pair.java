package utils;

public class Pair<A, B> {
    public A left;
    public B right;

    public Pair(A left, B right) {
        this.left = left;
        this.right = right;
    }

    public static<A,B> Pair<A,B> empty(){
        return new Pair<>(null, null);
    }

    public boolean isEmpty(){
        return left == null && right == null;
    }

    @Override
    public String toString() {
        return "{Pair: "+left.toString()+", "+right.toString()+"}";
    }
}
