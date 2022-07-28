
import java.util.Scanner;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import java.util.HashMap;
public class Main {

	private static final char String = 0;
	public static HashMap<String, Integer> Lzw = new HashMap<String, Integer>();
	public static HashMap<Integer, String> Lzw1 = new HashMap<Integer, String>();
	public static void main(String[] args) {

		//				System.out.println(lzwEncoded("bananabanana"));


		String code = lzwEncoded("bigbaloonec ");
		System.out.println(code);
		//		        System.out.println(lzwDecoded(code));    
	}



	public static String lzwEncoded (String text) {
		Lzw.put("bi" , 256 );
		Lzw.put("big" , 257 );
		Lzw.put("bigb" , 258 );   //bigbaloon  257 264  == bigbaloon 
		Lzw.put("ba" , 260 );     //258 97 bigb a
		Lzw.put("bal" , 261 );
		Lzw.put("balo" , 262 );
		Lzw.put("baloo" , 263 );
		Lzw.put("baloon" , 264 );
		Lzw.put("lo" , 265 );
		Lzw.put("loo" , 266 );
		Lzw.put("loon" , 267 );
		Lzw.put("loone" , 268 );
		Lzw.put("loonec" , 269 );

		String newCode = "";
		String printWord  = "";
		String checkWord2 = "";
		int number = 256;
		int k;
		char a;
		String newCode2 = "";
		int depth = 3;
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

					if (count < 2) {

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
//				System.out.println(i + " big ");
		String b ="";
		String newCode="";
		String checkWord3 = "";
		int count = 0;
		String buildI = "";

		for (int j = 0; j < depth; j++) {         //bigbaloon
			

			if (printWord.length() == 1) {

				b += printWord.charAt(0);
				//						System.out.print(b  + "  ");
				//			newCode += b + " ";
				newCode += b;
				 buildI=printWord;
				printWord ="";
//				System.out.println(newCode);


			}
			if (printWord.length() > 1) {
				//						System.out.print(Lzw.get(printWord ));    //bananabananabanana  length = 18
				//						System.out.print("  ");
				newCode += printWord ;
				buildI=printWord;
				printWord ="";  
//				System.out.println(newCode);

			}
			
			checkWord3="";
			
			if (count==0) {
//				i++;
//				System.out.println(i);
				count++;
			} 
			else {
			for (int j2 = buildI.length(); j2 > 0; j2--) {   //bigbaloon  
				i++;
			}
			}
			
			  
			
			
			if (i>=text.length()) {
				break;
			}
			

			
			for (int j2 = 0; j2 <= text.length(); j2++) {   //bigbaloon

				checkWord3 += text.charAt(i+j2);
//				System.out.println(checkWord3 +  " checkWord");
				//								System.out.println(text.charAt(i+k));
				//				i++;



				if (Lzw.get(checkWord3) == null && checkWord3.length() !=-1 && checkWord3.length() != 1  ) {
					printWord = checkWord3.substring(0 , checkWord3.length()-1);
					//					System.out.println(printWord + " printWord");
					//					j2=text.length
					break;
				}
				if ( i+j2 >= text.length()-1  ) {
					
					printWord = checkWord3;
					
					//					System.out.println(printWord);
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