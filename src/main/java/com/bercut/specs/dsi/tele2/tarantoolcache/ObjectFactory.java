//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.08.24 at 02:18:46 PM MSK 
//


package com.bercut.specs.dsi.tele2.tarantoolcache;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.bercut.specs.dsi.tele2.tarantoolcache package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.bercut.specs.dsi.tele2.tarantoolcache
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link InsertNewTokenRequest }
     * 
     */
    public InsertNewTokenRequest createInsertNewTokenRequest() {
        return new InsertNewTokenRequest();
    }

    /**
     * Create an instance of {@link ReadTokenRequest }
     * 
     */
    public ReadTokenRequest createReadTokenRequest() {
        return new ReadTokenRequest();
    }

    /**
     * Create an instance of {@link ReadTokenResponse }
     * 
     */
    public ReadTokenResponse createReadTokenResponse() {
        return new ReadTokenResponse();
    }

    /**
     * Create an instance of {@link DeleteTokenResponse }
     * 
     */
    public DeleteTokenResponse createDeleteTokenResponse() {
        return new DeleteTokenResponse();
    }

    /**
     * Create an instance of {@link InsertNewTokenResponse }
     * 
     */
    public InsertNewTokenResponse createInsertNewTokenResponse() {
        return new InsertNewTokenResponse();
    }

    /**
     * Create an instance of {@link DeleteTokenRequest }
     * 
     */
    public DeleteTokenRequest createDeleteTokenRequest() {
        return new DeleteTokenRequest();
    }

}