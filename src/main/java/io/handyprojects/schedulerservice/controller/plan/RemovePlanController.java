package io.handyprojects.schedulerservice.controller.plan;

import io.handyprojects.schedulerservice.service.PlanManagementService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RemovePlanController {

    private final PlanManagementService planManagementService;

    public RemovePlanController(PlanManagementService planManagementService) {
        this.planManagementService = planManagementService;
    }

    @DeleteMapping("/api/plans/{id}")
    public void handle(@PathVariable Long id) {
        planManagementService.removePlan(id);
    }
}
