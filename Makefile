FILES:=$(shell find src/inf/ci728/kepe/des -iname *.java)

all: jars

jars: classes
	jar -cvfm lib/des.jar manifest -C classes/des .

classes: mkdirs
	javac -d classes/des $(FILES)

mkdirs:
	@mkdir -p classes/des/
	mkdir -p lib

clean:
	rm -rf classes
	rm -rf lib/*
