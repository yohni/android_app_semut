package id.sisemut.apps.Model;

import java.util.ArrayList;
import java.util.List;

public class ProjectSemut {

    public String project_name;
    public Boolean project_status;
    public List<String>  project_ethnic;
    public List<String> project_occupation;
    public String project_age_category;
    public String project_image;
    public List<String> project_member;

    public ProjectSemut() {

    }

    public ProjectSemut(String project_name, Boolean project_status, List<String> project_ethnic, List<String> project_occupation, String project_age_category, String project_image, List<String> project_member) {
        this.project_name = project_name;
        this.project_status = project_status;
        this.project_ethnic = project_ethnic;
        this.project_occupation = project_occupation;
        this.project_age_category = project_age_category;
        this.project_image = project_image;
        this.project_member = project_member;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public Boolean getProject_status() {
        return project_status;
    }

    public void setProject_status(Boolean project_status) {
        this.project_status = project_status;
    }

    public List<String> getProject_ethnic() {
        return project_ethnic;
    }

    public void setProject_ethnic(List<String> project_ethnic) {
        this.project_ethnic = project_ethnic;
    }

    public List<String> getProject_occupation() {
        return project_occupation;
    }

    public void setProject_occupation(List<String> project_occupation) {
        this.project_occupation = project_occupation;
    }

    public String getProject_age_category() {
        return project_age_category;
    }

    public void setProject_age_category(String project_age_category) {
        this.project_age_category = project_age_category;
    }

    public String getProject_image() {
        return project_image;
    }

    public void setProject_image(String project_image) {
        this.project_image = project_image;
    }

    public List<String> getProject_member() {
        return project_member;
    }

    public void setProject_member(List<String> project_member) {
        this.project_member = project_member;
    }
}
