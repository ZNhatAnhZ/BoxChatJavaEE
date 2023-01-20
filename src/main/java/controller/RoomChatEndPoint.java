package controller;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import model.Message;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@ServerEndpoint("/RoomChatEndPoint")
public class RoomChatEndPoint {
    private static Set<Session> sessions = Collections.synchronizedSet(new HashSet<Session>());
    @OnMessage
    public void onMessage(Session session, String message) {
        System.out.println("Greeting received:" + message);
    }

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("On open: " + session.getId());
        sessions.add(session);
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("On close: " + session.getId());
        sessions.remove(session);
    }
}
