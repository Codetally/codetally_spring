package codetally.model;

import com.codetally.plugin.EventAction;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;
import javax.persistence.*;

//Charges exist only in an administrative sense. Weird? We track events relative to a project,
// but do not store the resulting charges. I guess the idea here is to allow recalculations...

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
@Entity
@Table(name = "charge")
public class Charge {

    /* going to need a chargetemplate. Or a more rich linking object*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private double chargeAmount;
    @SerializedName("chargeref")
    private String mChargeref;

    private ChargeType chargetype;
    @SerializedName("description")
    private String mDescription;

    private EventAction eventAction;


    public String getChargeref() {
        return mChargeref;
    }

    public void setChargeref(String chargeref) {
        mChargeref = chargeref;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EventAction getEventAction() {
        return eventAction;
    }

    public void setEventAction(EventAction eventAction) {
        this.eventAction = eventAction;
    }

    public ChargeType getChargetype() {
        return chargetype;
    }

    public void setChargetype(ChargeType chargetype) {
        this.chargetype = chargetype;
    }

    public double getChargeAmount() {
        return chargeAmount;
    }

    public void setChargeAmount(double chargeAmount) {
        this.chargeAmount = chargeAmount;
    }
}
