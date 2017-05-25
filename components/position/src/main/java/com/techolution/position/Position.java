package com.techolution.position;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Created by tdelesio on 5/23/17.
 */
public class Position {

    private String id;
    private String positionName;
    private String companyName;
    private String companyId;

    private String recruiterId;
    private Set<String> skills = new HashSet<>();


    public Position()
    {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getRecruiterId() {
        return recruiterId;
    }

    public void setRecruiterId(String recruiterId) {
        this.recruiterId = recruiterId;
    }

    public Set<String> getSkills() {
        return skills;
    }

    public void setSkills(Set<String> skills) {
        this.skills = skills;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        return id.equals(position.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
