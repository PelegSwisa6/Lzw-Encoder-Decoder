import java.util.Scanner;
import java.util.HashMap;
public class Main {

	public static void main(String[] args) {

		HashMap<String, Integer> Lzw = new HashMap<String, Integer>();


		String banana = "bananabanana ";
		String printWord  = "";
		String checkWord2 = "";
		int number = 256;
		int k;
		char a;
		for (int i = 0; i < banana.length(); i++) {
			//			if (banana.charAt(i) == '$')
			//				break;
			//		checkWord = banana.substring(i,i+1);
			k=i;
			for (int j = i; j < banana.length(); j++) {
				checkWord2 += banana.charAt(k);
				if (Lzw.get(checkWord2) == null && checkWord2.length() !=-1 ) {
					if (printWord.length() == 1) {
						a = printWord.charAt(0); 
						int b = (int) a;
						System.out.print(b  + "  ");
					}
					if (printWord.length() > 1) {
						System.out.print(Lzw.get(printWord ));
						System.out.print("  ");

					}
				}


				printWord += banana.charAt(k);
				//			checkWord = banana.substring(i,j);
				if (Lzw.get(checkWord2) == null && checkWord2.length() != 1) {
					Lzw.put(checkWord2 , number );
					number++;
					//	            System.out.println(checkWord2 +" " +  i);

					if (checkWord2.length() > 2) {
						//	            	for (int j2 = printWord.length(); j2 > 0; j2--) {
						i++;
						//					}
					}
					checkWord2 = "";
					printWord="";
					break;
				}
				else 
					k++;



			}



		}
		System.out.println(" ");
		System.out.println(Lzw.size());
		//		System.out.println(Lzw.get(" "));
		for (String i : Lzw.keySet()) {
			System.out.println(i);
		}
		String peleg = lzwEncoded("bananabanana ");
		System.out.println(peleg);
	}

	public static String lzwEncoded (String code) {
		HashMap<String, Integer> Lzw = new HashMap<String, Integer>();
        String newCode = "";
		String printWord  = "";
		String checkWord2 = "";
		int number = 256;
		int k;
		char a;
		
		for (int i = 0; i < code.length(); i++) {

			k=i;
			for (int j = i; j < code.length(); j++) {
				checkWord2 += code.charAt(k);
				if (Lzw.get(checkWord2) == null && checkWord2.length() !=-1 ) {
					if (printWord.length() == 1) {
						a = printWord.charAt(0); 
						int b = (int) a;
//						System.out.print(b  + "  ");
						newCode += b + "  ";
						
					}
					if (printWord.length() > 1) {
//						System.out.print(Lzw.get(printWord ));
//						System.out.print("  ");
						newCode += Lzw.get(printWord) + "  " ;

					}
				}


				printWord += code.charAt(k);
				if (Lzw.get(checkWord2) == null && checkWord2.length() != 1) {
					Lzw.put(checkWord2 , number );
					number++;
					

					if (checkWord2.length() > 2) {

						i++;

					}
					checkWord2 = "";
					printWord="";
					break;
				}
				else 
					k++;



			}



		}
		
		return newCode;

	}



}
