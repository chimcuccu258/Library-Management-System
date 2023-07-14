use LMS;

INSERT INTO author (author_name) VALUES('Stephen King'),('J.K. Rowling'),('Harper Lee'),('George Orwell'),('Agatha Christie'),
('Ernest Hemingway'),('Mark Twain'), ('William Shakespeare'),('Emily Bronte'),('Jane Austen'),('Toni Morrison'),('Leo Tolstoy'),
('F. Scott Fitzgerald'), ('Charles Dickens'),('Virginia Woolf'),('Gabriel Garcia Marquez'),('Hermann Hesse'),('Oscar Wilde'),
('Rudyard Kipling'), ('Arthur Conan Doyle');


INSERT INTO category (ctg_name)  
VALUES ('Fiction'),('Mystery/Thriller'),('Romance'),('Science Fiction'),('Fantasy'),('Biography'),('History'), 
('Self-help/Personal Development'),('Business/Entrepreneurship'),('Science/Technology'),('Travel'),('Art/Photography'),
('Philosophy'), ('Religion/Spirituality'),('Health/Fitness'),('Cookbooks'),('Children\'s Books'),('Young Adult'),('Poetry'),
('Graphic Novels/Comics');


INSERT INTO role (role_name) VALUES ('ROLE_ADMIN');
INSERT INTO role (role_name) VALUES ('ROLE_LIBRARIAN');
INSERT INTO role (role_name) VALUES ('ROLE_USER');

INSERT INTO book (book_name, description, inventory, price, author_id, category_id) 
VALUES     
('Harry Potter and the Philosopher\'s Stone', 'The first book in the Harry Potter series', 200, 200000, 2, 5),     
('Harry Potter and the Chamber of Secrets', 'The second book in the Harry Potter series', 150, 180000, 2, 5),     
('Harry Potter and the Prisoner of Azkaban', 'The third book in the Harry Potter series', 180, 190000, 2, 5),     
('Harry Potter and the Goblet of Fire', 'The fourth book in the Harry Potter series', 160, 210000, 2, 5),     
('Harry Potter and the Order of the Phoenix', 'The fifth book in the Harry Potter series', 140, 220000, 2, 5),     
('Harry Potter and the Half-Blood Prince', 'The sixth book in the Harry Potter series', 120, 230000, 2, 5),     
('Harry Potter and the Deathly Hallows', 'The seventh and final book in the Harry Potter series', 100, 240000, 2, 5);

