package io.handyprojects.schedulerservice.controller.job;

import io.handyprojects.schedulerservice.domain.Job;
import io.handyprojects.schedulerservice.service.PlanManagementService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@RestController
public class EditJobController {

    private final PlanManagementService planManagementService;

    public EditJobController(PlanManagementService planManagementService) {
        this.planManagementService = planManagementService;
    }

    @PutMapping("/api/jobs/{id}")
    public Job handle(@PathVariable Long id, @Valid @RequestBody Request request) {
        return planManagementService.editJob(id, request.getCurlCommand(), request.getOrder());
    }

    public static class Request {
        @NotBlank
        private String curlCommand;
        @NotNull
        private Integer order;

        public String getCurlCommand() {
            return curlCommand;
        }

        public Integer getOrder() {
            return order;
        }
    }
}
