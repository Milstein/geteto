ROOT = jabuti
BIBTEX := $(wildcard *.bib)
PDFLATEX_ARGS = -file-line-error -interaction nonstopmode -recorder --src-specials

all: clean pdf

clean:
	rm -f *.log *.aux *.toc *.dvi *.bbl *.blg *.fls *.cb *.*~* *.backup *.bak *.tmp *.glo *.lot *.lof *.idx
	rm -f $(ROOT).bib

bibtex:
	rm -f $(ROOT).bib
	touch $(ROOT).bib
ifdef BIBTEX
	cat $(BIBTEX) > $(ROOT).bib.tmp
	mv $(ROOT).bib.tmp $(ROOT).bib
endif

check:
	chktex -n 2 -n 8 -n 6 -n 38 -n 13 -n 36 -n 24 $(ROOT)
ifdef BIBTEX
	bibclean $(BIBTEX) > /dev/null
endif

pdf: clean bibtex
	pdflatex $(PDFLATEX_ARGS) $(ROOT)
	-bibtex $(ROOT)
	pdflatex $(PDFLATEX_ARGS) $(ROOT)
	pdflatex $(PDFLATEX_ARGS) $(ROOT)

ps: clean bibtex
	latex $(PDFLATEX_ARGS) $(ROOT)
	-bibtex $(ROOT)
	latex $(PDFLATEX_ARGS) $(ROOT)
	latex $(PDFLATEX_ARGS) $(ROOT)

