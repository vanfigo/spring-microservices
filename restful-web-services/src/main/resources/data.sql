INSERT INTO User(id, birth_date, name) VALUES
(10001, sysdate(), 'Adán'),
(10002, sysdate(), 'Eve'),
(10003, sysdate(), 'Rodrigo');
INSERT INTO Post(id, description, user_id) VALUES
(11001, 'My first post', 10001),
(11002, 'My second post', 10001);