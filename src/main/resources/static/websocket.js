let websocket;

function onOpen() {
  websocket.send("connected");
  console.log("connected: onOpen()");
}

function onMessage(receivedMessage) {
  showMessage(receivedMessage.data);
  console.log("received: onMessage()");
}

function onClose() {
  console.log("disconnected: onClose()");
}

function connect() {
  websocket = new WebSocket("ws://localhost:8080/ws/chats");
  websocket.onmessage = onMessage;
  websocket.onopen = onOpen;
  websocket.onclose = onClose;

  setConnected(true);
  console.log("connected: connect()");
}

function disconnect() {
  websocket.close();

  setConnected(false);
  console.log("disconnected: disconnect()");
}

function sendMessage() {
  let message = document.getElementById("message");

  websocket.send(message.value);
  message.value = "";
  console.log("sent: send()");
}

function showMessage(message) {
  $("#messages").append("<tr><td>" + message + "</td></tr>");
}

function setConnected(connected) {
  $("#connect").prop("disabled", connected);
  $("#disconnect").prop("disabled", !connected);
  if (connected) {
    $("#conversation").show();
  } else {
    $("#conversation").hide();
  }
  $("#messages").html("");
}

$(function () {
  // connect 버튼 클릭시 -> connect 함수 실행
  $("#connect").click(() => connect());
  // disconnect 버튼 클릭시 -> disconnect 함수 실행
  $("#disconnect").click(() => disconnect());
  // send 버튼 클릭시 -> send 함수 실행
  $("#send").click(() => sendMessage());
});