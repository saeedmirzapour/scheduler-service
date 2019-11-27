package io.handyprojects.schedulerservice.controller.plan;

import io.handyprojects.schedulerservice.config.constraint.CronString;
import io.handyprojects.schedulerservice.domain.Plan;
import io.handyprojects.schedulerservice.service.PlanServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@RestController
public class CreatePlanController {

    private final PlanServiceImpl planService;

    public CreatePlanController(PlanServiceImpl planService) {
        this.planService = planService;
    }

    @PostMapping("/api/plans")
    public Plan handle(@Valid @RequestBody Request request) {
        return planService.create(request.getName(), request.getCronString(), request.getActive());
    }

    public static class Request {
        @NotBlank
        private String name;
        @CronString
        private String cronString;
        @NotNull
        private Boolean active;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCronString() {
            return cronString;
        }

        public void setCronString(String cronString) {
            this.cronString = cronString;
        }

        public Boolean getActive() {
            return active;
        }

        public void setActive(Boolean active) {
            this.active = active;
        }
    }
}
