package audio_editor;

public class Wave {

    //    RiftHeader
    private int CHUNK_ID;
    private int chunkSize;
    private int FORMAT;

    //    Subchunk1
    private int SUBCHUNK1_ID;
    private int SUBCHUNK1_SIZE;
    private int AUDIO_FORMAT;
    private int numChannels;   // Mono = 1, Stereo = 2
    private int sampleRate;    // Наприклад 44100
    private int byteRate;
    private int blockAlign;
    private int bitsPerSample; // 8 bits = 8, 16 bits = 16, etc.


    //    Subchunk2
    private int SUBCHUNK2_ID;
    private int subchunk2Size; // == NumSamples * NumChannels * BitsPerSample/8, кількість байтів аудіоданих
    private byte[] data;


    public Wave(byte[] data) {
        CHUNK_ID = byteArrayToInt(data,0, 4);
        /*byteRate = sampleRate * numChannels * bitsPerSample / 8;
        blockAlign = numChannels * bitsPerSample / 8;*/
        this.data = data;
        /*int numSamples = duration * sampleRate;
        subchunk2Size = numSamples * numChannels * bitsPerSample/8;
        chunkSize = 36 + subchunk2Size;*/
    }

    public static int byteArrayToInt(byte[] b, int start, int end) {
        int value = 0;
        for (int i = start; i < end; i++) {
            int shift = (end - 1 - i) * 8;
            value += (b[i] & 0x000000FF) << shift;
        }
        return value;
    }

}