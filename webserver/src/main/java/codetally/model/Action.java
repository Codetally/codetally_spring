package codetally.model;


import javax.persistence.*;

@Entity
@Table(name = "action")
public class Action {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ActionType actiontype;
    private String actionref;
    private String author; //hmmm, should this be a hard link to a user?
}
