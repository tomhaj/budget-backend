default: .help

test:
	mvn clean verify

.help:
	@ echo "\
make test - running tests"
