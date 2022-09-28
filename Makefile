
install:
	./gradlew clean install

run-dist:
	./build/install/app/bin/app -f stylish file1.json file2.json

check-updates:
	./gradlew dependencyUpdates

lint:
	./gradlew checkstyleMain

build:
	./gradlew clean build

report:
	./gradlew jacocoTestReport

.PHONY: build