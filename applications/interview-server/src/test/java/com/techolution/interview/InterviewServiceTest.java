package com.techolution.interview;

import com.techolution.position.Position;
import com.techolution.skill.Question;
import com.techolution.skill.Skill;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import java.util.*;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class InterviewServiceTest {

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Mock
    private InterviewRepository mockInterviewRepository;

    @InjectMocks
    private InterviewService interviewService;

    @Mock
    private PositionGateway positionGateway;

    @Mock
    private SkillGateway skillGateway;

    @Before
    public void before() {
        interviewService.setPositionGateway(positionGateway);
        interviewService.setSkillGateway(skillGateway);
    }

    @Test
    public void startInterview() {

        final Interview interview = createInterview();

        Set<String> skillIds = new HashSet<>();
        skillIds.add("1");

        final Skill[] skills = new Skill[1];

        skills[0] = createSkill();

        final Position position = createPosition();

        when(mockInterviewRepository.findOne("1")).thenReturn(interview);

        when(positionGateway.getPositionById("1")).thenReturn(position);

        when(skillGateway.getSkillsbySkillIds(skillIds)).thenReturn(skills);

        Map<String, Object> model = interviewService.startInterview("1");

        Assert.assertEquals(model.size(), 3);

    }

    private Interview createInterview() {

        final Interview interview = new Interview();

        interview.setId("1");
        interview.setCandidateName("John");
        interview.setInterviewer("peter");
        interview.setPositionId("1");
        interview.setDate(System.currentTimeMillis());

        return interview;


    }

    private Position createPosition() {

        Position position = new Position();
        position.setId("1");
        position.setCompanyId("1");
        position.setRecruiterId("1");
        position.getSkills().add("1");

        return position;

    }

    private Skill createSkill() {

        Question question = new Question();
        question.setId("1");
        question.setSkillId("1");
        question.setLevel(1);
        question.setQuestion("What is HashMap in java");
        question.setCorrectAnswer("Hashmap is a key/value store that provides constant-time performance for the basic operations");

        final Skill skill = new Skill();
        skill.setId("1");
        skill.setName("Java");
        skill.addQuestion(question);

        return skill;
    }
}
