//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.01.11 at 07:33:22 PM EST 
//


package com.ninjendo.rave.gen.zillow;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Regions complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Regions"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="zipcode-id" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="city-id" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="county-id" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="state-id" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Regions", propOrder = {
    "zipcodeId",
    "cityId",
    "countyId",
    "stateId"
})
public class Regions {

    @XmlElement(name = "zipcode-id", required = true)
    protected BigInteger zipcodeId;
    @XmlElement(name = "city-id", required = true)
    protected BigInteger cityId;
    @XmlElement(name = "county-id", required = true)
    protected BigInteger countyId;
    @XmlElement(name = "state-id", required = true)
    protected BigInteger stateId;

    /**
     * Gets the value of the zipcodeId property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getZipcodeId() {
        return zipcodeId;
    }

    /**
     * Sets the value of the zipcodeId property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setZipcodeId(BigInteger value) {
        this.zipcodeId = value;
    }

    /**
     * Gets the value of the cityId property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getCityId() {
        return cityId;
    }

    /**
     * Sets the value of the cityId property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setCityId(BigInteger value) {
        this.cityId = value;
    }

    /**
     * Gets the value of the countyId property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getCountyId() {
        return countyId;
    }

    /**
     * Sets the value of the countyId property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setCountyId(BigInteger value) {
        this.countyId = value;
    }

    /**
     * Gets the value of the stateId property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getStateId() {
        return stateId;
    }

    /**
     * Sets the value of the stateId property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setStateId(BigInteger value) {
        this.stateId = value;
    }

}
