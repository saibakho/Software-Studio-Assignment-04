SRC  = guicomponent\BGPanel.java \
	   guicomponent\PokemonLabel.java \
	   guicomponent\PokemonSprite.java \
	   *.java
DUMP = guicomponent\BGPanel.class \
	   guicomponent\PokemonLabel.class \
	   guicomponent\PokemonSprite.class \
	   guicomponent\WhacPokemonState.class \
	   *.class
DEL  = del

all: $(SRC)
	javac *.java
	jar -cfm PokemonCatcher.jar manifest.mf $(DUMP)
	$(DEL) $(DUMP)
	java -jar PokemonCatcher.jar
clean: $(DUMP)
	$(DEL) $(DUMP)