package fastestline;
import java.util.*;

public class FastestLine {
    static int lines=2;
    static int n=5;
    double [][]f;
    double [][]a;
    double [][]t;
    int []l1,l2;
    double[] e,x;
    int []optimalpath;
    public FastestLine(double min,double max){
        
        l1=new int[n];
        l2=new int[n];
        e=new double[lines];
        x=new double[lines];
        t=new double[lines][n];
        a=new double[lines][n];
        f=new double[lines][n];
        optimalpath=new int[n+1];
        setValues(max,min);
    }
    
    public void setValues(double max,double min){
    
        Random random = new Random();
        double range,scaled,shifted;
        
        e[0]=2;
        e[1]=0;
        x[0]=10;
        x[1]=8;
        a[0][0]=1;
        a[0][1]=1;
        a[0][2]=2;
        a[0][3]=10;
        a[0][4]=9;
        a[1][0]=2;
        a[1][1]=16;
        a[1][2]=12;
        a[1][3]=1;
        a[1][4]=90;
        
        t[0][0]=2;
        t[0][1]=4;
        t[0][2]=5;
        t[0][3]=1;
        
        t[1][0]=8;
        t[1][1]=1;
        t[1][2]=2;
        t[1][3]=1;
//        for(int i=0;i<lines;i++){
//            for(int j=0;j<n;j++){
//                
//                
//                
//                range = max - min; //generate random times (type double) in range (min,max)
//                scaled = random.nextDouble() * range; //scaling factor since random only generates from -1 to 1
//                shifted = scaled + min;     //actual random time.
//                a[i][j]= shifted;
//                scaled = random.nextDouble() * range;
//                shifted = scaled + min;
//                t[i][j]=shifted;  //generating and storing random switching times between stations.!
//                System.out.printf("%.1f\t",a[i][j]);
//                
//                System.out.printf("%.1f\t",t[i][j]);
//            }
//            System.out.println("");
//        }
//        System.out.println("transfer times");
//        for(int i=0;i<lines;i++){
//            for(int j=0;j<n;j++){
//                System.out.printf("%.1f\t",t[i][j]);
//            }
//            System.out.println("");
//        }
    }
    
    
    public double calculate_fastest(){
        
        f[0][0]=e[0]+a[0][0];
        f[1][0]=e[1]+a[1][0];
        int lstar=0;
        double fstar=0;   
        for(int j=1;j<n;j++){
            if(f[0][j-1] <= f[1][j-1]+t[1][j-1]){
               // System.out.println("inside if in iteration pipe 1" + j );
                f[0][j]=f[0][j-1]+a[0][j];
                //System.out.println("f[0][j] is "  +f[0][j] + " iteration " + j);
                l1[j]=1;
            }
            else{
                f[0][j]=f[1][j-1]+a[0][j]+t[1][j-1];
                //System.out.println("inside else in iteration pipe 1 " + j );
                
                //System.out.println("f[0][j] is "  +f[0][j] + " iteration " + j);
                l1[j]=2;
            }
            if(f[1][j-1]+a[1][j] <= f[0][j-1]+t[0][j-1]+a[1][j]){
                f[1][j]=f[1][j-1]+a[1][j];
                l2[j]=2;
                //System.out.println("inside if in iteration pipe 2 " + j );
                //System.out.println("f[1][j] is "  +f[1][j] + " iteration " + j);
            }
            else{
                
                f[1][j]=f[0][j-1]+a[1][j]+t[0][j-1];
                l2[j]=1;
                //System.out.println("inside else in iteration pipe 2 " + j );
                //System.out.println("f[1][j] is hahaa "  +f[1][j] + " iteration " + j);
            }
        }
        
        //System.out.println(" f[0][n-1] is " +f[0][n-1] + " f[1][n-1] is " +f[1][n-1]);
        if(f[0][n-1]+x[0]<=f[1][n-1]+x[1]){
            //System.out.println("in if!");
            fstar=f[0][n-1]+x[0];
            lstar=1;
        }
        else{
            System.out.println("in else!");
            fstar=f[1][n-1]+x[1];
            lstar=2;
        }
        
        for(int j=1;j<n;j++){
            if(lstar==1){
                optimalpath[j]=l1[j];
                System.out.printf("%d ",l1[j]);
            }
            else{
                optimalpath[j]=l2[j];
                System.out.printf("%d ",l2[j]);
            }
        }
        optimalpath[n]=lstar;
        System.out.printf("%d ",lstar);
        return fstar;
    } 
    
//    public static void main(String[] args) {
  ////      FastestLine a=new FastestLine(0,10);
        
////        System.out.println("Result is "+a.calculate_fastest());
//    }
}
