import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.StringTokenizer;
/**
 * 
 * @author Haiquan Wang
 *This is the project3 class which contains the main method, 
 *read the jobs from input files, and pass the jobs set to scheduler class 
 */
public class Project3 {

	public static boolean done = false;//create boolean for signal use

	public static void main(String[] args) {
		FileReader fileReader = null;
		BufferedReader br = null;
		Scheduler scheduler = new Scheduler();//create the scheduler thread
		scheduler.start();//let the thread start running
		try {
			fileReader = new FileReader(new File("project3.txt"));//read from the file "project3.txt"

			br = new BufferedReader(fileReader);//buffer read the filereader
			String line = null;//create string for later use
			int preJobArrivalTime = 0;//create integer for later use
			while ((line = br.readLine()) != null) {//the file has information
				int id, priority, arrivalTime, length;//create integer for pass
				StringTokenizer stoken = new StringTokenizer(line);//create StringTokenizer for use
				id = Integer.valueOf(stoken.nextToken());//take the first token of the string as id
				priority = Integer.valueOf(stoken.nextToken());//take the second token of the string as priority
				arrivalTime = Integer.valueOf(stoken.nextToken());//take the third token of the string as arrival time
				length = Integer.valueOf(stoken.nextToken());//take the fourth token of the string as length
				int sleepTime = arrivalTime - preJobArrivalTime;//create integer sleep time equal to current job's arrival time minus previous job's arrival time
				preJobArrivalTime = arrivalTime;//assign preJobArribalTime to current arrival time
				for (int i = 0; i < sleepTime; i++) {//let the thread sleep until it's arrived
					Thread.sleep(1);
				}
				Job pass = new Job(id, priority, arrivalTime, length);//create job with the information got
				scheduler.addJob(pass);//pass this job information to scheduler
			}
			done = true;//all the job has been passed, signal the scheduler that main  is done
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
