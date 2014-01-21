GPP=gpp
JAVAC=javac

PP = comparable
SRC = $(shell find src | grep '\.java$$')
PPD = $(shell find src | grep '\.java$$' | sed -e 's:^src:obj:')
OBJ = $(shell find src | grep '\.java$$' | sed -e 's:^src:obj:' | sed -e 's:java$$:class:')


.PHONY: all
all: $(OBJ)

obj/%.class: obj/%.java
	$(JAVAC) '-Xlint:all,-cast' -s obj -d obj -cp obj "$<"

obj/%.java: src/%.java $(foreach F, $(PP), src/$(F))
	mkdir -p "$(shell dirname "$@")"
	$(GPP) -s £ < "$<" > "$@"


.PHONY: clean
clean:
	-rm -r -- bin obj

