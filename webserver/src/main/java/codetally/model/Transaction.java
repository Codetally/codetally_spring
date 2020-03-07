package codetally.model;


import com.codetally.plugin.Event;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TransactionType transactiontype;
    private String actionref;
    private List<Charge> chargeList;
    private Event event;
    private List<Logline> loglineList;
    private double transactionCredit;
    private double transactionDebit;
    private double transactionNet; //before tax
    private double transactionGross; //after tax

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TransactionType getTransactiontype() {
        return transactiontype;
    }

    public void setTransactiontype(TransactionType transactiontype) {
        this.transactiontype = transactiontype;
    }

    public String getActionref() {
        return actionref;
    }

    public void setActionref(String actionref) {
        this.actionref = actionref;
    }

    public List<Charge> getChargeList() {
        return chargeList;
    }

    public void setChargeList(List<Charge> chargeList) {
        this.chargeList = chargeList;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public List<Logline> getLoglineList() {
        return loglineList;
    }

    public void setLoglineList(List<Logline> loglineList) {
        this.loglineList = loglineList;
    }

    public double getTransactionCredit() {
        return transactionCredit;
    }

    public void setTransactionCredit(double transactionCredit) {
        this.transactionCredit = transactionCredit;
    }

    public double getTransactionDebit() {
        return transactionDebit;
    }

    public void setTransactionDebit(double transactionDebit) {
        this.transactionDebit = transactionDebit;
    }

    public double getTransactionNet() {
        return transactionNet;
    }

    public void setTransactionNet(double transactionNet) {
        this.transactionNet = transactionNet;
    }

    public double getTransactionGross() {
        return transactionGross;
    }

    public void setTransactionGross(double transactionGross) {
        this.transactionGross = transactionGross;
    }
}
