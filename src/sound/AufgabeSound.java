package sound;


import java.util.Arrays;

public class AufgabeSound {

    public double[][] getShortMelody(){

        double[]frequenzen={504.3,357.0,309.0,504.0,590.0};
        double[]dauer={500.0, 500.0,500.0,500.0, 500.0};
        double[][]melody={frequenzen,dauer};
        return  melody;

    }
public void transformOctaveDown(double[] frenquenzen,double[]lange){

    for (int i = 0; i < frenquenzen.length; i++) {
        frenquenzen[i] = frenquenzen[i] / 2;

}}

    public void transformToDoubleTime(double[] frenquenzen,double[]lange){
        for (int i=0; i<lange.length; i++) {
            lange[i]=lange[i]/2;
        }
    }
   public double[][]chipmunkify(double[] frenquenzen,double[]lange){

        double[]newFrequenzen= Arrays.copyOf(frenquenzen,frenquenzen.length);
        double[]newLange= Arrays.copyOf(lange,lange.length);
        for (int i=0; i<newFrequenzen.length; i++) {
           newFrequenzen[i]=frenquenzen[i]*2;
        }
        for (int i=0;i<newLange.length;i++){
            newLange[i]=lange[i]*0.75;
        }

        double[][]newMelody={newFrequenzen,newLange};
        return newMelody;
   }

}



