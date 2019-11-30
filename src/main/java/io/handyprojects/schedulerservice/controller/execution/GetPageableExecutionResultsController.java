package io.handyprojects.schedulerservice.controller.execution;

import io.handyprojects.schedulerservice.domain.ExecutionResult;
import io.handyprojects.schedulerservice.service.Executor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetPageableExecutionResultsController {

    private final Executor executor;

    public GetPageableExecutionResultsController(Executor executor) {
        this.executor = executor;
    }

    @GetMapping("/api/execution-results")
    public Page<ExecutionResult> handle(Pageable pageable) {
        return executor.getAllExecutionResults(pageable);
    }
}
