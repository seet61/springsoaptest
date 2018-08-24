package ru.seet61.springsoaptest;

import com.bercut.specs.dsi.tele2.tarantoolcache.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.tarantool.SocketChannelProvider;
import org.tarantool.TarantoolClient;
import org.tarantool.TarantoolClientConfig;
import org.tarantool.TarantoolClientImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Endpoint
public class TarantoolCacheEndpoint {
    private static final String NAMESPACE_URI = "http://www.bercut.com/specs/dsi/tele2/tarantoolCache";
    private TarantoolClient client;

    @Autowired
    public TarantoolCacheEndpoint() {
        TarantoolClientConfig config = new TarantoolClientConfig();
        config.username = "tokens_user";
        config.password = "tokens_pwd";

        SocketChannelProvider socketChannelProvider = new SocketChannelProvider() {
            @Override
            public SocketChannel get(int retryNumber, Throwable lastError) {
                if (lastError != null) {
                    lastError.printStackTrace(System.out);
                }
                try {
                    return SocketChannel.open(new InetSocketAddress("10.78.221.57", 3301));
                } catch (IOException e) {
                    throw new IllegalStateException(e);
                }
            }
        };

        this.client = new TarantoolClientImpl(socketChannelProvider, config);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "insertNewTokenRequest")
    @ResponsePayload
    public InsertNewTokenResponse insertNewToken(@RequestPayload InsertNewTokenRequest request) {
        InsertNewTokenResponse response = new InsertNewTokenResponse();

        try {
            List tuple = new ArrayList();
            tuple.add(request.getName());
            tuple.add(request.getBpmloader());
            tuple.add(request.getAspxauth());
            tuple.add(request.getUsername());
            tuple.add(request.getUsername());

            List list = this.client.syncOps().insert(1000, tuple);

            //System.out.println("Saved token: " + list);

            response.setCodeResult(0);
            response.setDescription("");
        } catch (UnsupportedOperationException e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            //System.out.println("insertNewToken " + e.getMessage() + "\n" + sw.toString());
            if (e.getMessage().equals("Not the same number of elements at rows")) {
                response.setCodeResult(1);
                response.setDescription(e.getMessage() + "\n" + sw.toString());
            } else {
                response.setCodeResult(2);
                response.setDescription(e.getMessage() + "\n" + sw.toString());
            }
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            //System.out.println("insertNewToken " + e.getMessage() + "\n" + sw.toString());
            response.setCodeResult(9);
            response.setDescription("Internal error: " + e.getMessage() + "\n" + sw.toString());
        }

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "readTokenRequest")
    @ResponsePayload
    public ReadTokenResponse readToken(@RequestPayload ReadTokenRequest request) {
        ReadTokenResponse response = new ReadTokenResponse();


        try {
            List tuple = new ArrayList();
            tuple.add(request.getName());
            List list = this.client.syncOps().select(1000,0, tuple,0,1,0);
            //System.out.println("Selected token: " + list);

            if (list.isEmpty()) {
                throw new NoSuchElementException("Elements of these criteria is not found");
            }

            List res = (List) list.get(0);

            response.setCodeResult(0);
            response.setDescription("");
            response.setName(res.get(0).toString());
            response.setBpmloader(res.get(1).toString());
            response.setAspxauth(res.get(2).toString());
            response.setUsername(res.get(3).toString());
            response.setBpmSessionId(res.get(4).toString());

        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            //System.out.println("readToken " + e.getMessage() + "\n" + sw.toString());
            response.setCodeResult(9);
            response.setDescription("Internal error: " + e.getMessage() + "\n" + sw.toString());
        }

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteTokenRequest")
    @ResponsePayload
    public DeleteTokenResponse deleteToken(@RequestPayload DeleteTokenRequest request) {
        DeleteTokenResponse response = new DeleteTokenResponse();

        try {
            List tuple = new ArrayList();
            tuple.add(request.getName());
            List list = this.client.syncOps().delete(1000, tuple);

            //System.out.println("Deleted token: " + list);

            if (list.isEmpty()) {
                throw new NoSuchElementException("Elements of these criteria is not found");
            }
            response.setCodeResult(0);
            response.setDescription("");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            //System.out.println("insertNewToken " + e.getMessage() + "\n" + sw.toString());
            response.setCodeResult(9);
            response.setDescription("Internal error: " + e.getMessage() + "\n" + sw.toString());
        }

        return response;
    }
}
