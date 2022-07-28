
import java.util.Scanner;
import java.util.HashMap;
public class Main {

	private static final char String = 0;
	public static HashMap<String, Integer> Lzw = new HashMap<String, Integer>();
	public static HashMap<Integer, String> Lzw1 = new HashMap<Integer, String>();
	public static void main(String[] args) {

		//				System.out.println(lzwEncoded("bananabanana"));


		String code = lzwEncoded("banmbaloon");
		System.out.println(code);
		//		        System.out.println(lzwDecoded(code));    
	}



	public static String lzwEncoded (String text) {
		Lzw.put("ba" , 256 );
		Lzw.put("ban" , 257 );
		Lzw.put("banm" , 258 );   //bigbaloon  257 264  == bigbaloon 
		Lzw.put("ba" , 260 );     //258 97 bigb a
		Lzw.put("bal" , 261 );
		Lzw.put("balo" , 262 );
		Lzw.put("baloo" , 263 );
		Lzw.put("baloon" , 264 );

		String newCode = "";
		String printWord  = "";
		String checkWord2 = "";
		int number = 256;
		int k;
		char a;
		String newCode2 = "";
		int depth = 2;
		int count = 1; 


		for (int i = 0; i <= text.length(); i++) {

			k=i;
			for (int j = i; j < text.length(); j++) {
				checkWord2 += text.charAt(k);  //bigba
				if (Lzw.get(checkWord2) == null && checkWord2.length() !=-1 && checkWord2.length() !=1  ) {

					//					System.out.println(k + " makom");
					//					newCode = depthCheck(text, k, printWord , depth);



					
					System.out.println(depthCheck(text, k, printWord , depth) + " 1" );
                    System.out.println(depthCheck(text, k-1, printWord.substring(0, printWord.length()-1) , depth) + " 2" );
					
                     if (count < depth) {
						
                    	 if (depthCheck(text, k, printWord , depth).length() >= depthCheck(text, k-1, printWord.substring(0, printWord.length()-1) , depth).length()) {
                    		 newCode = printWord; //bigb //big //bi
                    	 count++;}
                    	 else {
                    		 newCode = printWord.substring(0, printWord.length()-1);
                    		 i--;
                    		 k--;
                    		 count++;
                    	 }
					}
                     else { 
                    	 
                    	 newCode = depthCheck(text, k, printWord , 1);
                     }
                     





					//					System.out.println(newCode);             bigba
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


	public static String depthCheck(String text, int i , String printWord , int depth ) {
		//		System.out.println(i + " big ");
		String b ="";
		String newCode="";
		String checkWord3 = "";

		for (int j = 0; j < depth; j++) {         //bigbaloon

			if (printWord.length() == 1) {

				b += printWord.charAt(0);
				//						System.out.print(b  + "  ");
				//			newCode += b + " ";
				newCode += b;
				printWord ="";
				//			System.out.print(b + " ");
				//			System.out.println(printWord);

			}
			if (printWord.length() > 1) {
				//						System.out.print(Lzw.get(printWord ));    //bananabananabanana  length = 18
				//						System.out.print("  ");
				newCode += printWord ;
				printWord ="";  
				//            System.out.print(Lzw.get(printWord) + " ");
				//            System.out.println(printWord);
			}

			for (int j2 = printWord.length(); j2 > 2; j2--) {   //bigbaloon  
				i++;


			}
			int k=0;
			//			System.out.println(i);
			//			System.out.println(printWord);
			//			System.out.println(k);

			int r=0;
			for (int j2 = 0; j2 <= text.length(); j2++) {   //bigbaloon

				checkWord3 += text.charAt(i+j2);
//								System.out.println(checkWord3 +  " checkWord");
//								System.out.println(text.charAt(i+k));
				//				i++;


				if ( i+j2 == text.length()-1 ) {

					printWord = checkWord3;
					//					System.out.println(printWord);
					break;

				}

				if (Lzw.get(checkWord3) == null && checkWord3.length() !=-1 && checkWord3.length()!=1  ) {
					printWord = checkWord3.substring(0 , checkWord3.length()-1);
					//					System.out.println(printWord + " printWord");
					//					j2=text.length
					break;
				}

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