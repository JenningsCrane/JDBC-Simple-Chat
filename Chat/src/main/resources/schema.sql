drop schema if exists Chat cascade;
create schema if not exists Chat;

CREATE TABLE Chat.Users (
                            UserID SERIAL PRIMARY KEY,
                            Login VARCHAR(16) UNIQUE NOT NULL,
                            Password VARCHAR(16) NOT NULL
);

CREATE TABLE Chat.Chatroom (
                               ChatroomID SERIAL PRIMARY KEY,
                               ChatroomName VARCHAR(20) NOT NULL,
                               ChatroomOwner INT,
                               FOREIGN KEY (ChatroomOwner) REFERENCES Chat.Users(UserID)
);

CREATE TABLE Chat.Message (
                              MessageID SERIAL PRIMARY KEY,
                              MessageAuthor INT,
                              MessageRoom INT,
                              MessageText TEXT NOT NULL,
                              MessageDateTime TIMESTAMP,
                              FOREIGN KEY (MessageAuthor) REFERENCES Chat.Users(UserID),
                              FOREIGN KEY (MessageRoom) REFERENCES Chat.Chatroom(ChatroomID)
);

CREATE TABLE Chat.User_ChatRooms (
                                     ChatRoomsId SERIAL PRIMARY KEY,
                                     UserId INT NOT NULL,
                                     ChatRoomId INT NOT NULL,
                                     foreign key (UserId) references Chat.Users(UserId),
                                     foreign key (ChatRoomId) references Chat.ChatRoom(ChatRoomId)
);
