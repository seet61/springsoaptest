package ru.seet61.springsoaptest;

import com.bercut.specs.dsi.tele2.tarantoolcache.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class TarantoolCacheEndpoint {
    private static final String NAMESPACE_URI = "http://www.bercut.com/specs/dsi/tele2/tarantoolCache";

    @Autowired
    public TarantoolCacheEndpoint() {
        //this.tarantoolRepository = tarantoolRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "insertNewTokenRequest")
    @ResponsePayload
    public InsertNewTokenResponse insertNewToken(@RequestPayload InsertNewTokenRequest request) {
        InsertNewTokenResponse response = new InsertNewTokenResponse();

        // to do logic
        System.out.println(request.toString());

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "readTokenRequest")
    @ResponsePayload
    public ReadTokenResponse readToken(@RequestPayload ReadTokenRequest request) {
        ReadTokenResponse response = new ReadTokenResponse();

        // to do logic
        System.out.println(request.toString());

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteTokenRequest")
    @ResponsePayload
    public DeleteTokenResponse deleteToken(@RequestPayload DeleteTokenRequest request) {
        DeleteTokenResponse response = new DeleteTokenResponse();

        // to do logic
        System.out.println(request.toString());

        return response;
    }
}
