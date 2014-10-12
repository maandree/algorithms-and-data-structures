GPP=gpp
JAVAC=javac

PP = comparable
SRC = $(shell find src | grep '\.java$$')
PPD = $(shell find src | grep '\.java$$' | sed -e 's:^src:obj:')
OBJ = $(shell find src | grep '\.java$$' | sed -e 's:^src:obj:' | sed -e 's:java$$:class:')

OBJ_LINKED_LISTS = $(shell find src/datastructures/linkedlists | grep 'LinkedList.java$$' | sed -e 's:^src:obj:')


.PHONY: all
all: $(OBJ)

obj/%.class: obj/%.java
	$(JAVAC) '-Xlint:all,-cast' -s obj -d obj -cp obj "$<"

obj/%.java: src/%.java $(foreach F, $(PP), src/$(F))
	mkdir -p "$(shell dirname "$@")"
	env GPP="$(GPP)" $(GPP) -s £ < "$<" > "$@"


$(OBJ_LINKED_LISTS): src/datastructures/linkedlists/template obj/algorithms/bits/Powers.class
obj/algorithms/searching/MultiinterpolationSearch.class: obj/algorithms/searching/InterpolationSearch.class
obj/algorithms/searching/MultibinarySearch.class: obj/algorithms/searching/BinarySearch.class
obj/datastructures/PerformanceTest.class: $(OBJ_LINKED_LISTS)


.PHONY: clean
clean:
	-rm -r -- bin obj

