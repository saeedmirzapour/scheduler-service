package io.handyprojects.schedulerservice.controller.plan;

import io.handyprojects.schedulerservice.service.PlanManagementServiceImpl;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ActivatePlanController {

    private final PlanManagementServiceImpl planService;

    public ActivatePlanController(PlanManagementServiceImpl planService) {
        this.planService = planService;
    }

    @PutMapping("/api/plans/{id}/_activate")
    public void handle(@PathVariable Long id) {
        planService.activatePlan(id);
    }
}
