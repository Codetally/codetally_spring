package codetally.service;


import codetally.model.*;
import codetally.repository.ChargeRepository;
import com.codetally.plugin.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by greg on 25/06/17.
 * CRUD for charge "templates"
 */
@Service
public class ChargeService {
    private static final Logger logger = LoggerFactory.getLogger(ChargeService.class);

    @Autowired
    private ChargeRepository chargeRepository;

    @Autowired
    private LogService logService;

    public List<Charge> calculateCharges(Project project, Event event) {

        List<Charge> returnCharges = new ArrayList<>();
        //These are the possible charges for this project.
        List<Charge> chargeList = project.getCharges();
        //for each charge, does the event apply to it?
        chargeList.forEach(charge -> {
            if (charge.getEventAction() == event.getEventAction()) { //added,removed.modified
                if (charge.getChargeref().equalsIgnoreCase(event.getRef())) //most likely the author's email
                    returnCharges.add(charge);
            }
            if (charge.getChargetype() == ChargeType.tax) {
                returnCharges.add(charge);
            }
        });

        return returnCharges;
    }

    public Transaction calculateTransaction(Event event, List<Charge> chargeList) {
        Transaction returnTransaction  = new Transaction();
        returnTransaction.setChargeList(chargeList);
        returnTransaction.setEvent(event);
        for (Charge charge: chargeList) {
            if (charge.getChargetype() == ChargeType.author) {
                double chargeAmount = charge.getChargeAmount();
                if (chargeAmount>0)
                    returnTransaction.setTransactionCredit(chargeAmount);
                else
                    returnTransaction.setTransactionDebit(chargeAmount);
            }
        }
        // Net = Credit-Debit, as Credit is normal
        returnTransaction.setTransactionNet(returnTransaction.getTransactionCredit()-returnTransaction.getTransactionDebit());
        // Default gross to net
        returnTransaction.setTransactionGross(returnTransaction.getTransactionNet());

        //probably a better way than this, but I need to add tax
        for (Charge charge: chargeList) {
            if (charge.getChargetype() == ChargeType.tax) {
                double chargeAmount = charge.getChargeAmount();
                //tax charge is 4% or 0.04.
                //So set the gross to be the old gross, plus the old gross times the tax percent
                // ex. $100 + ($100 * 0.04) = $104
                returnTransaction.setTransactionGross(returnTransaction.getTransactionGross()+ (returnTransaction.getTransactionGross()*chargeAmount));
            }
        }

        return returnTransaction;
    }

    //create and update
    public void save(Charge charge) {
        chargeRepository.save(charge);
    }
    //read
    public List<Charge> getAll() {
        return chargeRepository.findAll();
    }
    //delete
    public void deleteCharge(Charge charge) {
        //charges is use may not be deleted.
        chargeRepository.delete(charge);
    }
}
