package com.codetally.model;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class CalculationResult {

    @SerializedName("author_name")
    private String mAuthorName;
    @SerializedName("calculation_currency")
    private String mCalculationCurrency;
    @SerializedName("calculation_date")
    private String mCalculationDate;
    @SerializedName("calculation_result")
    private Boolean mCalculationResult;
    @SerializedName("calculation_unit")
    private String mCalculationUnit;
    @SerializedName("calculation_value")
    private String mCalculationValue;
    @SerializedName("commit_date")
    private String mCommitDate;
    @SerializedName("commit_id")
    private String mCommitId;
    @SerializedName("commit_url")
    private String mCommitUrl;
    @SerializedName("committer_name")
    private String mCommitterName;
    @SerializedName("description")
    private String mDescription;
    @SerializedName("elapsed_time")
    private String mElapsedTime;
    @SerializedName("html_url")
    private String mHtmlUrl;
    @SerializedName("message")
    private String mMessage;
    @SerializedName("relative_url")
    private String mRelativeUrl;
    @SerializedName("repo_total_cost")
    private String mRepoTotalCost;
    @SerializedName("title")
    private String mTitle;

    public String getAuthorName() {
        return mAuthorName;
    }

    public void setAuthorName(String authorName) {
        mAuthorName = authorName;
    }

    public String getCalculationCurrency() {
        return mCalculationCurrency;
    }

    public void setCalculationCurrency(String calculationCurrency) {
        mCalculationCurrency = calculationCurrency;
    }

    public String getCalculationDate() {
        return mCalculationDate;
    }

    public void setCalculationDate(String calculationDate) {
        mCalculationDate = calculationDate;
    }

    public Boolean getCalculationResult() {
        return mCalculationResult;
    }

    public void setCalculationResult(Boolean calculationResult) {
        mCalculationResult = calculationResult;
    }

    public String getCalculationUnit() {
        return mCalculationUnit;
    }

    public void setCalculationUnit(String calculationUnit) {
        mCalculationUnit = calculationUnit;
    }

    public String getCalculationValue() {
        return mCalculationValue;
    }

    public void setCalculationValue(String calculationValue) {
        mCalculationValue = calculationValue;
    }

    public String getCommitDate() {
        return mCommitDate;
    }

    public void setCommitDate(String commitDate) {
        mCommitDate = commitDate;
    }

    public String getCommitId() {
        return mCommitId;
    }

    public void setCommitId(String commitId) {
        mCommitId = commitId;
    }

    public String getCommitUrl() {
        return mCommitUrl;
    }

    public void setCommitUrl(String commitUrl) {
        mCommitUrl = commitUrl;
    }

    public String getCommitterName() {
        return mCommitterName;
    }

    public void setCommitterName(String committerName) {
        mCommitterName = committerName;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getElapsedTime() {
        return mElapsedTime;
    }

    public void setElapsedTime(String elapsedTime) {
        mElapsedTime = elapsedTime;
    }

    public String getHtmlUrl() {
        return mHtmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        mHtmlUrl = htmlUrl;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public String getRelativeUrl() {
        return mRelativeUrl;
    }

    public void setRelativeUrl(String relativeUrl) {
        mRelativeUrl = relativeUrl;
    }

    public String getRepoTotalCost() {
        return mRepoTotalCost;
    }

    public void setRepoTotalCost(String repoTotalCost) {
        mRepoTotalCost = repoTotalCost;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

}
