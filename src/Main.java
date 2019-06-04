import audio_editor.Wave;
import audio_editor.WaveReader;
import audio_editor.WaveWritter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static final String INPUT_FILE = "Game of Thrones.wav";
    private static final String OUTPUT_FILE = "NewOne.wav";

    public static void main(String[] args) {
        byte[] fileBytesArr = readBytesFromFile(Paths.get(INPUT_FILE));
        Wave wave = WaveReader.readFromByteArray(fileBytesArr);
        multiplyWave(wave, getIntFromUser());
        byte[] output = WaveWritter.writeToByteArray(wave);
        writeBytesToFile(output);
    }

    private static int getIntFromUser() {
        int user = 0;
        Scanner in = new Scanner(System.in);
        System.out.println("Введіть кількість раз, в яку хочете розтягнути аудіозапис:");
        if (in.hasNextInt()) {
            user = in.nextInt();
        } else {
            throw new RuntimeException("Invalid input");
        }
        return user;
    }

    private static byte[] readBytesFromFile(Path file) {
        try {
            return Files.readAllBytes(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void multiplyWave(Wave wave, int times) {
        byte[] multipliedData = getMultipliedData(wave, times);
        wave.setData(multipliedData);
        wave.setSubchunk2Size(wave.getSubchunk2Size() * times);
    }

    private static byte[] getMultipliedData(Wave wave, int times) {
        byte[] data = wave.getData();
        byte[] multipliedData = new byte[data.length * times];
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < times; j++) {
                if (data[i] < 0) {
                    multipliedData[i * times + j] = data[i];
                }
                multipliedData[i * times + j] = data[i];
            }

        }
        return multipliedData;
    }

    private static void writeBytesToFile(byte[] output) {
        try {
            Files.write(Paths.get(OUTPUT_FILE), output);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Done");
    }

}