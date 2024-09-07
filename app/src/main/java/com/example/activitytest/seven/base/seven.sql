

create table Book (
    id integer primary key autoincrement,
    author text,
    price real,
    pages integer,
    name text)

create table Category (
    id integer primary key autoincrement,
    category_name text,
    category_code integer)