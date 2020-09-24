package com.project.asiancountries;

import android.content.Intent;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Country {
    @SerializedName("name")
    private String name;
    @SerializedName("capital")
    private String capital;
    @SerializedName("flag")
    private String flagUrl;
    @SerializedName("region")
    private String region;
    @SerializedName("subregion")
    private String subRegion;
    @SerializedName("population")
    private Integer population;
    @SerializedName("borders")
    private List<String> borders;
    @SerializedName("languages")
    private List<Language> languageList;

    public Country(String name, String capital, String flagUrl, String region, String subRegion, Integer population, List<String> borders, List<Language> languageList) {
        this.name = name;
        this.capital = capital;
        this.flagUrl = flagUrl;
        this.region = region;
        this.subRegion = subRegion;
        this.population = population;
        this.borders = borders;
        this.languageList = languageList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getFlagUrl() {
        return flagUrl;
    }

    public void setFlagUrl(String flagUrl) {
        this.flagUrl = flagUrl;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSubRegion() {
        return subRegion;
    }

    public void setSubRegion(String subRegion) {
        this.subRegion = subRegion;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public List<String> getBorders() {
        return borders;
    }

    public void setBorders(List<String> borders) {
        this.borders = borders;
    }

    public List<Language> getLanguageList() {
        return languageList;
    }

    public void setLanguageList(List<Language> languageList) {
        this.languageList = languageList;
    }

    @Override
    public String toString() {
        return "Country{" +
                "name='" + name + '\'' +
                ", capital='" + capital + '\'' +
                ", flagUrl='" + flagUrl + '\'' +
                ", region='" + region + '\'' +
                ", subRegion='" + subRegion + '\'' +
                ", population=" + population +
                ", borders=" + borders +
                ", languageList=" + languageList +
                '}';
    }
}
