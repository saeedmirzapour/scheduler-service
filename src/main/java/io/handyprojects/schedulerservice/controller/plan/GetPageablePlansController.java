package io.handyprojects.schedulerservice.controller.plan;

import io.handyprojects.schedulerservice.domain.Plan;
import io.handyprojects.schedulerservice.service.PlanService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetPageablePlansController {

    private final PlanService planService;

    public GetPageablePlansController(PlanService planService) {
        this.planService = planService;
    }

    @GetMapping("/api/plans")
    public Page<Plan> handle(Pageable pageable) {
        return planService.getPageable(pageable);
    }
}
