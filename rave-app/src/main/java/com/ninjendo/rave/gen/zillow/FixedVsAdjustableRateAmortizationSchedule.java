//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.01.11 at 07:33:22 PM EST 
//


package com.ninjendo.rave.gen.zillow;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FixedVsAdjustableRateAmortizationSchedule complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FixedVsAdjustableRateAmortizationSchedule"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Payment" maxOccurs="unbounded" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="period" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *                   &lt;element name="fixedPayment" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *                   &lt;element name="fixedBalance" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *                   &lt;element name="adjustablePayment" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *                   &lt;element name="adjustableBalance" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *                   &lt;element name="adjustableRate" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="frequency" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FixedVsAdjustableRateAmortizationSchedule", propOrder = {
    "payment"
})
public class FixedVsAdjustableRateAmortizationSchedule {

    @XmlElement(name = "Payment")
    protected List<FixedVsAdjustableRateAmortizationSchedule.Payment> payment;
    @XmlAttribute(name = "frequency", required = true)
    protected String frequency;

    /**
     * Gets the value of the payment property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the payment property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPayment().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FixedVsAdjustableRateAmortizationSchedule.Payment }
     * 
     * 
     */
    public List<FixedVsAdjustableRateAmortizationSchedule.Payment> getPayment() {
        if (payment == null) {
            payment = new ArrayList<FixedVsAdjustableRateAmortizationSchedule.Payment>();
        }
        return this.payment;
    }

    /**
     * Gets the value of the frequency property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFrequency() {
        return frequency;
    }

    /**
     * Sets the value of the frequency property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFrequency(String value) {
        this.frequency = value;
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
     *         &lt;element name="period" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
     *         &lt;element name="fixedPayment" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
     *         &lt;element name="fixedBalance" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
     *         &lt;element name="adjustablePayment" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
     *         &lt;element name="adjustableBalance" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
     *         &lt;element name="adjustableRate" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
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
        "period",
        "fixedPayment",
        "fixedBalance",
        "adjustablePayment",
        "adjustableBalance",
        "adjustableRate"
    })
    public static class Payment {

        @XmlElement(required = true)
        protected BigInteger period;
        @XmlElement(required = true)
        protected BigInteger fixedPayment;
        @XmlElement(required = true)
        protected BigInteger fixedBalance;
        @XmlElement(required = true)
        protected BigInteger adjustablePayment;
        @XmlElement(required = true)
        protected BigInteger adjustableBalance;
        @XmlElement(required = true)
        protected BigInteger adjustableRate;

        /**
         * Gets the value of the period property.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getPeriod() {
            return period;
        }

        /**
         * Sets the value of the period property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setPeriod(BigInteger value) {
            this.period = value;
        }

        /**
         * Gets the value of the fixedPayment property.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getFixedPayment() {
            return fixedPayment;
        }

        /**
         * Sets the value of the fixedPayment property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setFixedPayment(BigInteger value) {
            this.fixedPayment = value;
        }

        /**
         * Gets the value of the fixedBalance property.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getFixedBalance() {
            return fixedBalance;
        }

        /**
         * Sets the value of the fixedBalance property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setFixedBalance(BigInteger value) {
            this.fixedBalance = value;
        }

        /**
         * Gets the value of the adjustablePayment property.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getAdjustablePayment() {
            return adjustablePayment;
        }

        /**
         * Sets the value of the adjustablePayment property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setAdjustablePayment(BigInteger value) {
            this.adjustablePayment = value;
        }

        /**
         * Gets the value of the adjustableBalance property.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getAdjustableBalance() {
            return adjustableBalance;
        }

        /**
         * Sets the value of the adjustableBalance property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setAdjustableBalance(BigInteger value) {
            this.adjustableBalance = value;
        }

        /**
         * Gets the value of the adjustableRate property.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getAdjustableRate() {
            return adjustableRate;
        }

        /**
         * Sets the value of the adjustableRate property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setAdjustableRate(BigInteger value) {
            this.adjustableRate = value;
        }

    }

}
