package com.kane.calendar.event.update;

import java.time.ZonedDateTime;
import java.util.Set;

public class UpdateMeeting extends AbstractAuditableEvent {

    private final Set<String> participants;
    private final String meetingRoom;
    private final String agenda;

    public UpdateMeeting(String title, ZonedDateTime starAt, ZonedDateTime endAt,
                         Set<String> participants, String meetingRoom, String agenda) {
        super(title, starAt, endAt);

        this.participants = participants;
        this.meetingRoom = meetingRoom;
        this.agenda = agenda;
    }

    public Set<String> getParticipants() {
        return participants;
    }

    public String getMeetingRoom() {
        return meetingRoom;
    }

    public String getAgenda() {
        return agenda;
    }
}
