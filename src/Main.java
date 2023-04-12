import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

class InterfazBrowser {
    private Stack<String> pila = new Stack<>(); //pila principal
    private Stack<String> pilaNumDos = new Stack<>(); // Nueva pila para almacenar elementos retirados
    private JFrame frame;
    private JTextField textoWeb; // Texto para la busqueda
    private JTextArea textArea; //Area donde se imprime
    private JButton botonAtras;
    private JButton botonAdelante;

    public static void main(String[] args) {
        InterfazBrowser browser = new InterfazBrowser();
        browser.createGUI();
    }

    private void createGUI() {
        frame = new JFrame("");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        textoWeb = new JTextField(20);

        //BOTON IR
        JButton botonIr = new JButton("IR");
        botonIr.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String texto = textoWeb.getText();
                if (!texto.isEmpty()) {
                    pila.push(texto);
                    textArea.append(texto + "\n");
                    textoWeb.setText("");
                    if(pila.size()==1){
                        botonAtras.setEnabled(false);
                    }
                    else{
                        botonAtras.setEnabled(true);
                    }
                    botonAdelante.setEnabled(false);

                    if(!pila.isEmpty() && !pilaNumDos.isEmpty()){


                            pilaNumDos.clear();

                    }
                }
            }
        });

        //BOTON ATRAS
        botonAtras = new JButton("ATRAS");
        botonAtras.setEnabled(false);
        botonAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!pila.isEmpty()) {
                    String elemento = pila.pop();
                    pilaNumDos.push(elemento); // Agregar el elemento retirado a la otra pila
                    textArea.setText("");
                    for (String str : pila) {
                        textArea.append(str + "\n");
                    }
                    botonAdelante.setEnabled(true);
                    if(pila.size()==1){
                        botonAtras.setEnabled(false);
                    }

                    if(pilaNumDos.isEmpty()){
                        botonAdelante.setEnabled(false);
                    }
                }
            }
        });
        //BOTON ADELANTE
        botonAdelante = new JButton("ADELANTE");
        botonAdelante.setEnabled(false);
        botonAdelante.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!pilaNumDos.isEmpty()){

                    botonAtras.setEnabled(true);
                    String texto = pilaNumDos.pop();
                    pila.push(texto);
                    textArea.append(texto + "\n");
                    if(pilaNumDos.isEmpty()){
                        botonAdelante.setEnabled(false);
                    }
                }
            }
        });

        //COMPONENTES DE LA INTERFAZ
        textArea = new JTextArea(10, 20);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        frame.setLayout(new FlowLayout());
        frame.add(textoWeb);
        frame.add(botonIr);
        frame.add(botonAtras);
        frame.add(botonAdelante);
        frame.add(textArea);
        frame.add(scrollPane);
        frame.pack();
        frame.setVisible(true);
    }
}
