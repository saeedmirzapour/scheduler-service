package io.handyprojects.schedulerservice.controller.plan;

import io.handyprojects.schedulerservice.config.constraint.CronString;
import io.handyprojects.schedulerservice.domain.Plan;
import io.handyprojects.schedulerservice.service.PlanManagementService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@RestController
public class EditPlanController {

    private final PlanManagementService planManagementService;

    public EditPlanController(PlanManagementService planManagementService) {
        this.planManagementService = planManagementService;
    }

    @PutMapping("/api/plans/{id}")
    public Plan handle(@PathVariable Long id,
                       @Valid @RequestBody Request request) {
        return planManagementService.editPlan(id, request.getName(), request.getCronString());
    }

    public static class Request {
        @NotBlank
        private String name;
        @CronString
        private String cronString;

        public String getName() {
            return name;
        }

        public String getCronString() {
            return cronString;
        }
    }
}
