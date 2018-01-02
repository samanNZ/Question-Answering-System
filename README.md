# Question Answering System
This is java project (DBpediaCategory.java) which:
- parses the file HTML-Nachricht
- reads the second the column to get a set of synonymous words
- for each word make a request to the DBpedia and find a category which has the word as label (sample SPARQL query: select distinct? cat where {? cat < http://www.w3.org/1999/02/22- rdf-syntax-ns # type > < http://www.w3.org/2004/02/skos/core#Concept >.? cat < http://www.w3.org/2000/01/rdf-schema #label >? label FILTER (regex (str (? label), "Capitals in Europe")}}
- write mappings to a txt file (synsets.txt)
