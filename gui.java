package fastestline;
import java.awt.*;
import java.util.Formatter;
import javax.swing.*;

// Extends JPanel, so as to override the paintComponent() for custom rendering codes. 
public class gui extends JPanel {
   // Container box's width and height
   private static final int BOX_WIDTH = 800;
   private static final int BOX_HEIGHT = 600;
  //the optimal path array
   FastestLine b=new FastestLine(0,10);
   public int[] path_to_follow;
   private int number;
   //lines 1 and 2

   //car attributes
   private float car_h=50;
   private float car_w=40;
   private float initialx=BOX_WIDTH/2;
   private float carx=BOX_WIDTH/2;
   private float cary=BOX_HEIGHT-150;
   private static final int UPDATE_RATE = 1; // Number of refresh per second
   private String car_color="50000000";
   /** Constructor to create the UI components and initialise game objects. */
   public gui(final int [] op,final int n) {
       //
       number=n;
        //
       path_to_follow=new int[n+1];
       //
      final int jump=BOX_HEIGHT/number-(int)car_h;
      this.setPreferredSize(new Dimension(BOX_WIDTH, BOX_HEIGHT));
      Thread gameThread = new Thread() {
         public void run() {
             try {
                  Thread.sleep(1000);  // milliseconds
               } catch (InterruptedException ex) { }
            for (int i=1;i<n+1;i++) {
                System.out.println("op[i] is " +op[i]+ i);
                    int c=Integer.parseInt(car_color);
                        c=c-5000;
                        String temp=Integer.toString(c);
                    car_color=temp;
                    carx=initialx;
                    cary=cary-jump;
                    if(op[i]==1){
                        System.out.println("main hun if");
                        carx=carx-150;
                        repaint();
                        try {
                        Thread.sleep(100);  // milliseconds
                        } catch (InterruptedException ex) { }
                          
                    }
                    else{
                        System.out.println("main hun else");
                        carx=carx+90;
                        repaint();
                        try {
                        Thread.sleep(100);  // milliseconds
                        } catch (InterruptedException ex) { }
                    }
               // Delay for timing control and give other threads a chance
               try {
                  Thread.sleep(1000/UPDATE_RATE);  // milliseconds
               } catch (InterruptedException ex) { }
            }
             try {
                  Thread.sleep(100/UPDATE_RATE);  // milliseconds
               } catch (InterruptedException ex) { }
            cary=cary-50;
            car_color="0";
            repaint();
         }
      };
      gameThread.start();  // Callback run()
   }
  
   /** Custom rendering codes for drawing the JPanel */
   @Override
   public void paintComponent(Graphics g) {
      super.paintComponent(g);    // Paint background
         int station_h=(BOX_HEIGHT/((number*2)+1));
         System.out.println(BOX_HEIGHT+" ye " +station_h);
      int station_w=20;
      
      // Draw the box
      g.setColor(Color.decode("0xFF"));
      g.fillRect(0, 0, BOX_WIDTH, BOX_HEIGHT);
      int temp=BOX_WIDTH/4;
      int temp2=BOX_HEIGHT-100;/*BOX_HEIGHT-(BOX_HEIGHT/((number*2)));*/
   
      for(int i=0;i<number;i++){
          g.setColor(Color.LIGHT_GRAY);
          g.fillRect(temp,temp2, station_w, station_h);
          
          g.setColor(Color.LIGHT_GRAY);
          g.fillRect(BOX_WIDTH-temp,temp2, station_w, station_h);
          
//      temp2=temp2-(BOX_HEIGHT/(number));
          temp2=temp2-80;
      }
      //draw the assembly line 1
      //g.setColor(Color.RED);
      //g.fillRect(100, 100, (int)width, (int)height);
      //draw the assembly line 2
      //g.setColor(Color.red);
      //g.fillRect(BOX_WIDTH-200, 100, (int)width, (int)height);
      //draw the car
      g.setColor(Color.decode(car_color));
      g.fillRect((int)carx,(int)cary, (int)car_w-20, (int)car_h-30);
        // Display the ball's information
//      g.setColor(Color.WHITE);
//      g.setFont(new Font("Courier New", Font.PLAIN, 12));
//      StringBuilder sb = new StringBuilder();
//      Formatter formatter = new Formatter(sb);
//      formatter.format("Ball @(%3.0f,%3.0f) Speed=(%2.0f,%2.0f)", ballX, ballY,
//            ballSpeedX, ballSpeedY);
//      g.drawString(sb.toString(), 20, 30);
   }

   /* main program (entry point) */
   public static void main(String[] args) {
      // Run GUI in the Event Dispatcher Thread (EDT) instead of main thread.
      javax.swing.SwingUtilities.invokeLater(new Runnable(){
        public void run(){
            FastestLine a=new FastestLine(0,10);
            System.out.println("Result is "+a.calculate_fastest());
            // Set up main window (using Swing's Jframe)
            JFrame frame = new JFrame("Fastest Assembly Line Simulation");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setContentPane(new gui(a.optimalpath,a.n));
            frame.pack();
            frame.setVisible(true);
        }
      });
   }
}
