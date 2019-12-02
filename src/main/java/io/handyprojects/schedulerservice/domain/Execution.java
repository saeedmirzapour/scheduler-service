package io.handyprojects.schedulerservice.domain;

import io.handyprojects.schedulerservice.config.Constants;
import io.handyprojects.schedulerservice.util.DateUtil;
import io.handyprojects.schedulerservice.util.StringUtil;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "execution", schema = "scheduler")
public class Execution {

    private Long id;
    private Status status;
    private Date startDate;
    private Date endDate;
    private Long jobId;
    private String curlCommand;
    private String result;

    protected Execution() {
    }

    public Execution(Long jobId, String curlCommand) {
        setJobId(jobId);
        setCurlCommand(curlCommand);
        setStartDate(DateUtil.now());
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(length = 64, nullable = false)
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Temporal(TemporalType.TIMESTAMP)
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Long getJobId() {
        return jobId;
    }

    private void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    @Column(length = 3000)
    public String getCurlCommand() {
        return curlCommand;
    }

    public void setCurlCommand(String curlCommand) {
        this.curlCommand = StringUtil.getFirstNCharacterOf(curlCommand, Constants.TEXT_LENGTH);
    }

    @Column(length = 3000)
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = StringUtil.getFirstNCharacterOf(result, Constants.TEXT_LENGTH);
    }

    public enum Status {
        STARTED, SUCCEEDED, FAILED
    }

    @Override
    public String toString() {
        return "JobExecutionLog{" +
                "id=" + id +
                ", status=" + status +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", jobId=" + jobId +
                ", curlCommand='" + curlCommand + '\'' +
                ", curlResult='" + result + '\'' +
                '}';
    }
}
