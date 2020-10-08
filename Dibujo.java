
package icon;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 
 *
 * @author alanpc
 */
public class Dibujo extends JPanel {

    //ventana
    private JFrame ventana;
    //contenedor
    private Container contenedor;
    int contador = 0;

    private final int[] Figura = {
       0x00f00,
0x01f10,
0x03f30,
0x07f70,
0x0fff0,
0x1fff1,
0x3fff3,
0x3fff3,
0x1fff1,
0x0fff0,
0x07f70,
0x03f30,
0x01f10,
0x00f00,
0x00100,

    };

    //mascara
    private final int MASCARA = 0x8000000;

    //ancho de bits
    private final int ANCHO_BITS = 32;

    //coordenadas
    private int coordenadas_x, coordenadas_y;

    //hilo
    private Thread hilo;

    public Dibujo() {
        // inicalizra la ventana
        ventana = new JFrame("Dibujando icocno");
        //deifinir un tamaño
        ventana.setSize(600, 600);
        ventana.setVisible(true);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setResizable(false);

        //Contenedor Config
        contenedor = ventana.getContentPane();
        contenedor.setSize(600, 600);
        //agregar la ventana en el contenedor
        contenedor.add(this, BorderLayout.CENTER);
        //definir el hilo
        this.hilo = new Thread();

    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        /*
        graphics.setColor(Color.GRAY);
        graphics.drawLine(0, 0, 100, 100);
        ¨*/
        System.out.println(this.coordenadas_x);
        System.out.println(this.coordenadas_y);
        //iterador de la figura
        for (int i = 0; i < this.Figura.length; i++) {
            //iterador para el ancho en bits de la figura
            for (int j = 0; j < this.ANCHO_BITS; j++) {
                int temp = this.Figura[i] & (this.MASCARA >> j);

                if (temp != 0) {
                    graphics.drawLine(
                            this.coordenadas_y + j,
                            this.coordenadas_x + i,
                            this.coordenadas_y + j,
                            this.coordenadas_x + i);

                }
            }
        }
    }

    public void dibujar() {
        while (contador<10) {
            try {
                this.coordenadas_x = (int) (Math.random() * 700);
                this.coordenadas_y = (int) (Math.random() * 700);
                this.hilo.sleep(500);
                paint(getGraphics());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            if (coordenadas_x >= 600) {//colicion Derecha
                contador++;
                System.out.println("toque borde x+ancho"+ "contador va en "+contador);
               

            } else if (coordenadas_x == 0) {
                contador++;
                System.out.println("toque borde en x 0"+ "contador va en "+contador);
              
              
            } else if (coordenadas_y >= 600) {
                contador++;
                System.out.println("toque borde y+ancho"+ "contador en "+contador);
            
                
            } else if (coordenadas_y == 0) {
                contador++;
                System.out.println("toque borde y en 0"+ "contador en "+contador);       
            }

        }

    }

}
