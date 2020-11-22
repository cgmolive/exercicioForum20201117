package com.example.demo;

import java.util.LinkedList;

public class JobQueue {
	private LinkedList<Integer> jobs = new LinkedList<>();
	private JobQueueListener listener;
	
	public JobQueue() {
		super();
	}
	
	public void addJobQueueListener(JobQueueListener listener) {
		this.listener = listener;
	}
	
	public static interface JobQueueListener{
		void jobQueueChanged(int novoTamanho);
	}
	
	public synchronized void queueJob(int job) {
		synchronized (this) {
			this.jobs.add(job);
			if(this.listener != null) {
				this.listener.jobQueueChanged(this.jobs.size());
			}
		}
	}
	
	public synchronized Integer getNextJob() {
		synchronized(this) {
			if (this.jobs.isEmpty()) {
                return null;
            }
            Integer job = this.jobs.removeFirst();
            System.out.println("puxando outro job!");
            if (this.listener != null) {
                this.listener.jobQueueChanged(this.jobs.size());
            }
            return job;
		}
	}
}
