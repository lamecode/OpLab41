package audio_editor;

public class WaveReader {

    public static Wave readFromByteArray(byte[] buffer) {
        Wave wave = new Wave();
        setAttributes(wave, buffer);
        return wave;
    }

    private static void setAttributes(Wave wave, byte[] buffer) {
        wave.setChunkId(byteArrayToInt(buffer, 0, 4));
        wave.setChunkSize(byteArrayToInt(buffer, 4, 4));
        wave.setFormat(byteArrayToInt(buffer, 8, 4));
        wave.setSubchunk1Id(byteArrayToInt(buffer, 12, 4));
        wave.setSubchunk1Size(byteArrayToInt(buffer, 16, 4));
        wave.setAudioFormat(byteArrayToInt(buffer, 20, 2));
        wave.setNumChannels(byteArrayToInt(buffer, 22, 2));
        wave.setSampleRate(byteArrayToInt(buffer, 24, 4));
        wave.setByteRate(byteArrayToInt(buffer, 28, 4));
        wave.setBlockAlign(byteArrayToInt(buffer, 32, 2));
        wave.setBitsPerSample(byteArrayToInt(buffer, 34, 2));
        wave.setSubchunk2Id(byteArrayToInt(buffer, 36, 4));
        wave.setSubchunk2Size(byteArrayToInt(buffer, 40, 4));
        wave.setData(getDataArray(buffer));
    }

    private static int byteArrayToInt(byte[] byteArray, int start, int length) {
        int value = 0;
        int end = start + length;
        for (int i = start; i < end; i++) {
            int shift = (end - 1 - i) * 8;
            value += (byteArray[i] & 0x000000FF) << shift;
        }
        return value;
    }

    private static byte[] getDataArray(byte[] buffer) {
        int dataLength = buffer.length - 44;
        byte[] data = new byte[dataLength];
        System.arraycopy(buffer, 44, data, 0, dataLength);
        return data;
    }


}