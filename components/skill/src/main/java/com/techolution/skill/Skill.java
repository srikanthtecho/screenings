package com.techolution.skill;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Created by tdelesio on 5/23/17.
 */
public class Skill {

    private String id;
    private String name;

    private Set<Question> questions = new HashSet<>();

    public Skill()
    {
        id = UUID.randomUUID().toString();
    }




    public void addQuestion(Question q)
    {
        questions.add(q);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Skill skill = (Skill) o;

        return id != null ? id.equals(skill.id) : skill.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
