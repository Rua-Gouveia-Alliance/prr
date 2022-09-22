PO_UILIB_DIR=../../po-uilib-202209081626
JARNAME=prr-app

all:
	(cd src; javac -cp $(PO_UILIB_DIR)/po-uilib.jar:../../prr-core/prr-core.jar -encoding UTF-8 `find . -name \*.java`)
	(cd src; jar cf ../$(JARNAME).jar `find . -name \*.class -o -name \*.java` )

clean:
	$(RM) $(JARNAME).jar `find . -name \*.class`
