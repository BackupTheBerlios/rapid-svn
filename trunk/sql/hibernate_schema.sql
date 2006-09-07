alter table component_comments drop foreign key FK5AC23EF6FAB809C8;
alter table component_comments drop foreign key FK5AC23EF691A90220;
alter table components drop foreign key FKE55483169784E2B4;
alter table components drop foreign key FKE55483163586655F;
alter table components drop foreign key FKE55483161BEB2FD8;
alter table components drop foreign key FKE5548316183EA791;
alter table components drop foreign key FKE554831648DFE672;
alter table issue_comments drop foreign key FK8C9D4A9AAD28807C;
alter table issue_comments drop foreign key FK8C9D4A9A1E244C6C;
alter table issues drop foreign key FKB9B772BA9784E2B4;
alter table issues drop foreign key FKB9B772BA3586655F;
alter table issues drop foreign key FKB9B772BA1BEB2FD8;
alter table issues drop foreign key FKB9B772BA442A7057;
alter table issues drop foreign key FKB9B772BA40C24769;
alter table issues drop foreign key FKB9B772BA48DFE672;
alter table issues drop foreign key FKB9B772BA91A90220;
alter table messages drop foreign key FKE475014C24367CBD;
alter table messages drop foreign key FKE475014C2A024C77;
alter table module_comments drop foreign key FKF3E7E26741DB4A01;
alter table module_comments drop foreign key FKF3E7E26748DFE672;
alter table modules drop foreign key FK492927879784E2B4;
alter table modules drop foreign key FK492927873586655F;
alter table modules drop foreign key FK49292787183EA791;
alter table project_comments drop foreign key FK953D305A1BEB2FD8;
alter table project_comments drop foreign key FK953D305A44DD732C;
alter table projects drop foreign key FKC479187A9784E2B4;
alter table projects drop foreign key FKC479187A3586655F;
alter table projects drop foreign key FKC479187A183EA791;
alter table projects drop foreign key FKC479187A48DFE672;
alter table todos drop foreign key FK696A0ED608A553B;
drop table if exists component_comments;
drop table if exists components;
drop table if exists issue_comments;
drop table if exists issues;
drop table if exists messages;
drop table if exists module_comments;
drop table if exists modules;
drop table if exists project_comments;
drop table if exists projects;
drop table if exists todos;
drop table if exists users;
create table component_comments (id bigint not null auto_increment, parent bigint, component bigint, primary key (id));
create table components (id bigint not null auto_increment, leader bigint, module bigint, project bigint, created datetime, creator bigint, description varchar(255), modified datetime, modifier bigint, name varchar(255), primary key (id));
create table issue_comments (id bigint not null auto_increment, parent bigint, issue bigint, primary key (id));
create table issues (id bigint not null auto_increment, component bigint, module bigint, project bigint, assigned datetime, assignee bigint, created datetime, creator bigint, description varchar(255), modified datetime, modifier bigint, priority varchar(255), reported datetime, reporter bigint, status varchar(255), summary varchar(255), type varchar(255), primary key (id));
create table messages (id bigint not null auto_increment, status varchar(255), body text, receiver bigint, send datetime, sender bigint, subject varchar(255), primary key (id));
create table module_comments (id bigint not null auto_increment, parent bigint, module bigint, primary key (id));
create table modules (id bigint not null auto_increment, leader bigint, created datetime, creator bigint, description varchar(255), modified datetime, modifier bigint, name varchar(255), primary key (id));
create table project_comments (id bigint not null auto_increment, parent bigint, project bigint, primary key (id));
create table projects (id bigint not null auto_increment, home varchar(255), leader bigint, module bigint, created datetime, creator bigint, description varchar(255), modified datetime, modifier bigint, name varchar(255), primary key (id));
create table todos (id bigint not null auto_increment, done bit, created datetime, description text, modified datetime, owner bigint, summary varchar(255), primary key (id));
create table users (id bigint not null auto_increment, email varchar(255), name varchar(255), account varchar(255), password varchar(255), primary key (id));
alter table component_comments add index FK5AC23EF6FAB809C8 (parent), add constraint FK5AC23EF6FAB809C8 foreign key (parent) references component_comments (id);
alter table component_comments add index FK5AC23EF691A90220 (component), add constraint FK5AC23EF691A90220 foreign key (component) references components (id);
alter table components add index FKE55483169784E2B4 (creator), add constraint FKE55483169784E2B4 foreign key (creator) references users (id);
alter table components add index FKE55483163586655F (modifier), add constraint FKE55483163586655F foreign key (modifier) references users (id);
alter table components add index FKE55483161BEB2FD8 (project), add constraint FKE55483161BEB2FD8 foreign key (project) references projects (id);
alter table components add index FKE5548316183EA791 (leader), add constraint FKE5548316183EA791 foreign key (leader) references users (id);
alter table components add index FKE554831648DFE672 (module), add constraint FKE554831648DFE672 foreign key (module) references modules (id);
alter table issue_comments add index FK8C9D4A9AAD28807C (issue), add constraint FK8C9D4A9AAD28807C foreign key (issue) references issues (id);
alter table issue_comments add index FK8C9D4A9A1E244C6C (parent), add constraint FK8C9D4A9A1E244C6C foreign key (parent) references issue_comments (id);
alter table issues add index FKB9B772BA9784E2B4 (creator), add constraint FKB9B772BA9784E2B4 foreign key (creator) references users (id);
alter table issues add index FKB9B772BA3586655F (modifier), add constraint FKB9B772BA3586655F foreign key (modifier) references users (id);
alter table issues add index FKB9B772BA1BEB2FD8 (project), add constraint FKB9B772BA1BEB2FD8 foreign key (project) references projects (id);
alter table issues add index FKB9B772BA442A7057 (assignee), add constraint FKB9B772BA442A7057 foreign key (assignee) references users (id);
alter table issues add index FKB9B772BA40C24769 (reporter), add constraint FKB9B772BA40C24769 foreign key (reporter) references users (id);
alter table issues add index FKB9B772BA48DFE672 (module), add constraint FKB9B772BA48DFE672 foreign key (module) references modules (id);
alter table issues add index FKB9B772BA91A90220 (component), add constraint FKB9B772BA91A90220 foreign key (component) references components (id);
alter table messages add index FKE475014C24367CBD (sender), add constraint FKE475014C24367CBD foreign key (sender) references users (id);
alter table messages add index FKE475014C2A024C77 (receiver), add constraint FKE475014C2A024C77 foreign key (receiver) references users (id);
alter table module_comments add index FKF3E7E26741DB4A01 (parent), add constraint FKF3E7E26741DB4A01 foreign key (parent) references module_comments (id);
alter table module_comments add index FKF3E7E26748DFE672 (module), add constraint FKF3E7E26748DFE672 foreign key (module) references modules (id);
alter table modules add index FK492927879784E2B4 (creator), add constraint FK492927879784E2B4 foreign key (creator) references users (id);
alter table modules add index FK492927873586655F (modifier), add constraint FK492927873586655F foreign key (modifier) references users (id);
alter table modules add index FK49292787183EA791 (leader), add constraint FK49292787183EA791 foreign key (leader) references users (id);
alter table project_comments add index FK953D305A1BEB2FD8 (project), add constraint FK953D305A1BEB2FD8 foreign key (project) references projects (id);
alter table project_comments add index FK953D305A44DD732C (parent), add constraint FK953D305A44DD732C foreign key (parent) references project_comments (id);
alter table projects add index FKC479187A9784E2B4 (creator), add constraint FKC479187A9784E2B4 foreign key (creator) references users (id);
alter table projects add index FKC479187A3586655F (modifier), add constraint FKC479187A3586655F foreign key (modifier) references users (id);
alter table projects add index FKC479187A183EA791 (leader), add constraint FKC479187A183EA791 foreign key (leader) references users (id);
alter table projects add index FKC479187A48DFE672 (module), add constraint FKC479187A48DFE672 foreign key (module) references modules (id);
alter table todos add index FK696A0ED608A553B (owner), add constraint FK696A0ED608A553B foreign key (owner) references users (id);
