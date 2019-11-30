package io.handyprojects.schedulerservice.service;

import io.handyprojects.schedulerservice.domain.Job;
import io.handyprojects.schedulerservice.domain.Plan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.*;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class Executor {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Async
    public void execute(Plan plan) {
        logger.info("Starting to execute plan{}", plan);
        List<Job> jobs = plan.getJobs();
        jobs.sort(Comparator.comparingInt(Job::getOrder));
        jobs.stream().filter(Job::isActive).forEach(this::doJob);
    }

    private void doJob(Job job) {
        String curlCommand = job.getCurlCommand();
        String result = runCommand(curlCommand);
        logger.info("Job:{} finished with Result:{}", job, result);
    }

    private String runCommand(String command) {
        ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));
        String result = "";
        try {
            Process process = processBuilder.start();
            process.waitFor();
            InputStream inputStream = process.getInputStream();
            InputStream errorStream = process.getErrorStream();
            InputStreamReader streamReader;
            if (process.exitValue() == 0)
                streamReader = new InputStreamReader(inputStream);
            else
                streamReader = new InputStreamReader(errorStream);
            BufferedReader bufferedReader = new BufferedReader(streamReader);
            result = bufferedReader.lines().collect(Collectors.joining());
            process.destroy();
        } catch (Exception e) {
            logger.error("Exception happened in starting process", e);
            result = "Exception happened in starting process with message: " + e.getMessage();
        }
        return result;
    }
}
