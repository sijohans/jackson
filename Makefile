ROOT_DIR := $(shell dirname $(realpath $(firstword $(MAKEFILE_LIST))))
JAZZER_DIR := /Users/simonjohansson/Downloads/jazzer
JAR_NAME := jackson-playground-1.0-SNAPSHOT-jar-with-dependencies.jar
JAR_PATH := $(ROOT_DIR)/target/$(JAR_NAME)
PKG_NAME := se.omegapoint.jackson

test:
	mvn test

target/jackson-playground-1.0-SNAPSHOT-jar-with-dependencies.jar:
	mvn clean compile assembly:single -DskipTests

fuzz_person: target/jackson-playground-1.0-SNAPSHOT-jar-with-dependencies.jar
	$(JAZZER_DIR)/jazzer --cp=$(JAR_PATH) --target_class=$(PKG_NAME).person.PersonFuzzer fuzz/person

fuzz_shapes: target/jackson-playground-1.0-SNAPSHOT-jar-with-dependencies.jar
	$(JAZZER_DIR)/jazzer --cp=$(JAR_PATH) --target_class=$(PKG_NAME).shapes.ShapesFuzzer fuzz/shapes
