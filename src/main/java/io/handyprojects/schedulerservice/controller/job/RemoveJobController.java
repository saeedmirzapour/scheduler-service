package io.handyprojects.schedulerservice.controller.job;

import io.handyprojects.schedulerservice.service.PlanManagementService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RemoveJobController {

    private final PlanManagementService planManagementService;

    public RemoveJobController(PlanManagementService planManagementService) {
        this.planManagementService = planManagementService;
    }

    @DeleteMapping("/api/jobs/{id}")
    public void handle(@PathVariable Long id) {
        planManagementService.removeJob(id);
    }
}
