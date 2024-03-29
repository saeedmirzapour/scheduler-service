package io.handyprojects.schedulerservice.controller.plan;

import io.handyprojects.schedulerservice.domain.Plan;
import io.handyprojects.schedulerservice.service.PlanManagementService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetPlanController {

    private final PlanManagementService planManagementService;

    public GetPlanController(PlanManagementService planManagementService) {
        this.planManagementService = planManagementService;
    }

    @GetMapping("/api/plans/{id}")
    public Plan handle(@PathVariable Long id) {
        return planManagementService.getPlan(id);
    }
}
