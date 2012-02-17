package tests;



import java.util.Arrays;


import junit.framework.TestCase;





public class TestByteOp extends TestCase {

	

	public void testEncode() {

		byte msgByte = -1;

		byte[] wav = new byte[16];		

		byte[] expLittleEndian = new byte[16];

		byte[] expBigEndian = new byte[16];

		byte[] actual = null;

		

		for (int i = 0; i < 16; i++) {

			wav[i] = (byte)-i;

			expLittleEndian[i] = wav[i];

			expBigEndian[i] = wav[i];

		}

		for (int i = 0; i < 8; i++) {

			expLittleEndian[i*2] |= 1;

			expBigEndian[i*2 + 1] |= 1;

		}

		

		actual = ByteOp.encode(msgByte, wav.clone(), true);

		assertTrue(Arrays.equals(expLittleEndian, actual));

		

		actual = ByteOp.encode(msgByte, wav.clone(), false);

		assertTrue(Arrays.equals(expBigEndian, actual));

	}

	

	public void testDecode() {

		byte expLittleEndian = -1;

		byte expBigEndian = 0;

		byte[] wav = new byte[16];

		

		for (int i = 0; i < 8; i++) {

			wav[i*2] = 5;

			wav[i*2+1] = 10;

		}

		

		assertEquals(expLittleEndian, ByteOp.decode(wav, true));

		assertEquals(expBigEndian, ByteOp.decode(wav, false));

	}



}

