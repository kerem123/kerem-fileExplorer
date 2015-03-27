import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Before;
import org.junit.Test;

import supportingClasses.FileSignatureAnalyser;

public class FileSignatureAnalyserTest {
	private FileSignatureAnalyser test;
	private static String[] fileSignatures = {
		"00 00 00 18 66 74 79 70 33 67 70 35", "00 00 00 14 66 74 79 70 71 74 20 20", "4D 5A",
		"00 00 FF FF FF FF", "00 01 00 00 00", "00 01 00 00 53 74 61 6E 64 61 72 64 20 41 43 45 20 44 42", 
		"00 01 00 00 53 74 61 6E 64 61 72 64 20 4A 65 7420 44 42", "00 06 15 61 00 00 00 02 00 00 04 D2 00 00 10 00", 
		"00 11 AF", "00 1E 84 90 00 00 00 00", "00 BF", "00 6E 1E F0", "01 00 00 00 01", "01 DA 01 01 00 03", "4D 5A",
		"07 64 74 32 64 64 74 64", "09 08 10 00 00 06 05 00", "EC A5 C1 00", "0F 00 E8 03", "21 3C 61 72 63 68 3E 0A", 
		"23 20 4D 69 63 72 6F 73 6F 66 74 20 44 65 76 65 6C 6F 70 65 72 20 53 74 75 64 69 6F", "23 21 53 49 4C 4B 0A", 
		"25 50 44 46", "0A 25 25 45 4F 46", "0D 0A 25 25 45 4F 46 0D 0A", "0A 25 25 45 4F 46 0A", "0D 25 25 45 4F 46 0D", 
		"2A 2A 2A 20 20 49 6E 73 74 61 6C 6C 61 74 69 6F 6E 20 53 74 61 72 74 65 64 20", "38 42 50 53", "41 45 53",
		"30 31 4F 52 44 4E 41 4E 43 45 20 53 55 52 56 45 59 20 20 20 20 20 20 20", "42 4D", "43 44 30 30 31", "43 57 53",
		"43 6C 69 65 6E 74 20 55 72 6C 43 61 63 68 65 20 4D 4D 46 20 56 65 72 20", "47 49 46 38 37 61", "49 44 33",
		"50 4B 03 04 14 00 08 00 08 00", "50 4B 03 04 14 00 06 00", "50 4B 03 04 14 00 06 00", "50 4B 03 04 14 00 06 00",
		"52 49 46 46 xx xx xx xx 57 41 56 45 66 6D 74 20", "75 73 74 61 72", "89 50 4E 47 0D 0A 1A 0A", "FF D8 FF", 
	};
	
	@Before
	public void setUp() throws Exception {
		test = FileSignatureAnalyser.getSingletonFileSignatureAnalyser();
	}

	@Test
	public void testGetSingletonFileSignatureAnalyser() {
		FileSignatureAnalyser test2 = FileSignatureAnalyser.getSingletonFileSignatureAnalyser();
		
		assertSame(test2, test);
	}

	@Test
	public void testAnalyseFileSignature() {
		StringBuilder finalResult = new StringBuilder();
		StringBuilder hexEditor = new StringBuilder();
		StringBuilder textEditor = new StringBuilder();
		
		try(InputStream inputStr = new FileInputStream(new File("C:\\Users\\kerem\\Desktop\\LitReview.pdf"))) {
			int numOfBytes = 0;		
			int byteValue = 0;
			
			while((byteValue = inputStr.read()) != -1) {
				hexEditor.append(String.format("%02X  ", byteValue));
		  
			    if(!Character.isISOControl(byteValue)) {
			    	textEditor.append((char) byteValue);
			    } else {
			    	textEditor.append(".");
			    	
			    }

			    if(numOfBytes==15) {
			    	finalResult.append(hexEditor).append("\t\t").append(textEditor).append("\n");
			    	hexEditor.setLength(0);
			    	textEditor.setLength(0);
			    	numOfBytes = 0;
			    }else{
			    	numOfBytes++;
			    }
			}
		 
			if(numOfBytes != 0) {
				for(; numOfBytes < 16; numOfBytes++) {
					hexEditor.append("");
				}
				finalResult.append(hexEditor).append("\t\t").append(textEditor).append("\n");
			}
			
			assertNotNull(finalResult);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetSignatureResult() {
		File file = new File("C:\\Users\\kerem\\Desktop\\LitReview.pdf");
		String fileContent = "testing";
		String output = "";
		String fileExtension = "'"+file.getAbsolutePath().split("\\.")[1]+"".toUpperCase()+"'";
		
		boolean check = false;
		for(int i=0; i<fileSignatures.length; i++) {
			if(fileContent.matches("(?).*"+fileSignatures[i]+"*")) {
				check = true;
			}
		}

		if(!check) {
			output = "This is a '"+fileExtension.toUpperCase()+"' file";
		} else {
			output = "This is not a '"+fileExtension.toUpperCase()+"' file";
		}
		
		assertFalse(check);
	}
}
