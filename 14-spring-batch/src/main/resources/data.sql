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
values (1, 'класс', current_timestamp, 2);
insert into book_comments(id, text, created_on, book_id)
values (2, 'супер', current_timestamp, 2);
insert into book_comments(id, text, created_on, book_id)
values (3, 'не плохо', current_timestamp, 1)