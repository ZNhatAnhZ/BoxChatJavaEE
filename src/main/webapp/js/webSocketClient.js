let wsURI = "ws://" + document.location.host + "/RoomChatEndPointFinal";

// Create WebSocket connection.
const socket = new WebSocket(wsURI);

// Connection opened
socket.addEventListener('open', (event) => {
    let json = {
        "username": "a",
        "content": "test message"
    }
    socket.send(JSON.stringify(json));
});

// Listen for messages
socket.addEventListener('message', (event) => {
    console.log('Message from server ', event.data);
});