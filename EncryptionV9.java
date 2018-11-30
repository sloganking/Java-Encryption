/* ======================================================
 * 
 * == Header ==
 * Project: EncryptionV5
 * Designer: Logan King
 * Date: Nov. 19. 2015
 * Current Version: 9
 * 
 * === NOTES BELOW ===
 * 
 * V1:
 * - added basic encryption algorithm with input and output through files.
 * - shows first string of input from file INPUT.txt for the user to verify what they are encrypting
 * - only two option in menu which are encryption and decryption
 * - upon picking option, you are prompted to enter two rotor values between 0 and 94 which act as the encryption key
 * - after operation, shows the output and stores it to OUTPUT.txt
 * 
 * V2:
 * - took out the two prompts for manual rotor inputs for key
 * - Added Key file which stores an encryption key as a string and is used to encrypt/decrypt
 * - shows the current key being used before the menu for the user to verify what key they are using
 * - key allows for infinite number of characters and not just two layers of encryption security
 * - added third menu option "New Key" which allows user to generare a random key
 * - upon picking option three the program asks the user how long of an encryption key they want to generate
 * 
 * V3: 
 * - added a cipher text file which stores a cipher
 * - shows cipher at the top of program before menu
 * - if cipher is shown, shows a refrence which allows the use to see what each character turns into 
 * - Added a cipher that takes place after every character is encrypted and before every character is decrypted
 * - added new menu option "New Cipher"
 * - offers options of either generating new random cipher, or useing no cipher 
 * - also now works with no encryption or Cipher now so you can either the KEY.txt / CIPHER.txt files empty and the program will still run without that data being needed
 * - this allows the user to only use a cipher or encryption if they dont want one of the options but for best security it is recomended to use both
 * 
 * V4: 
 * - allows multi line encryption and decryption, 
 * - the program can now work with return characters in the INPUT.txt file
 * 
 * V5:
 * - added a cipher before each encryption as well the already existing after, and after decryption as well as the already existing before to fix a security flaw.
 * - added a header which contains all of this information you are reading right now
 * - added an if statement before each character encryption which detects if the character is a printable ascii character, if the character is not then it changes it to a "space" character in order not to crash the program
 * 
 * V6:
 * - Made it so returns are no longer in the same position in output as the input
 * - made all return characters ciphered into an invisible character (127)
 * - made the character  TAB, turned into invisible character (128)
 * 
 * V7:
 * - Took away most of the terminal prompts.
 * - Added a Gui for functionality instead
 * - GUI contains 4 buttons leading to the input files
 * - GUI contains one combo box containing options "Encryption, Decryption, New Key, and New Cipher"
 * - GUI contains one button called run which runs the function picked from the ComboBox
 * - Updated function "getInt" calling it "getIntV2"
 * - getIntV2 simply was changed so that you input a string into it in the code and it does not scan for input from the terminal
 * 
 * V8:
 * - Added a help button which brings up file HELP.txt and shows a manual of how to use the program
 * 
 * V9: (Current)
 * - Deleted useless variables
 * - Optimized Code size
 * - Took out unneeded spaces
 * 
 * ======================================================
 */

import java.awt.Color;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
//import java.text.DecimalFormat;
//import java.util.Scanner;

public class EncryptionV9 extends JFrame
{
	//=============================================================================
	//=== Declare Constants ===
	//=============================================================================
	
	//final ImageIcon Icon = new ImageIcon("/Users/s045211/Documents/Main/Pictures/Logan's  6+ 2015.11.10_21.JPG");
	final String FileLocation = "C:/Users/Logan/Desktop/EncryptionFiles/EncryptionV9/";
	
	//=============================================================================
	//=== Declare Functions ===
	//=============================================================================
	
	public static int getIntV2(String input1){
		int length,count,temp,count2,Error,NOTDONE;
		double Number = 0;
		int Number2 = 0;
		char input3;
		NOTDONE = 0;
		while (NOTDONE == 0){
			Error = 0;
			String input2 = input1;
			length = input2.length();
			count2 = 0;
			Number = 0;
			for(count = length - 1; count >= 0; count--){
				input3 = input2.charAt(count);
				if ((input3 >= 48) && (input3 <= 57))
				{
					temp = (int) input3;// turns character into an integer in temp
					temp = temp - 48;
					Number = Number + (temp * Math.pow(10, count2));
					count2++;
				}else{//end of if
					Error = 1;
				}//end of else
			}//end of for
			if(Error == 0){
				//System.out.println("Number = "+ Number);
				NOTDONE = 1;
			}else{//end of if
				System.out.println("Error, non number character entered");
				System.out.println("Please Enter a Valid Integer...");	//can get this message in infinite loop if input not a valid integer
			}//end of else
		}//end of while
		Number2 = (int) Number;
		return Number2;
	}//end of getInt();

