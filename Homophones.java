package Stack;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Homophones {

	public static void main(String[] args) throws IOException {
		
		// Step 1  -  Read the File and store the data in to MAP. Handle validation while reading the file.   
		// Step 2 - 

		File f = new File("cmducit.txt");
		Map<String, List<String>> hmap = new TreeMap<String, List<String>>();
		Map<String, Integer> hmap1 = new TreeMap<String, Integer>();
		BufferedReader br = new BufferedReader(new FileReader(f));
		int count = 0;
		String BufferLine, key, value;
		List<String> valueList = null;
		while ((BufferLine = br.readLine()) != null) {
			if ((BufferLine.charAt(0) != ';') && (BufferLine.charAt(1) != ';')) {
				String[] splited = BufferLine.split("\\s+", 2);
				value = splited[0];
				key = splited[1];
				if (hmap.containsKey(key)) {
					valueList = hmap.get(key);
					valueList.add(value);
					Collections.sort(valueList);
					hmap1.put(key, valueList.size());
				} else {
					valueList = new ArrayList<String>();
					valueList.add(value);
					hmap.put(key, valueList);
					hmap1.put(key, 1);

				}
			}
		}
		
		TreeMap<Integer, String> resultmap = new TreeMap<Integer, String>();
		List<String> resultlist = null;
		for (Map.Entry entry : hmap1.entrySet()) {
			String resultvalue = (String) entry.getKey();
			int resultkey = (int) entry.getValue();
			if (!resultmap.containsKey(resultkey)) {
				resultmap.put(resultkey, resultvalue);
			}
		}

		int k;
		int highestRank = resultmap.lastKey();
		System.out.println("enter the k value");
		Scanner in = new Scanner(System.in);
		int input = in.nextInt();
		int rankcount = 0;
		int totalcount = 0;
		int temp1 = input;
		while (totalcount <= input) {
			int rank = highestRank - rankcount;
			System.out.println(rank);
			String temp = resultmap.get(rank);
			List<String> result = hmap.get(temp);
			int Listsize = result.size();
			temp1 = temp1 - Listsize;
			if (!(temp1 <= 0)) {// get all elments of that list
				totalcount = totalcount + Listsize;
				for (int i = 0; i < Listsize; i++) {
					System.out.println(result.get(i) + "\t" + rank);
				}
			} else {
				totalcount = totalcount + temp1;
				// get upto temp elemnts
				for (int i = 0; i < temp1; i++) {
					System.out.println(result.get(i) + "\t" + rank);
				}
			}
			rankcount++;
		}
	}
}
