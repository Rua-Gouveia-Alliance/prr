
all:
	(cd po-uilib; make)
	(cd prr-app; make)
	(cd prr-core; make)

clean:
	(cd po-uilib; make clean)
	(cd prr-app; make clean)
	(cd prr-core; make clean)
