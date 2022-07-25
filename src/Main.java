import java.util.Scanner;
import java.util.HashMap;
public class Main {

	private static final char String = 0;
	public static HashMap<String, Integer> Lzw = new HashMap<String, Integer>();
	public static HashMap<Integer, String> Lzw1 = new HashMap<Integer, String>();
	public static void main(String[] args) {

//				System.out.println(lzwEncoded("bananabanana"));
		
		
				String code = lzwEncoded("bananabananabanana");
				System.out.println(code);
		        System.out.println(lzwDecoded(code));    
	}

	
	
	public static String lzwEncoded (String text) {
//		Lzw.put("bi" , 256 );
//		Lzw.put("big" , 257 );
//		Lzw.put("bigb" , 258 );   //bigbaloon  257 264  == bigbaloon 
//		Lzw.put("ba" , 260 );     //258 97 bigb a
//		Lzw.put("bal" , 261 );
//		Lzw.put("balo" , 262 );
//		Lzw.put("baloo" , 263 );
//		Lzw.put("baloon" , 264 );
		
		String newCode = "";
		String printWord  = "";
		String checkWord2 = "";
		int number = 256;
		int k;
		char a;
		String newCode2 = "";
		

		for (int i = 0; i <= text.length(); i++) {

			k=i;
			for (int j = i; j < text.length(); j++) {
				checkWord2 += text.charAt(k);
				if (Lzw.get(checkWord2) == null && checkWord2.length() !=-1 && checkWord2.length() !=1  ) {
					
					newCode = depthCheck(text, i, printWord);
					
//					System.out.println(newCode);
					if (newCode.length() ==1) {
					a = newCode.charAt(0);
					int b = (int)a;
					newCode2+= b + " "; }
					else {
						
						newCode2 += Lzw.get(newCode) + " ";
					}
					
					
					
				}


				printWord += text.charAt(k);
				if (Lzw.get(checkWord2) == null && checkWord2.length() != 1) {
					Lzw.put(checkWord2 , number );
//					System.out.print(checkWord2 + " ");
//					System.out.println(number);
					Lzw1.put(number, checkWord2);
					number++;


//					if (printWord.length() >= 2) {
                    for (int j2 = printWord.length(); j2 > 2; j2--) {
							
                               	i++;
						}
						

//					}
					checkWord2 ="";
					printWord="";
					break;
				}
				else 
					k++;



			}



		}

		return newCode2;

	}
	
	
	public static String depthCheck(String text, int i , String printWord) {
		String b ="";
		String newCode="";
		
		
		if (printWord.length() == 1) {
		
			 b += printWord.charAt(0);
			//						System.out.print(b  + "  ");
//			newCode += b + " ";
			return b;
//			System.out.print(b + " ");
//			System.out.println(printWord);

		}
		if (printWord.length() > 1) {
			//						System.out.print(Lzw.get(printWord ));
			//						System.out.print("  ");
			newCode += printWord ;
			return newCode;
//            System.out.print(Lzw.get(printWord) + " ");
//            System.out.println(printWord);
		}
		
		
		return "";
	}

	public static String lzwDecoded (String code) {
		String check1 = "";
		String check2 = "";
		int word = 0;


		for (int i = 0; i < code.length(); i++) {




			if ((int)code.charAt(i)  == 32  ) {
//				System.out.print(word + " " );
				if (word<256) {
					check1 += ((char)word);
				}
				else {
					check1+= Lzw1.get(word) ;
				}

				word = 0;


			}

			else {

				word *= 10;
				word = word +  (code.charAt(i)-48) ;
			}






		}




//		System.out.println(check1);   
		return check1;
	}



}