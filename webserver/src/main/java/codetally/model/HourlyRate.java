package codetally.model;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class HourlyRate {

    @SerializedName("author_email")
    private String mAuthorEmail;
    @SerializedName("cost_per_hour")
    private String mCostPerHour;
    @SerializedName("description")
    private String mDescription;

    public String getAuthorEmail() {
        return mAuthorEmail;
    }

    public void setAuthorEmail(String authorEmail) {
        mAuthorEmail = authorEmail;
    }

    public String getCostPerHour() {
        return mCostPerHour;
    }

    public void setCostPerHour(String costPerHour) {
        mCostPerHour = costPerHour;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

}
