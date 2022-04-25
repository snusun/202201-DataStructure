public class HanoiTower {
    static int count =0;

    public void move(int number, String first, String center, String last){

        if(number == 1){
            ++ count;
            System.out.println(count + ": "+ first + " -> " + center);
        } else{
            move(number -1, first, last, center);
            move(1, first, center, last);
            move(number -1, last, center, first);
        }
    }

    public void move4Tower(int number, String first, String center1, String center2, String last){

        if(number == 1){
            ++ count;
            System.out.println(count + ": "+ first + " -> " + center1);
        } else if(number == 2) {
            ++ count;
            System.out.println(count + ": "+ first + " -> " + center2);
            ++ count;
            System.out.println(count + ": "+ first + " -> " + center1);
            ++ count;
            System.out.println(count + ": "+ center2 + " -> " + center1);
        } else {
            move4Tower(number - 2, first, last, center1, center2);
            move4Tower(2, first, center1, center2, last);
            move4Tower(number -2, last, center1, center2, first);
        }
    }

    public void move3(int n, String first, String center, String last){
        if(n<=0) return;
        if(n<=3) {
            System.out.println(n+"개 이동");
            System.out.println(count + ": "+ first + " -> " + center);
        } else {
            move3(n-3, first, last, center);
            move3(3, first, center, last);
            move3(n-3, last, center, first);
        }
    }

    public void move2(int n, String first, String center, String last){
        if(n<=0) return;
        if(n<=2) {
            System.out.println(n+"개 이동");
            System.out.println(count + ": "+ first + " -> " + center);
        } else {
            move2(n-2, first, last, center);
            move2(2, first, center, last);
            move2(n-2, last, center, first);
        }
    }

}