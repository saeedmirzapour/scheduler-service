package io.handyprojects.schedulerservice.controller.job;

import io.handyprojects.schedulerservice.domain.Job;
import io.handyprojects.schedulerservice.service.PlanManagementService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class CreateJobController {

    private final PlanManagementService planManagementService;

    public CreateJobController(PlanManagementService planManagementService) {
        this.planManagementService = planManagementService;
    }

    @PostMapping("/api/jobs")
    public Job handle(@Valid @RequestBody Request request) {
        return planManagementService.createJob(request.getPlanId(),
                request.getCurlCommand(),
                request.getOrder(),
                request.isActive());
    }

    public static class Request {
        private Long planId;
        private String curlCommand;
        private int order;
        private boolean active;

        public Long getPlanId() {
            return planId;
        }

        public String getCurlCommand() {
            return curlCommand;
        }

        public int getOrder() {
            return order;
        }

        public boolean isActive() {
            return active;
        }
    }
}
