package io.handyprojects.schedulerservice.controller.plan;

import io.handyprojects.schedulerservice.domain.Plan;
import io.handyprojects.schedulerservice.service.PlanService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetPlanController {

    private final PlanService planService;

    public GetPlanController(PlanService planService) {
        this.planService = planService;
    }

    @GetMapping("/api/plans/{id}")
    public Plan handle(@PathVariable Long id) {
        return planService.get(id);
    }
}
