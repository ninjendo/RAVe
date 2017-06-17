//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.01.11 at 07:33:22 PM EST 
//


package com.ninjendo.rave.gen.zillow;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SimpleProperty complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SimpleProperty"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.zillow.com/static/xsd/ZillowTypes.xsd}Property"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="zestimate" type="{http://www.zillow.com/static/xsd/ZillowTypes.xsd}Zestimate"/&gt;
 *         &lt;element name="rentzestimate" type="{http://www.zillow.com/static/xsd/ZillowTypes.xsd}Zestimate" minOccurs="0"/&gt;
 *         &lt;element name="localRealEstate" type="{http://www.zillow.com/static/xsd/ZillowTypes.xsd}LocalRealEstate"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SimpleProperty", propOrder = {
    "zestimate",
    "rentzestimate",
    "localRealEstate"
})
@XmlSeeAlso({
    ComparableProperty.class,
    DetailedProperty.class
})

public class SimpleProperty extends Property
{

    @XmlElement(required = true)
    protected Zestimate zestimate;
    protected Zestimate rentzestimate;
    @XmlElement(required = true)
    protected LocalRealEstate localRealEstate;

    /**
     * Gets the value of the zestimate property.
     * 
     * @return
     *     possible object is
     *     {@link Zestimate }
     *     
     */
    public Zestimate getZestimate() {
        return zestimate;
    }

    /**
     * Sets the value of the zestimate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Zestimate }
     *     
     */
    public void setZestimate(Zestimate value) {
        this.zestimate = value;
    }

    /**
     * Gets the value of the rentzestimate property.
     * 
     * @return
     *     possible object is
     *     {@link Zestimate }
     *     
     */
    public Zestimate getRentzestimate() {
        return rentzestimate;
    }

    /**
     * Sets the value of the rentzestimate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Zestimate }
     *     
     */
    public void setRentzestimate(Zestimate value) {
        this.rentzestimate = value;
    }

    /**
     * Gets the value of the localRealEstate property.
     * 
     * @return
     *     possible object is
     *     {@link LocalRealEstate }
     *     
     */
    public LocalRealEstate getLocalRealEstate() {
        return localRealEstate;
    }

    /**
     * Sets the value of the localRealEstate property.
     * 
     * @param value
     *     allowed object is
     *     {@link LocalRealEstate }
     *     
     */
    public void setLocalRealEstate(LocalRealEstate value) {
        this.localRealEstate = value;
    }

}