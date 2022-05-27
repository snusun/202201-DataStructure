public class Position implements Comparable<Position> {
    int i;
    int j;

    public Position(int i, int j){
        this.i = i;
        this.j = j;
    }

    @Override
    public int compareTo(Position o) {
        if(this.i==o.i) {
            return this.j - o.j;
        } return this.i - o.i;
    }

    @Override
    public String toString(){
        return "(" + i + ", " + j + ")";
    }
}
