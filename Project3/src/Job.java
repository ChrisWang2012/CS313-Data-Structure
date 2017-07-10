/**
 * 
 * @author Haiquan Wang
 *This is the job class which contains the job constructor,compareTo method and basic getter and setter method
 */
public class Job implements Comparable<Job> {
	private int id;//create integer id
	private int priority;//create integer priority
	private int arrivalTime;//create integer arrival time
	private int length;//create integer length

	public Job(int id, int priority, int arrivalTime, int length) {//job constructor
		this.id = id;
		this.priority = priority;
		this.arrivalTime = arrivalTime;
		this.length = length;
	}

	@Override
	public int compareTo(Job o) {//compareTo method , let BinaryHeap know how to compare between two jobs
		return this.priority < o.priority ? -1 : ((this.priority == o.priority) ? 0 : 1);//if current job's priority < job o's priority,return-1,if equal return 0,else return 1
	}

	public int getId() {//id getter
		return id;
	}

	public void setId(int id) {//id setter
		this.id = id;
	}

	public int getPriority() {//priority getter
		return priority;
	}

	public void setPriority(int priority) {//priority setter
		this.priority = priority;
	}

	public int getArrivalTime() {//arrival time getter
		return arrivalTime;
	}

	public void setArrivalTime(int arrivalTime) {//arrival time setter
		this.arrivalTime = arrivalTime;
	}

	public int getLength() {//length getter
		return length;
	}

	public void setLength(int length) {//length setter
		this.length = length;
	}

}