INSERT INTO book (book_name, description, inventory, price, author_id, category_id) 
VALUES     
-- Books by Stephen King      
('The Shining', 'A suspenseful horror novel', FLOOR(RAND() * 251) + 50, 170000, 1, 1),     
('Carrie', 'A supernatural thriller', FLOOR(RAND() * 251) + 50, 130000, 1, 1),     
('The Stand', 'A post-apocalyptic horror novel', FLOOR(RAND() * 251) + 50, 170000, 1, 1),     
('It', 'A supernatural horror novel', FLOOR(RAND() * 251) + 50, 160000, 1, 1),     
('Misery', 'A psychological horror novel', FLOOR(RAND() * 251) + 50, 140000, 1, 1),     
-- Books by Harper Lee     
('To Kill a Mockingbird', 'A classic novel set in the 1930s', FLOOR(RAND() * 251) + 50, 120000, 3, 1),     
('Go Set a Watchman', 'A companion novel to "To Kill a Mockingbird"', FLOOR(RAND() * 251) + 50, 100000, 3, 1),     
('Go Set a Watchman', 'A companion novel to "To Kill a Mockingbird"', FLOOR(RAND() * 251) + 50, 100000, 3, 1),     
('To Kill a Mockingbird', 'A classic novel set in the 1930s', FLOOR(RAND() * 251) + 50, 120000, 3, 1),     
-- Books by George Orwell     
('1984', 'A dystopian novel depicting a totalitarian society', FLOOR(RAND() * 251) + 50, 100000, 4, 4),     
('Animal Farm', 'A political allegory', FLOOR(RAND() * 251) + 50, 80000, 4, 4),     
('Nineteen Eighty-Four', 'A dystopian novel', FLOOR(RAND() * 251) + 50, 110000, 4, 4),     
('Animal Farm', 'A political allegory', FLOOR(RAND() * 251) + 50, 90000, 4, 4),     
-- Books by Agatha Christie     
('Murder on the Orient Express', 'A thrilling detective novel', FLOOR(RAND() * 251) + 50, 90000, 5, 2),     
('And Then There Were None', 'A classic murder mystery', FLOOR(RAND() * 251) + 50, 70000, 5, 2),     
('The Murder of Roger Ackroyd', 'A classic detective novel', FLOOR(RAND() * 251) + 50, 950000, 5, 2),     
('Death on the Nile', 'A thrilling murder mystery', FLOOR(RAND() * 251) + 50, 80000, 5, 2),     
-- Books by Ernest Hemingway     
('The Old Man and the Sea', 'A story of an old fisherman\'s struggle', FLOOR(RAND() * 251) + 50, 80000, 6, 6),     
('A Farewell to Arms', 'A novel set during World War I', FLOOR(RAND() * 251) + 50, 60000, 6, 6),    
('For Whom the Bell Tolls', 'A war novel', FLOOR(RAND() * 251) + 50, 70000, 6, 6),     
('The Sun Also Rises', 'A story of disillusionment', FLOOR(RAND() * 251) + 50, 650000, 6, 6),     
-- Books by Mark Twain     
('The Adventures of Huckleberry Finn', 'An adventure novel set along the Mississippi River', FLOOR(RAND() * 251) + 50, 70000, 7, 1),  
('The Adventures of Tom Sawyer', 'A story of a young boy growing up in the Mississippi River town', FLOOR(RAND() * 251) + 50, 60000, 7, 1),     
('The Prince and the Pauper', 'A historical fiction novel', FLOOR(RAND() * 251) + 50, 550000, 7, 1),     
('A Connecticut Yankee in King Arthur\'s Court', 'A satirical novel', FLOOR(RAND() * 251) + 50, 60000, 7, 1),     
-- Books by William Shakespeare     
('Romeo and Juliet', 'A tragic love story', FLOOR(RAND() * 251) + 50, 60000, 8, 3),     
('Hamlet', 'A revenge tragedy', FLOOR(RAND() * 251) + 50, 50000, 8, 3),     
('Macbeth', 'A tragedy', FLOOR(RAND() * 251) + 50, 550000, 8, 3),     
('Othello', 'A tragedy', FLOOR(RAND() * 251) + 50, 50000, 8, 3),     
-- Books by Emily Bronte     
('Wuthering Heights', 'A passionate tale of love and revenge', FLOOR(RAND() * 251) + 50, 50000, 9, 1),     
('Jane Eyre', 'A Gothic romance novel', FLOOR(RAND() * 251) + 50, 40000, 9, 3),     
-- Books by Jane Austen     
('Pride and Prejudice', 'A classic romance novel', FLOOR(RAND() * 251) + 50, 40000, 10, 3),     
('The Tenant of Wildfell Hall', 'A classic novel', FLOOR(RAND() * 251) + 50, 450000, 9, 1),     
('Poems by Currer, Ellis, and Acton Bell', 'A poetry collection', FLOOR(RAND() * 251) + 50, 400000, 9, 3),     
('Sense and Sensibility', 'A story of two sisters and their romantic experiences', FLOOR(RAND() * 251) + 50, 300000, 10, 3);

INSERT INTO user (email, gender, password, phone_number, user_name) 
VALUES 
('john.doe@gmail.com', true, 'john123', '0912345678', 'John Doe'), 
('jane.smith@gmail.com', false, 'jane456', '0987654321', 'Jane Smith'), 
('michael.johnson@gmail.com', true, 'michael789', '0932123456', 'Michael Johnson'), 
('emily.davis@gmail.com', false, 'emilyabc', '0967890123', 'Emily Davis'), 
('daniel.wilson@gmail.com', true, 'danieldef', '0945678901', 'Daniel Wilson'), 
('sophia.anderson@gmail.com', false, 'sophiaeg', '0923456789', 'Sophia Anderson'), 
('william.lee@gmail.com', true, 'williamhij', '0901234567', 'William Lee'), 
('olivia.brown@gmail.com', false, 'oliviaklm', '0976543210', 'Olivia Brown'), 
('james.miller@gmail.com', true, 'jamesnop', '0912345678', 'James Miller'), 
('ava.johnson@gmail.com', false, 'avaqrs', '0987654321', 'Ava Johnson');

INSERT INTO user_role(user_id,role_id) VALUES (2,3),(3,3),(4,3),(5,3),(6,3),(7,3),(8,3),(9,3),(10,3),(11,3);

DELETE FROM user_role WHERE id=11;