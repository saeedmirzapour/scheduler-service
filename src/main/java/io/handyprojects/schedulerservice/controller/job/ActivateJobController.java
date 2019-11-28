package io.handyprojects.schedulerservice.controller.job;

import io.handyprojects.schedulerservice.service.PlanManagementService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ActivateJobController {

    private final PlanManagementService planManagementService;

    public ActivateJobController(PlanManagementService planManagementService) {
        this.planManagementService = planManagementService;
    }

    @PutMapping("/api/jobs/{id}/_activate")
    public void handle(@PathVariable Long id) {
        planManagementService.activateJob(id);
    }
}
