package com.codetally.service;

import com.codetally.model.ShieldCost;
import com.codetally.model.github.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.Currency;
import java.util.Locale;

/**
 * Created by greg on 29/06/17.
 */
@Service
public class ShieldService {
    private static final Logger logger = LoggerFactory.getLogger(ShieldService.class);

    @Autowired
    RepositoryService repositoryService;

    @Autowired
    CommitService commitService;

    public String getShieldByOwnerAndRepo(String owner, String repo) {
        ShieldCost shieldCost = getShieldCostByOwnerAndRepo(owner, repo);
        return shieldCost.getCurrency_sign() + shieldCost.getAmount() + " " + shieldCost.getMultiplier();
    }

    public ShieldCost getShieldCostByOwnerAndRepo(String owner, String repo) {

        long repositoryId = repositoryService.getSingleIdByOwnerAndRepo(owner, repo);
        float repoCost = commitService.getRepoCodecost(repositoryId);
        Currency currency = repositoryService.getCurrency(repositoryId);
        return getFriendlyShieldValue(repoCost, currency);
    }

    public ShieldCost getFriendlyShieldValue(float repoCost, Currency currency) {

        Locale locale = new Locale("en", currency.getCurrencyCode().substring(0, 2));

        ShieldCost shieldCost = new ShieldCost();
        shieldCost.setCurrency_sign(currency.getSymbol(locale));
        shieldCost.setCurrency_abbreviation(currency.getCurrencyCode());
        shieldCost.setMultiplier("");

        float roundedCost = repoCost;
        if (repoCost > 999999999) {
            roundedCost = roundTwoDecimals(repoCost / 1000000000);
            shieldCost.setMultiplier("B");
        } else if (repoCost > 999999) {
            roundedCost = roundTwoDecimals(repoCost / 1000000);
            shieldCost.setMultiplier("M");
        } else if (repoCost > 999) {
            roundedCost = roundTwoDecimals(repoCost / 1000);
            shieldCost.setMultiplier("K");
        }
        shieldCost.setAmount(String.valueOf(roundedCost));
        return shieldCost;
    }

    public String getShieldByValue(String localcost) {

        String response =
                "<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"120\" height=\"20\">" +
                        "        <linearGradient id=\"a\" x2=\"0\" y2=\"100%%\">" +
                        "                <stop offset=\"0\" stop-color=\"#bbb\" stop-opacity=\".1\"/>" +
                        "                <stop offset=\"1\" stop-opacity=\".1\"/>" +
                        "        </linearGradient>" +
                        "        <rect rx=\"3\" width=\"120\" height=\"20\" fill=\"#555\"/>" +
                        "        <rect rx=\"3\" x=\"60\" width=\"60\" height=\"20\" fill=\"#2E8B57\"/>" +
                        "        <path fill=\"#2E8B57\" d=\"M60 0h4v20h-4z\"/>" +
                        "        <rect rx=\"3\" width=\"120\" height=\"20\" fill=\"url(#a)\"/>" +
                        "        <g fill=\"#fff\" text-anchor=\"middle\" font-family=\"DejaVu Sans,Verdana,Geneva,sans-serif\" font-size=\"11\">" +
                        "                <text x=\"29.5\" y=\"15\" fill=\"#010101\" fill-opacity=\".3\">codetally</text>" +
                        "                <text x=\"29.5\" y=\"14\">codetally</text>" +
                        "                <text x=\"87\" y=\"15\" fill=\"#010101\" fill-opacity=\".3\">%s</text>" +
                        "                <text x=\"87\" y=\"14\">%s</text>" +
                        "        </g>" +
                        "</svg>";


        return String.format(response, localcost, localcost);
    }

    private float roundTwoDecimals(float d) {
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        return Float.valueOf(twoDForm.format(d));
    }

    public String getShieldByOwnerAndRepoAndCost(String owner, String repo, String shieldCost) {

        Repository repository = repositoryService.getSingleByOwnerAndRepo(owner, repo);
        repository.getC
        Currency currency = repositoryService.getCurrency(repositoryId);
        ShieldCost shieldCostObject = getFriendlyShieldValue(Float.valueOf(shieldCost), currency);

        return shieldCostObject.getCurrency_sign() + " " + shieldCostObject.getAmount() + " " + shieldCostObject.getMultiplier();
    }
}
