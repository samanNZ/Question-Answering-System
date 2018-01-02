import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.ResultSet;

public class DBpediaCategory{

	public static void main(String[] args) throws IOException {
		String fileName = "C:\\Users\\Saleem\\Downloads\\synsets.txt";
		ReadFile(fileName);     
	}

	public static void ReadFile(String fileName) throws IOException {
		FileReader fr = new FileReader(fileName);
		BufferedReader br = new BufferedReader(fr);
		FileWriter fw=new FileWriter("synTocats.txt");    
		String line;
		int counter = 1;
		while((line = br.readLine()) != null) {
			String [] columns = line.split(",");    
			String synm = columns[1];
			List<String> cats=  getDBpediaCat(synm);
			fw.write(synm+"\t"+cats+"\n");
			System.out.println(counter+": synonym: "+ synm + " categories printed to file");
			counter++;   
		}
		fw.close();
		br.close();
	}

	public static List<String> getDBpediaCat(String synm) {
		List<String> cats = new ArrayList<String>();
		String sparqlQuery = "select distinct ?cat where "
				+ "{"
				+ "?cat a <http://www.w3.org/2004/02/skos/core#Concept> . "
				+ "?cat <http://www.w3.org/2000/01/rdf-schema#label> ?label ."
				+ "FILTER regex (str(?label), \""+synm+"\")"
				+ "}";
		//System.out.println(sparqlQuery);
		Query query = QueryFactory.create(sparqlQuery);
		QueryExecution qexec = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", query);
		ResultSet results = qexec.execSelect(); 
		while(results.hasNext()){
			cats.add(results.next().getResource("cat").toString());
		}
		qexec.close() ;
		return cats;

	}
}