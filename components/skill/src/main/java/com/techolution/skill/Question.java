package com.techolution.skill;

import java.util.UUID;

/**
 * Created by tdelesio on 5/23/17.
 */
public class Question {

    private String id;
    private String question;
    private String correctAnswer;
    private int level=1;

    private String skillId;

    public Question()
    {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getSkillId() {
        return skillId;
    }

    public void setSkillId(String skillId) {
        this.skillId = skillId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Question question = (Question) o;

        if (level != question.level) return false;
        return id.equals(question.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
