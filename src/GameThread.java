

import guicomponent.PokemonLabel;

import java.util.ArrayList;

/**
 * Created by jerry on 2017/3/26.
 */
public class GameThread implements Runnable {
    ArrayList<PokemonLabel> pokemons;

    public GameThread(ArrayList<PokemonLabel> pokemonLabels)
    {
        //TODO create and start the thread
        pokemons = pokemonLabels;
        Thread thread = new Thread(this);
        thread.start();
    }
    @Override
    public void run() {
        while(true) {
            //TODO Update the pokemonLabels
            for (int i = 0; i < 5; i++)
                pokemons.get(i).update();
            //TODO use Thread.sleep to make the loop go slower
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
