package audio_editor;

public class WaveWritter {

    public static byte[] writeToByteArray(Wave wave) {
        byte[] buffer = new byte[44 + wave.getData().length];
        prepareByteArray(wave, buffer);
        return buffer;
    }

    private static void prepareByteArray(Wave wave, byte[] buffer) {
        byte[] chunkIdBytes = intToByteArray(wave.getChunkId(), 4);
        byte[] chunkSizeBytes = intToByteArray(wave.getChunkSize(), 4);
        byte[] formatBytes = intToByteArray(wave.getFormat(), 4);
        byte[] subchunk1IdBytes = intToByteArray(wave.getSubchunk1Id(), 4);
        byte[] subchunk1SizeBytes = intToByteArray(wave.getSubchunk1Size(), 4);
        byte[] audioFormatBytes = intToByteArray(wave.getAudioFormat(), 2);
        byte[] numChannelsBytes = intToByteArray(wave.getNumChannels(), 2);
        byte[] sampleRateBytes = reverse(intToByteArray(wave.getSampleRate(), 4));
       /* byte[] sampleRateBytes2 = new byte[sampleRateBytes.length];
        int j = 4;
        for (int i = 0; i < 4; i++) {
            sampleRateBytes2[j - 1] = sampleRateBytes[i];
            j = j - 1;
        }*/
        byte[] byteRateBytes = intToByteArray(wave.getByteRate(), 4);
        byte[] blockAlignBytes = intToByteArray(wave.getBlockAlign(), 2);
        byte[] bitsPerSampleBytes = intToByteArray(wave.getBitsPerSample(), 2);
        byte[] subchunk2IdBytes = intToByteArray(wave.getSubchunk2Id(), 4);
        byte[] subchunk2SizeBytes = intToByteArray(wave.getSubchunk2Size(), 4);
        System.arraycopy(chunkIdBytes, 0, buffer, 0, 4);
        System.arraycopy(chunkSizeBytes, 0, buffer, 4, 4);
        System.arraycopy(formatBytes, 0, buffer, 8, 4);
        System.arraycopy(subchunk1IdBytes, 0, buffer, 12, 4);
        System.arraycopy(subchunk1SizeBytes, 0, buffer, 16, 4);
        System.arraycopy(audioFormatBytes, 0, buffer, 20, 2);
        System.arraycopy(numChannelsBytes, 0, buffer, 22, 2);
        System.arraycopy(sampleRateBytes, 0, buffer, 24, 4);
        System.arraycopy(byteRateBytes, 0, buffer, 28, 4);
        System.arraycopy(blockAlignBytes, 0, buffer, 32, 2);
        System.arraycopy(bitsPerSampleBytes, 0, buffer, 34, 2);
        System.arraycopy(subchunk2IdBytes, 0, buffer, 36, 4);
        System.arraycopy(subchunk2SizeBytes, 0, buffer, 40, 4);
        System.arraycopy(wave.getData(), 0, buffer, 44, wave.getData().length);
    }

    private static byte[] intToByteArray(int value, int arraySize) {
        switch (arraySize) {
            case 2:
                return new byte[]{(byte) (value >>> 8), (byte) value};
            case 4:
                return new byte[]{(byte) (value >>> 24), (byte) (value >>> 16), (byte) (value >>> 8), (byte) value};
            default:
                throw new RuntimeException("invalid byte array size");
        }
    }

    private static byte[] reverse(byte[] b) {
        byte[] reversed = new byte[b.length];
        for (int i = 0; i < b.length; i++) {
            reversed[b.length - 1 - i] = b[i];
        }
        return reversed;
    }

}