package io.handyprojects.schedulerservice.controller.plan;

import io.handyprojects.schedulerservice.service.PlanManagementServiceImpl;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeactivatePlanController {

    private final PlanManagementServiceImpl planService;

    public DeactivatePlanController(PlanManagementServiceImpl planService) {
        this.planService = planService;
    }

    @PutMapping("/api/plans/{id}/_deactivate")
    public void handle(@PathVariable Long id) {
        planService.deactivatePlan(id);
    }
}
