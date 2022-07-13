import java.util.Scanner;
import java.util.HashMap;
public class Main {

	public static HashMap<String, Integer> Lzw = new HashMap<String, Integer>();
	public static HashMap<Integer, String> Lzw1 = new HashMap<Integer, String>();
	public static void main(String[] args) {

				System.out.println(lzwEncoded("bananabanana "));
//				String code = lzwEncoded("bananabanana " ); 
		lzwDecoded("98 97 110 257 97 256 258 258");    
	}
//	"bananabanana" 98 97 110 97 258 

//	98 97 110 257 97 
//	
//	ba 256
//	an 257
//	na 258
//	an 259
//	ab 260
	
	
	public static String lzwEncoded (String text) {
		//		HashMap<String, Integer> Lzw = new HashMap<String, Integer>();
		String newCode = "";
		String printWord  = "";
		String checkWord2 = "";
		int number = 256;
		int k;
		char a;

		for (int i = 0; i < text.length(); i++) {

			k=i;
			for (int j = i; j < text.length(); j++) {
				checkWord2 += text.charAt(k);
				if (Lzw.get(checkWord2) == null && checkWord2.length() !=-1 ) {
					if (printWord.length() == 1) {
						a = printWord.charAt(0); 
						int b = (int) a;
						//						System.out.print(b  + "  ");
						newCode += b + " ";
//						System.out.print(b + " ");
//						System.out.println(printWord);

					}
					if (printWord.length() > 1) {
						//						System.out.print(Lzw.get(printWord ));
						//						System.out.print("  ");
						newCode += Lzw.get(printWord) + " " ;
//                        System.out.print(Lzw.get(printWord) + " ");
//                        System.out.println(printWord);
					}
				}


				printWord += text.charAt(k);
				if (Lzw.get(checkWord2) == null && checkWord2.length() != 1) {
					Lzw.put(checkWord2 , number );
//					System.out.print(checkWord2 + " ");
//					System.out.println(number);
					Lzw1.put(number, checkWord2);
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

	public static String lzwDecoded (String code) {
		String check1 = "";
		String check2 = "";
		int word = 0;


		for (int i = 0; i < code.length(); i++) {




			if ((int)code.charAt(i)  == 32  ) {
				System.out.print(word + " " );
				if (word<256) {
					check1 += ((char)word);
				}
				else {
					check1+= Lzw1.get(word);
				}

				word = 0;


			}

			else {

				word *= 10;
				word = word +  (code.charAt(i)-48) ;
			}






		}




		System.out.println(check1);   
		return check2;
	}



}
