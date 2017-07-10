/**
 * 
 * @author Haiquan Wang
 *This is the scheduler class which contains the thread"scheduler" and the running method of it
 *also there's methods of scheduling using binary heap as priority queue
 */
public class Scheduler extends Thread {
	private BinaryHeap<Job> bh = new BinaryHeap<Job>();//create binaryheap for use
	public int TIME_SLICE = 1;//assign time slice to 1

	public Scheduler() {//the name of the thread :D
		super("Scheduler");
	}

	@Override
	public void run() {
		try {
			Job currentJob = null;//create empty job for later use
			int runningTime = 0;//create integer running time for later use
			while (true) {
				if (currentJob == null && bh.isEmpty() && Project3.done) {//if there's no job coming and binaryheap is empty and main thread is done, break the loop
					break;
				}
				if (!bh.isEmpty() && currentJob == null) {//if current job is null, binaryheap has jobs
					currentJob = bh.deleteMinimum();//get a job of the highest priority in binaryheap and assign it to current job,let it run
				}
				if (currentJob != null) {//if there's job running
					runningTime += TIME_SLICE;//let the integer running time add time slice
					System.out.println("Current running job id is " + currentJob.getId());//output the current running job id
				}
				int bhsize = bh.size();//create integer to handle the size of binaryheap
				Thread.sleep(TIME_SLICE);//the job is running for (TIME_SLICE) time
				if (bhsize == bh.size()) {//if the size of BinaryHeap doesn't change,which means no job passed in from main thread,no new job come to run
					System.out.println("No new job arrived during this time slice.");//output that no new job arrived
				}
				if (currentJob != null && runningTime >= currentJob.getLength()) {//if there is job running, but the running time is greater than or equal to its length,which means this job finishes during the this time slice
					currentJob = null;//assign currentJob to null for later job use
					runningTime = 0;//assign runningTime to 0 for later job use
				}
			}
		} catch (InterruptedException e) {//should not appear
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

	public void addJob(Job job) {//pass the information got from main thread to BinaryHeap for scheduling
		System.out.println("Added job with ID " + job.getId() + ", length " + job.getLength() + " and priority "
				+ job.getPriority());//output that new job arrived with its information
		bh.insert(job);//insert the job to the BinaryHeap ,let it schedule
	}

}
