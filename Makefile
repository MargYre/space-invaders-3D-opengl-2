# Variables
JAVAC = javac
JAVA = java
SRC_DIR = src/main/java
BUILD_DIR = build/classes
LIB_DIR = lib/jogamp-all-platforms/jar
RESOURCES_DIR = src/main/resources

# Classpath avec les dépendances JOGL
CLASSPATH = $(LIB_DIR)/gluegen-rt.jar:$(LIB_DIR)/jogl-all.jar:$(BUILD_DIR)

# Sources Java
SOURCES = $(shell find $(SRC_DIR) -name "*.java")

# Règles principales
.PHONY: all clean run

all: $(BUILD_DIR)
	$(JAVAC) -cp $(CLASSPATH) -d $(BUILD_DIR) $(SOURCES)

# Création du répertoire de build
$(BUILD_DIR):
	mkdir -p $(BUILD_DIR)

# Nettoyage
clean:
	rm -rf $(BUILD_DIR)

# Exécution du jeu
run: all
	$(JAVA) -cp $(CLASSPATH) -Djava.library.path=$(NATIVE_PATH) main.java.game.Main

# Configuration des bibliothèques natives selon l'OS
ifeq ($(OS_NAME), Darwin)
    NATIVE_PATH = $(LIB_DIR)/../native/macosx-universal
else ifeq ($(OS_NAME), Linux)
    NATIVE_PATH = $(LIB_DIR)/../native/linux-amd64
else
    NATIVE_PATH = $(LIB_DIR)/../native/windows-amd64
endif