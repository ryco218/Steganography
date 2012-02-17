package stega;

public class ByteOp {

    // takes 16 bytes from wav
    public static byte[] encode(byte msgByte, byte[] wavBytes, boolean littleEndian) {
            int offset = 0;
            byte[] newBytes = new byte[8];
            for (int i = 0; i < 8; i++) {
                    newBytes[i] = (byte)(msgByte << i >> 7);
                    newBytes[i] &= (byte)(1);
            }
            if(!littleEndian){
                    offset = 1;
            }

            for (int i = 0; i < 8; i++) {
                    wavBytes[offset] &= -2;
                    wavBytes[offset] |= newBytes[i];
                    offset += 2;
            }

            return wavBytes;
    }

    // takes 16 bytes from wav
    public static byte decode(byte[] wavBytes, boolean littleEndian) {
            int offset = 0;
            byte returnedByte = 0;

            if(!littleEndian){
                    offset = 1;
            }

            for (int i = 0; i < 8; i++) {
                    byte temp = (byte) (wavBytes[offset] & 1);
                    returnedByte |= temp << (7 - i);
                    offset += 2;
            }

            return returnedByte;
    }


}
