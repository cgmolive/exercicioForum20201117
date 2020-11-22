package com.example.demo;


public class Worker extends Thread{
	private Job jobAtual = new Job(0);
	private int workDone;
	
	public synchronized void setCurrentJob (Job jobAtual) {
		this.jobAtual = jobAtual;
	}
	
	@Override
	public void run() {
		System.out.println("Começando trabalho.");
		while (true) {
			try {
				while(workDone == jobAtual.getTamanho()) {
					this.wait();
				}
				this.sleep(1000);
				workDone++;
				System.out.println(this.toString() + "==>" + workDone);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
