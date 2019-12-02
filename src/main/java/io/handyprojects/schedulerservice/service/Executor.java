package io.handyprojects.schedulerservice.service;

import io.handyprojects.schedulerservice.domain.Job;
import io.handyprojects.schedulerservice.domain.Execution;
import io.handyprojects.schedulerservice.domain.Plan;
import io.handyprojects.schedulerservice.repository.ExecutionRepository;
import io.handyprojects.schedulerservice.util.DateUtil;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class Executor {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ExecutionRepository executionRepository;

    public Executor(ExecutionRepository executionRepository) {
        this.executionRepository = executionRepository;
    }

    @Async
    public void execute(Plan plan) {
        logger.info("starting to execute plan:{}", plan);
        List<Job> jobs = plan.getJobs();
        jobs.sort(Comparator.comparingInt(Job::getOrder));
        jobs.stream().filter(Job::isActive).forEach(this::doJob);
    }

    private void doJob(Job job) {
        String curlCommand = job.getCurlCommand();
        Execution result = new Execution(job.getId(), curlCommand);
        runCommand(curlCommand, result);
        saveResult(result);
    }

    private void runCommand(String command, Execution result) {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));
            Process process = processBuilder.start();
            process.waitFor();
            InputStream inputStream = process.getInputStream();
            InputStream errorStream = process.getErrorStream();
            InputStreamReader streamReader;
            if (process.exitValue() == 0) {
                streamReader = new InputStreamReader(inputStream);
                result.setStatus(Execution.Status.SUCCEEDED);
            }
            else {
                streamReader = new InputStreamReader(errorStream);
                result.setStatus(Execution.Status.FAILED);
            }
            BufferedReader bufferedReader = new BufferedReader(streamReader);
            String response = bufferedReader.lines().collect(Collectors.joining());
            result.setResult(response);
            process.destroy();
        } catch (Exception e) {
            logger.error("exception happened in starting process", e);
            result.setStatus(Execution.Status.FAILED);
            result.setResult(ExceptionUtils.getStackTrace(e));
        }
        result.setEndDate(DateUtil.now());
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    void saveResult(Execution result) {
        executionRepository.save(result);
    }

    public Page<Execution> getAllExecutionResults(Pageable pageable) {
        return executionRepository.findAll(pageable);
    }
}
