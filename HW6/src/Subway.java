import java.io.*;
import java.util.ArrayList;
import java.util.Map;

public class Subway {
    //static ArrayList<ArrayList<Station>> subwayGraph;
    static Map<Station, ArrayList<Station>> subwayGraph;

    public static void main(String[] args) {
        makeGraph(args[0]);
        // ../text/subway.txt

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            try {
                String input = br.readLine();
                if (input.compareTo("QUIT") == 0)
                    break;

                findShortestPath(input);
            } catch (IOException e) {
                System.out.println("입력이 잘못되었습니다. 오류 : " + e.toString());
            }
        }
    }

    private static void makeGraph(String filePath) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8"));

            String str;
            while ((str = reader.readLine()) != null) {
                System.out.println(str);
            }
            reader.close();
        } catch (Exception e) {
            System.out.println("File not found");
        }

        // input 받아 그래프 생성 -> map
        // 환승역은 edge 0으로 해서 연결
        // vertex : 고유번호, 역이름, line
        // edge

        // make graph
    }

    private static void findShortestPath(String input) {

        // find shortest path
    }
}
