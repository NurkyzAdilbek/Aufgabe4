package sound;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.SourceDataLine;

public class SoundPlayer {
    // Audio format: 44.1kHz, 8-bit, mono
    private final static AudioFormat format = new AudioFormat(44100, 8, 1, true, true);
    private static final SourceDataLine line;
    private static final double[] mysterySolutionEncoded = {940450.0, 764177.5, 1116722.5, 495130.0, 1116722.5, 541517.5, 819842.5, 968282.5, 829120.0, 931172.5, 764177.5};

    static {
        try {
            line = AudioSystem.getSourceDataLine(format);
            line.open(format);
            line.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public static double[] getMystery() {
        return new double[]{
                293.66, 329.63, 440.00, 392.00, 246.94
        };
    }

    public static double[][] getTetris() {
        // adopted from https://www.jk-quantized.com/blog/2013/11/22/tetris-theme-song-using-processing
        double[] freqs = {659.25511, 493.8833, 523.25113, 587.32954, 523.25113, 493.8833, 440.0, 440.0,
                523.25113, 659.25511, 587.32954, 523.25113, 493.8833, 523.25113, 587.32954,
                659.25511, 523.25113, 440.0, 440.0, 440.0, 493.8833, 523.25113, 587.32954,
                698.45646, 880.0, 783.99087, 698.45646, 659.25511, 523.25113, 659.25511,
                587.32954, 523.25113, 493.8833, 493.8833, 523.25113, 587.32954, 659.25511,
                523.25113, 440.0, 440.0};
        double[] durations = {    406.250, 203.125, 203.125, 406.250, 203.125, 203.125, 406.250, 203.125,
                203.125, 406.250, 203.125, 203.125, 609.375, 203.125, 406.250, 406.250,
                406.250, 406.250, 203.125, 203.125, 203.125, 203.125, 609.375, 203.125,
                406.250, 203.125, 203.125, 609.375, 203.125, 406.250, 203.125, 203.125,
                406.250, 203.125, 203.125, 406.250, 406.250, 406.250, 406.250, 406.250};
        return new double[][]{freqs, durations};
    }

    public static double[][] getShortMelody() {
        double[] freqs = {392, 392, 392, 311, -1,  349.23, 349.23, 349.23, 293.66};
        double[] durations = {500, 500, 500, 800, 500, 500, 500, 500, 800};
        return new double[][]{freqs, durations};
    }


    public static void playTune(double[] frequencies, double[] durations) {
        if (frequencies == null || durations == null) {
            throw new IllegalArgumentException("frequencies and durations arrays must not be null!");
        }
        if (frequencies.length != durations.length) {
            throw new IllegalArgumentException("frequencies and durations arrays must be the same length! We have " + frequencies.length + " frequencies and " + durations.length + " durations.");
        }

        double freqSum = 0;
        double durationSum = 0;
        for (int i = 0; i < frequencies.length; i++) {
            freqSum += frequencies[i];
            durationSum += durations[i];
        }
        if ( (int) (freqSum * durationSum) % 123456 == 47226) {
            var decodedArr = new char[mysterySolutionEncoded.length];
            for (int i = 0; i < decodedArr.length; i++) {
                decodedArr[i] = (char) ((mysterySolutionEncoded[i] - durationSum) / freqSum);
            }
            System.out.println("Decoded secret message: https://www.youtube.com/watch?v=" + new String(decodedArr));
        }

        System.out.print("Playing tune: [ ");
        try {
            for (int i = 0; i < frequencies.length; i++) {
                System.out.print(frequencies[i] + " Hz for " + durations[i] + " ms");
                if (i < frequencies.length - 1) {
                    System.out.print(", ");
                } else {
                    System.out.println(" ]");
                }

                if (frequencies[i] < 0) {
                    Thread.sleep(Math.round(durations[i]));
                    continue;
                }

                byte[] tone = generateTone(frequencies[i], (int) Math.round(durations[i]));
                line.write(tone, 0, tone.length);
                Thread.sleep(Math.round(durations[i]));
                line.drain();
            }
        } catch (Exception e) {
            System.out.println("Error playing sound...");
            e.printStackTrace();
        }
    }

    // Generate a byte array for a tone of a specific frequency and duration (ms)
    private static byte[] generateTone(double frequency, int durationMs) {
        int sampleRate = 44100;
        int numSamples = (int)((durationMs / 1000.0) * sampleRate);
        byte[] samples = new byte[numSamples];

        for (int i = 0; i < numSamples; i++) {
            double angle = 2.0 * Math.PI * i * frequency / sampleRate;
            samples[i] = (byte) (Math.sin(angle) * 127);  // 8-bit sound
        }

        return samples;
    }
}
