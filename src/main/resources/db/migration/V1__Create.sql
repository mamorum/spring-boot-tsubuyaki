CREATE TABLE tweet (
  id bigserial PRIMARY KEY,
  txt varchar(100) NOT NULL,
  version bigint NOT NULL,
  update_time timestamp NOT NULL DEFAULT current_timestamp,
  create_time timestamp NOT NULL DEFAULT current_timestamp
);
