//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.01.11 at 07:33:22 PM EST 
//


package com.ninjendo.rave.gen.zillow;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RankTypes.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="RankTypes"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="priceHighToLow"/&gt;
 *     &lt;enumeration value="priceLowToHigh"/&gt;
 *     &lt;enumeration value="featured"/&gt;
 *     &lt;enumeration value="dateSold"/&gt;
 *     &lt;enumeration value="daysOnZillow"/&gt;
 *     &lt;enumeration value="bedrooms"/&gt;
 *     &lt;enumeration value="bathrooms"/&gt;
 *     &lt;enumeration value="livingArea"/&gt;
 *     &lt;enumeration value="yearBuilt"/&gt;
 *     &lt;enumeration value="lotArea"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "RankTypes")
@XmlEnum
public enum RankTypes {

    @XmlEnumValue("priceHighToLow")
    PRICE_HIGH_TO_LOW("priceHighToLow"),
    @XmlEnumValue("priceLowToHigh")
    PRICE_LOW_TO_HIGH("priceLowToHigh"),
    @XmlEnumValue("featured")
    FEATURED("featured"),
    @XmlEnumValue("dateSold")
    DATE_SOLD("dateSold"),
    @XmlEnumValue("daysOnZillow")
    DAYS_ON_ZILLOW("daysOnZillow"),
    @XmlEnumValue("bedrooms")
    BEDROOMS("bedrooms"),
    @XmlEnumValue("bathrooms")
    BATHROOMS("bathrooms"),
    @XmlEnumValue("livingArea")
    LIVING_AREA("livingArea"),
    @XmlEnumValue("yearBuilt")
    YEAR_BUILT("yearBuilt"),
    @XmlEnumValue("lotArea")
    LOT_AREA("lotArea");
    private final String value;

    RankTypes(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static RankTypes fromValue(String v) {
        for (RankTypes c: RankTypes.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
