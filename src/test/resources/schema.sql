create table user_info (
    id bigint generated by default as identity,
    user_id varchar(255) not null,
    is_follow bit(1) not null,
    primary key (id),
    unique key (user_id)
);