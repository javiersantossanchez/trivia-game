package jdss.trivia.microservice.core.entities;

import javax.persistence.*;


@Entity
@Table(name = "scoreboard")
public class Score {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name" ,nullable = false)
    private String name;

    @Column(name = "score" )
    private int score;

    @Column(name = "time" )
    private int time;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
