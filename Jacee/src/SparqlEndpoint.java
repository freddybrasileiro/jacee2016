import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;

public class SparqlEndpoint {

	public static void main(String[] args) {
		String queryString = ""
				+ "select * "
				+ "where{ "
					+ "<http://www.wikidata.org/entity/Q3282983> ?p ?o . "
				+ "}"
				+ "limit 10";
		
		String wikidataEndPoint = "https://query.wikidata.org/sparql";
		
		QueryExecution queryExecution = QueryExecutionFactory.sparqlService(wikidataEndPoint, queryString);	
		ResultSet results = queryExecution.execSelect();
		
		ResultSetFormatter.out(System.out, results);
		
//		while (results.hasNext()){
//			QuerySolution row = results.next();
//			String s = row.get("?s").toString();
//			String p = row.get("?p").toString();
//			String o = row.get("?o").toString();
//			
//			System.out.println(s);
//			System.out.println(p);
//			System.out.println(o);
//			System.out.println();
//		}
		
		queryExecution.close();
	}

}
