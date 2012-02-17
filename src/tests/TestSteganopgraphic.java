package tests;


import stega.Steganographic;
import junit.framework.TestCase;

public class TestSteganopgraphic extends TestCase {
	
	public void testEncoding() {

		String[] cmd = {"--encode", "src\\tests\\files\\Hewlett.wav", "src\\tests\\files\\MSG.jpg", "src\\tests\\files\\Outcome.wav"};

		Steganographic.main(cmd);
	}
	
	public void testDecoding() {

		String[] cmd = {"--decode", "src\\tests\\files\\Outcome.wav", "src\\tests\\files\\Decoded.jpg"};

		Steganographic.main(cmd);
		
		String[] cmd2 = {"--decode", "src\\tests\\files\\Hewlett.wav", "src\\tests\\files\\Decoded_bad.jpg"};

		Steganographic.main(cmd2);
	}
	
	public void testBytesToNumber() {
		int exp = -123241241;
		byte[] bytes = Steganographic.numberToBytes(exp);
		int act = (int)Steganographic.bytesToNumber(bytes, true);
		
		assertEquals(exp, act);
		
		byte[] bytes2 = {-1, -1, -1, -1};
		act = (int) Steganographic.bytesToNumber(bytes2, true);
		
		assertEquals(-1, act);
	}
}
