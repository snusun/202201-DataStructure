import java.io.*;
import java.util.LinkedList;

public class Matching
{
	static String[]	fileData;

	public static void main(String args[])
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
		} else if(inputInfo[0].equals("@")) {
			int indexNumber = Integer.parseInt(inputInfo[1]);
			String[] slotResult = findSlot(indexNumber);
			// print
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

		return result;
	}

	private static String[] findSlot(int index){
		return new String[0];
	}

	private static String searchPattern(String pattern){
		return "";
	}
}
