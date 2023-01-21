let wsURI = "ws://" + document.location.host + "/RoomChatEndPointFinal";

// Create WebSocket connection.
const socket = new WebSocket(wsURI);
const submit_button = document.getElementById("submit_button");
let message_display = document.getElementById("message_display");
let input = document.getElementById("message_input");
let username = document.getElementById("username").innerText;

// Connection opened
socket.addEventListener('open', (event) => {
    console.log("open socket successfully");
});

// Listen for messages
socket.addEventListener('message', (event) => {
    console.log(event.data);
    const data = JSON.parse(event.data);
    let message = document.createElement("p")
    message.innerText = data.username + " said: " + data.content;
    message_display.append(message);
});

function send_message(e) {
    console.log("send message trigger");
    username = username.substring(8, username.length);

    if (input.value.length > 0) {
        let json = {
            "username": username,
            "content": input.value
        }
        socket.send(JSON.stringify(json));
        input.value = '';
    }
}

submit_button.addEventListener( 'click', send_message)
input.addEventListener('keyup', (e) => {
    if (e.key === "Enter") {
        send_message();
    }
})