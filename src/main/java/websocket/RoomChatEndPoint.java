package websocket;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

import dao.MessageDao;
import dao.UserDao;
import model.Message;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@ServerEndpoint(value = "/RoomChatEndPointFinal", encoders = MessageEncoder.class, decoders = MessageDecoder.class)
public class RoomChatEndPoint {
    private MessageDao messageDao;
    private static Set<Session> sessions = Collections.synchronizedSet(new HashSet<Session>());

    @OnMessage
    public void onMessage(Session session, Message message) {
        System.out.println("Greeting received:" + message.toString());
        for(Session s : sessions) {
            try {
                s.getBasicRemote().sendObject(message);
                messageDao.registerMessage(message);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (EncodeException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("On open: " + session.getId());
        sessions.add(session);
        messageDao = new MessageDao();
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("On close: " + session.getId());
        sessions.remove(session);
    }
}
