package guicomponent;

import java.util.Random;
import javax.swing.*;
import java.awt.*;

/**
 * Created by jerry on 2017/3/26.
 */
public class PokemonLabel extends JLabel
{
    WhacPokemonState state = WhacPokemonState.Hide;
    private JLabel msgLabel;
    private int freq = 1;
    private int lastTime = 3;
    private int counter = -1;
    private int pokemonId;

    public PokemonLabel()
    {
        setIcon(PokemonSprite.bushIcon());
        // msg
        msgLabel = new JLabel();
    }
    public void setMSG(JLabel inputLabel)
    {
        msgLabel = inputLabel;
    }
    public void update()
    {
        //TODO setup a counter, if time up you may want to change to another state
        counter = (counter+1)%freq;
        
        if (state == WhacPokemonState.Caught) {
            if (counter == 10) {
                counter = 0;
                hidePokemon();
                // score dealing
            } else if (counter < 5) {
                msgLabel.setBounds(getX()+20, getY()-10-counter*5, 200, 30);
            } else {
                msgLabel.setVisible(false);
            }
        } else {
            if (counter == 0) {
                Random random = new Random();
                switch(random.nextInt(6)) {
                    case 0 : pokemonId = 0;     freq = 23;  lastTime = 6;   break;
                    case 1 : pokemonId = 1;     freq = 20;  lastTime = 4;   break;
                    case 2 : pokemonId = 2;     freq = 18;  lastTime = 3;   break;
                    case 3 : pokemonId = 62;    freq = 35;  lastTime = 8;   break;
                    case 4 : pokemonId = 64;    freq = 30;  lastTime = 7;   break;
                    case 5 : pokemonId = 56;    freq = 33;  lastTime = 10;  break;
                }   hidePokemon();
            } else if (counter == (freq-lastTime) ) {
                popPokemon();
            }
        }        
    }
    public void popPokemon()
    {
        //TODO when a pokemon pop up
        state = WhacPokemonState.Show;
        setIcon(new ImageIcon(PokemonSprite.getSprite(pokemonId)));
    }
    public void hidePokemon()
    {
        //TODO when the pokemon hide into the bushes
        state = WhacPokemonState.Hide;
        setIcon(PokemonSprite.bushIcon());
    }
    public int caught()
    {
        int retValue = 0;
        //TODO when player caught the pokemon
        counter = 0;
        state = WhacPokemonState.Caught;
        setIcon(PokemonSprite.pokeballIcon());
        // message
        msgLabel.setVisible(true);
        switch (pokemonId) {
            case 0 :    case 1 :    case 2 :
                msgLabel.setText("+10");
                msgLabel.setForeground(Color.blue);
                retValue = 10;
                break;
            case 62 :   case 64 :
                msgLabel.setText("-10");
                msgLabel.setForeground(Color.red);
                retValue = -10;
                break;
            case 56 :
                msgLabel.setText("Yew~ Stinks~");
                msgLabel.setForeground(Color.red);
                retValue = -99999999;
                break;
        }   return retValue;
    }
    public boolean isCatchable()
    {
        //a beautiful way to write it, no need to use if
        return state == WhacPokemonState.Show;
    }
}
enum WhacPokemonState {Hide, Show, Caught}
