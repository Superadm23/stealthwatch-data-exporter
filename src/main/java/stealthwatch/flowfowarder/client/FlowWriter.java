package stealthwatch.flowfowarder.client;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.DeploymentException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

import static javax.websocket.ContainerProvider.getWebSocketContainer;

@ClientEndpoint
class FlowWriter {

    static void start(String host) throws URISyntaxException, IOException, DeploymentException {
        try (Session ignored = getWebSocketContainer().connectToServer(new FlowWriter(),
                                                                       new URI("ws://" + host + ":8092/websocket"))) {
            ; // Intentionally empty
        }
    }

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("onOpen " + session);
    }

    @OnClose
    public void onClose(Session session, CloseReason reason) {
        System.out.println("onClose " + session + " " + reason);
    }

    @OnMessage
    public void onMessage(String message) {
        System.out.println("onMessage " + message);
    }
}