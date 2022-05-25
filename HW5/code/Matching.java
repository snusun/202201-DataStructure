import java.io.*;
import java.util.LinkedList;
import java.util.Hashtable;

public class Matching
{
	static String[]	fileData;
	static Hashtable<Integer, AVLTree<String, Position>> hashtable;

	public static void main(String[] args)
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true)
		{
			try
			{
				String input = br.readLine();
				if (input.compareTo("QUIT") == 0)
					break;

				command(input);
			}
			catch (IOException e)
			{
				System.out.println("입력이 잘못되었습니다. 오류 : " + e.toString());
			}
		}
	}

	private static void command(String input)
	{
		// TODO : 아래 문장을 삭제하고 구현해라.
		//System.out.println("<< command 함수에서 " + input + " 명령을 처리할 예정입니다 >>");
		String[] inputInfo = input.split(" ");
		if(inputInfo[0].equals("<")) {
			fileData = readFile(inputInfo[1]);
			hashtable = makeHashTable(fileData);
		} else if(inputInfo[0].equals("@")) {
			int indexNumber = Integer.parseInt(inputInfo[1]);
			findSlot(indexNumber);
		} else if(inputInfo[0].equals("?")) {
			String searchResult = searchPattern(inputInfo[1]);
			// print
		}
	}

	private static String[] readFile(String filePath){

		LinkedList<String> strList = new LinkedList<>();

		try {
			BufferedReader reader = new BufferedReader(new FileReader(filePath));

			String str;
			while ((str = reader.readLine()) != null) {
				strList.add(str);
			}
			reader.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		String[] result = new String[strList.size()];
		for(int i=0; i<strList.size(); i++){
			result[i] = strList.get(i);
		}

		//fileData = result;
		return result;
	}

	private static Hashtable<Integer, AVLTree<String, Position>> makeHashTable(String[] data){
		Hashtable<Integer, AVLTree<String, Position>> hashtable = new Hashtable<>();

		//for(String str: data){
		for(int i=0; i<data.length; i++){
			String str = data[i];
			for(int j=0; j<str.length()-5; j++){
				String subString = str.substring(j, j+6);
				char[] chars = subString.toCharArray();
				int ascii = 0;
				for(char c: chars){
					ascii += c;
				}
				int slotNum = ascii % 100;
				// 해당 key의 tree에 추가
				if(hashtable.get(slotNum)==null){
					hashtable.put(slotNum, new AVLTree<>());
				}
				hashtable.get(slotNum).insert(subString, new Position(i+1, j+1));
			}
		}

		return hashtable;
	}

	private static void findSlot(int index){
		AVLTree<String, Position> avlTree = hashtable.get(index);
		avlTree.preOrderTraversal(avlTree.getRoot());
		//return new LinkedList<>();
	}

	private static String searchPattern(String pattern){
		// pattern 6글자씩 끊어서 검색
		// 맨 처음 위치 검색 한 다음 위치 저장(여러개일 수 있으니 for loop)
		// 각 위치마자 pattern 쪼갠 것 위치 찾아가며 이어지면 위치 list에 저장
		// 마지막 부분이 6으로 떨어지지 않으면 뒤에서 부터 6글자 해서 검색 후 index 계산하기
		return "";
	}
}
