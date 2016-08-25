import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.ModelFactory;

public class StaticFile {

	public static void main(String[] args) throws FileNotFoundException {
		OntModel model = ModelFactory.createOntologyModel();
		FileInputStream inOwl = new FileInputStream(new File("auto-da-compadecida.owl"));
		model.read(inOwl,null, "RDF/XML");
		
		String queryString = ""
				+ "select * "
				+ "where{ "
					+ "?s ?p ?o . "
				+ "}"
				+ "limit 100";
		
		Query query = QueryFactory.create(queryString);
		QueryExecution queryExecution = QueryExecutionFactory.create(query,model);
		ResultSet results = queryExecution.execSelect();
		
//		ResultSetFormatter.out(System.out, results, query);
		
		while (results.hasNext()){
			QuerySolution row = results.next();
			String s = row.get("?s").toString();
			String p = row.get("?p").toString();
			String o = row.get("?o").toString();
			
			System.out.println(s);
			System.out.println(p);
			System.out.println(o);
			System.out.println();
		}
		
		queryExecution.close();
	}

}
