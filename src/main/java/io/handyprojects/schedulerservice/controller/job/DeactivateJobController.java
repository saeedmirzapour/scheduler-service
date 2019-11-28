package io.handyprojects.schedulerservice.controller.job;

import io.handyprojects.schedulerservice.service.PlanManagementService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeactivateJobController {

    private final PlanManagementService planManagementService;

    public DeactivateJobController(PlanManagementService planManagementService) {
        this.planManagementService = planManagementService;
    }

    @PutMapping("/api/jobs/{id}/_deactivate")
    public void handle(@PathVariable Long id) {
        planManagementService.deactivateJob(id);
    }
}
