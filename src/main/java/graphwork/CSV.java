package graphwork;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class CSV {
	
	private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";

	private static final String FILE_HEADER = ""
			+ "Graph Name,"
			+ "Weigh of Most Connected,Time of Most Connected (ms),"
			+ "Weigh of Random,Time of Random (ms)";
	
	public static void createResultsFile(String filePath) {
		PrintWriter writer;
		FileWriter fileWriter = null;
		try {
			// Reset file
			writer = new PrintWriter(filePath);
			writer.print("");
			
			// Add header
			fileWriter = new FileWriter(filePath);
            fileWriter.append(FILE_HEADER);
			
			// New line
            fileWriter.append(NEW_LINE_SEPARATOR);
			
			System.out.println("CSV file created");
		} catch (FileNotFoundException ex) {
			// Do nothing
		} catch (IOException ex) {
			// Do nothing
		} finally {
            try {
				if (fileWriter != null) {
					fileWriter.flush();
					fileWriter.close();
				}
            } catch (IOException e) {
                System.out.println("Error while flushing and closing");
            }
        }
	}
	
	public static void saveResults(String filePath, String name,
			Float weigh_alg, Long time_alg,
			Float weigh_random, Long time_random) {
		
		FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(filePath, true);
			
			// Graph name identifier
			fileWriter.append(name);
			fileWriter.append(COMMA_DELIMITER);
			
			// Alg
			fileWriter.append(weigh_alg.toString());
			fileWriter.append(COMMA_DELIMITER);
			fileWriter.append(time_alg.toString());
			fileWriter.append(COMMA_DELIMITER);

			// Random
			fileWriter.append(weigh_random.toString());
			fileWriter.append(COMMA_DELIMITER);
			fileWriter.append(time_random.toString());
			
			// New line
			fileWriter.append(NEW_LINE_SEPARATOR);
			
            System.out.println("Graph appended to CSV file");
        } catch (Exception e) {
            System.out.println("Error while creating CSV file");
        } finally {
            try {
				if (fileWriter != null) {
					fileWriter.flush();
					fileWriter.close();
				}
            } catch (IOException e) {
                System.out.println("Error while flushing and closing");
            }
        }
	}
	
}