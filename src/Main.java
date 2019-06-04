package audio_editor;


import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) {
//        change_duration_in_integer_times();
//        write_to_wav();
        byte[] audioBytes = convert_to_byte_array();
        Wave s = new Wave(audioBytes);

    }

    public static byte[] convert_to_byte_array() {
        try {
            BufferedInputStream sound = new BufferedInputStream(new FileInputStream("Game of Thrones.wav"));
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int read;
            byte[] buff = new byte[1024];
            while ((read = sound.read(buff)) > 0) {
                out.write(buff, 0, read);
            }
            out.flush();
            byte[] audioBytes = out.toByteArray();
            return audioBytes;
        } catch (IOException e) {
            e.printStackTrace();
        }



    /*public static void write_to_wav(){

    }*/
        return null;
    }

    /*public static void change_duration_in_integer_times(byte[] a) {
        byte[]
        Wave newone = new Wave();
    }*/

    public static String GetChunkID(byte[] b) {
        byte[] chunkSize = new byte[4];
        for (int i = 0; i < 4; i++) {
            chunkSize[i] = b[i];
        }
        String str = new String(chunkSize, 0, 4, StandardCharsets.UTF_8);
        return str;
    }
}
