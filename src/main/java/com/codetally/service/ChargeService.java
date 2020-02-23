package com.codetally.service;

import com.codetally.model.github.Commit;


public interface ChargeService {

    public void synchChargesByOwnernameAndRepo(String ownername, String repo);

    public float calculateAuthorCharge(Commit commit, long repositoryId);

    public float calculateTaxCharge(long repositoryId, float chargeamount);

    public float calculateTimeCharge(Commit commit, long repositoryId, String owner, String repo);

    public String getChargeConfig(String owner, String repo);

}
