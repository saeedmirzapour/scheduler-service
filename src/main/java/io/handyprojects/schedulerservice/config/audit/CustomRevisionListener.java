package io.handyprojects.schedulerservice.config.audit;

import org.hibernate.envers.RevisionListener;


public class CustomRevisionListener implements RevisionListener {

    public void newRevision(Object revisionEntity) {
        CustomRevisionEntity revision = (CustomRevisionEntity) revisionEntity;
        //todo: set security information about the user in revision object
    }

}
