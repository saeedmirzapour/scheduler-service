package io.handyprojects.schedulerservice.controller.plan;

import io.handyprojects.schedulerservice.service.PlanServiceImpl;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ActivatePlanController {

    private final PlanServiceImpl planService;

    public ActivatePlanController(PlanServiceImpl planService) {
        this.planService = planService;
    }

    @PutMapping("/api/plans/{id}/_activate")
    public void handle(@PathVariable Long id) {
        planService.activate(id);
    }
}
