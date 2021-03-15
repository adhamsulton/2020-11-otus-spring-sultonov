insert into roles(id, name)
values (1, 'ADMIN');
insert into roles(id, name)
values (2, 'USER');
insert into roles(id, name)
values (3, 'OWNER');
insert into users(id, username, password)
values (1, 'admin', '$2y$12$W.Fs2tS5iHnbVpEuAfYj.u2lVAFEyT/NM02SuBkXG.YLvxgP23pQK');
insert into users(id, username, password)
values (2, 'user', '$2y$12$Mmbx31CVuDeP2HscUzlrBuhVAl//npnKTBt284BYXM9924/se3bOa');
insert into users(id, username, password)
values (3, 'owner', '$2y$12$Vc72u4v4AUelixCkV8unBehYmLJto.38FO487hAYvMPF1RA0kFUXC');
insert into user_roles(user_id, role_id)
values (1, 1);
insert into user_roles(user_id, role_id)
values (2, 2);
insert into user_roles(user_id, role_id)
values (3, 3);

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
insert into books(id, name, genre_id)
values (3, 'Неизданные рассказы', 1);

insert into book_authors(book_id, author_id)
values (1, 2);
insert into book_authors(book_id, author_id)
values (2, 1);
insert into book_authors(book_id, author_id)
values (3, 2);

insert into book_comments(id, text, created_on, book_id)
values (1, 'класс', current_timestamp, 2);

--ACL data begin
INSERT INTO acl_sid (id, principal, sid) VALUES
(1, 1, 'admin'),
(2, 1, 'user'),
(3, 0, 'ROLE_OWNER');

INSERT INTO acl_class (id, class) VALUES
(1, 'ru.otus.springsecurityacl.domain.Book');

INSERT INTO acl_object_identity (id, object_id_class, object_id_identity, parent_object, owner_sid, entries_inheriting) VALUES
(1, 1, 1, NULL, 3, 0),
(2, 1, 2, NULL, 3, 0),
(3, 1, 3, NULL, 3, 0);

INSERT INTO acl_entry (id, acl_object_identity, ace_order, sid, mask, granting, audit_success, audit_failure) VALUES
(1, 1, 1, 1, 1, 1, 1, 1),
(2, 1, 2, 2, 1, 1, 1, 1),
(3, 1, 3, 3, 1, 1, 1, 1),
(4, 2, 1, 1, 1, 1, 1, 1),
(5, 2, 2, 2, 1, 1, 1, 1),
(6, 2, 3, 3, 1, 1, 1, 1),
(7, 3, 1, 1, 1, 1, 1, 1),
(8, 3, 2, 2, 1, 1, 1, 1),
(9, 3, 3, 3, 1, 1, 1, 1),
(10, 3, 4, 3, 2, 1, 1, 1),
(11, 3, 5, 3, 4, 1, 1, 1),
(12, 3, 6, 3, 8, 1, 1, 1);--BasePermission.READ.getMask()
--ACL data end