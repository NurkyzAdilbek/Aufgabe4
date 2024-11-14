import Prog1Tools.IOTools;

public class Primazahlen {
    public static void main(String[] args) {
        while (true){
        System.out.println("Willkommen im Primazahlenrechner!");
        System.out.println("Bitte eine Zahl eingeben");
        int number= IOTools.readInt();

        if (number<2 ) {
            System.out.println(number+" kann keine Primzahl sein");
        }
        else{
            boolean isPrime=true;


        for (int i=2; i<number; i++) {
            if (number%i==0) {
                isPrime = false;

            }

        break;}
        if (isPrime){
            System.out.println("Die Zahl "+number+ " ist eine Primzahl");
        }
        else {
            System.out.println("Die Zahl "+number+ " ist keine Primzahl");
        }
    }}}
}
