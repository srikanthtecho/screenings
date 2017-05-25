package com.techolution.interview;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by tdelesio on 5/24/17.
 */
@Entity
public class Interview {

    @Id
    private String id;

    private long date;
    private int status = InterviewStatus.SCHEDULED.ordinal();
    private int type = 1;
    private String interviewer;
    private String positionId;
    private String candidateName;
    private String notes;

    public enum InterviewStatus { SCHEDULED, L1, L2, DONE}

}