	//===================================================
	//start of gui construction

	public JButton InputTXT,OutputTXT,KeyTXT,CipherTXT,HelpTXT;
	public JComboBox Options;
	public JButton Run;
	String[] Soptions = {"Encryption", "Decryption", "New Key", "New Cipher"};
	public static Font font = new Font("Times New Roman", Font.BOLD,14);
	public static Font Output_font = new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16);

	public EncryptionV9()
	{
		setLayout(new FlowLayout(FlowLayout.LEFT, 10, 20));
		InputTXT = new JButton("INPUT");//adds button input
		add(InputTXT);
		OutputTXT = new JButton("OUTPUT");//adds button input
		add(OutputTXT);
		KeyTXT = new JButton("KEY");//adds button input
		add(KeyTXT);
		CipherTXT = new JButton("CIPHER");//adds button input
		add(CipherTXT);
		HelpTXT = new JButton("HELP");//adds button input
		add(HelpTXT);

		final JComboBox Options = new JComboBox(Soptions);
		Options.setSelectedIndex(0);
		add(Options);
		setEnabled(true);

		Run = new JButton("Run");//adds button run
		add(Run);
		
		//=============================================================================
		//=== Declare ActionListeners ===
		//=============================================================================
		
		InputTXT.addActionListener( new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				try
				{
					Desktop.getDesktop().open(new File(FileLocation + "INPUT.txt"));
				}//end of try
				catch(java.io.IOException e)
				{
					System.out.println("IOError");
				}//end of catch
			}//** actionPerformed
		});  //** Action Listener

		OutputTXT.addActionListener( new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				try
				{
					Desktop.getDesktop().open(new File(FileLocation + "Output.txt"));
				}//end of try
				catch(java.io.IOException e)
				{
					System.out.println("IOError");
				}//end of catch
			}//** actionPerformed
		});  //** Action Listener

		KeyTXT.addActionListener( new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				try
				{
					Desktop.getDesktop().open(new File(FileLocation + "Key.txt"));
				}//end of try
				catch(java.io.IOException e)
				{
					System.out.println("IOError");
				}//end of catch
			}//** actionPerformed
		});  //** Action Listener

		CipherTXT.addActionListener( new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				try
				{
					Desktop.getDesktop().open(new File(FileLocation + "Cipher.txt"));
				}//end of try
				catch(java.io.IOException e)
				{
					System.out.println("IOError");
				}//end of catch
			}//** actionPerformed
		});  //** Action Listener
		
		HelpTXT.addActionListener( new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				try
				{
					Desktop.getDesktop().open(new File(FileLocation + "HELP.txt"));
				}//end of try
				catch(java.io.IOException e)
				{
					System.out.println("IOError");
				}//end of catch
			}//** actionPerformed
		});  //** Action Listener

		//=============================================================================
		//start of run action listener

		Run.addActionListener( new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				//==============================================
				//start of main
				
				Random generator = new Random();
				int length,i,temp, Errs = 0;
				char Output;
				String Key = "INITIALIZED";
				String CipherS = "TEST";
				String Input = new String ("vkmx&p{)k+!r\"$");
				int KL = 0;
				int i2;
				int KLG;
				int Cipher[];
				Cipher = new int[95]; // creates an array
				boolean cipher,key;
				int DeCipher[];
				DeCipher = new int[95];

				//=============================================================
				// grabs cipher from file and puts in string

				String fileName4 = FileLocation + "CIPHER.txt";
				try {
					// FileReader reads text files in the default encoding.
					FileReader fileReader = 
							new FileReader(fileName4);
					// Always wrap FileReader in BufferedReader.
					BufferedReader bufferedReader = 
							new BufferedReader(fileReader);
					CipherS = bufferedReader.readLine(); // <------------ grabs input
					// Always close files.
					bufferedReader.close();         
				}//end of try
				catch(FileNotFoundException ex) {
					System.out.println("Unable to open file '" + fileName4 + "'");                
				}//end of catch
				catch(IOException ex) {
					System.out.println("Error reading file '" + fileName4 + "'");                  
					// Or we could just do this: 
					// ex.printStackTrace();
				}//end of catch

				//=============================================================
				//takes cipher string and puts it into the cipher array

				if(CipherS == null){
					cipher = false;
				}else{//end of if
					cipher = true;
					int temp5;
					char temp6;
					for(i=0;i<95;i++){
						temp6 = CipherS.charAt(i);//good			
						temp5 = (int)temp6;//good 
						//temp5 = temp5 - 32; // takes characters and subtracts 32 so space is 0
						Cipher[i] = temp5;
						//System.out.print((char)(Cipher[i] + 32)); // ads 32 before displaying character to turn 0 back to space
					}//end of for

					//========================================================
					//creates array DeCipher

					int case1 = 0;
					for(i=0;i<95;i++){ // creates DeCipher
						case1 = (Cipher[i] - 32);
						DeCipher[case1] = i + 32;
					}//end of for
				}//end of else

				//========================================================
				//gets string from INPUT.txt and stores in string "input"

				// The name of the file to open.
				String fileName = FileLocation + "INPUT.txt";
				try {
					// FileReader reads text files in the default encoding.
					FileReader fileReader = new FileReader(fileName);
					// Always wrap FileReader in BufferedReader.
					BufferedReader bufferedReader = new BufferedReader(fileReader);
					Input = bufferedReader.readLine(); // <------------ grabs input
					// Always close files.
					bufferedReader.close();         
				}//end of try
				catch(FileNotFoundException ex) {
					System.out.println("Unable to open file '" + fileName + "'");                
				}//end of catch
				catch(IOException ex) {
					System.out.println("Error reading file '" + fileName + "'");                  
					// Or we could just do this: 
					// ex.printStackTrace();
				}//end of catch

				//=================================================================
				// grabs Key from file

				String fileName2 = FileLocation + "KEY.txt";
				try {
					// FileReader reads text files in the default encoding.
					FileReader fileReader = 
							new FileReader(fileName2);

					// Always wrap FileReader in BufferedReader.
					BufferedReader bufferedReader = 
							new BufferedReader(fileReader);
					Key = bufferedReader.readLine();
					// Always close files.
					bufferedReader.close();         
				}//end of try
				catch(FileNotFoundException ex) {
					System.out.println("Unable to open file '" + fileName2 + "'");                
				}//end of catch
				catch(IOException ex) {
					System.out.println("Error reading file '" + fileName2 + "'");                  
					// Or we could just do this: 
					// ex.printStackTrace();
				}//end of catch

				//=================================================================
				// detects if key is true or false

				if(Key != null){
					key = true;
					KL = Key.length();//finds length of key
				}else{//end of if
					key = false;
					KL = 0;
				}//end of else

				//============================================================
				// starts printing of menu

				length = 0;
				length = Input.length(); // length function

				//============================================================
				//initializes rotor array and grabs key length

				int [] rotor = new int[KL + 1]; //declared array of rotors and makes KL slots
				if(key == true){
					//KL = Key.length();//finds length of key
					//declares all the spaces needed plus one for the adder to add to at the end
					for(i=0;i<KL;i++){// sets rotor full of integers that correspond to the character inputs
						rotor[i] = Key.charAt(i) - 32;
					}//end of for loop
				}//end of if

				//==============================================================
				//opens file OUTPUT.txt

				try{
					// opens file
					File file = new File(FileLocation + "OUTPUT.txt");
					// if file doesn't exists, then create it
					if (!file.exists()) {
						file.createNewFile();
					}//end of if
					FileWriter fw = new FileWriter(file.getAbsoluteFile());
					BufferedWriter bw = new BufferedWriter(fw);

					//=========================================
					//start of switch that does main functions of program

					int psel = Options.getSelectedIndex();
					switch(psel)
					{
					case 0://encryption
						System.out.println("=== Starting Encryption ===");

						//========================================================
						//gets string from INPUT.txt and stores in string "input"

						// The name of the file to open.
						//String fileName = "/Users/s045211/Documents/workspace/Chapter 2 Labs/src/INPUT.txt";
						String fileName5 = FileLocation + "INPUT.txt";

						try {
							// FileReader reads text files in the default encoding.
							FileReader fileReader = new FileReader(fileName5);
							// Always wrap FileReader in BufferedReader.
							BufferedReader bufferedReader = new BufferedReader(fileReader);

							//=================================================
							//start of encryption algorithm after file INPUT.txt has been opened
							//does one cycle per line, loops till all lines encrypted

							Input = bufferedReader.readLine(); // <------------ grabs input
							while(Input != null){
								length = Input.length(); // length function
								for(i=0;i<length;i++){
									temp = Input.charAt(i);
									if(temp == 9){
										temp = 128;
										System.out.print(temp);
										bw.write(temp);
									}else{
										if((temp < 32) || (temp > 126)){ // detects if input is not ascii and fixes it
											temp = 35;
											Errs++; // counts how many non ascii characters were in the program
										}//end of if
										//cipher part for one rotor below
										if(cipher == true){
											temp = Cipher[temp - 32];
										}//end of if
										if(key == true){
											for(i2 = 0;i2<KL;i2++)
											{
												//encryption by one rotor below
												temp = temp + rotor[i2];
												if(temp > 126){
													temp = temp - 95;
												}//end of if
											}//end of for
										}//end of if
										//cipher part for one rotor below
										if(cipher == true){
											temp = Cipher[temp - 32];
										}//end of if
										Output = (char)temp;
										System.out.print(Output);
										bw.write(Output);
										if(key == true){
											rotor[0]++;
											for(i2 = 0;i2<KL;i2++)//updates rotors for next character input
											{
												if(rotor[i2] > 94){
													rotor[i2] = rotor[i2] - 95; // 95 - 95 = 0;
													if(i2 < KL - 1){
														rotor[i2 + 1]++;
													}//end of if
												}//end of if
											}//end of for
										}//end of if
									}//end of else
								}//end of for
								Input = bufferedReader.readLine(); // <------------ grabs input
								if(Input != null){
									//System.out.println("");
									bw.write((char)127);
								}//end of if
							}//end of while

							//==============================================
							//outputs System Status after operation is complete

							System.out.println("\n\n=== Encryption Complete... ===");
							if(Errs != 0)
								System.out.println("=== WARNING: " + Errs + " non ASCII characters detected and converted to space ===");
							
							//===============================================
							//closes INPUT.txt
							
							// Always close files.
							bufferedReader.close();         
						}//end of try
						catch(FileNotFoundException ex) {
							System.out.println("Unable to open file '" + fileName5 + "'");                
						}//end of catch
						catch(IOException ex) {
							System.out.println("Error reading file '" + fileName5 + "'");                  
							// Or we could just do this: 
							// ex.printStackTrace();
						}// end of catch
						break;

						//======================================================================				
						//decryption

					case 1://decryption
						System.out.println("=== Starting Decryption ===");
						//================================
						//opens file input
						//========================================================
						//gets string from INPUT.txt and stores in string "input"
						
						// The name of the file to open.
						//String fileName = "/Users/s045211/Documents/workspace/Chapter 2 Labs/src/INPUT.txt";
						String fileName6 = FileLocation + "INPUT.txt";
						try {
							// FileReader reads text files in the default encoding.
							FileReader fileReader = new FileReader(fileName6);
							// Always wrap FileReader in BufferedReader.
							BufferedReader bufferedReader = new BufferedReader(fileReader);
							Input = bufferedReader.readLine(); // <------------ grabs input
							while(Input != null){
								length = Input.length(); // length function
								for(i=0;i<length;i++){
									temp = Input.charAt(i);
									if((temp == 127)||(temp == 128)){ // if character is a designated invisible character in the encryption
										switch(temp){
										case 127:
											bw.write("\n");
											System.out.print("\n");
											break;
										case 128:
											bw.write("\t");
											System.out.print("\t");
											break;
										default:
											System.out.println("ERROR");
											break;
										}//end of switch
									}else{
										if((temp < 32) || (temp > 126)){ // detects if input is not ascii and fixes it
											temp = 35;
											Errs++; // counts how many non ascii characters were in the program
										}//end of if
										//cipher for one rotor
										if(cipher == true){
											//System.out.print("INPUT:\t" + (char)temp + " OUTPUT:\t" + (char)DeCipher[temp - 32] + "\n");// De-Cipher bug test (working)
											temp = DeCipher[temp - 32];
										}//end of if
										if(key == true){
											for(i2 = 0;i2<KL;i2++)
											{
												//decryption for one rotor
												//System.out.println("ROTOR: " + i2 + " " +  rotor[i2]);
												temp = temp - rotor[i2];
												if(temp < 32){
													temp = temp + 95;
												}//end of if
											}//end of for
										}//end of if
										if(cipher == true){
											temp = DeCipher[temp - 32];
										}//end of if
										Output = (char)temp;
										System.out.print(Output);
										bw.write(Output);
										rotor[0]++;
										for(i2 = 0;i2<KL;i2++)
										{
											if(rotor[i2] > 94){
												rotor[i2] = rotor[i2] - 95; // 95 - 95 = 0;
												if(i2 < KL - 1){
													rotor[i2 + 1]++;
												}//end of if
											}//end of if
										}//end of for
									}//end of else
								}//end of for
								Input = bufferedReader.readLine(); // <------------ grabs input
								if(Input != null){
									//System.out.println("");
									//bw.write("\n");
								}//end of if
							}//end of while

							//==============================================
							//outputs System Status after operation is complete

							System.out.println("\n\n=== Decryption Complete... ===");
							if(Errs != 0){
								System.out.println("=== WARNING: " + Errs + " non ASCII characters detected and converted to \"#\" ===");
								JOptionPane.showMessageDialog(null,"=== WARNING: " + Errs + " non ASCII characters detected and converted to \"#\" ===");
							}//end of if
							
							//==============================================
							//closes INPUT.txt

							// Always close files.
							bufferedReader.close();         
						}//end of try
						catch(FileNotFoundException ex) {
							System.out.println("Unable to open file '" + fileName6 + "'");                
						}//end of catch
						catch(IOException ex) {
							System.out.println("Error reading file '" + fileName6 + "'");                  
							// Or we could just do this: 
							// ex.printStackTrace();
						}// end of catch
						break;
						
						//======================================================================				
						// GENERATE NEW KEY
						
					case 2:
						//default icon, custom title
						int n = JOptionPane.showConfirmDialog(null,
								"=== WARNING: Proceding any further will permenantely delete your currently stored key ===\nAre you sure you want to continue?",
								"WARNING",
								JOptionPane.YES_NO_OPTION);//yes = 0, no = 1, x button = -1
						if(n == 0){
							int lucky2;
							char lucky3;
							String TempS = JOptionPane.showInputDialog(null, "Enter Valid Integer");
							KLG = getIntV2(TempS);
							try{
								// opens file
								File file2 = new File(FileLocation + "KEY.txt");
								// if file doesnt exists, then create it
								if (!file2.exists()) {
									file2.createNewFile();
								}//end of if
								FileWriter fw2 = new FileWriter(file2.getAbsoluteFile());
								BufferedWriter bw2 = new BufferedWriter(fw2);
								for(i=0;i<KLG;i++){
									lucky2 = generator.nextInt(95);//generates between 0 and 94
									lucky2 = lucky2 + 32;
									lucky3 = (char)lucky2;
									bw2.write(lucky3);
								}//end of for
								System.out.println("Key Generated");
								bw2.close();// closes output file
							}//end of try
							catch (IOException e) {
								e.printStackTrace();
							}//end if catch
						}//end of if
						break;

						//======================================================================				

					case 3:
						//default icon, custom title
						int j = JOptionPane.showConfirmDialog(null,
								"=== WARNING: Proceding any further will permenantely delete your currently stored cipher ===\nAre you sure you want to continue?",
								"WARNING",
								JOptionPane.YES_NO_OPTION);//yes = 0, no = 1, x button = -1
						if(j == 0){
							try{
								// opens file
								File file2 = new File(FileLocation + "CIPHER.txt");
								// if file doesn't exists, then create it
								if (!file2.exists()) {
									file2.createNewFile();
								}//end of if
								FileWriter fw2 = new FileWriter(file2.getAbsoluteFile());
								BufferedWriter bw2 = new BufferedWriter(fw2);
								char tempC;
								int point2;
								int point = 0;
								int temp3 = 0;
								int left = 95;
								int remaining[];
								remaining = new int[96]; // creates an array
								for(i = 0; i<95;i++){ // loads array with all possible characters
									remaining[i] = i;
								}//end of for loop
								System.out.print("\nREFRENCE:   ");
								for(i=0;i<95;i++){
									System.out.print((char)(i + 32));
								}//end of for
								System.out.print("\nNEW CIPHER: ");
								for(i=0;i<95;i++){
									point = generator.nextInt(left-i);//generates between 0 and 94
									temp3 = remaining[point];
									point2 = point;
									while(point2 <= 94){
										remaining[point2] = remaining[point2 + 1];
										point2++;
									}//end of while
									temp3 = temp3 + 32;
									tempC = (char)temp3;
									System.out.print(tempC);
									bw2.write(temp3);
								}//end of for
								bw2.close();// closes output file
							}//end of try
							catch (IOException e) {
								e.printStackTrace();
							}//end of catch
						}//end of if
						break;
						//======================================================================				
					default:
						System.out.println("ERROR: passed an invalid menu select value");
						break;
					}//end of switch
					//==============================================
					//end of 1 switch program
					bw.close();// closes output file
				}//end of try
				catch (IOException e) {
					e.printStackTrace();
				}//end of catch
			}//** actionPerformed
		});  //** Action Listener
	} //** PWeights constructor
	public static void main(String[] args) 
	{
		EncryptionV9 frame = new EncryptionV9();	
		frame.setTitle("EncryptionV9");
		frame.setFont(font);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(475, 200);
		frame.setBackground(Color.CYAN);
		frame.getContentPane().setBackground(Color.lightGray);
		frame.setVisible(true);
	} //** main
}  //** class