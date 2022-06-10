public class Station implements Comparable<Station> {
    String number;
    String name;
    String line;
    int cost=0;

    public Station(String number, String name, String line){
        this.number = number;
        this.name = name;
        this.line = line;
    }

    @Override
    public int compareTo(Station o) {
        return this.number.compareTo(o.number);
    }
}
