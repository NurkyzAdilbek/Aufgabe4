import sound.AufgabeSound;
import sound.SoundPlayer;


public class Main {
    public static void main(String[] args) {
        AufgabeSound aufgabeSound=new AufgabeSound();
        double[] frequenzen ={734.0,867.0,379.0,476.0,575.0,654.0};
              double[]lange={500.0, 450.0, 500.0, 200.0, 500.0, 300.0};
         double [][]normaleMelody={frequenzen,lange};
//        SoundPlayer.playTune(myMelody);
        SoundPlayer.playTune(frequenzen,lange);

        System.out.println("Short Melody");
        aufgabeSound.getShortMelody();
        SoundPlayer.playTune(aufgabeSound.getShortMelody()[0],aufgabeSound.getShortMelody()[1] );


        System.out.println("nach der Anderung von Frequenzen");
        aufgabeSound.transformOctaveDown(frequenzen,lange);
       SoundPlayer.playTune(frequenzen,lange);

        System.out.println("nach der Anderung von Lange");
        aufgabeSound.transformOctaveDown(frequenzen,lange);
        double[][ ]schnelleMelodie={frequenzen,lange};
        SoundPlayer.playTune(frequenzen,lange);

        System.out.println("chipmunkify Methode");
        double[][] chipmunkMelody = aufgabeSound.chipmunkify(frequenzen, lange);
        aufgabeSound.chipmunkify(frequenzen,lange);
        SoundPlayer.playTune(chipmunkMelody[0],chipmunkMelody[1]);


        System.out.println("Combined Melody");
        double [][]combinedMelody={normaleMelody[0],normaleMelody[1]};
        SoundPlayer.playTune(combinedMelody[0],combinedMelody[1]);

    }
}