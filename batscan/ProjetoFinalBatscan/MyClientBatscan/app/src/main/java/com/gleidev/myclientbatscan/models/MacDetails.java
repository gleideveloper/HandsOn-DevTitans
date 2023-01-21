package com.gleidev.myclientbatscan.models;

public class MacDetails {
    private boolean success;
    private boolean found;
    private String macPrefix;
    private String company;
    private String country;
    private String blockStart;
    private String blockEnd;
    private Integer blockSize;
    private String blockType;
    private String updated;
    private boolean isRand;
    private boolean isPrivate;

    public MacDetails(boolean success, boolean found, String macPrefix, String company, String country, String blockStart, String blockEnd, Integer blockSize, String blockType, String updated, boolean isRand, boolean isPrivate) {
        this.success = success;
        this.found = found;
        this.macPrefix = macPrefix;
        this.company = company;
        this.country = country;
        this.blockStart = blockStart;
        this.blockEnd = blockEnd;
        this.blockSize = blockSize;
        this.blockType = blockType;
        this.updated = updated;
        this.isRand = isRand;
        this.isPrivate = isPrivate;
    }
}
