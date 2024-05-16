function uploadFile() {
    const fileInput = document.getElementById('file-input');
    const file = fileInput.files[0];

    if (file) {
        const reader = new FileReader();

        reader.onload = function(e) {
            const text = e.target.result;
            displayMessage("You uploaded the file: " + file.name);
            // You can send the 'text' to your RAG model for training here
        }

        reader.readAsText(file);
    } else {
        displayMessage("Please select a file.");
    }
}

function displayMessage(message) {
    const chatBox = document.getElementById('chat-box');
    const messageElement = document.createElement('div');
    messageElement.innerText = message;
    chatBox.appendChild(messageElement);
}
