import java.io.*;

public class Steganographic {
       private static int buffer_size_wav = 1024;
       private static int buffer_size_msg = 1024;

       public static void main(String[] args) {
               String mode = args[1];
               File payload = null;
               File carrier = null;
               File output = null;
               FileInputStream inStr = null;
               FileOutputStream outStr = null;
               byte[] wavHeader = new byte[44];
               byte[] payloadSize = null;
               byte[] wavBuffer = null;
               byte[] payloadBuffer = null;
               boolean littleEndian = false;
               String resultMsg = null;

               try {

                       carrier = new File(args[2]);
                       carrier.setReadOnly();

                       if (mode.equals("--encode")) {

                       payload = new File(args[3]);
                       output = new File(args[4]);
                       inStr = new FileInputStream(carrier);

                       inStr.read(wavHeader);

                       long size = payload.length();

                       byte[] encodeArray = new byte[16];



                       inStr.read(wavHeader);
                       if ((char)wavHeader[3] == 'F') {
                               littleEndian = true;
                       }




                       }else if (mode.equals("--decode")) {

                       }

                       inStr.close();
                       outStr.flush();
                       outStr.close();

               }
               catch(Exception e) {
                       e.printStackTrace();
                       resultMsg = "SomeErrorMsg";
               }
               finally {
                       System.out.println(resultMsg);
               }
       }


       private boolean sizeChecking(int size_msg, int size_wav_data) {
               int portion = 16;
               boolean re = false;
               if(size_msg*portion <= size_wav_data) {
                       re = true;
               }
               return re;
       }

       //littile endian
       private byte[] numberToBytes(int size) {
               byte[] re = new byte[4];
               for (int i = 0; i < 4; i++) {
                       re[i] = 0;
                       re[i] |= size >> (i*8);
               }
               return re;
       }

       private int bytesToNumber(byte[] bytes, boolean littleEndian) {
               int re = 0;
               byte[] newBytes = new byte[4];

               if (littleEndian) {
                       newBytes = bytes.clone();
               } else {
                       for (int i = 0; i < 4; i++) {
                               newBytes[i] = bytes[4 - i];
                       }
               }

               for (int i = 0; i < 4; i++) {
                       re |= newBytes[i];
                       re <<= 8;
               }
               return re;
       }
}