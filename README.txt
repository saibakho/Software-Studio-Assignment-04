Software Stujardio 2017 Spring Assignment 04

1. Program Running Method : makefile (or already packed "PokemonCatcher.jar")

2. Program Structure :

	PokemonCatcher - BGPanel - pokemon1
							 - pokemon2 (PokemonLabel) - counter
							 						   - freq (frequency of the pop-ups)
							 						   - lastTime (lasting time of showing the pokemon)
							 						   - pokemonId
							 						   - state
							 - ...
							 - msgLabel (0) : Message Labels moving above caught Pokemons
							 - msgLabel (1)
							 - ...
							 - scoreLabel
				   - HashMap < PokemonLabel, msgLabel >
				   - GameThread (the thread to count the counter)
				   - score

	About the gaming :
		Pokemons :
			Bulbasaur :
			Ivysaur	  :
			Venusaur  : +10 points

			Diglett	  :
			Dugtrio   : -10 points

			Gloom : Yew~ Stinks~ Back to 0 point


3. Problems Encountered :
	
	I don't exactly understand the usage of Threads... That is, all I did to the GameThread is to trigger the counter every 0.1 sec, similar to a clock rate in some hardware stuff. And it can be simlified to a while loop. So I'm wondering how we can do in this assignment to improve the threading.