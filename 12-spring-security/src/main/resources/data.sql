insert into roles(id, name)
values (1, 'ADMIN');
insert into users(id, username, password)
values (1, 'admin', '$2y$12$iwb5ZDNnT.tivD8gVYxvdurgK0t1KsvXoSNt9swi7.2/V0vemmIya');
insert into user_roles(user_id, role_id)
values (1, 1);

insert into authors(id, full_name)
values (1, 'Стивен Кинг');
insert into authors(id, full_name)
values (2, 'Артур Конан Дойл');

insert into genres(id, name)
values (1, 'Детектив');
insert into genres(id, name)
values (2, 'Роман');

insert into books(id, name, genre_id)
values (1, 'Затерянный мир', 1);
insert into books(id, name, genre_id)
values (2, 'Темная башня', 2);

insert into book_authors(book_id, author_id)
values (1, 2);
insert into book_authors(book_id, author_id)
values (2, 1);

insert into book_comments(id, text, created_on, book_id)
values (1, 'класс', current_timestamp, 2)