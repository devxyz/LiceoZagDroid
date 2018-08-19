package it.gov.scuolesuperioridizagarolo.model.bitorario;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by stefano on 02/04/2017.
 */
public class LessonWrapper implements Serializable {
    public transient final LessonXml xml;
    public String teacher;
    public int duration;
    public int labDuration=0;
    public String subject;//materia
    public String classroom;//classe
    public String room;//aula

    public LessonWrapper(String teacher, int duration, String subject, String classroom, String room) {
        this.teacher = teacher;
        this.duration = duration;
        this.subject = subject;
        this.classroom = classroom;
        this.room = room;
        xml = null;
    }

    public LessonWrapper(LessonXml xml) {
        this.xml = xml;
        loadFromXml();
    }

    public boolean sameTeacherClassRoomSubject(LessonWrapper l) {
        return
                Objects.equals(teacher, l.teacher) &&
                        Objects.equals(classroom, l.classroom) &&
                        Objects.equals(subject, l.subject);
    }

    public Classroom getClassroomEnum() {
        return Classroom.getByLabel(classroom);
    }

    @Override
    public String toString() {
        return "LessonWrapper{" +
                "teacher='" + teacher + '\'' +
                ", duration=" + duration +
                ", subject='" + subject + '\'' +
                ", classroom='" + classroom + '\'' +
                ", room='" + room + '\'' +
                '}';
    }

    public void reload() {
        loadFromXml();
    }

    private void loadFromXml() {
        teacher = xml.getTeacher();
        duration = xml.getDuration();
        subject = xml.getSubject();
        classroom = xml.getClassroom();
        room = xml.getRoom();
    }

    /**
     * salva solo l'aula
     */
    public void saveRoom() {
        xml.setRoom(room);
    }

    public void save() {
        xml.setClassroom(classroom);
        xml.setRoom(room);
        xml.setDuration(duration);
        xml.setTeacher(teacher);
        xml.setSubject(subject);
    }

}
