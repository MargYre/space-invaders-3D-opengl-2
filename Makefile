# Variables
MAIN_CLASS=com.example.App
MVN=mvn

# Targets
all: clean build run

clean:
	@echo "Cleaning the project..."
	$(MVN) clean

build:
	@echo "Building the project..."
	$(MVN) compile

run:
	@echo "Running the application..."
	$(MVN) exec:java -Dexec.mainClass=$(MAIN_CLASS)

.PHONY: all clean build run test
