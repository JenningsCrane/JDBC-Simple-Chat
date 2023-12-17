INSERT INTO Chat.Users (Login, Password)
VALUES
    ('user1', 'password1'),
    ('user2', 'password2'),
    ('user3', 'password3'),
    ('user4', 'password4'),
    ('user5', 'password5');

INSERT INTO Chat.Chatroom (ChatroomName, ChatroomOwner)
VALUES
    ('Chatroom1', 1),
    ('Chatroom2', 2),
    ('Chatroom3', 3),
    ('Chatroom4', 4),
    ('Chatroom5', 5);

INSERT INTO Chat.Message (MessageAuthor, MessageRoom, MessageText, MessageDateTime)
VALUES
    (1, 1, 'Hello from user 1 in Chatroom 1', CURRENT_TIMESTAMP),
    (2, 1, 'Hi, user 1! Chatroom 1 is great!', CURRENT_TIMESTAMP),
    (3, 2, 'Greetings from user 3 in Chatroom 2', CURRENT_TIMESTAMP),
    (4, 2, 'Hello everyone in Chatroom 2!', CURRENT_TIMESTAMP),
    (5, 3, 'Welcome to Chatroom 3!', CURRENT_TIMESTAMP);

INSERT INTO Chat.User_ChatRooms (UserId, ChatRoomId)
VALUES
    (1, 1),
    (2, 2),
    (3, 3),
    (4, 4),
    (5, 5);
