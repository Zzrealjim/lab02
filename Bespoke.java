package persistentdata;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

//Michael Curtotti - 2017
//Example of saving and loading info a simple bespoke format
//Values saved to and loaded from simple comma separated data
//The format handles "Student" data. Student class defines Student
//objects. During processing Student data held in a Java Map data structure.

public class Bespoke {
	// data structure for holding student data
	private Map<String, Student> data;
	
	public Bespoke(){
		
		}
		
		public void loadStudentData(String filePath){
			// try-with-resource statement since Java 7
			try{
				BufferedReader b_reader = new BufferedReader(new FileReader(filePath));
				// read in line and test if null
				String line;
				
				// a dictionary like structure for holding our student data
				this.data = new HashMap<String, Student>();
				

				while((line = b_reader.readLine()) != null){
					System.out.println(line);
					// break string into array
					String[] dataline  = line.split(",");
					// put data into new Student object
					Student student = new Student(dataline[0],Integer.parseInt(dataline[1]),dataline[2],dataline[3]);
					
					// generate unique identifier
					String uniqueID =  UUID.randomUUID().toString();
					
					// ensure uniqueID not in hashmap
					while(data.containsKey(uniqueID)){
						uniqueID = UUID.randomUUID().toString();
					}
					
					// place data into hashmap
					data.put(uniqueID, student);
									
				}
				// release system resources
				b_reader.close();
			} 
			catch (IOException x) {
				System.err.format("IOException: %s%n", x);
			}
		
		}
		
		
	
		
		public void saveStudentData(Map<String,Student> studentData,String filePath){
			
			// try-with-resource-statement since Java 7

			try{BufferedWriter b_writer = new BufferedWriter(new FileWriter(filePath));

				for (Map.Entry<String, Student> entry : studentData.entrySet()) {
					String key = entry.getKey();
					Student student = entry.getValue();
					
					String line = student.getName()+","+ Integer.toString(student.getAge())
					+ "," + student.getCourse() + "," + student.getPreference();
					
					// debug code
					System.out.println(line);
					
					// write single student data to file
					b_writer.write(line);
					// insert linebreak
					b_writer.newLine();
					
					
					}
					
				
				// release system resources
				b_writer.close();
			} 
			catch (IOException x) {
				System.err.format("IOException: %s%n", x);
			}
		}
		
	
	public Map<String,Student> getStudentData(){
		return this.data;
	}

	public static void main(String[] args) {
		// read in data from file
		System.out.println("loading");
		Bespoke bespoke = new Bespoke();
		bespoke.loadStudentData("test.txt");
		
		// Get data for writing to file		
		 Map<String,Student> studentData = bespoke.getStudentData();
		 System.out.println("saving");
		// write to file
		bespoke.saveStudentData(studentData,"test.txt");

	}

}
