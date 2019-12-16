package ru.stqa.pft.mantis.tests;

import biz.futureware.mantis.rpc.soap.client.ObjectRef;
import com.google.protobuf.ServiceException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.Issue;
import ru.stqa.pft.mantis.model.Project;

import java.io.IOException;
import java.util.Set;

public class IssueFixed extends TestBase{
    private int issueId;
    private ObjectRef status;
    private int projectId;

    @BeforeMethod
    public void getIssueAndProjectIds() throws IOException, ServiceException, javax.xml.rpc.ServiceException {
        Set<Project> projects = app.soap().getProjects();
        Project project = projects.iterator().next();
        Set<Issue> Issues = app.soap().getIssues(project);
        Issue myissue = Issues.iterator().next();
        issueId = myissue.getId();
        status = myissue.getStatus();
        projectId = project.getId();
    }

    @Test
    private void testIsMantisIssueFixed() throws IOException, ServiceException, javax.xml.rpc.ServiceException {
        //System.out.println("id = " + issueId);
        System.out.println("status = " + status);
        skipIfNotFixed(issueId);
    }

    @Test
    private void IsMantisIssueOpen() throws IOException, ServiceException, javax.xml.rpc.ServiceException {
        //System.out.println("id = " + issueId);
        System.out.println("status = " + status);
        skipIfOpen(issueId);
    }
}
