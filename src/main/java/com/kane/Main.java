package com.kane;

import com.kane.calendarapp.event.*;
import com.kane.calendarapp.event.update.UpdateMeeting;
import com.kane.calendarapp.reader.EventCsvReader;
import com.kane.calendarapp.reader.RawCsvReader;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        Schedule schedule = new Schedule();

        EventCsvReader csvReader = new EventCsvReader(new RawCsvReader());
        String meetingCsvPath = "/data/meeting.csv";
        String noDisturbanceCsvPath = "/data/no_disturbance.csv";
        String outOfOfficeCsvPath = "/data/out_of_office.csv";
        String toDoCsvPath = "/data/to_do.csv";


        List<Meeting> meetings = csvReader.readMeetings(meetingCsvPath);
        meetings.forEach(schedule::add);

        List<NoDisturbance> noDisturbances = csvReader.readNoDisturbance(noDisturbanceCsvPath);
        noDisturbances.forEach(schedule::add);

        List<OutOfOffice> outOfOffices = csvReader.readOutOfOffice(outOfOfficeCsvPath);
        outOfOffices.forEach(schedule::add);

        List<Todo> todos = csvReader.readTodo(toDoCsvPath);
        todos.forEach(schedule::add);

        schedule.printAll();

        Meeting meeting = meetings.get(0);
        meeting.print();

        System.out.println("수정 후 ... ");
        meeting.vaildateAndUpdate(
                new UpdateMeeting(
                        "new title",
                        ZonedDateTime.now(),
                        ZonedDateTime.now().plusHours(1),
                        null,
                        "A",
                        "new agenda"

                )
        );

        meeting.delete(true);
        System.out.println("삭제 후 수정 시도 ... ");
        meeting.vaildateAndUpdate(
                new UpdateMeeting(
                        "new title",
                        ZonedDateTime.now(),
                        ZonedDateTime.now().plusHours(1),
                        null,
                        "B",
                        "new agenda2"

                )
        );
        meeting.print();
    }
}
