insert into s_roles(id, name)
values ('2c945a11-e3a7-4078-94c9-c8cbfd518672', 'OWNER'),
       ('03719fb3-e5b6-4747-9a5f-c6add790a6c1', 'ADMIN');

insert into s_users(id, email, name, id_role)
values ('e0c5a933-9bc6-4edf-88c0-bec1a2f03967', 'user1@gmail.com', 'user1', '2c945a11-e3a7-4078-94c9-c8cbfd518672'),
       ('eb288dbc-4ac3-4130-bef4-578f42fdede4', 'user2@gmail.com', 'user2', '2c945a11-e3a7-4078-94c9-c8cbfd518672');

insert into s_password(id_user, password)
values ('e0c5a933-9bc6-4edf-88c0-bec1a2f03967', '$2a$12$T6jv1AbmQtWaGPxuU1CS5uE8wTteIHfrOmD91kY/VmaxhBDA/rUy.'),
       ('eb288dbc-4ac3-4130-bef4-578f42fdede4', '$2a$12$F2PPgTjasYi5VGcitz3QOOX0Qhle6VGiBY5hwDaAJD3opktCnknSa');

insert into task(id, description, status, title, id_user)
values ('cef733bb-1ddf-4e95-a843-9783b4c2dd15', 'this is decription', 'TODO', 'Dinner', 'e0c5a933-9bc6-4edf-88c0-bec1a2f03967'),
       ('46952551-a3be-43dd-a41a-b9b8aad047c5', 'this is decription', 'TODO', 'Breakfast', 'e0c5a933-9bc6-4edf-88c0-bec1a2f03967'),
       ('5682f8f5-2cbc-4929-99d6-608062dc4cdb', 'this is decription', 'TODO', 'Lunch', 'e0c5a933-9bc6-4edf-88c0-bec1a2f03967'),
       ('4eed9240-214a-41b3-a4d3-a045044c3829', 'this is decription', 'TODO', 'Java Spring Boot', 'eb288dbc-4ac3-4130-bef4-578f42fdede4');