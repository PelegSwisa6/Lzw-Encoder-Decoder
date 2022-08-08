
import java.util.Scanner;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import com.sun.org.apache.xpath.internal.axes.WalkingIteratorSorted;

import java.awt.desktop.ScreenSleepEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Writer;
import java.util.Arrays;
import java.util.HashMap;
public class Main {

	private static final char String = 0;
	public static HashMap<String, Integer> Lzw = new HashMap<String, Integer>();
	public static HashMap<Integer, String> Lzw1 = new HashMap<Integer, String>();
	public static HashMap<String, Integer> Lzw3 = new HashMap<String, Integer>();   
	public static HashMap<Integer,String> Lzw4 = new HashMap< Integer,String>();   

	public static String IN_FILE_PATH = "";
	public static String BACK_UP_PATH = "";
	public static String COMPRESS_PATH;
	public static String OUT_FILE_PATH = "c.txt";
	static String END_FILE_PATH = "Smileyb.bmp";       //    
	static int depth;

	public Main(String IN_FILE_PATH , String COMPRESS_PATH , int depth , String BACK_UP_PATH) {
		this.IN_FILE_PATH = IN_FILE_PATH ;
		this.COMPRESS_PATH = COMPRESS_PATH;
		this.depth = depth;
		this.BACK_UP_PATH = BACK_UP_PATH ;
		

	}

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException, InterruptedException {

		MainFrame window = new MainFrame();

		boolean wait = true;

		while(IN_FILE_PATH.length() == 0 ) {
			System.out.println("wait for user");

		}

		IN_FILE_PATH = IN_FILE_PATH;
        COMPRESS_PATH = COMPRESS_PATH;
		depth = depth;
		BACK_UP_PATH = BACK_UP_PATH;
		

		String code2 = "";
		char a =' ';

		FileInputStream input;
		FileOutputStream output;
		FileOutputStream end;



		//		File path = new File("OnTheOrigin.txt");
		//		byte[] array = method(path);


		input = new FileInputStream(IN_FILE_PATH);	
		//		output = new FileOutputStream(OUT_FILE_PATH);
		//		end = new FileOutputStream(END_FILE_PATH);

		String mishpat = "a";
		StringBuilder fb = new StringBuilder();
		int i = 1;


		while (true)				

		{
			int x = (int) input.read();
			if (x != -1) // -1 is EOF
			{

				for (char character : mishpat.toCharArray()) {
					fb.append((char)x);

				}

			}
			else
			{                                   

				break;
			}
		}


		String code = lzwEncoded(fb.toString() ,depth);
		BufferedWriter writer1 = new BufferedWriter(new FileWriter("numbers.txt"));
		writer1.write(code);

		
		String write = numbersCompress(code);

		BufferedWriter writer4 = new BufferedWriter(new FileWriter(COMPRESS_PATH +".txt"));
		writer4.write(write);




		String end2;
		end2 = restoreNumbers(write);
		BufferedWriter writer5 = new BufferedWriter(new FileWriter("end2.txt"));
		writer5.write(end2);


		String decode = lzwDecoded(end2);	
		


		int start ;
		int endText ; 
		if (IN_FILE_PATH.charAt(IN_FILE_PATH.length()-4) == '.') {
			 start = IN_FILE_PATH.length()-4;		
		}
		else
			start = IN_FILE_PATH.length()-5;		
		
		
		 endText = IN_FILE_PATH.length();
		
		BufferedWriter writer6 = new BufferedWriter(new FileWriter(BACK_UP_PATH + IN_FILE_PATH.substring(start, endText)));

		writer6.write(decode);
		
		System.out.println("done");
		
		
		MainFrame done = new MainFrame("THE FILE IS COMPRESSED AND SAVE IN LZW FOLDER UNDER THE NAME: " + COMPRESS_PATH + " THE RESTORE FILE SAVE UNDER NAME: " + BACK_UP_PATH  );

		
		writer1.close();
		writer4.close();   
		writer5.close();
		writer6.close(); 
	}
	


	public static String restoreNumbers (String bytes) {

		String check2 = "";
		String end2 = "";


		for (int j = 0; j < bytes.length(); j++) {

			if ( check2.length() == 2) {

				end2 += Lzw3.get(check2) + " "; 

				check2 = "";
			}

			check2 += bytes.charAt(j);

		}
		return end2 ;

	}

	public static String numbersCompress (String code) {
		int word = 0;
		int A = 1;
		int B = 1;

		String check1 = "";
		String write = "";
		for (int k = 0; k < code.length(); k++) {


			if ( code.charAt(k)  == ' '  ) {
			
				if(word >= 0) {
					check1 += (char)A;
					check1 += (char)B;

					System.out.println(check1);
					System.out.println(word);
					Lzw3.put(check1, word);
					Lzw4.put(word ,check1 );
					write += check1;

					if (A == 32 || A == 34) 
						A++;

					if (B == 32 || B == 34 )
						B++;

//					if (C == 32 || C == 34) 
//						C++;

					if (B == 254 && A != 254) {
						A++;
						B=1;
					}
					
//					if (A == 254 && B == 254) {
////						C++;
//						A = 35;
//						B = 35;
//					}

					B++;
					
					check1 = "";
					word = 0;
				}

			}

			else {

				word *= 10;
				word = word +  (code.charAt(k)-48) ;
			}
		}
		return write;
	}



	
	
	
	public static String lzwEncoded (String text, int depth2) throws IOException, InterruptedException {



		String newCode = "";
		String printWord  = "";
		String checkWord2 = "";
		int number = 256;
		
		int k;
		char a;
		String newCode2 = "";
		int depth = depth2;
		int count = depth; 
		int numOfNum = 0;




		for (int i = 0; i <= text.length(); i++) {

			k=i;
			for (int j = i; j < text.length(); j++) {
				checkWord2 += text.charAt(k);  //bigba

				if (Lzw.get(checkWord2) == null && checkWord2.length() !=-1 && checkWord2.length() !=1  ) {


					if (depth % count == 0 ) {

						if (depthCheck(text, k, printWord , depth).length() >= depthCheck(text, k-1, printWord.substring(0, printWord.length()-1) , depth).length()) {
							newCode = printWord; 
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
					if (newCode.length() == 1) {
						a = newCode.charAt(0);
						int b = (int)a;
						newCode2+= b + " " ; 
						numOfNum++;}


					else {

						newCode2 += Lzw.get(newCode) + " " ;
						numOfNum++;
					}



				}


				printWord += text.charAt(k);
				if (Lzw.get(checkWord2) == null && checkWord2.length() != 1) {
					Lzw.put(checkWord2 , number );
					Lzw1.put(number, checkWord2);
					number++;


				
					for (int j2 = printWord.length(); j2 > 2; j2--) {

						i++;
					}


			
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
				


			}
			if (printWord.length() > 1) {
			
				newCode += printWord ;
				buildI=printWord;
				printWord ="";  
				

			}

			checkWord3="";

			if (count==0) {
				
				count++;
			} 
			else {
				for (int j2 = buildI.length(); j2 > 0; j2--) {    
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
		
		String array [] = code.split(" ");
		
		
		
		
		for (int i = 0; i < array.length; i++) {
			
			
			
			if (Integer.parseInt(array[i]) < 256) {
				check1 += ((char)Integer.parseInt(array[i]));
				
			}
			
			else
				check1 += Lzw1.get(Integer.parseInt(array[i])) ; 
			
			
		}
		return check1; 
		







		  
		
	}





}