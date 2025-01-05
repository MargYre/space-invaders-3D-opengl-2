# Variables
JAVAC = javac
JAVA = java
SRC_DIR = src/main/java/game
BUILD_DIR = build/classes
LIB_DIR = lib/jogamp-all-platforms/jar
RESOURCES_DIR = src/main/resources

# Classpath avec les dépendances JOGL
CLASSPATH = $(LIB_DIR)/gluegen-rt.jar:$(LIB_DIR)/jogl-all.jar:$(BUILD_DIR)

# Sources Java
SOURCES = $(shell find $(SRC_DIR) -name "*.java")
CLASSES = $(SOURCES:$(SRC_DIR)/%.java=$(BUILD_DIR)/%.class)

# Système d'exploitation et architecture
OS_NAME = $(shell uname -s)
OS_ARCH = $(shell uname -m)

# Configuration des bibliothèques natives selon l'OS
ifeq ($(OS_NAME), Darwin)
    NATIVE_PATH = $(LIB_DIR)/../native/macosx-universal
else ifeq ($(OS_NAME), Linux)
    NATIVE_PATH = $(LIB_DIR)/../native/linux-amd64
else
    NATIVE_PATH = $(LIB_DIR)/../native/windows-amd64
endif

# Règles principales
.PHONY: all clean run

all: $(BUILD_DIR) $(CLASSES)

# Création du répertoire de build
$(BUILD_DIR):
	mkdir -p $(BUILD_DIR)

# Compilation des fichiers Java
$(BUILD_DIR)/%.class: $(SRC_DIR)/%.java
	@mkdir -p $(dir $@)
	$(JAVAC) -cp $(CLASSPATH) -d $(BUILD_DIR) $<

# Nettoyage
clean:
	rm -rf $(BUILD_DIR)

# Exécution du jeu
run: all
	$(JAVA) -cp $(CLASSPATH) \
	-Djava.library.path=$(NATIVE_PATH) \
	game.Main

# Installation des dépendances (à adapter selon votre système de gestion de paquets)
.PHONY: install-deps
install-deps:
ifeq ($(OS_NAME), Darwin)
	brew install jogamp-jogl
else ifeq ($(OS_NAME), Linux)
	sudo apt-get update
	sudo apt-get install -y libjogamp-java
else
	@echo "Veuillez télécharger manuellement JOGL depuis https://jogamp.org/deployment/jogamp-current/archive/"
endif

# Construction du jar exécutable
.PHONY: jar
jar: all
	jar cvfe SpaceInvaders3D.jar game.Main -C $(BUILD_DIR) .
	
# Documentation JavaDoc
.PHONY: doc
doc:
	javadoc -d docs/javadoc \
	-sourcepath $(SRC_DIR) \
	-subpackages game \
	-classpath $(CLASSPATH)

# Tests
.PHONY: test
test:
	$(JAVAC) -cp $(CLASSPATH):src/test/java \
	-d $(BUILD_DIR) src/test/java/game/tests/*.java
	$(JAVA) -cp $(CLASSPATH):$(BUILD_DIR) \
	org.junit.runner.JUnitCore game.tests.CollisionTest game.tests.MovementTest

# Aide
.PHONY: help
help:
	@echo "Commandes disponibles:"
	@echo "  make all          - Compile le projet"
	@echo "  make clean        - Nettoie les fichiers compilés"
	@echo "  make run          - Exécute le jeu"
	@echo "  make install-deps - Installe les dépendances"
	@echo "  make jar          - Crée un jar exécutable"
	@echo "  make doc          - Génère la documentation JavaDoc"
	@echo "  make test         - Exécute les tests"
	@echo "  make help         - Affiche cette aide"
