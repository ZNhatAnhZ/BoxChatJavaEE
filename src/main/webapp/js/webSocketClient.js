let wsURI = "ws://" + document.location.host + "/RoomChatEndPoint";

// Create WebSocket connection.
const socket = new WebSocket(wsURI);

// Connection opened
socket.addEventListener('open', (event) => {
    socket.send('Hello Server!');
});

// Listen for messages
socket.addEventListener('message', (event) => {
    console.log('Message from server ', event.data);
});