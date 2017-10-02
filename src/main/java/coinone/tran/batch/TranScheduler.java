package coinone.tran.batch;

import java.util.Date;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;

public class TranScheduler {
	@Scheduled(fixedRate = 1000 * 20)
	public void tickerJob() throws JobExecutionAlreadyRunningException, JobRestartException,
			JobInstanceAlreadyCompleteException, JobParametersInvalidException {

		String[] springConfig = { "spring/batch/jobs/ticker_job.xml" };

		ApplicationContext context = new ClassPathXmlApplicationContext(springConfig);

		JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");

		JobParametersBuilder builder = new JobParametersBuilder();
		builder.addDate("date", new Date());

		Job job = (Job) context.getBean("tickerJob");

		try {

			JobExecution execution = jobLauncher.run(job, builder.toJobParameters());
			System.out.println("Exit Status : " + execution.getStatus());

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("tickerJob Done");
	}


	@Scheduled(cron = "0 * * * * *")
	public void tranLimitBuyJob() throws JobExecutionAlreadyRunningException, JobRestartException,
			JobInstanceAlreadyCompleteException, JobParametersInvalidException {

		String[] springConfig = { "spring/batch/jobs/tran_job.xml" };

		ApplicationContext context = new ClassPathXmlApplicationContext(springConfig);

		JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");

		JobParametersBuilder builder = new JobParametersBuilder();
		builder.addDate("date", new Date());

		Job job = (Job) context.getBean("tranLimitBuyJob");

		try {

			JobExecution execution = jobLauncher.run(job, builder.toJobParameters());
			System.out.println("Exit Status : " + execution.getStatus());

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("tranLimitBuyJob Done");
	}

	@Scheduled(cron = "10 * * * * *")
	public void tranLimitSellJob() throws JobExecutionAlreadyRunningException, JobRestartException,
			JobInstanceAlreadyCompleteException, JobParametersInvalidException {

		String[] springConfig = { "spring/batch/jobs/tran_job.xml" };

		ApplicationContext context = new ClassPathXmlApplicationContext(springConfig);

		JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");

		JobParametersBuilder builder = new JobParametersBuilder();
		builder.addDate("date", new Date());

		Job job = (Job) context.getBean("tranLimitSellJob");

		try {

			JobExecution execution = jobLauncher.run(job, builder.toJobParameters());
			System.out.println("Exit Status : " + execution.getStatus());

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("tranLimitSellJob Done");
	}

	@Scheduled(cron = "20 * * * * *")
	public void tranCompleteChkJob() throws JobExecutionAlreadyRunningException, JobRestartException,
			JobInstanceAlreadyCompleteException, JobParametersInvalidException {

		String[] springConfig = { "spring/batch/jobs/tran_job.xml" };

		ApplicationContext context = new ClassPathXmlApplicationContext(springConfig);

		JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");

		JobParametersBuilder builder = new JobParametersBuilder();
		builder.addDate("date", new Date());

		Job job = (Job) context.getBean("tranCompleteChkJob");

		try {

			JobExecution execution = jobLauncher.run(job, builder.toJobParameters());
			System.out.println("Exit Status : " + execution.getStatus());

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("tranCompleteChkJob Done");
	}
}