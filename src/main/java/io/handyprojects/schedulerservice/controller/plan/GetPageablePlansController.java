package io.handyprojects.schedulerservice.controller.plan;

import io.handyprojects.schedulerservice.domain.Plan;
import io.handyprojects.schedulerservice.repository.specification.PlanSpecification;
import io.handyprojects.schedulerservice.service.PlanManagementService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetPageablePlansController {

    private final PlanManagementService planManagementService;

    public GetPageablePlansController(PlanManagementService planManagementService) {
        this.planManagementService = planManagementService;
    }

    @GetMapping("/api/plans")
    public Page<Plan> handle(@ModelAttribute PlanSpecification planSpecification, Pageable pageable) {
        return planManagementService.getPageablePlan(planSpecification, pageable);
    }
}
