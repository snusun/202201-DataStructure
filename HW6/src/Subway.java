import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Subway {
    static Map<String, String> stations = new HashMap<>(); // number, name
    static Map<String, ArrayList<String>> stationMap = new HashMap<>(); // name numberList
    static Map<String, ArrayList<Cost>> subwayGraph = new HashMap<>();
    static Map<String, Long> costMap = new HashMap<>(); // number, cost
    static Map<String, String> trackRoute = new HashMap<>(); // number, number

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
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8));

            boolean isFirst = true;
            String str;
            while ((str = reader.readLine()) != null) { // make graph
                if (str.equals("")) {
                    isFirst = false;
                    continue;
                }

                if (isFirst) { // 역 정보 input
                    String[] stationInfo = str.split(" ");
                    String num = stationInfo[0];
                    String name = stationInfo[1];

                    stations.put(num, name);

                    if (stationMap.get(name) == null) {
                        ArrayList<String> stationList = new ArrayList<>();
                        stationList.add(num);
                        stationMap.put(name, stationList);
                    } else {
                        stationMap.get(name).add(num);
                    }

                    costMap.put(num, Long.MAX_VALUE);
                } else { // 경로 정보 input
                    String[] routeInfo = str.split(" ");
                    String start = routeInfo[0];
                    String goal = routeInfo[1];
                    int cost = Integer.parseInt(routeInfo[2]);

                    if (subwayGraph.get(start) == null) {
                        ArrayList<Cost> costList = new ArrayList<>();
                        costList.add(new Cost(goal, cost));
                        subwayGraph.put(start, costList);
                    } else {
                        subwayGraph.get(start).add(new Cost(goal, cost));
                    }
                }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 환승역끼리도 경로 생성, cost 0
        for (String key : stationMap.keySet()) {
            ArrayList<String> stationList = stationMap.get(key);
            int size = stationList.size();
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (i == j) continue;
                    String start = stationList.get(i);
                    String goal = stationList.get(j);
                    if (subwayGraph.get(start) == null) {
                        ArrayList<Cost> costList = new ArrayList<>();
                        costList.add(new Cost(goal));
                        subwayGraph.put(start, costList);
                    } else {
                        subwayGraph.get(start).add(new Cost(goal));
                    }
                }
            }
        }
    }

    private static void findShortestPath(String input) {
        String[] findPath = input.split(" ");
        String start = findPath[0];
        String goal = findPath[1];

        String startNum = stationMap.get(start).get(0);
        String goalNum = stationMap.get(goal).get(0);

        System.out.println(start + " " + startNum);
        System.out.println(goal + " " + goalNum);

        PriorityQueue<Cost> pQ = new PriorityQueue<>();
        pQ.offer(new Cost(startNum, 0));
        costMap.put(startNum, 0L);
        while (!pQ.isEmpty()) {
            Cost temp = pQ.poll();
            String now = temp.number;
            long nowCost = temp.cost;
            if (costMap.get(now) < nowCost) continue;
            for (Cost ob : subwayGraph.get(now)) {
                if (costMap.get(ob.number) > ob.cost + nowCost) {
                    costMap.put(ob.number, (ob.cost + nowCost));
                    System.out.println(ob.number + " " + (ob.cost + nowCost));
                    pQ.offer(new Cost(ob.number, costMap.get(ob.number)));
                    trackRoute.put(ob.number, now);
                }
            }
        }

        Stack<String> stack = new Stack<>();
        String tempNum = goalNum;
        while (true) {
            stack.push(tempNum);
            tempNum = trackRoute.get(tempNum);
            if (tempNum.equals(startNum)) {
                stack.push(tempNum);
                break;
            }
        }
        while (!stack.isEmpty()) {
            String num = stack.pop();
            System.out.print(stations.get(num) + " ");
        }
        System.out.println();
        System.out.println(costMap.get(goalNum));
    }
}
