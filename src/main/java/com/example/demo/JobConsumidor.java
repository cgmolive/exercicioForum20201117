package com.example.demo;

public class JobConsumidor extends Thread {
	private JobQueue jobs;
	private Integer jobsDesignados = null;
	
	public JobConsumidor (JobQueue jobs) {
		this.jobs = jobs;
	}
	
	@Override
	public void run() {
		while(true) {
			if(jobsDesignados == null ||jobsDesignados == 0) {
				try {
					jobsDesignados = jobs.getNextJob();
					if(jobsDesignados == null) {
						System.out.println("Nada pra fazer..." + System.currentTimeMillis()+ " " + this);
						this.sleep(5000);
					}
					
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			else {
				int workPendente = jobsDesignados;
				for(int i = jobsDesignados; i>= 0; i--) {
					System.out.println("Estou trabalhando. Tamanho do Job " + jobsDesignados + ", " + i + "restantes" + System.currentTimeMillis());
					try {
						this.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				jobsDesignados = null;
			}
		}
	}
}
