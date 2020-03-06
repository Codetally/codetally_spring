package codetally.model;

import codetally.model.github.Repository;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;
import javax.persistence.*;

@Generated("net.hexar.json2pojo")
@Entity
@Table(name = "logline")
public class Logline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @SerializedName("level")
    private String mLevel;
    @SerializedName("message")
    private String mMessage;
    @SerializedName("timestamp")
    private String mTimestamp;

    @ManyToOne
    @JoinColumn(name = "repoid")
    private Repository repository;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLevel() {
        return mLevel;
    }

    public void setLevel(String level) {
        mLevel = level;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public String getTimestamp() {
        return mTimestamp;
    }

    public void setTimestamp(String timestamp) {
        mTimestamp = timestamp;
    }

    public Repository getRepository() {
        return repository;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }
}
