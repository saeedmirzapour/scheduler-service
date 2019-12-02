package io.handyprojects.schedulerservice.controller.run;

import io.handyprojects.schedulerservice.service.RunnerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
public class ImmediateRunController {

    private final RunnerService runnerService;

    public ImmediateRunController(RunnerService runnerService) {
        this.runnerService = runnerService;
    }

    @PostMapping("/api/run")
    public void handle(@Valid @RequestBody Request request) {
        runnerService.run(request.getPlanId());
    }

    public static class Request {
        @NotNull
        private Long planId;

        public Long getPlanId() {
            return planId;
        }
    }
}
