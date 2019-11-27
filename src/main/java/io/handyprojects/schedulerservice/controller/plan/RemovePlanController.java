package io.handyprojects.schedulerservice.controller.plan;

import io.handyprojects.schedulerservice.service.PlanService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RemovePlanController {

    private final PlanService planService;

    public RemovePlanController(PlanService planService) {
        this.planService = planService;
    }

    @DeleteMapping("/api/plans/{id}")
    public void handle(@PathVariable Long id) {
        planService.remove(id);
    }
}
