alter table components drop foreign key FKE55483169784E2B4;
alter table components drop foreign key FKE55483163586655F;
alter table components drop foreign key FKE55483161BEB2FD8;
alter table components drop foreign key FKE554831648DFE672;
alter table modules drop foreign key FK492927879784E2B4;
alter table modules drop foreign key FK492927873586655F;
alter table projects drop foreign key FKC479187A9784E2B4;
alter table projects drop foreign key FKC479187A3586655F;
alter table projects drop foreign key FKC479187A48DFE672;
drop table if exists components;
drop table if exists modules;
drop table if exists projects;
drop table if exists users;
create table components (id bigint not null auto_increment, module bigint, project bigint, created datetime, creator bigint, description varchar(255), modified datetime, modifier bigint, name varchar(255), primary key (id));
create table modules (id bigint not null auto_increment, created datetime, creator bigint, description varchar(255), modified datetime, modifier bigint, name varchar(255), primary key (id));
create table projects (id bigint not null auto_increment, module bigint, created datetime, creator bigint, description varchar(255), modified datetime, modifier bigint, name varchar(255), primary key (id));
create table users (id bigint not null auto_increment, email varchar(255), name varchar(255), account varchar(255), password varchar(255), primary key (id));
alter table components add index FKE55483169784E2B4 (creator), add constraint FKE55483169784E2B4 foreign key (creator) references users (id);
alter table components add index FKE55483163586655F (modifier), add constraint FKE55483163586655F foreign key (modifier) references users (id);
alter table components add index FKE55483161BEB2FD8 (project), add constraint FKE55483161BEB2FD8 foreign key (project) references projects (id);
alter table components add index FKE554831648DFE672 (module), add constraint FKE554831648DFE672 foreign key (module) references modules (id);
alter table modules add index FK492927879784E2B4 (creator), add constraint FK492927879784E2B4 foreign key (creator) references users (id);
alter table modules add index FK492927873586655F (modifier), add constraint FK492927873586655F foreign key (modifier) references users (id);
alter table projects add index FKC479187A9784E2B4 (creator), add constraint FKC479187A9784E2B4 foreign key (creator) references users (id);
alter table projects add index FKC479187A3586655F (modifier), add constraint FKC479187A3586655F foreign key (modifier) references users (id);
alter table projects add index FKC479187A48DFE672 (module), add constraint FKC479187A48DFE672 foreign key (module) references modules (id);