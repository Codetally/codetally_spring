package com.codetally.service;

import com.codetally.model.ShieldCost;

import java.util.Currency;

public interface ShieldService {
    public String getShieldByOwnerAndRepo(String owner, String repo);
    public ShieldCost getShieldCostByOwnerAndRepo(String owner, String repo);
    public ShieldCost getFriendlyShieldValue(float repoCost, Currency currency);
    public String getShieldByValue(String localcost);
    public String getShieldByOwnerAndRepoAndCost(String owner, String repo, String shieldCost);
}
