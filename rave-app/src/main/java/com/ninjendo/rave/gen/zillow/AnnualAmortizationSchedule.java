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
 * <p>Java class for annualAmortizationSchedule complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="annualAmortizationSchedule"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Payment" maxOccurs="unbounded" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="period" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *                   &lt;element name="beginningBalance" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *                   &lt;element name="payment" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *                   &lt;element name="interest" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *                   &lt;element name="endingBalance" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
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
@XmlType(name = "annualAmortizationSchedule", propOrder = {
    "payment"
})
public class AnnualAmortizationSchedule {

    @XmlElement(name = "Payment")
    protected List<AnnualAmortizationSchedule.Payment> payment;
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
     * {@link AnnualAmortizationSchedule.Payment }
     * 
     * 
     */
    public List<AnnualAmortizationSchedule.Payment> getPayment() {
        if (payment == null) {
            payment = new ArrayList<AnnualAmortizationSchedule.Payment>();
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
     *         &lt;element name="beginningBalance" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
     *         &lt;element name="payment" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
     *         &lt;element name="interest" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
     *         &lt;element name="endingBalance" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
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
        "beginningBalance",
        "payment",
        "interest",
        "endingBalance"
    })
    public static class Payment {

        @XmlElement(required = true)
        protected BigInteger period;
        @XmlElement(required = true)
        protected BigInteger beginningBalance;
        @XmlElement(required = true)
        protected BigInteger payment;
        @XmlElement(required = true)
        protected BigInteger interest;
        @XmlElement(required = true)
        protected BigInteger endingBalance;

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
         * Gets the value of the beginningBalance property.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getBeginningBalance() {
            return beginningBalance;
        }

        /**
         * Sets the value of the beginningBalance property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setBeginningBalance(BigInteger value) {
            this.beginningBalance = value;
        }

        /**
         * Gets the value of the payment property.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getPayment() {
            return payment;
        }

        /**
         * Sets the value of the payment property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setPayment(BigInteger value) {
            this.payment = value;
        }

        /**
         * Gets the value of the interest property.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getInterest() {
            return interest;
        }

        /**
         * Sets the value of the interest property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setInterest(BigInteger value) {
            this.interest = value;
        }

        /**
         * Gets the value of the endingBalance property.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getEndingBalance() {
            return endingBalance;
        }

        /**
         * Sets the value of the endingBalance property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setEndingBalance(BigInteger value) {
            this.endingBalance = value;
        }

    }

}
