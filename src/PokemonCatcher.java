//import GameThread;
import guicomponent.PokemonLabel;
import guicomponent.BGPanel;
import guicomponent.PokemonSprite;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by jerry on 2017/3/26.
 */
public class PokemonCatcher {
    GameThread gameThread;
    private JPanel bgPanel;
    private JLabel pokemon1;
    private JLabel pokemon2;
    private JLabel pokemon3;
    private JLabel pokemon4;
    private JLabel pokemon5;
    private JLabel msgLabel;
    private JLabel scoreLabel;

    ArrayList<PokemonLabel> pokemons;
    HashMap<PokemonLabel, JLabel> msgMap;
    int score = 0;
    // constructor
    public PokemonCatcher() {
        createUIComponents();

        MouseAdapter listener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                //force cast and get pokemon from the event component
                PokemonLabel pokemon = (PokemonLabel) e.getComponent();
                //TODO see if pokemon is catchable, update the UI with SwingUtilities.invokeLater
                if (pokemon.isCatchable())
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            pokemon.setMSG(msgMap.get(pokemon));
                            score += pokemon.caught();
                            if (score < 0)  score = 0;
                            scoreLabel.setText("Score : " + score);
                        }
                    });
            }
        };
        //add mouse listeners to all the pokemonLabels
        for(JLabel pokemon:pokemons) {
            pokemon.addMouseListener(listener);
        }
        //create the game thread,
        gameThread = new GameThread(pokemons);
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("PokemonCatcher");
        frame.add(new PokemonCatcher().bgPanel);
        frame.setSize(660, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLayout(null);
    }
    private void createUIComponents() {
        BufferedImage img = null;

        try {
            img = ImageIO.read(new File("guicomponent/pic/bg.png"));
        } catch (IOException e) {}

        bgPanel = new BGPanel(img);
        pokemons = new ArrayList<>();
        msgMap = new HashMap<>();

        scoreLabel = new JLabel("Score : " + score);
        scoreLabel.setFont(new Font("Consolas", Font.BOLD, 20));
        scoreLabel.setForeground(Color.yellow);
        scoreLabel.setBounds(30, 20, 200, 50);
        bgPanel.add(scoreLabel);

        for(int i = 0; i < 5; i++) {
            pokemons.add(new PokemonLabel());
            msgMap.put(pokemons.get(i), new JLabel());
        }
        pokemon1 = pokemons.get(0);
        pokemon1.setBounds(265, 60, 80, 60);
        bgPanel.add(pokemon1);
        
        pokemon2 = pokemons.get(1);
        pokemon2.setBounds(105, 318, 80, 60);
        bgPanel.add(pokemon2);

        pokemon3 = pokemons.get(2);
        pokemon3.setBounds(265, 255, 80, 60);
        bgPanel.add(pokemon3);

        pokemon4 = pokemons.get(3);
        pokemon4.setBounds(325, 255, 80, 60);
        bgPanel.add(pokemon4);

        pokemon5 = pokemons.get(4);
        pokemon5.setBounds(520, 190, 80, 60);
        bgPanel.add(pokemon5);

        for (PokemonLabel tempLabel : pokemons) {
            msgMap.get(tempLabel).setFont(new Font("Consolas", Font.BOLD, 20));
            bgPanel.add(msgMap.get(tempLabel));
        }
    }
}
