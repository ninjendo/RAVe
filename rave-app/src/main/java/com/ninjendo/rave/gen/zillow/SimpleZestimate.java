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
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SimpleZestimate complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SimpleZestimate"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="amount" type="{http://www.zillow.com/static/xsd/ZillowTypes.xsd}Amount"/&gt;
 *         &lt;element name="valuationRange"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="low" type="{http://www.zillow.com/static/xsd/ZillowTypes.xsd}Amount"/&gt;
 *                   &lt;element name="high" type="{http://www.zillow.com/static/xsd/ZillowTypes.xsd}Amount"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SimpleZestimate", propOrder = {
    "amount",
    "valuationRange"
})
public class SimpleZestimate {

    @XmlElement(required = true)
    protected Amount amount;
    @XmlElement(required = true)
    protected SimpleZestimate.ValuationRange valuationRange;

    /**
     * Gets the value of the amount property.
     * 
     * @return
     *     possible object is
     *     {@link Amount }
     *     
     */
    public Amount getAmount() {
        return amount;
    }

    /**
     * Sets the value of the amount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Amount }
     *     
     */
    public void setAmount(Amount value) {
        this.amount = value;
    }

    /**
     * Gets the value of the valuationRange property.
     * 
     * @return
     *     possible object is
     *     {@link SimpleZestimate.ValuationRange }
     *     
     */
    public SimpleZestimate.ValuationRange getValuationRange() {
        return valuationRange;
    }

    /**
     * Sets the value of the valuationRange property.
     * 
     * @param value
     *     allowed object is
     *     {@link SimpleZestimate.ValuationRange }
     *     
     */
    public void setValuationRange(SimpleZestimate.ValuationRange value) {
        this.valuationRange = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="low" type="{http://www.zillow.com/static/xsd/ZillowTypes.xsd}Amount"/&gt;
     *         &lt;element name="high" type="{http://www.zillow.com/static/xsd/ZillowTypes.xsd}Amount"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "low",
        "high"
    })
    public static class ValuationRange {

        @XmlElement(required = true)
        protected Amount low;
        @XmlElement(required = true)
        protected Amount high;

        /**
         * Gets the value of the low property.
         * 
         * @return
         *     possible object is
         *     {@link Amount }
         *     
         */
        public Amount getLow() {
            return low;
        }

        /**
         * Sets the value of the low property.
         * 
         * @param value
         *     allowed object is
         *     {@link Amount }
         *     
         */
        public void setLow(Amount value) {
            this.low = value;
        }

        /**
         * Gets the value of the high property.
         * 
         * @return
         *     possible object is
         *     {@link Amount }
         *     
         */
        public Amount getHigh() {
            return high;
        }

        /**
         * Sets the value of the high property.
         * 
         * @param value
         *     allowed object is
         *     {@link Amount }
         *     
         */
        public void setHigh(Amount value) {
            this.high = value;
        }

    }

}