# ActiveMQ: How to send and receive messages
How to use ActiveMQ for sending and receiving messages by Joel Patrick Llosa.

### Point-to-Point Messaging Style (Queue)
1. Make sure ActiveMQ is running
2. Run SendMessageToQueue
3. Check ActiveMQ if the message is in the queue (http://localhost:8161)
4. Run ReceiveMessageFromQueue

### Publish/Subscribe Messaging Style (Topic)
1. Make sure ActiveMQ is running
2. Run SubscribeForMessage
3. Check ActiveMQ if the subscriber is registered (http://localhost:8161)
4. Run PublishMessage
